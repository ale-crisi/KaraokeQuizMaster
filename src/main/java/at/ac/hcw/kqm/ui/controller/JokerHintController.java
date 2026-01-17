package at.ac.hcw.kqm.ui.controller;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.engine.GameEngine;
import at.ac.hcw.kqm.joker.HintJoker;
import at.ac.hcw.kqm.model.JokerType;
import at.ac.hcw.kqm.model.Question;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class JokerHintController {

    @FXML
    private Button answerA, answerB, answerC, answerD;

    @FXML
    private Button fillJumpButton, fiftyFiftyButton;

    @FXML
    private Label questionLabel;

    private GameEngine engine;
    private Question currentQuestion;
    private int selectedOptionId = -1;

    private final HintJoker hintJoker = new HintJoker();

    @FXML
    public void initialize() {
        engine = AppState.get().getEngine();
        currentQuestion = engine.currentQuestion();

        questionLabel.setText(currentQuestion.getQuestionText());
        var opts = currentQuestion.getAnswerOptions();
        setAnswerButton(answerA, opts.size() > 0 ? opts.get(0) : null);
        setAnswerButton(answerB, opts.size() > 1 ? opts.get(1) : null);
        setAnswerButton(answerC, opts.size() > 2 ? opts.get(2) : null);
        setAnswerButton(answerD, opts.size() > 3 ? opts.get(3) : null);

        // Apply Hint joker effect
        Button[] buttons = { answerA, answerB, answerC, answerD };
        hintJoker.apply(currentQuestion, buttons, questionLabel);

        // Refresh joker button states
        refreshJokerButtonsState();
    }

    private void refreshJokerButtonsState() {
        boolean fillAvailable = engine.currentPlayer().isJokerAvailable(JokerType.FIILL_AND_JUMP);
        boolean fiftyAvailable = engine.currentPlayer().isJokerAvailable(JokerType.FIFTY_FIFTY);

        setJokerButtonState(fillJumpButton, !fillAvailable);
        setJokerButtonState(fiftyFiftyButton, !fiftyAvailable);
    }

    private void setJokerButtonState(Button btn, boolean used) {
        btn.getStyleClass().remove("joker-used");
        btn.setDisable(false);
        if (used) {
            btn.getStyleClass().add("joker-used");
            btn.setDisable(true);
        }
    }

    private void setAnswerButton(Button b, at.ac.hcw.kqm.model.AnswerOption opt) {
        if (opt != null) {
            b.setText(opt.getText());
            b.setUserData(opt.getId());
            b.setOnAction(e -> selectOption(b));
        }
    }

    private void selectOption(Button b) {
        Object userData = b.getUserData();
        if (userData instanceof Integer id) {
            selectedOptionId = id;
            // Zeige visuelles Feedback: alle Buttons mit der Antwort-Rückmeldung färben
            showAnswerFeedback();
            // Auto-submit nach kurzer Verzögerung damit der User die Farben sieht
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.millis(800));
            pause.setOnFinished(event -> submit());
            pause.play();
        }
    }

    private void showAnswerFeedback() {
        int correctId = currentQuestion.getCorrectOptionId();

        // Färbe alle Buttons entsprechend
        colorAnswerButton(answerA, (Integer)answerA.getUserData(), correctId);
        colorAnswerButton(answerB, (Integer)answerB.getUserData(), correctId);
        colorAnswerButton(answerC, (Integer)answerC.getUserData(), correctId);
        colorAnswerButton(answerD, (Integer)answerD.getUserData(), correctId);
    }

    private void colorAnswerButton(Button btn, int optionId, int correctId) {
        if (optionId == correctId) {
            // Richtige Antwort = Dunkelgrün mit intensivem Farbverlauf und Tiefe
            btn.setStyle("-fx-background-color: linear-gradient(to bottom, rgba(50,180,50,0.67), rgba(40,160,40,0.72), rgba(30,140,30,0.77), rgba(20,120,20,0.82), rgba(15,100,15,0.77), rgba(10,80,10,0.72)) !important; -fx-text-fill: white !important; -fx-font-weight: bold !important; -fx-background-radius: 30 50 40 45 !important; -fx-effect: innershadow(gaussian, rgba(0,0,0,0.5), 12, 0.7, 0, 3), innershadow(gaussian, rgba(0,50,0,0.4), 8, 0.5, -4, 2), innershadow(gaussian, rgba(0,50,0,0.4), 8, 0.5, 4, 2), dropshadow(gaussian, rgba(0,0,0,0.6), 10, 0.6, 0, 2);");
        } else if (optionId == selectedOptionId) {
            // Gewählte aber falsche Antwort = Dunkelrot mit intensivem Farbverlauf und Tiefe
            btn.setStyle("-fx-background-color: linear-gradient(to bottom, rgba(180,50,50,0.77), rgba(160,40,40,0.82), rgba(140,30,30,0.87), rgba(120,20,20,0.89), rgba(100,15,15,0.87), rgba(80,10,10,0.82)) !important; -fx-text-fill: white !important; -fx-font-weight: bold !important; -fx-background-radius: 35 45 50 40 !important; -fx-effect: innershadow(gaussian, rgba(0,0,0,0.5), 12, 0.7, 0, 3), innershadow(gaussian, rgba(100,0,0,0.4), 8, 0.5, -4, 2), innershadow(gaussian, rgba(100,0,0,0.4), 8, 0.5, 4, 2), dropshadow(gaussian, rgba(0,0,0,0.6), 10, 0.6, 0, 2);");
        } else {
            // Nicht gewählte Antworten = normal
            btn.setStyle("");
        }
    }

    private void submit() {
        if (selectedOptionId < 0)
            return;

        // Mark Hint joker as used when answering the question
        at.ac.hcw.kqm.model.Player currentPlayer = engine.currentPlayer();
        currentPlayer.useJoker(JokerType.HINT);
        AppState.get().setJokerUsed(JokerType.HINT);

        engine.answerQuestion(selectedOptionId);

        if (engine.isGameFinished()) {
            SceneManager.get().showResult();
            return;
        }

        AppState.get().resetJokersForNewQuestion();
        SceneManager.get().showQuiz();
    }

    @FXML
    public void backToQuiz() {
        SceneManager.get().showQuiz();
    }

    @FXML
    public void applyFillAndReturn() {
        at.ac.hcw.kqm.model.Player currentPlayer = engine.currentPlayer();
        if (!currentPlayer.isJokerAvailable(JokerType.FIILL_AND_JUMP))
            return;

        currentPlayer.useJoker(JokerType.FIILL_AND_JUMP);
        AppState.get().setJokerUsed(JokerType.FIILL_AND_JUMP);
        AppState.get().setPendingJoker(JokerType.FIILL_AND_JUMP);
        SceneManager.get().showQuiz();
    }

    @FXML
    public void applyFiftyAndReturn() {
        at.ac.hcw.kqm.model.Player currentPlayer = engine.currentPlayer();
        if (!currentPlayer.isJokerAvailable(JokerType.FIFTY_FIFTY))
            return;

        currentPlayer.useJoker(JokerType.FIFTY_FIFTY);
        AppState.get().setJokerUsed(JokerType.FIFTY_FIFTY);
        AppState.get().setPendingJoker(JokerType.FIFTY_FIFTY);
        SceneManager.get().showQuiz();
    }
}

