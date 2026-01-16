package at.ac.hcw.kqm.ui.controller;

import java.util.ArrayList;
import java.util.List;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.engine.GameEngine;
import at.ac.hcw.kqm.joker.FiftyFiftyJoker;
import at.ac.hcw.kqm.joker.HintJoker;
import at.ac.hcw.kqm.model.AnswerOption;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Question;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class QuizController {

    @FXML
    private Label questionLabel;
    @FXML
    private Label playerInfoLabel;
    @FXML
    private Label songInfoLabel;
    @FXML
    private Button answerA, answerB, answerC, answerD;
    @FXML
    private Button replacementButton, fiftyFiftyButton, hintButton, submitButton;

    private GameEngine engine;

    private Question current;
    private int selectedOptionId = -1;

    private final FiftyFiftyJoker fiftyFiftyJoker = new FiftyFiftyJoker();
    private final HintJoker hintJoker = new HintJoker();

    @FXML
    public void initialize() {
        try {
            System.out.println("QuizController.initialize() called");
            this.engine = AppState.get().getEngine();
            System.out.println("QuizController: Got engine from AppState");
            loadQuestion();
            System.out.println("QuizController: Question loaded");

            answerA.setOnAction(e -> selectOption(1));
            answerB.setOnAction(e -> selectOption(2));
            answerC.setOnAction(e -> selectOption(3));
            answerD.setOnAction(e -> selectOption(4));

            fiftyFiftyButton.setOnAction(e -> applyFifty());
            hintButton.setOnAction(e -> applyHint());
            replacementButton.setOnAction(e -> applyReplacement());
            submitButton.setVisible(false); // Hide submit button - auto-submit on answer
            refreshJokerButtonsState();
            System.out.println("QuizController.initialize() completed successfully");
        } catch (Exception e) {
            System.err.println("QuizController.initialize() error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private void loadQuestion() {
        current = engine.currentQuestion();
        selectedOptionId = -1;
        questionLabel.setText(current.getQuestionText());
        updatePlayerInfo();
        List<AnswerOption> opts = new ArrayList<>(current.getAnswerOptions());
        // Ensure we have four options
        while (opts.size() < 4) {
            opts.add(new AnswerOption(opts.size() + 1, "Option " + (opts.size() + 1)));
        }
        setAnswerButton(answerA, opts.get(0));
        setAnswerButton(answerB, opts.get(1));
        setAnswerButton(answerC, opts.get(2));
        setAnswerButton(answerD, opts.get(3));
        resetAnswerButtonsEnabled();
        refreshJokerButtonsState();
    }

    private void updatePlayerInfo() {
        String playerName = engine.currentPlayer().getName();
        List<Player> players = AppState.get().getPlayers();
        int playerIndex = -1;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(playerName)) {
                playerIndex = i;
                break;
            }
        }
        at.ac.hcw.kqm.model.Song song = null;
        if (playerIndex >= 0) {
            song = AppState.get().getSongForPlayer(players.get(playerIndex));
        }
        String songName = (song != null) ? song.getDisplayName() : "N/A";
        // Show player (red/bold) and song (white) on separate labels
        playerInfoLabel.setText(playerName);
        songInfoLabel.setText(songName);
    }

    private void setAnswerButton(Button b, AnswerOption opt) {
        b.setText(opt.getText());
    }

    private void selectOption(int id) {
        selectedOptionId = id;
        // Zeige visuelles Feedback: alle Buttons mit der Antwort-Rückmeldung färben
        showAnswerFeedback();
        // Auto-submit nach kurzer Verzögerung damit der User die Farben sieht
        javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.millis(800));
        pause.setOnFinished(event -> submit());
        pause.play();
    }

    private void showAnswerFeedback() {
        Question q = current;
        int correctId = q.getCorrectOptionId();

        // Färbe alle Buttons entsprechend
        colorAnswerButton(answerA, 1, correctId);
        colorAnswerButton(answerB, 2, correctId);
        colorAnswerButton(answerC, 3, correctId);
        colorAnswerButton(answerD, 4, correctId);
    }

    private void colorAnswerButton(Button btn, int optionId, int correctId) {
        if (optionId == correctId) {
            // Richtige Antwort = GRÜN
            btn.setStyle("-fx-background-color: #00aa00; -fx-text-fill: white; -fx-font-weight: bold;");
        } else if (optionId == selectedOptionId) {
            // Gewählte aber falsche Antwort = ROT
            btn.setStyle("-fx-background-color: #cc0000; -fx-text-fill: white; -fx-font-weight: bold;");
        } else {
            // Nicht gewählte Antworten = normal
            btn.setStyle("");
        }
    }

    private void submit() {
        if (selectedOptionId < 0)
            return;
        engine.answerQuestion(selectedOptionId);
        flowNext();
    }

    private void flowNext() {
        if (engine.isGameFinished()) {
            // Always go to result screen first
            // If tie-break is needed, it will be handled there
            SceneManager.get().showResult();
        } else {
            // new question for possibly next player
            AppState.get().resetJokersForNewQuestion();
            loadQuestion();
        }
    }

    private void resetAnswerButtonsEnabled() {
        answerA.setDisable(false);
        answerA.setStyle("");
        answerB.setDisable(false);
        answerB.setStyle("");
        answerC.setDisable(false);
        answerC.setStyle("");
        answerD.setDisable(false);
        answerD.setStyle("");
    }

    private void refreshJokerButtonsState() {
        // Check if jokers are available for current player
        Player currentPlayer = engine.currentPlayer();

        boolean fiftyAvailable = currentPlayer.isJokerAvailable(at.ac.hcw.kqm.model.JokerType.FIFTY_FIFTY);
        boolean hintAvailable = currentPlayer.isJokerAvailable(at.ac.hcw.kqm.model.JokerType.HINT);
        boolean replaceAvailable = currentPlayer.isJokerAvailable(at.ac.hcw.kqm.model.JokerType.REPLACE_QUESTION);

        setJokerButtonState(fiftyFiftyButton, !fiftyAvailable);
        setJokerButtonState(hintButton, !hintAvailable);
        setJokerButtonState(replacementButton, !replaceAvailable);
    }

    private void setJokerButtonState(Button btn, boolean used) {
        btn.getStyleClass().remove("joker-used");
        btn.setDisable(false);
        if (used) {
            btn.getStyleClass().add("joker-used");
            btn.setDisable(true);
        }
    }

    private void applyFifty() {
        Player currentPlayer = engine.currentPlayer();
        if (!currentPlayer.isJokerAvailable(at.ac.hcw.kqm.model.JokerType.FIFTY_FIFTY))
            return;

        currentPlayer.useJoker(at.ac.hcw.kqm.model.JokerType.FIFTY_FIFTY);
        AppState.get().setJokerUsed(at.ac.hcw.kqm.model.JokerType.FIFTY_FIFTY);

        Button[] buttons = { answerA, answerB, answerC, answerD };
        fiftyFiftyJoker.apply(current, buttons, questionLabel);

        refreshJokerButtonsState();
    }

    private void applyHint() {
        Player currentPlayer = engine.currentPlayer();
        if (!currentPlayer.isJokerAvailable(at.ac.hcw.kqm.model.JokerType.HINT))
            return;

        currentPlayer.useJoker(at.ac.hcw.kqm.model.JokerType.HINT);
        AppState.get().setJokerUsed(at.ac.hcw.kqm.model.JokerType.HINT);

        Button[] buttons = { answerA, answerB, answerC, answerD };
        hintJoker.apply(current, buttons, questionLabel);

        refreshJokerButtonsState();
    }

    private void applyReplacement() {
        Player currentPlayer = engine.currentPlayer();
        if (!currentPlayer.isJokerAvailable(at.ac.hcw.kqm.model.JokerType.REPLACE_QUESTION))
            return;

        currentPlayer.useJoker(at.ac.hcw.kqm.model.JokerType.REPLACE_QUESTION);
        AppState.get().setJokerUsed(at.ac.hcw.kqm.model.JokerType.REPLACE_QUESTION);

        // Replace with a special Fill&Jump lyric completion question from the same song
        // if available
        List<Question> pool = AppState.get().getQuestionRepo().getQuestionsBySongId(current.getSongId());
        Question replacement = null;
        for (Question q : pool) {
            String t = q.getQuestionText() == null ? "" : q.getQuestionText().toLowerCase();
            boolean isFill = t.startsWith("vervollständige") || t.startsWith("vervollstaendige")
                    || t.contains("vervollständige den text") || t.contains("vervollstaendige den text");
            if (isFill && q.getId() != current.getId()) {
                replacement = q;
                break;
            }
        }
        if (replacement == null) {
            // fallback to any other different question
            for (Question q : pool) {
                if (q.getId() != current.getId()) {
                    replacement = q;
                    break;
                }
            }
        }
        if (replacement != null) {
            current = replacement;
            questionLabel.setText(current.getQuestionText());
            List<AnswerOption> opts = new ArrayList<>(current.getAnswerOptions());
            setAnswerButton(answerA, opts.get(0));
            setAnswerButton(answerB, opts.get(1));
            setAnswerButton(answerC, opts.get(2));
            setAnswerButton(answerD, opts.get(3));
            resetAnswerButtonsEnabled();
            selectedOptionId = -1;
            refreshJokerButtonsState();
        }
    }
}
