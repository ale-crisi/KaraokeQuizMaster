package at.ac.hcw.kqm.ui.controller;

import java.util.List;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.engine.GameEngine;
import at.ac.hcw.kqm.engine.TieBreakManager;
import at.ac.hcw.kqm.model.AnswerOption;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Question;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class TieBreakController {

    @FXML
    private Label playersLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private RadioButton answerA, answerB, answerC, answerD;
    @FXML
    private ToggleGroup answersToggleGroup;
    @FXML
    private Button submitButton;

    private final GameEngine engine = AppState.get().getEngine();
    private final TieBreakManager tiebreak = new TieBreakManager();
    private long startTime;

    @FXML
    public void initialize() {
        // Show tied players
        List<Player> tied = engine.getRanking().stream()
                .filter(p -> p.getPoints() == engine.getRanking().get(0).getPoints())
                .toList();
        playersLabel
                .setText("Spieler: " + tied.stream().map(Player::getName).reduce((a, b) -> a + ", " + b).orElse(""));

        // Start tie break in engine
        engine.startTieBreak(tiebreak.getProgrammingQuestions());
        loadQuestion();

        submitButton.setOnAction(e -> submit());
    }

    private void loadQuestion() {
        Question q = engine.currentQuestion();
        Player currentPlayer = engine.currentPlayer();

        questionLabel.setText(currentPlayer.getName() + ": " + q.getQuestionText());
        List<AnswerOption> opts = q.getAnswerOptions();
        answerA.setText("A) " + opts.get(0).getText());
        answerB.setText("B) " + opts.get(1).getText());
        answerC.setText("C) " + opts.get(2).getText());
        answerD.setText("D) " + opts.get(3).getText());
        answersToggleGroup.selectToggle(null);
        startTime = System.nanoTime();
    }

    private void submit() {
        if (answersToggleGroup.getSelectedToggle() == null)
            return;
        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000L;

        int selectedId = switch (((RadioButton) answersToggleGroup.getSelectedToggle()).getId()) {
            case "answerA" -> 1;
            case "answerB" -> 2;
            case "answerC" -> 3;
            case "answerD" -> 4;
            default -> -1;
        };

        // Record answer with time
        engine.answerTieBreakQuestion(selectedId, durationMs);

        // Check if all players have answered
        if (engine.allTieBreakPlayersAnswered()) {
            // Show results or move to next question after a short delay
            try {
                Thread.sleep(1000); // 1 second pause to show results
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (engine.isGameFinished()) {
                SceneManager.get().showResult();
            } else {
                // Next question for all players
                loadQuestion();
            }
        } else {
            // Load same question for next player
            loadQuestion();
        }
    }
}
