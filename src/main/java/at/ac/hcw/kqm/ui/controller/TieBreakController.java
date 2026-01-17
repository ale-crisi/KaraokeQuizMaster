package at.ac.hcw.kqm.ui.controller;

import java.util.List;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.engine.GameEngine;
import at.ac.hcw.kqm.engine.TieBreakManager;
import at.ac.hcw.kqm.model.AnswerOption;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Question;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TieBreakController {

    @FXML
    private Label timerLabel;
    @FXML
    private Label currentPlayerLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private Button answerA, answerB, answerC, answerD;
    @FXML
    private Button submitButton;

    private final GameEngine engine = AppState.get().getEngine();
    private long startTime;
    private Timeline timerTimeline;
    private boolean hasAnswered = false;
    private int selectedOptionId = -1;
    private Question current;

    @FXML
    public void initialize() {
        loadQuestion();

        answerA.setOnAction(e -> selectOption(1));
        answerB.setOnAction(e -> selectOption(2));
        answerC.setOnAction(e -> selectOption(3));
        answerD.setOnAction(e -> selectOption(4));
    }

    private void loadQuestion() {
        current = engine.currentQuestion();
        Player currentPlayer = engine.currentPlayer();

        hasAnswered = false;
        selectedOptionId = -1;
        currentPlayerLabel.setText(currentPlayer.getName());
        questionLabel.setText(current.getQuestionText());

        List<AnswerOption> opts = current.getAnswerOptions();
        answerA.setText(opts.get(0).getText());
        answerB.setText(opts.get(1).getText());
        answerC.setText(opts.get(2).getText());
        answerD.setText(opts.get(3).getText());

        resetButtonStyles();

        // Start timer
        startTime = System.nanoTime();
        startTimerDisplay();
    }

    private void resetButtonStyles() {
        answerA.setStyle("");
        answerB.setStyle("");
        answerC.setStyle("");
        answerD.setStyle("");
    }

    private void startTimerDisplay() {
        if (timerTimeline != null) {
            timerTimeline.stop();
        }

        timerTimeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            long currentTime = System.nanoTime();
            double elapsedSeconds = (currentTime - startTime) / 1_000_000_000.0;
            timerLabel.setText(String.format("%.2f", elapsedSeconds));
        }));
        timerTimeline.setCycleCount(Timeline.INDEFINITE);
        timerTimeline.play();
    }

    private void selectOption(int id) {
        if (hasAnswered)
            return;

        selectedOptionId = id;
        hasAnswered = true;
        timerTimeline.stop();

        // Auto-submit after delay
        PauseTransition pause = new PauseTransition(Duration.millis(800));
        pause.setOnFinished(event -> submit());
        pause.play();
    }

    private void submit() {
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000L;

        // Record answer with time and get whether the round finished
        boolean roundFinished = engine.answerTieBreakQuestion(selectedOptionId, durationMs);
        System.out.println("TieBreakController.submit(): roundFinished=" + roundFinished + ", isGameFinished=" + engine.isGameFinished());

        // Check if all players have answered this question
        if (roundFinished) {
            // All players answered the same question
            // Wait 1.5 seconds to show the final time, then proceed
            Timeline delay = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                System.out.println("TieBreakController: Checking end of round. isGameFinished=" + engine.isGameFinished());
                if (engine.isGameFinished()) {
                    // Tie-break complete, show final results
                    System.out.println("TieBreakController: Game finished, showing result");
                    SceneManager.get().showResult();
                } else {
                    // Next question for all players
                    System.out.println("TieBreakController: Loading next question");
                    loadQuestion();
                }
            }));
            delay.setCycleCount(1);
            delay.play();
        } else {
            // Load same question for next player
            System.out.println("TieBreakController: Loading question for next player");
            loadQuestion();
        }
    }
}