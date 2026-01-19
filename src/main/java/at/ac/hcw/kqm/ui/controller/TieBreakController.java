package at.ac.hcw.kqm.ui.controller;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.engine.GameEngine;
import at.ac.hcw.kqm.model.AnswerOption;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Question;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TieBreakController {

    @FXML private Label timerLabel;
    @FXML private Label currentPlayerLabel;
    @FXML private Label questionLabel;

    @FXML private Button answerA, answerB, answerC, answerD;

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

        List<AnswerOption> opts = new ArrayList<>(current.getAnswerOptions());
        while (opts.size() < 4) {
            opts.add(new AnswerOption(opts.size() + 1, "Option " + (opts.size() + 1)));
        }

        answerA.setText(opts.get(0).getText());
        answerB.setText(opts.get(1).getText());
        answerC.setText(opts.get(2).getText());
        answerD.setText(opts.get(3).getText());

        resetButtonStyles();

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
        stopTimer();

        timerTimeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            long currentTime = System.nanoTime();
            double elapsedSeconds = (currentTime - startTime) / 1_000_000_000.0;
            timerLabel.setText(String.format("%.2f", elapsedSeconds));
        }));
        timerTimeline.setCycleCount(Timeline.INDEFINITE);
        timerTimeline.play();
    }

    private void stopTimer() {
        if (timerTimeline != null) {
            timerTimeline.stop();
            timerTimeline = null;
        }
    }

    private void selectOption(int id) {
        if (hasAnswered) return;

        selectedOptionId = id;
        hasAnswered = true;
        stopTimer();

        PauseTransition pause = new PauseTransition(Duration.millis(800));
        pause.setOnFinished(event -> submit());
        pause.play();
    }

    private void submit() {
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000L;

        boolean roundFinished = engine.answerTieBreakQuestion(selectedOptionId, durationMs);

        if (roundFinished) {
            Timeline delay = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                if (engine.isGameFinished()) {
                    SceneManager.get().showResult(); // FINAL RESULT nach TieBreak
                } else {
                    loadQuestion(); // nächste TieBreak-Frage
                }
            }));
            delay.setCycleCount(1);
            delay.play();
        } else {
            loadQuestion(); // gleicher Frageblock, nächster Spieler
        }
    }
}
