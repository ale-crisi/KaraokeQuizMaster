package at.ac.hcw.kqm.persistence;

import at.ac.hcw.kqm.model.AnswerOption;
import at.ac.hcw.kqm.model.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * In-memory implementation of QuestionRepository.
 * Stores questions that are specifically related to karaoke songs.
 *
 * WORKFLOW:
 * 1. Player chooses a song from the song list
 * 2. System retrieves questions for that specific song using getQuestionsBySongId()
 * 3. Player answers questions about the chosen song
 *
 * DATA: Contains 30 questions for 10 different songs (3 questions per song)
 */
public class InMemoryQuestionRepository implements QuestionRepository {

    private List<Question> questions;

    /**
     * Constructor that initializes the repository with song-specific questions.
     */
    public InMemoryQuestionRepository() {
        this.questions = new ArrayList<>();
        initializeQuestions();
    }

    /**
     * Initializes questions for each karaoke song.
     * Each song has 3 questions about lyrics, artist, album, year, etc.
     */
    private void initializeQuestions() {
        // ========== SONG 1: Bohemian Rhapsody by Queen ==========
        Question q1_1 = new Question(1, 1, "Welches Jahr wurde 'Bohemian Rhapsody' veröffentlicht?", 3, "Es war Mitte der 1970er Jahre");
        q1_1.addAnswerOption(new AnswerOption(1, "1973"));
        q1_1.addAnswerOption(new AnswerOption(2, "1974"));
        q1_1.addAnswerOption(new AnswerOption(3, "1975"));
        q1_1.addAnswerOption(new AnswerOption(4, "1976"));
        questions.add(q1_1);

        Question q1_2 = new Question(2, 1, "Vervollständige den Text: 'Is this the real life? Is this just ___?'", 2, "Denk an Fantasie");
        q1_2.addAnswerOption(new AnswerOption(1, "a dream"));
        q1_2.addAnswerOption(new AnswerOption(2, "fantasy"));
        q1_2.addAnswerOption(new AnswerOption(3, "illusion"));
        q1_2.addAnswerOption(new AnswerOption(4, "reality"));
        questions.add(q1_2);

        Question q1_3 = new Question(3, 1, "Wer ist der Leadsänger von Queen?", 1, "Er war bekannt für seine kraftvolle Stimme");
        q1_3.addAnswerOption(new AnswerOption(1, "Freddie Mercury"));
        q1_3.addAnswerOption(new AnswerOption(2, "Brian May"));
        q1_3.addAnswerOption(new AnswerOption(3, "Roger Taylor"));
        q1_3.addAnswerOption(new AnswerOption(4, "John Deacon"));
        questions.add(q1_3);

        // ========== SONG 2: Shape of You by Ed Sheeran ==========
        Question q2_1 = new Question(4, 2, "In welchem Jahr wurde 'Shape of You' veröffentlicht?", 3, "Es war ein aktueller Hit");
        q2_1.addAnswerOption(new AnswerOption(1, "2015"));
        q2_1.addAnswerOption(new AnswerOption(2, "2016"));
        q2_1.addAnswerOption(new AnswerOption(3, "2017"));
        q2_1.addAnswerOption(new AnswerOption(4, "2018"));
        questions.add(q2_1);

        Question q2_2 = new Question(5, 2, "Vervollständige: 'I'm in love with the ___ of you'", 1, "Es geht um das Aussehen");
        q2_2.addAnswerOption(new AnswerOption(1, "shape"));
        q2_2.addAnswerOption(new AnswerOption(2, "sound"));
        q2_2.addAnswerOption(new AnswerOption(3, "smile"));
        q2_2.addAnswerOption(new AnswerOption(4, "heart"));
        questions.add(q2_2);

        Question q2_3 = new Question(6, 2, "Welches Album enthält 'Shape of You'?", 4, "Es ist nach einem Mathesymbol benannt");
        q2_3.addAnswerOption(new AnswerOption(1, "+"));
        q2_3.addAnswerOption(new AnswerOption(2, "x"));
        q2_3.addAnswerOption(new AnswerOption(3, "-"));
        q2_3.addAnswerOption(new AnswerOption(4, "÷"));
        questions.add(q2_3);

        // ========== SONG 3: Billie Jean by Michael Jackson ==========
        Question q3_1 = new Question(7, 3, "Welches Album enthält 'Billie Jean'?", 1, "Es ist sein meistverkauftes Album");
        q3_1.addAnswerOption(new AnswerOption(1, "Thriller"));
        q3_1.addAnswerOption(new AnswerOption(2, "Bad"));
        q3_1.addAnswerOption(new AnswerOption(3, "Dangerous"));
        q3_1.addAnswerOption(new AnswerOption(4, "Off the Wall"));
        questions.add(q3_1);

        Question q3_2 = new Question(8, 3, "Vervollständige: 'Billie Jean is not my ___'", 2, "Es geht um eine Beziehung");
        q3_2.addAnswerOption(new AnswerOption(1, "girl"));
        q3_2.addAnswerOption(new AnswerOption(2, "lover"));
        q3_2.addAnswerOption(new AnswerOption(3, "friend"));
        q3_2.addAnswerOption(new AnswerOption(4, "wife"));
        questions.add(q3_2);

        Question q3_3 = new Question(9, 3, "In welchem Jahr wurde 'Billie Jean' veröffentlicht?", 3, "Anfang der 1980er");
        q3_3.addAnswerOption(new AnswerOption(1, "1981"));
        q3_3.addAnswerOption(new AnswerOption(2, "1982"));
        q3_3.addAnswerOption(new AnswerOption(3, "1983"));
        q3_3.addAnswerOption(new AnswerOption(4, "1984"));
        questions.add(q3_3);

        // ========== SONG 4: Rolling in the Deep by Adele ==========
        Question q4_1 = new Question(10, 4, "Vervollständige: 'We could have had it ___'", 2, "Es geht darum, was hätte sein können");
        q4_1.addAnswerOption(new AnswerOption(1, "forever"));
        q4_1.addAnswerOption(new AnswerOption(2, "all"));
        q4_1.addAnswerOption(new AnswerOption(3, "together"));
        q4_1.addAnswerOption(new AnswerOption(4, "right"));
        questions.add(q4_1);

        Question q4_2 = new Question(11, 4, "Von welchem Album stammt 'Rolling in the Deep'?", 2, "Es ist eine Zahl");
        q4_2.addAnswerOption(new AnswerOption(1, "19"));
        q4_2.addAnswerOption(new AnswerOption(2, "21"));
        q4_2.addAnswerOption(new AnswerOption(3, "25"));
        q4_2.addAnswerOption(new AnswerOption(4, "30"));
        questions.add(q4_2);

        Question q4_3 = new Question(12, 4, "In welchem Jahr wurde 'Rolling in the Deep' veröffentlicht?", 1, "Anfang der 2010er");
        q4_3.addAnswerOption(new AnswerOption(1, "2010"));
        q4_3.addAnswerOption(new AnswerOption(2, "2011"));
        q4_3.addAnswerOption(new AnswerOption(3, "2012"));
        q4_3.addAnswerOption(new AnswerOption(4, "2013"));
        questions.add(q4_3);

        // ========== SONG 5: Blinding Lights by The Weeknd ==========
        Question q5_1 = new Question(13, 5, "Welches Genre beschreibt 'Blinding Lights' am besten?", 4, "Es hat einen Retro-80er-Sound");
        q5_1.addAnswerOption(new AnswerOption(1, "Rock"));
        q5_1.addAnswerOption(new AnswerOption(2, "Hip Hop"));
        q5_1.addAnswerOption(new AnswerOption(3, "Country"));
        q5_1.addAnswerOption(new AnswerOption(4, "Synthwave/Pop"));
        questions.add(q5_1);

        Question q5_2 = new Question(14, 5, "Vervollständige: 'I've been tryna call, I've been on my own for ___'", 3, "Es geht um Zeit");
        q5_2.addAnswerOption(new AnswerOption(1, "days"));
        q5_2.addAnswerOption(new AnswerOption(2, "weeks"));
        q5_2.addAnswerOption(new AnswerOption(3, "long enough"));
        q5_2.addAnswerOption(new AnswerOption(4, "too long"));
        questions.add(q5_2);

        Question q5_3 = new Question(15, 5, "Welches Album enthält 'Blinding Lights'?", 2, "Es geht um die Tageszeit");
        q5_3.addAnswerOption(new AnswerOption(1, "Starboy"));
        q5_3.addAnswerOption(new AnswerOption(2, "After Hours"));
        q5_3.addAnswerOption(new AnswerOption(3, "Beauty Behind the Madness"));
        q5_3.addAnswerOption(new AnswerOption(4, "Dawn FM"));
        questions.add(q5_3);

        // ========== SONG 6: Imagine by John Lennon ==========
        Question q6_1 = new Question(16, 6, "Wer schrieb und sang 'Imagine'?", 3, "Er war Mitglied der Beatles");
        q6_1.addAnswerOption(new AnswerOption(1, "Paul McCartney"));
        q6_1.addAnswerOption(new AnswerOption(2, "George Harrison"));
        q6_1.addAnswerOption(new AnswerOption(3, "John Lennon"));
        q6_1.addAnswerOption(new AnswerOption(4, "Ringo Starr"));
        questions.add(q6_1);

        Question q6_2 = new Question(17, 6, "Vervollständige: 'Imagine there's no ___'", 1, "Der Song spricht von Frieden");
        q6_2.addAnswerOption(new AnswerOption(1, "heaven"));
        q6_2.addAnswerOption(new AnswerOption(2, "countries"));
        q6_2.addAnswerOption(new AnswerOption(3, "religion"));
        q6_2.addAnswerOption(new AnswerOption(4, "war"));
        questions.add(q6_2);

        Question q6_3 = new Question(18, 6, "In welchem Jahr wurde 'Imagine' veröffentlicht?", 2, "Anfang der 1970er");
        q6_3.addAnswerOption(new AnswerOption(1, "1970"));
        q6_3.addAnswerOption(new AnswerOption(2, "1971"));
        q6_3.addAnswerOption(new AnswerOption(3, "1972"));
        q6_3.addAnswerOption(new AnswerOption(4, "1973"));
        questions.add(q6_3);

        // ========== SONG 7: Someone Like You by Adele ==========
        Question q7_1 = new Question(19, 7, "Welches Adele-Album enthält 'Someone Like You'?", 2, "Es ist eine Zahl");
        q7_1.addAnswerOption(new AnswerOption(1, "19"));
        q7_1.addAnswerOption(new AnswerOption(2, "21"));
        q7_1.addAnswerOption(new AnswerOption(3, "25"));
        q7_1.addAnswerOption(new AnswerOption(4, "30"));
        questions.add(q7_1);

        Question q7_2 = new Question(20, 7, "Vervollständige: 'Never mind, I'll find someone like ___'", 1, "Es ist im Titel");
        q7_2.addAnswerOption(new AnswerOption(1, "you"));
        q7_2.addAnswerOption(new AnswerOption(2, "me"));
        q7_2.addAnswerOption(new AnswerOption(3, "them"));
        q7_2.addAnswerOption(new AnswerOption(4, "us"));
        questions.add(q7_2);

        Question q7_3 = new Question(21, 7, "In welchem Jahr wurde 'Someone Like You' veröffentlicht?", 2, "Anfang der 2010er");
        q7_3.addAnswerOption(new AnswerOption(1, "2010"));
        q7_3.addAnswerOption(new AnswerOption(2, "2011"));
        q7_3.addAnswerOption(new AnswerOption(3, "2012"));
        q7_3.addAnswerOption(new AnswerOption(4, "2013"));
        questions.add(q7_3);

        // ========== SONG 8: Hotel California by Eagles ==========
        Question q8_1 = new Question(22, 8, "Was ist die berühmte Eröffnungszeile von 'Hotel California'?", 1, "Es beginnt mit einer Straße");
        q8_1.addAnswerOption(new AnswerOption(1, "On a dark desert highway"));
        q8_1.addAnswerOption(new AnswerOption(2, "Welcome to the Hotel California"));
        q8_1.addAnswerOption(new AnswerOption(3, "Last thing I remember"));
        q8_1.addAnswerOption(new AnswerOption(4, "Mirrors on the ceiling"));
        questions.add(q8_1);

        Question q8_2 = new Question(23, 8, "In welchem Jahr wurde 'Hotel California' veröffentlicht?", 3, "Mitte der 1970er");
        q8_2.addAnswerOption(new AnswerOption(1, "1975"));
        q8_2.addAnswerOption(new AnswerOption(2, "1976"));
        q8_2.addAnswerOption(new AnswerOption(3, "1977"));
        q8_2.addAnswerOption(new AnswerOption(4, "1978"));
        questions.add(q8_2);

        Question q8_3 = new Question(24, 8, "Vervollständige: 'You can check out any time you like, but you can never ___'", 2, "Es geht ums Verlassen");
        q8_3.addAnswerOption(new AnswerOption(1, "return"));
        q8_3.addAnswerOption(new AnswerOption(2, "leave"));
        q8_3.addAnswerOption(new AnswerOption(3, "escape"));
        q8_3.addAnswerOption(new AnswerOption(4, "go"));
        questions.add(q8_3);

        // ========== SONG 9: Don't Stop Believin' by Journey ==========
        Question q9_1 = new Question(25, 9, "Von welcher Band stammt 'Don't Stop Believin''?", 2, "Sie sind eine amerikanische Rockband");
        q9_1.addAnswerOption(new AnswerOption(1, "Boston"));
        q9_1.addAnswerOption(new AnswerOption(2, "Journey"));
        q9_1.addAnswerOption(new AnswerOption(3, "Foreigner"));
        q9_1.addAnswerOption(new AnswerOption(4, "Styx"));
        questions.add(q9_1);

        Question q9_2 = new Question(26, 9, "Vervollständige: 'Just a small town girl, living in a ___ world'", 1, "Es reimt sich auf 'girl'");
        q9_2.addAnswerOption(new AnswerOption(1, "lonely"));
        q9_2.addAnswerOption(new AnswerOption(2, "crazy"));
        q9_2.addAnswerOption(new AnswerOption(3, "busy"));
        q9_2.addAnswerOption(new AnswerOption(4, "pretty"));
        questions.add(q9_2);

        Question q9_3 = new Question(27, 9, "In welchem Jahr wurde 'Don't Stop Believin'' veröffentlicht?", 3, "Anfang der 1980er");
        q9_3.addAnswerOption(new AnswerOption(1, "1979"));
        q9_3.addAnswerOption(new AnswerOption(2, "1980"));
        q9_3.addAnswerOption(new AnswerOption(3, "1981"));
        q9_3.addAnswerOption(new AnswerOption(4, "1982"));
        questions.add(q9_3);

        // ========== SONG 10: Sweet Child O' Mine by Guns N' Roses ==========
        Question q10_1 = new Question(28, 10, "Wer ist der Leadsänger von Guns N' Roses?", 1, "Er ist bekannt für seine hohe Stimme");
        q10_1.addAnswerOption(new AnswerOption(1, "Axl Rose"));
        q10_1.addAnswerOption(new AnswerOption(2, "Slash"));
        q10_1.addAnswerOption(new AnswerOption(3, "Duff McKagan"));
        q10_1.addAnswerOption(new AnswerOption(4, "Izzy Stradlin"));
        questions.add(q10_1);

        Question q10_2 = new Question(29, 10, "Vervollständige: 'Sweet child o' mine, where do we go ___?'", 3, "Es fragt nach einer Richtung");
        q10_2.addAnswerOption(new AnswerOption(1, "today"));
        q10_2.addAnswerOption(new AnswerOption(2, "tonight"));
        q10_2.addAnswerOption(new AnswerOption(3, "now"));
        q10_2.addAnswerOption(new AnswerOption(4, "away"));
        questions.add(q10_2);

        Question q10_3 = new Question(30, 10, "Welches Album enthält 'Sweet Child O' Mine'?", 1, "Es ist ihr Debütalbum");
        q10_3.addAnswerOption(new AnswerOption(1, "Appetite for Destruction"));
        q10_3.addAnswerOption(new AnswerOption(2, "Use Your Illusion I"));
        q10_3.addAnswerOption(new AnswerOption(3, "Use Your Illusion II"));
        q10_3.addAnswerOption(new AnswerOption(4, "The Spaghetti Incident?"));
        questions.add(q10_3);
    }

    @Override
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    @Override
    public Question getQuestionById(int id) {
        for (Question question : questions) {
            if (question.getId() == id) {
                return question;
            }
        }
        return null;
    }

    @Override
    public List<Question> getQuestionsBySongId(int songId) {
        return questions.stream()
                .filter(q -> q.getSongId() == songId)
                .collect(Collectors.toList());
    }

    @Override
    public int getQuestionCount() {
        return questions.size();
    }
}