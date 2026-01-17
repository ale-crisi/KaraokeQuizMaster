package at.ac.hcw.kqm.model;

/**
 * Enum representing the different types of jokers (lifelines) available in the game.
 * Each player can use each joker type once per game.
 */
public enum JokerType {
    /**
     * Fifty-Fifty: Removes two incorrect answer options, leaving only two choices.
     */
    FIFTY_FIFTY,

    /**
     * Hint: Provides a helpful hint or additional information about the question.
     */
    HINT,

    /**
     * Fill and Jump: Skips the current question and replaces it with a new one.
     */
    FIILL_AND_JUMP
}
