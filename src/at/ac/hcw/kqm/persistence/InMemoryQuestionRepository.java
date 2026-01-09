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
 * DATA: Contains 40 questions for 10 different songs (4 questions per song)
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
     * Each song has 4 questions about lyrics, artist, album, year, etc.
     */
    private void initializeQuestions() {

        // Song IDs (must match your SongRepository / song list order):
        // 1: It's My Life (Bon Jovi)
        // 2: Nothing Else Matters (Metallica)
        // 3: Creep (Radiohead)
        // 4: Bohemian Rhapsody (Queen)
        // 5: Back to Black (Amy Winehouse)
        // 6: Smells Like Teen Spirit (Nirvana)
        // 7: Billie Jean (Michael Jackson)
        // 8: Rolling in the Deep (Adele)
        // 9: Wonderwall (Oasis)
        // 10: Mr. Brightside (The Killers)

        // ========== SONG 1: It's My Life by Bon Jovi ==========
        Question q1_1 = new Question(1, 1, "In which year was 'It's My Life' released?", 3, "It became a huge hit around the early 2000s.");
        q1_1.addAnswerOption(new AnswerOption(1, "1998"));
        q1_1.addAnswerOption(new AnswerOption(2, "1999"));
        q1_1.addAnswerOption(new AnswerOption(3, "2000"));
        q1_1.addAnswerOption(new AnswerOption(4, "2001"));
        questions.add(q1_1);

        Question q1_2 = new Question(2, 1, "Complete the lyric: 'It's my life, it's now or ___'", 3, "It’s the opposite of 'always'.");
        q1_2.addAnswerOption(new AnswerOption(1, "later"));
        q1_2.addAnswerOption(new AnswerOption(2, "nothing"));
        q1_2.addAnswerOption(new AnswerOption(3, "never"));
        q1_2.addAnswerOption(new AnswerOption(4, "forever"));
        questions.add(q1_2);

        Question q1_3 = new Question(3, 1, "Who performs 'It's My Life'?", 2, "It’s the band name used as the artist.");
        q1_3.addAnswerOption(new AnswerOption(1, "U2"));
        q1_3.addAnswerOption(new AnswerOption(2, "Bon Jovi"));
        q1_3.addAnswerOption(new AnswerOption(3, "Aerosmith"));
        q1_3.addAnswerOption(new AnswerOption(4, "The Killers"));
        questions.add(q1_3);

        Question q1_4 = new Question(4, 1, "Which album includes 'It's My Life'?", 2, "The album title is a single word meaning 'crush'.");
        q1_4.addAnswerOption(new AnswerOption(1, "Slippery When Wet"));
        q1_4.addAnswerOption(new AnswerOption(2, "Crush"));
        q1_4.addAnswerOption(new AnswerOption(3, "Keep the Faith"));
        q1_4.addAnswerOption(new AnswerOption(4, "These Days"));
        questions.add(q1_4);

        // ========== SONG 2: Nothing Else Matters by Metallica ==========
        Question q2_1 = new Question(5, 2, "In which year was 'Nothing Else Matters' released as a single?", 3, "It came out in the early 1990s.");
        q2_1.addAnswerOption(new AnswerOption(1, "1989"));
        q2_1.addAnswerOption(new AnswerOption(2, "1991"));
        q2_1.addAnswerOption(new AnswerOption(3, "1992"));
        q2_1.addAnswerOption(new AnswerOption(4, "1994"));
        questions.add(q2_1);

        Question q2_2 = new Question(6, 2, "Complete the lyric: 'Nothing else ___'", 2, "It’s the song title’s last word.");
        q2_2.addAnswerOption(new AnswerOption(1, "hurts"));
        q2_2.addAnswerOption(new AnswerOption(2, "matters"));
        q2_2.addAnswerOption(new AnswerOption(3, "stays"));
        q2_2.addAnswerOption(new AnswerOption(4, "changes"));
        questions.add(q2_2);

        Question q2_3 = new Question(7, 2, "Which album features 'Nothing Else Matters'?", 3, "It’s also known as 'The Black Album'.");
        q2_3.addAnswerOption(new AnswerOption(1, "Master of Puppets"));
        q2_3.addAnswerOption(new AnswerOption(2, "...And Justice for All"));
        q2_3.addAnswerOption(new AnswerOption(3, "Metallica (The Black Album)"));
        q2_3.addAnswerOption(new AnswerOption(4, "Ride the Lightning"));
        questions.add(q2_3);

        Question q2_4 = new Question(8, 2, "Which genre fits 'Nothing Else Matters' best?", 3, "It’s a famous metal ballad.");
        q2_4.addAnswerOption(new AnswerOption(1, "EDM"));
        q2_4.addAnswerOption(new AnswerOption(2, "Country"));
        q2_4.addAnswerOption(new AnswerOption(3, "Heavy Metal / Rock Ballad"));
        q2_4.addAnswerOption(new AnswerOption(4, "Reggae"));
        questions.add(q2_4);

        // ========== SONG 3: Creep by Radiohead ==========
        Question q3_1 = new Question(9, 3, "In which year was 'Creep' originally released?", 2, "Early 1990s.");
        q3_1.addAnswerOption(new AnswerOption(1, "1991"));
        q3_1.addAnswerOption(new AnswerOption(2, "1992"));
        q3_1.addAnswerOption(new AnswerOption(3, "1994"));
        q3_1.addAnswerOption(new AnswerOption(4, "1996"));
        questions.add(q3_1);

        Question q3_2 = new Question(10, 3, "Complete the lyric: 'I want a perfect ___'", 3, "It’s something physical.");
        q3_2.addAnswerOption(new AnswerOption(1, "mind"));
        q3_2.addAnswerOption(new AnswerOption(2, "life"));
        q3_2.addAnswerOption(new AnswerOption(3, "body"));
        q3_2.addAnswerOption(new AnswerOption(4, "world"));
        questions.add(q3_2);

        Question q3_3 = new Question(11, 3, "Which album includes 'Creep'?", 3, "Radiohead’s debut album.");
        q3_3.addAnswerOption(new AnswerOption(1, "OK Computer"));
        q3_3.addAnswerOption(new AnswerOption(2, "The Bends"));
        q3_3.addAnswerOption(new AnswerOption(3, "Pablo Honey"));
        q3_3.addAnswerOption(new AnswerOption(4, "Kid A"));
        questions.add(q3_3);

        Question q3_4 = new Question(12, 3, "What is 'Creep' mainly about?", 3, "Think: not feeling good enough.");
        q3_4.addAnswerOption(new AnswerOption(1, "Celebrating success"));
        q3_4.addAnswerOption(new AnswerOption(2, "Traveling the world"));
        q3_4.addAnswerOption(new AnswerOption(3, "Feeling like an outsider"));
        q3_4.addAnswerOption(new AnswerOption(4, "Throwing a party"));
        questions.add(q3_4);

        // ========== SONG 4: Bohemian Rhapsody by Queen =========
        Question q4_1 = new Question(13, 4, "In which year was 'Bohemian Rhapsody' released?", 3, "It was the mid-1970s.");
        q4_1.addAnswerOption(new AnswerOption(1, "1973"));
        q4_1.addAnswerOption(new AnswerOption(2, "1974"));
        q4_1.addAnswerOption(new AnswerOption(3, "1975"));
        q4_1.addAnswerOption(new AnswerOption(4, "1976"));
        questions.add(q4_1);

        Question q4_2 = new Question(14, 4, "Complete the lyric: 'Is this the real life? Is this just ___?'", 2, "Think of imagination.");
        q4_2.addAnswerOption(new AnswerOption(1, "a dream"));
        q4_2.addAnswerOption(new AnswerOption(2, "fantasy"));
        q4_2.addAnswerOption(new AnswerOption(3, "illusion"));
        q4_2.addAnswerOption(new AnswerOption(4, "reality"));
        questions.add(q4_2);

        Question q4_3 = new Question(15, 4, "Who was the lead singer of Queen?", 1, "He was known for an extremely powerful voice.");
        q4_3.addAnswerOption(new AnswerOption(1, "Freddie Mercury"));
        q4_3.addAnswerOption(new AnswerOption(2, "Brian May"));
        q4_3.addAnswerOption(new AnswerOption(3, "Roger Taylor"));
        q4_3.addAnswerOption(new AnswerOption(4, "John Deacon"));
        questions.add(q4_3);

        Question q4_4 = new Question(16, 4, "Which album first featured 'Bohemian Rhapsody'?", 2, "It sounds like going to a show.");
        q4_4.addAnswerOption(new AnswerOption(1, "News of the World"));
        q4_4.addAnswerOption(new AnswerOption(2, "A Night at the Opera"));
        q4_4.addAnswerOption(new AnswerOption(3, "Sheer Heart Attack"));
        q4_4.addAnswerOption(new AnswerOption(4, "The Works"));
        questions.add(q4_4);

        // ========== SONG 5: Back to Black by Amy Winehouse ==========
        Question q5_1 = new Question(17, 5, "In which year was 'Back to Black' released?", 3, "Mid-2000s.");
        q5_1.addAnswerOption(new AnswerOption(1, "2004"));
        q5_1.addAnswerOption(new AnswerOption(2, "2005"));
        q5_1.addAnswerOption(new AnswerOption(3, "2006"));
        q5_1.addAnswerOption(new AnswerOption(4, "2007"));
        questions.add(q5_1);

        Question q5_2 = new Question(18, 5, "Complete the lyric: 'We only said goodbye with ___'", 2, "It’s what you say, not what you do.");
        q5_2.addAnswerOption(new AnswerOption(1, "tears"));
        q5_2.addAnswerOption(new AnswerOption(2, "words"));
        q5_2.addAnswerOption(new AnswerOption(3, "songs"));
        q5_2.addAnswerOption(new AnswerOption(4, "kisses"));
        questions.add(q5_2);

        Question q5_3 = new Question(19, 5, "Who performs 'Back to Black'?", 1, "A British singer with a very distinctive voice.");
        q5_3.addAnswerOption(new AnswerOption(1, "Amy Winehouse"));
        q5_3.addAnswerOption(new AnswerOption(2, "Adele"));
        q5_3.addAnswerOption(new AnswerOption(3, "Dua Lipa"));
        q5_3.addAnswerOption(new AnswerOption(4, "Lana Del Rey"));
        questions.add(q5_3);

        Question q5_4 = new Question(20, 5, "Which album includes 'Back to Black'?", 2, "Same name as the song.");
        q5_4.addAnswerOption(new AnswerOption(1, "Frank"));
        q5_4.addAnswerOption(new AnswerOption(2, "Back to Black"));
        q5_4.addAnswerOption(new AnswerOption(3, "Lioness: Hidden Treasures"));
        q5_4.addAnswerOption(new AnswerOption(4, "21"));
        questions.add(q5_4);

        // ========== SONG 6: Smells Like Teen Spirit by Nirvana ==========
        Question q6_1 = new Question(21, 6, "In which year was 'Smells Like Teen Spirit' released?", 3, "Early 1990s.");
        q6_1.addAnswerOption(new AnswerOption(1, "1989"));
        q6_1.addAnswerOption(new AnswerOption(2, "1990"));
        q6_1.addAnswerOption(new AnswerOption(3, "1991"));
        q6_1.addAnswerOption(new AnswerOption(4, "1993"));
        questions.add(q6_1);

        Question q6_2 = new Question(22, 6, "Complete the lyric: 'Here we are now, entertain ___'", 3, "A simple pronoun.");
        q6_2.addAnswerOption(new AnswerOption(1, "me"));
        q6_2.addAnswerOption(new AnswerOption(2, "you"));
        q6_2.addAnswerOption(new AnswerOption(3, "us"));
        q6_2.addAnswerOption(new AnswerOption(4, "them"));
        questions.add(q6_2);

        Question q6_3 = new Question(23, 6, "Which album includes 'Smells Like Teen Spirit'?", 3, "It’s the album that launched them worldwide.");
        q6_3.addAnswerOption(new AnswerOption(1, "Bleach"));
        q6_3.addAnswerOption(new AnswerOption(2, "In Utero"));
        q6_3.addAnswerOption(new AnswerOption(3, "Nevermind"));
        q6_3.addAnswerOption(new AnswerOption(4, "Ten"));
        questions.add(q6_3);

        Question q6_4 = new Question(24, 6, "Which genre fits the song best?", 2, "It’s the sound of the early 90s Seattle scene.");
        q6_4.addAnswerOption(new AnswerOption(1, "Disco"));
        q6_4.addAnswerOption(new AnswerOption(2, "Grunge / Alternative Rock"));
        q6_4.addAnswerOption(new AnswerOption(3, "Country"));
        q6_4.addAnswerOption(new AnswerOption(4, "Jazz"));
        questions.add(q6_4);

        // ========== SONG 7: Billie Jean by Michael Jackson ==========
        Question q7_1 = new Question(25, 7, "Which album includes 'Billie Jean'?", 1, "It’s his best-selling album.");
        q7_1.addAnswerOption(new AnswerOption(1, "Thriller"));
        q7_1.addAnswerOption(new AnswerOption(2, "Bad"));
        q7_1.addAnswerOption(new AnswerOption(3, "Dangerous"));
        q7_1.addAnswerOption(new AnswerOption(4, "Off the Wall"));
        questions.add(q7_1);

        Question q7_2 = new Question(26, 7, "Complete the lyric: 'Billie Jean is not my ___'", 2, "It’s about a romantic claim.");
        q7_2.addAnswerOption(new AnswerOption(1, "girl"));
        q7_2.addAnswerOption(new AnswerOption(2, "lover"));
        q7_2.addAnswerOption(new AnswerOption(3, "friend"));
        q7_2.addAnswerOption(new AnswerOption(4, "wife"));
        questions.add(q7_2);

        Question q7_3 = new Question(27, 7, "In which year was 'Billie Jean' released as a single?", 3, "Early 1980s.");
        q7_3.addAnswerOption(new AnswerOption(1, "1981"));
        q7_3.addAnswerOption(new AnswerOption(2, "1982"));
        q7_3.addAnswerOption(new AnswerOption(3, "1983"));
        q7_3.addAnswerOption(new AnswerOption(4, "1984"));
        questions.add(q7_3);

        Question q7_4 = new Question(28, 7, "What is the story of 'Billie Jean' mainly about?", 2, "Someone makes a serious claim about him.");
        q7_4.addAnswerOption(new AnswerOption(1, "A fun night at a club"));
        q7_4.addAnswerOption(new AnswerOption(2, "A woman claiming he is the father"));
        q7_4.addAnswerOption(new AnswerOption(3, "Winning a dance contest"));
        q7_4.addAnswerOption(new AnswerOption(4, "Going on a road trip"));
        questions.add(q7_4);

        // ========== SONG 8: Rolling in the Deep by Adele ==========
        Question q8_1 = new Question(29, 8, "Complete the lyric: 'We could have had it ___'", 2, "It means 'everything'.");
        q8_1.addAnswerOption(new AnswerOption(1, "forever"));
        q8_1.addAnswerOption(new AnswerOption(2, "all"));
        q8_1.addAnswerOption(new AnswerOption(3, "together"));
        q8_1.addAnswerOption(new AnswerOption(4, "right"));
        questions.add(q8_1);

        Question q8_2 = new Question(30, 8, "Which album is 'Rolling in the Deep' from?", 2, "It’s a number.");
        q8_2.addAnswerOption(new AnswerOption(1, "19"));
        q8_2.addAnswerOption(new AnswerOption(2, "21"));
        q8_2.addAnswerOption(new AnswerOption(3, "25"));
        q8_2.addAnswerOption(new AnswerOption(4, "30"));
        questions.add(q8_2);

        Question q8_3 = new Question(31, 8, "In which year was 'Rolling in the Deep' released?", 1, "Beginning of the 2010s.");
        q8_3.addAnswerOption(new AnswerOption(1, "2010"));
        q8_3.addAnswerOption(new AnswerOption(2, "2011"));
        q8_3.addAnswerOption(new AnswerOption(3, "2012"));
        q8_3.addAnswerOption(new AnswerOption(4, "2013"));
        questions.add(q8_3);

        Question q8_4 = new Question(32, 8, "What is the main theme of 'Rolling in the Deep'?", 3, "It’s a powerful breakup song.");
        q8_4.addAnswerOption(new AnswerOption(1, "Celebrating a new job"));
        q8_4.addAnswerOption(new AnswerOption(2, "A happy love story"));
        q8_4.addAnswerOption(new AnswerOption(3, "Anger and heartbreak after a breakup"));
        q8_4.addAnswerOption(new AnswerOption(4, "A vacation by the sea"));
        questions.add(q8_4);

        // ========== SONG 9: Wonderwall by Oasis ==========
        Question q9_1 = new Question(33, 9, "In which year was 'Wonderwall' released?", 3, "Mid-1990s.");
        q9_1.addAnswerOption(new AnswerOption(1, "1993"));
        q9_1.addAnswerOption(new AnswerOption(2, "1994"));
        q9_1.addAnswerOption(new AnswerOption(3, "1995"));
        q9_1.addAnswerOption(new AnswerOption(4, "1997"));
        questions.add(q9_1);

        Question q9_2 = new Question(34, 9, "Complete the lyric: 'You're gonna be the one that ___ me'", 2, "Think of rescue.");
        q9_2.addAnswerOption(new AnswerOption(1, "helps"));
        q9_2.addAnswerOption(new AnswerOption(2, "saves"));
        q9_2.addAnswerOption(new AnswerOption(3, "loves"));
        q9_2.addAnswerOption(new AnswerOption(4, "finds"));
        questions.add(q9_2);

        Question q9_3 = new Question(35, 9, "Which album includes 'Wonderwall'?", 2, "It starts with '(What's the Story)'.");
        q9_3.addAnswerOption(new AnswerOption(1, "Definitely Maybe"));
        q9_3.addAnswerOption(new AnswerOption(2, "(What's the Story) Morning Glory?"));
        q9_3.addAnswerOption(new AnswerOption(3, "Be Here Now"));
        q9_3.addAnswerOption(new AnswerOption(4, "Parklife"));
        questions.add(q9_3);

        Question q9_4 = new Question(36, 9, "Oasis is a band from which country?", 2, "Think: Manchester.");
        q9_4.addAnswerOption(new AnswerOption(1, "Ireland"));
        q9_4.addAnswerOption(new AnswerOption(2, "England"));
        q9_4.addAnswerOption(new AnswerOption(3, "USA"));
        q9_4.addAnswerOption(new AnswerOption(4, "Australia"));
        questions.add(q9_4);

        // ========== SONG 10: Mr. Brightside by The Killers ==========
        Question q10_1 = new Question(37, 10, "In which year was 'Mr. Brightside' originally released?", 3, "Early 2000s.");
        q10_1.addAnswerOption(new AnswerOption(1, "2001"));
        q10_1.addAnswerOption(new AnswerOption(2, "2002"));
        q10_1.addAnswerOption(new AnswerOption(3, "2003"));
        q10_1.addAnswerOption(new AnswerOption(4, "2005"));
        questions.add(q10_1);

        Question q10_2 = new Question(38, 10, "Complete the lyric: 'Coming out of my ___'", 3, "It’s something you can be trapped in.");
        q10_2.addAnswerOption(new AnswerOption(1, "car"));
        q10_2.addAnswerOption(new AnswerOption(2, "head"));
        q10_2.addAnswerOption(new AnswerOption(3, "cage"));
        q10_2.addAnswerOption(new AnswerOption(4, "room"));
        questions.add(q10_2);

        Question q10_3 = new Question(39, 10, "Which album includes 'Mr. Brightside'?", 2, "It sounds like a messy situation.");
        q10_3.addAnswerOption(new AnswerOption(1, "Sam's Town"));
        q10_3.addAnswerOption(new AnswerOption(2, "Hot Fuss"));
        q10_3.addAnswerOption(new AnswerOption(3, "Day & Age"));
        q10_3.addAnswerOption(new AnswerOption(4, "Imploding the Mirage"));
        questions.add(q10_3);

        Question q10_4 = new Question(40, 10, "What is 'Mr. Brightside' mainly about?", 2, "Think: jealousy and overthinking.");
        q10_4.addAnswerOption(new AnswerOption(1, "Winning a sports match"));
        q10_4.addAnswerOption(new AnswerOption(2, "Jealousy and paranoia in a relationship"));
        q10_4.addAnswerOption(new AnswerOption(3, "Moving to a new city"));
        q10_4.addAnswerOption(new AnswerOption(4, "Planning a surprise party"));
        questions.add(q10_4);
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
