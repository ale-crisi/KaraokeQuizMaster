package at.ac.hcw.kqm.model;

/**
 * Represents a tie-break question in the Karaoke Quiz Master game.
 * Used when multiple players have the same score at the end of the regular
 * quiz.
 * Tie-break questions are used to determine a winner.
 */
public class TieBreakQuestion extends Question {

    /**
     * Constructor for tie-break question.
     */
    public TieBreakQuestion(int id, int songId, String questionText, int correctOptionId) {
        super(id, songId, questionText, correctOptionId);
    }

    /**
     * Constructor for tie-break question with hint.
     */
    public TieBreakQuestion(int id, int songId, String questionText, int correctOptionId, String hint) {
        super(id, songId, questionText, correctOptionId, hint);
    }

    /**
     * Default constructor.
     */
    public TieBreakQuestion() {
        super();
    }
}
