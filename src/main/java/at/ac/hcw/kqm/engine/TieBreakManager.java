package at.ac.hcw.kqm.engine;

import at.ac.hcw.kqm.model.AnswerOption;
import at.ac.hcw.kqm.model.Question;

import java.util.ArrayList;
import java.util.List;

public class TieBreakManager {

    /**
     * Provides a single programming-themed multiple choice question
     * used for the tie-break. All players with top score answer one after another
     * and the one with the shortest time wins.
     */
    public List<Question> getProgrammingQuestions() {
        List<Question> list = new ArrayList<>();

        Question q1 = new Question(1001, 0,
                "Was bedeutet OOP?", 2,
                "Denke an Objektorientierung");
        q1.addAnswerOption(new AnswerOption(1, "Open Output Protocol"));
        q1.addAnswerOption(new AnswerOption(2, "Object Oriented Programming"));
        q1.addAnswerOption(new AnswerOption(3, "Optional Order Processing"));
        q1.addAnswerOption(new AnswerOption(4, "Operating On Pointers"));
        list.add(q1);

        return list;
    }
}

