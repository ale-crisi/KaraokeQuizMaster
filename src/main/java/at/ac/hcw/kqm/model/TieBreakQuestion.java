package at.ac.hcw.kqm.model;

/**
 * Repräsentiert eine Tie-Break-Frage im Karaoke Quiz Master Spiel.
 * Sie wird verwendet, wenn mehrere Spieler am Ende
 * die gleiche Punktzahl haben, um einen Gewinner zu bestimmen.
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
