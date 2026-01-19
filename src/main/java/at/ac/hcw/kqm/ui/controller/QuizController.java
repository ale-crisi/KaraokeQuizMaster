package at.ac.hcw.kqm.ui.controller;

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

import java.util.ArrayList;
import java.util.List;

public class QuizController {

    @FXML private Label questionLabel;
    @FXML private Label questionNumberLabel;
    @FXML private Label playerInfoLabel;
    @FXML private Label songInfoLabel;

    @FXML private Button answerA, answerB, answerC, answerD;
    @FXML private Button replacementButton, fiftyFiftyButton, hintButton, submitButton;
    @FXML private javafx.scene.image.ImageView replacementImage, fiftyFiftyImage, hintImage;

    private GameEngine engine;

    private Question current;
    private int selectedOptionId = -1;

    private final FiftyFiftyJoker fiftyFiftyJoker = new FiftyFiftyJoker();
    private final HintJoker hintJoker = new HintJoker();

    @FXML
    public void initialize() {
        this.engine = AppState.get().getEngine();
        loadQuestion();

        answerA.setOnAction(e -> selectOption(1));
        answerB.setOnAction(e -> selectOption(2));
        answerC.setOnAction(e -> selectOption(3));
        answerD.setOnAction(e -> selectOption(4));

        fiftyFiftyButton.setOnAction(e -> applyFifty());
        hintButton.setOnAction(e -> applyHint());
        replacementButton.setOnAction(e -> applyReplacement());

        submitButton.setVisible(false);
        refreshJokerButtonsState();
    }

    private void loadQuestion() {
        current = engine.currentQuestion();
        selectedOptionId = -1;

        questionLabel.setText(current.getQuestionText());
        updateQuestionNumber();
        updatePlayerInfo();

        List<AnswerOption> opts = new ArrayList<>(current.getAnswerOptions());
        while (opts.size() < 4) {
            opts.add(new AnswerOption(opts.size() + 1, "Option " + (opts.size() + 1)));
        }

        answerA.setText(opts.get(0).getText());
        answerB.setText(opts.get(1).getText());
        answerC.setText(opts.get(2).getText());
        answerD.setText(opts.get(3).getText());

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
        playerInfoLabel.setText(playerName);
        songInfoLabel.setText(songName);
    }

    private void updateQuestionNumber() {
        int currentQuestionNum = engine.getQuestionNumberForCurrentPlayer();
        int totalQuestions = 5;
        questionNumberLabel.setText("Frage " + currentQuestionNum + "/" + totalQuestions);
    }

    private void selectOption(int id) {
        selectedOptionId = id;
        showAnswerFeedback();

        javafx.animation.PauseTransition pause =
                new javafx.animation.PauseTransition(javafx.util.Duration.millis(800));
        pause.setOnFinished(event -> submit());
        pause.play();
    }

    private void showAnswerFeedback() {
        int correctId = current.getCorrectOptionId();
        colorAnswerButton(answerA, 1, correctId);
        colorAnswerButton(answerB, 2, correctId);
        colorAnswerButton(answerC, 3, correctId);
        colorAnswerButton(answerD, 4, correctId);
    }

    private void colorAnswerButton(Button btn, int optionId, int correctId) {
        if (optionId == correctId) {
            btn.setStyle("-fx-background-color: linear-gradient(to bottom, rgba(50,180,50,0.67), rgba(40,160,40,0.72), rgba(30,140,30,0.77), rgba(20,120,20,0.82), rgba(15,100,15,0.77), rgba(10,80,10,0.72)) !important; -fx-text-fill: white !important; -fx-font-weight: bold !important;");
        } else if (optionId == selectedOptionId) {
            btn.setStyle("-fx-background-color: linear-gradient(to bottom, rgba(180,50,50,0.77), rgba(160,40,40,0.82), rgba(140,30,30,0.87), rgba(120,20,20,0.89), rgba(100,15,15,0.87), rgba(80,10,10,0.82)) !important; -fx-text-fill: white !important; -fx-font-weight: bold !important;");
        } else {
            btn.setStyle("");
        }
    }

    private void submit() {
        if (selectedOptionId < 0) return;
        engine.answerQuestion(selectedOptionId);
        flowNext();
    }

    private void flowNext() {
        if (engine.isGameFinished()) {
            if (engine.needsTieBreak()) {
                SceneManager.get().showTieBreakIntro();
            } else {
                SceneManager.get().showResult();
            }
            return;
        }

        AppState.get().resetJokersForNewQuestion();
        loadQuestion();
    }

    private void resetAnswerButtonsEnabled() {
        answerA.setDisable(false); answerA.setStyle("");
        answerB.setDisable(false); answerB.setStyle("");
        answerC.setDisable(false); answerC.setStyle("");
        answerD.setDisable(false); answerD.setStyle("");
    }

    private void refreshJokerButtonsState() {
        Player currentPlayer = engine.currentPlayer();

        boolean fiftyAvailable = currentPlayer.isJokerAvailable(at.ac.hcw.kqm.model.JokerType.FIFTY_FIFTY);
        boolean hintAvailable = currentPlayer.isJokerAvailable(at.ac.hcw.kqm.model.JokerType.HINT);
        boolean replaceAvailable = currentPlayer.isJokerAvailable(at.ac.hcw.kqm.model.JokerType.FILL_AND_JUMP);

        setJokerButtonState(fiftyFiftyButton, fiftyFiftyImage,
                "/at/ac/hcw/kqm/ui/fxml/assets/JokerAss.png",
                "/at/ac/hcw/kqm/ui/fxml/assets/AssDead.png",
                !fiftyAvailable);

        setJokerButtonState(hintButton, hintImage,
                "/at/ac/hcw/kqm/ui/fxml/assets/JokerHint.png",
                "/at/ac/hcw/kqm/ui/fxml/assets/HintDead.png",
                !hintAvailable);

        setJokerButtonState(replacementButton, replacementImage,
                "/at/ac/hcw/kqm/ui/fxml/assets/JokerKing.png",
                "/at/ac/hcw/kqm/ui/fxml/assets/KingDead.png",
                !replaceAvailable);
    }

    private void setJokerButtonState(Button btn, javafx.scene.image.ImageView imgView,
                                     String originalImagePath, String deadImagePath, boolean used) {
        btn.setDisable(used);
        String path = used ? deadImagePath : originalImagePath;
        imgView.setImage(new javafx.scene.image.Image(
                QuizController.class.getResourceAsStream(path)
        ));
    }

    private void applyFifty() {
        Player currentPlayer = engine.currentPlayer();
        if (!currentPlayer.isJokerAvailable(at.ac.hcw.kqm.model.JokerType.FIFTY_FIFTY)) return;
        SceneManager.get().showJokerFifty();
    }

    private void applyHint() {
        Player currentPlayer = engine.currentPlayer();
        if (!currentPlayer.isJokerAvailable(at.ac.hcw.kqm.model.JokerType.HINT)) return;
        SceneManager.get().showJokerHint();
    }

    private void applyReplacement() {
        Player currentPlayer = engine.currentPlayer();
        if (!currentPlayer.isJokerAvailable(at.ac.hcw.kqm.model.JokerType.FILL_AND_JUMP)) return;
        SceneManager.get().showJokerFill();
    }
}
