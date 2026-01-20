package at.ac.hcw.kqm.joker;

import at.ac.hcw.kqm.model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Fifty-Fifty-Joker: Entfernt zwei falsche Antwortmöglichkeiten.
 */

public class FiftyFiftyJoker implements Joker {

    @Override
    public void apply(Question currentQuestion, Button[] answerButtons, Label questionLabel) {
        List<Integer> ids = List.of(1, 2, 3, 4);
        int correct = currentQuestion.getCorrectOptionId();
        Random rnd = new Random();
        List<Integer> wrongs = new ArrayList<>();

        for (int id : ids) {
            if (id != correct)
                wrongs.add(id);
        }

        // Pick two unique wrong answers
        int first = wrongs.remove(rnd.nextInt(wrongs.size()));
        int second = wrongs.get(rnd.nextInt(wrongs.size()));

        markWrongAndDisable(answerButtons, first);
        markWrongAndDisable(answerButtons, second);
    }

    private void markWrongAndDisable(Button[] buttons, int id) {
        Button btn = buttons[id - 1];
        btn.setDisable(true);
        btn.setStyle("-fx-background-color: #cc0000; -fx-text-fill: white;");
    }
}
