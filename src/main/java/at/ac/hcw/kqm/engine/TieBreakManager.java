package at.ac.hcw.kqm.engine;

import java.util.ArrayList;
import java.util.List;

import at.ac.hcw.kqm.model.AnswerOption;
import at.ac.hcw.kqm.model.Question;

public class TieBreakManager {

    /**
     * Provides a small set of programming-themed multiple choice questions
     * used for the tie-break. All players with top score answer one after another
     * and the one with most correct answers in the shortest time wins.
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

        Question q2 = new Question(1002, 0,
                "Welche Datenstruktur ist LIFO?", 1,
                "Letztes rein, erstes raus");
        q2.addAnswerOption(new AnswerOption(1, "Stack"));
        q2.addAnswerOption(new AnswerOption(2, "Queue"));
        q2.addAnswerOption(new AnswerOption(3, "Array"));
        q2.addAnswerOption(new AnswerOption(4, "Map"));
        list.add(q2);

        Question q3 = new Question(1003, 0,
                "Welche Komplexität hat binäre Suche im Worst-Case?", 3,
                "Logarithmisch");
        q3.addAnswerOption(new AnswerOption(1, "O(n)"));
        q3.addAnswerOption(new AnswerOption(2, "O(n log n)"));
        q3.addAnswerOption(new AnswerOption(3, "O(log n)"));
        q3.addAnswerOption(new AnswerOption(4, "O(1)"));
        list.add(q3);

        return list;
    }
}
