package at.ac.hcw.kqm.model;

/**
 * Beschreibt den aktuellen Zustand (Phase) des Spiels.
 * Die Phase wird in der Engine genutzt, um zu steuern, welche Aktionen gerade erlaubt sind
 * (z.B. normale Fragen beantworten, Tie-Break spielen, Ergebnis anzeigen).
 */
public enum GamePhase {

    /**
     * Das Spiel wurde noch nicht gestartet.
     * Es gibt noch keine laufende Fragerunde.
     */
    NOT_STARTED,

    /**
     * Normale Spielphase: Es werden Fragen gestellt und beantwortet,
     * und Punkte werden vergeben.
     */
    ASKING_QUESTION,

    /**
     * Tie-Break-Phase: Wird verwendet, wenn nach der normalen Spielphase ein Gleichstand besteht.
     * In dieser Phase spielen nur die Spieler im Gleichstand.
     */
    TIE_BREAK,

    /**
     * Das Spiel ist beendet. Es werden keine weiteren Fragen gestellt.
     * In dieser Phase kann z.B. das Endergebnis angezeigt werden.
     */
    FINISHED
}
