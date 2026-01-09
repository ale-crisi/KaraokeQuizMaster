package at.ac.hcw.kqm.joker;

import at.ac.hcw.kqm.model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Hint Joker: Displays a helpful hint for the current question.
 */
public class HintJoker implements Joker {

    @Override
    public void apply(Question currentQuestion, Button[] answerButtons, Label questionLabel) {
        String hint = currentQuestion.getHint();
        if (hint == null || hint.isEmpty()) {
            hint = "Keine Hinweise verfügbar.";
        }
        questionLabel.setText(currentQuestion.getQuestionText() + "\n\nHinweis: " + hint);
    }
}
