package at.ac.hcw.kqm.model;

public enum GamePhase {
    /**
     * NOT_STARTED it is running, but not started yet
     */
    NOT_STARTED,

    /**
     * ASKING_QUESTION it is asking Questions to a player
     */
    ASKING_QUESTION,

    /**
     * TIE_BREAK starts a tie-break if there is one
     */
    TIE_BREAK,

    /**
     * FINISHED the game is done
     */
    FINISHED
}
