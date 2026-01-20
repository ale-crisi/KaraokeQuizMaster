package at.ac.hcw.kqm.joker;

import at.ac.hcw.kqm.model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * Joker, der die aktuelle Frage durch eine andere Multiple-Choice-Frage ersetzt.
 * Die Ersetzung passiert im Controller, weil dort die aktuelle Frage gewechselt wird.
 */
public class Fill_JumpJoker implements Joker {
    @Override
    public void apply(Question currentQuestion, Button[] answerButtons, Label questionLabel) {
        // Wird im Controller umgesetzt.
    }
}

