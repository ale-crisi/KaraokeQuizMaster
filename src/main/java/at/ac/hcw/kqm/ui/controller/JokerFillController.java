package at.ac.hcw.kqm.ui.controller;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.model.JokerType;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.fxml.FXML;

public class JokerFillController {

    @FXML
    public void backToQuiz() {
        SceneManager.get().showQuiz();
    }

    @FXML
    public void applyAndReturn() {
        AppState.get().setPendingJoker(JokerType.REPLACE_QUESTION);
        SceneManager.get().showQuiz();
    }
}
