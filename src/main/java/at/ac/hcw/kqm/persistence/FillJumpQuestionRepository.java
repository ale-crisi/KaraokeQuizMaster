package at.ac.hcw.kqm.persistence;

import at.ac.hcw.kqm.model.AnswerOption;
import at.ac.hcw.kqm.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository für Fill-&-Jump-Joker-Fragen.
 * Diese Fragen sind Lückentext-Fragen,
 * bei denen ein Wort aus dem Refrain des Songs fehlt.
 */

public class FillJumpQuestionRepository {

    private List<Question> fillJumpQuestions;

    public FillJumpQuestionRepository() {
        this.fillJumpQuestions = new ArrayList<>();
        initializeFillJumpQuestions();
    }

    private void initializeFillJumpQuestions() {
        // ========== SONG 1: Simarik by Tarkan ==========
        Question fj1 = new Question(101, 1, "Vervollständige den Refrain: 'Simarik, simarik ___'", 1,
                "Der Refrain wiederholt sich");
        fj1.addAnswerOption(new AnswerOption(1, "simarik"));
        fj1.addAnswerOption(new AnswerOption(2, "seviyorum"));
        fj1.addAnswerOption(new AnswerOption(3, "gülüm"));
        fj1.addAnswerOption(new AnswerOption(4, "hoşça"));
        fillJumpQuestions.add(fj1);

        // ========== SONG 2: It's My Life by Bon Jovi ==========
        Question fj2 = new Question(102, 2, "Vervollständige: 'It's my life, it's now or ___'", 1,
                "Das Gegenteil von 'always'");
        fj2.addAnswerOption(new AnswerOption(1, "never"));
        fj2.addAnswerOption(new AnswerOption(2, "forever"));
        fj2.addAnswerOption(new AnswerOption(3, "always"));
        fj2.addAnswerOption(new AnswerOption(4, "ever"));
        fillJumpQuestions.add(fj2);

        // ========== SONG 3: Hit Me Baby One More Time by Britney Spears ==========
        Question fj3 = new Question(103, 3, "Vervollständige: 'Hit me baby one more ___'", 1,
                "Es ist der Songtitel");
        fj3.addAnswerOption(new AnswerOption(1, "time"));
        fj3.addAnswerOption(new AnswerOption(2, "night"));
        fj3.addAnswerOption(new AnswerOption(3, "day"));
        fj3.addAnswerOption(new AnswerOption(4, "way"));
        fillJumpQuestions.add(fj3);

        // ========== SONG 4: Rolling in the Deep by Adele ==========
        Question fj4 = new Question(104, 4, "Vervollständige: 'We could have had it ___'", 1,
                "Ein mächtiger Refrain");
        fj4.addAnswerOption(new AnswerOption(1, "all"));
        fj4.addAnswerOption(new AnswerOption(2, "now"));
        fj4.addAnswerOption(new AnswerOption(3, "forever"));
        fj4.addAnswerOption(new AnswerOption(4, "together"));
        fillJumpQuestions.add(fj4);

        // ========== SONG 5: Blinding Lights by The Weeknd ==========
        Question fj5 = new Question(105, 5, "Vervollständige: 'I said, ooh, I'm blinded by the ___'", 1,
                "Der Songtitel");
        fj5.addAnswerOption(new AnswerOption(1, "lights"));
        fj5.addAnswerOption(new AnswerOption(2, "night"));
        fj5.addAnswerOption(new AnswerOption(3, "stars"));
        fj5.addAnswerOption(new AnswerOption(4, "love"));
        fillJumpQuestions.add(fj5);

        // ========== SONG 6: Imagine by John Lennon ==========
        Question fj6 = new Question(106, 6, "Vervollständige: 'Imagine all the ___, living life in peace'", 1,
                "Eine Gruppe von Menschen");
        fj6.addAnswerOption(new AnswerOption(1, "people"));
        fj6.addAnswerOption(new AnswerOption(2, "children"));
        fj6.addAnswerOption(new AnswerOption(3, "nations"));
        fj6.addAnswerOption(new AnswerOption(4, "worlds"));
        fillJumpQuestions.add(fj6);

        // ========== SONG 7: Someone Like You by Adele ==========
        Question fj7 = new Question(107, 7, "Vervollständige: 'Never mind, I'll find someone like ___'", 1,
                "Es geht um eine Person");
        fj7.addAnswerOption(new AnswerOption(1, "you"));
        fj7.addAnswerOption(new AnswerOption(2, "me"));
        fj7.addAnswerOption(new AnswerOption(3, "him"));
        fj7.addAnswerOption(new AnswerOption(4, "her"));
        fillJumpQuestions.add(fj7);

        // ========== SONG 8: Hotel California by Eagles ==========
        Question fj8 = new Question(108, 8, "Vervollständige: 'Welcome to the Hotel ___'", 1,
                "Der Staat im Song");
        fj8.addAnswerOption(new AnswerOption(1, "California"));
        fj8.addAnswerOption(new AnswerOption(2, "Nevada"));
        fj8.addAnswerOption(new AnswerOption(3, "Arizona"));
        fj8.addAnswerOption(new AnswerOption(4, "Texas"));
        fillJumpQuestions.add(fj8);

        // ========== SONG 9: Don't Stop Believin' by Journey ==========
        Question fj9 = new Question(109, 9, "Vervollständige: 'Don't stop ___'", 1,
                "Ein motivierendes Wort");
        fj9.addAnswerOption(new AnswerOption(1, "believin'"));
        fj9.addAnswerOption(new AnswerOption(2, "dreamin'"));
        fj9.addAnswerOption(new AnswerOption(3, "hoping"));
        fj9.addAnswerOption(new AnswerOption(4, "loving"));
        fillJumpQuestions.add(fj9);

        // ========== SONG 10: Sweet Child O' Mine by Guns N' Roses ==========
        Question fj10 = new Question(110, 10, "Vervollständige: 'Sweet child o' ___'", 1,
                "Ein Besitzpronomen");
        fj10.addAnswerOption(new AnswerOption(1, "mine"));
        fj10.addAnswerOption(new AnswerOption(2, "yours"));
        fj10.addAnswerOption(new AnswerOption(3, "time"));
        fj10.addAnswerOption(new AnswerOption(4, "love"));
        fillJumpQuestions.add(fj10);
    }

    /**
     * Gets a Fill & Jump question for a specific song.
     *
     * @param songId The ID of the song
     * @return A Fill & Jump question for the song, or null if not found
     */
    public Question getQuestionForSong(int songId) {
        return fillJumpQuestions.stream()
                .filter(q -> q.getSongId() == songId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets all Fill & Jump questions.
     *
     * @return List of all Fill & Jump questions
     */
    public List<Question> getAllQuestions() {
        return new ArrayList<>(fillJumpQuestions);
    }
}
