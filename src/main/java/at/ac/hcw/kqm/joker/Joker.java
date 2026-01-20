package at.ac.hcw.kqm.joker;

import at.ac.hcw.kqm.model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * Interface für alle Joker-Typen im Quizspiel.
 * Jeder Joker implementiert seine eigene Logik,
 * um den Spieler zu unterstützen.
 */

public interface Joker {

    void apply(Question currentQuestion, Button[] answerButtons, Label questionLabel);
}
