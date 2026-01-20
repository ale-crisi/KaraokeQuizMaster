package at.ac.hcw.kqm.model;

/**
 * Beschreibt die verschiedenen Joker-Arten, die im Spiel verwendet werden können.
 * Joker unterstützen den Spieler beim Beantworten einer Frage
 * und können pro Spieler bzw. pro Frage nur einmal eingesetzt werden.
 */
public enum JokerType {

    /**
     * 50/50-Joker:
     * Entfernt zwei falsche Antwortmöglichkeiten,
     * sodass nur noch eine richtige und eine falsche Antwort übrig bleiben.
     */
    FIFTY_FIFTY,

    /**
     * Hinweis-Joker:
     * Zeigt einen zusätzlichen Hinweis zur aktuellen Frage an,
     * der dem Spieler bei der Beantwortung helfen soll.
     */
    HINT,

    /**
     * Fragenwechsel-Joker (Fill & Jump):
     * Ersetzt die aktuelle Frage durch eine andere Multiple-Choice-Frage.
     * Die neue Frage wird anstelle der ursprünglichen gespielt.
     */
    FILL_AND_JUMP
}
