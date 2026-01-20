package at.ac.hcw.kqm.engine;

import at.ac.hcw.kqm.model.AnswerOption;
import at.ac.hcw.kqm.model.Question;

import java.util.ArrayList;
import java.util.List;

public class TieBreakManager {

    /**
     * Stellt eine einzelne Multiple-Choice-Frage mit Programmier-Thema
     * für den Tie-Break bereit.
     * Alle Spieler mit der höchsten Punktzahl beantworten sie nacheinander,
     * und der Spieler mit der kürzesten Antwortzeit gewinnt.
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

