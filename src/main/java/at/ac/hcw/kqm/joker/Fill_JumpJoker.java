package at.ac.hcw.kqm.joker;

import at.ac.hcw.kqm.model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Fill-&-Jump-Joker: Ersetzt die aktuelle Frage
 * durch eine Lückentext-Frage aus dem Songtext.
 * Dieser Joker wird verwendet, um eine andere Frage zu erhalten,
 * meist vom Typ „Vervollständige …“.
 */

public class Fill_JumpJoker implements Joker {

    @Override
    public void apply(Question currentQuestion, Button[] answerButtons, Label questionLabel) {
        // Note: This joker actually needs to return a replacement question,
        // so it's handled differently in the controller.
        // The actual replacement logic stays in QuizController.applyReplacement()
        // because it needs access to the question repository and needs to swap
        // the current question reference.
    }
}
