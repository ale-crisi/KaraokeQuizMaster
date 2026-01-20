package at.ac.hcw.kqm.joker;

import at.ac.hcw.kqm.model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public interface Joker {

    void apply(Question currentQuestion, Button[] answerButtons, Label questionLabel);
}
