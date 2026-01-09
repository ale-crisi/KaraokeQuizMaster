package at.ac.hcw.kqm.joker;

import at.ac.hcw.kqm.model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Interface for all joker types in the quiz game.
 * Each joker implements its own logic for assisting the player.
 */
public interface Joker {
    /**
     * Applies the joker effect to the current question.
     * 
     * @param currentQuestion The current question
     * @param answerButtons   The four answer buttons (A, B, C, D)
     * @param questionLabel   The question label (for hint display)
     */
    void apply(Question currentQuestion, Button[] answerButtons, Label questionLabel);
}
