package at.ac.hcw.kqm.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.ac.hcw.kqm.model.AnswerOption;
import at.ac.hcw.kqm.model.Question;

/**
 * In-memory implementation of QuestionRepository.
 * Stores questions that are specifically related to karaoke songs.
 *
 * WORKFLOW:
 * 1. Player chooses a song from the song list
 * 2. System retrieves questions for that specific song using
 * getQuestionsBySongId()
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
        // ========== SONG 1: Simarik by Tarkan ==========
        Question q1_1 = new Question(1, 1, "Aus welchem Land stammt der Künstler Tarkan?", 3,
                "Ein Künstler aus dem östlichen Mittelmeer");
        q1_1.addAnswerOption(new AnswerOption(1, "Syrien"));
        q1_1.addAnswerOption(new AnswerOption(2, "Griechenland"));
        q1_1.addAnswerOption(new AnswerOption(3, "Türkei"));
        q1_1.addAnswerOption(new AnswerOption(4, "Ägypten"));
        questions.add(q1_1);

        Question q1_2 = new Question(2, 1, "Vervollständige den Text aus 'Simarik': 'Simarik, ___'", 1,
                "Das ist der Refrain des Songs");
        q1_2.addAnswerOption(new AnswerOption(1, "simarik simarik"));
        q1_2.addAnswerOption(new AnswerOption(2, "seni seviyorum"));
        q1_2.addAnswerOption(new AnswerOption(3, "gülüm benim"));
        q1_2.addAnswerOption(new AnswerOption(4, "hoşlanıyorum"));
        questions.add(q1_2);

        Question q1_3 = new Question(3, 1, "In welchem Jahrzehnt wurde 'Simarik' ein internationaler Hit?", 2,
                "Es war im späten 20. Jahrhundert");
        q1_3.addAnswerOption(new AnswerOption(1, "1980er"));
        q1_3.addAnswerOption(new AnswerOption(2, "1990er"));
        q1_3.addAnswerOption(new AnswerOption(3, "2000er"));
        q1_3.addAnswerOption(new AnswerOption(4, "2010er"));
        questions.add(q1_3);

        Question q1_4 = new Question(31, 1, "Was ist das Hauptthema von 'Simarik'?", 1,
                "Es geht um Zuneigung");
        q1_4.addAnswerOption(new AnswerOption(1, "Liebe"));
        q1_4.addAnswerOption(new AnswerOption(2, "Freundschaft"));
        q1_4.addAnswerOption(new AnswerOption(3, "Abenteuer"));
        q1_4.addAnswerOption(new AnswerOption(4, "Reisen"));
        questions.add(q1_4);

        Question q1_5 = new Question(32, 1, "Welcher Musikstil dominiert 'Simarik'?", 3,
                "Ein lateinamerikanischer Rhythmus");
        q1_5.addAnswerOption(new AnswerOption(1, "Reggae"));
        q1_5.addAnswerOption(new AnswerOption(2, "Jazz"));
        q1_5.addAnswerOption(new AnswerOption(3, "Samba"));
        q1_5.addAnswerOption(new AnswerOption(4, "Flamenco"));
        questions.add(q1_5);

        // ========== SONG 2: It's My Life by Bon Jovi ==========
        Question q2_1 = new Question(4, 2, "In welchem Jahr wurde 'It's My Life' veröffentlicht?", 1,
                "Ein großer Hit Anfang der 2000er");
        q2_1.addAnswerOption(new AnswerOption(1, "2000"));
        q2_1.addAnswerOption(new AnswerOption(2, "2001"));
        q2_1.addAnswerOption(new AnswerOption(3, "2002"));
        q2_1.addAnswerOption(new AnswerOption(4, "2003"));
        questions.add(q2_1);

        Question q2_2 = new Question(5, 2, "Vervollständige: 'It's my life, it's ___ or never'", 1,
                "Es geht um eine Chance");
        q2_2.addAnswerOption(new AnswerOption(1, "now"));
        q2_2.addAnswerOption(new AnswerOption(2, "then"));
        q2_2.addAnswerOption(new AnswerOption(3, "today"));
        q2_2.addAnswerOption(new AnswerOption(4, "tomorrow"));
        questions.add(q2_2);

        Question q2_3 = new Question(6, 2, "Welches Album enthält 'It's My Life'?", 3,
                "Eine Romanzahl");
        q2_3.addAnswerOption(new AnswerOption(1, "Slippery When Wet"));
        q2_3.addAnswerOption(new AnswerOption(2, "New Jersey"));
        q2_3.addAnswerOption(new AnswerOption(3, "Bounce"));
        q2_3.addAnswerOption(new AnswerOption(4, "Lost Highway"));
        questions.add(q2_3);

        Question q2_4 = new Question(33, 2, "Aus welchem Land stammt Bon Jovi?", 4,
                "Das Land von Hollywood und New York");
        q2_4.addAnswerOption(new AnswerOption(1, "Kanada"));
        q2_4.addAnswerOption(new AnswerOption(2, "Großbritannien"));
        q2_4.addAnswerOption(new AnswerOption(3, "Australien"));
        q2_4.addAnswerOption(new AnswerOption(4, "USA"));
        questions.add(q2_4);

        Question q2_5 = new Question(34, 2, "Was ist die Hauptbotschaft von 'It's My Life'?", 1,
                "Eine Hymne der Freiheit");
        q2_5.addAnswerOption(new AnswerOption(1, "Persönliche Freiheit und Entschlossenheit"));
        q2_5.addAnswerOption(new AnswerOption(2, "Traurigkeit"));
        q2_5.addAnswerOption(new AnswerOption(3, "Eifersucht"));
        q2_5.addAnswerOption(new AnswerOption(4, "Einsamkeit"));
        questions.add(q2_5);

        // ========== SONG 3: Hit Me Baby One More Time by Britney Spears ==========
        Question q3_1 = new Question(7, 3, "In welchem Jahr wurde 'Hit Me Baby One More Time' veröffentlicht?", 2,
                "Es war Ende der 1990er");
        q3_1.addAnswerOption(new AnswerOption(1, "1997"));
        q3_1.addAnswerOption(new AnswerOption(2, "1998"));
        q3_1.addAnswerOption(new AnswerOption(3, "1999"));
        q3_1.addAnswerOption(new AnswerOption(4, "2000"));
        questions.add(q3_1);

        Question q3_2 = new Question(8, 3, "Vervollständige: 'Hit me baby one more ___'", 1,
                "Es ist im Songtitel");
        q3_2.addAnswerOption(new AnswerOption(1, "time"));
        q3_2.addAnswerOption(new AnswerOption(2, "day"));
        q3_2.addAnswerOption(new AnswerOption(3, "night"));
        q3_2.addAnswerOption(new AnswerOption(4, "way"));
        questions.add(q3_2);

        Question q3_3 = new Question(9, 3, "Welches Album enthält 'Hit Me Baby One More Time'?", 1,
                "Es ist Britney's Debütalbum");
        q3_3.addAnswerOption(new AnswerOption(1, "...Baby One More Time"));
        q3_3.addAnswerOption(new AnswerOption(2, "Oops!... I Did It Again"));
        q3_3.addAnswerOption(new AnswerOption(3, "Toxic"));
        q3_3.addAnswerOption(new AnswerOption(4, "Britney Jean"));
        questions.add(q3_3);

        Question q3_4 = new Question(35, 3, "Aus welchem Land stammt Britney Spears?", 4,
                "Das Land mit dem größten Einfluss auf Pop-Musik");
        q3_4.addAnswerOption(new AnswerOption(1, "Kanada"));
        q3_4.addAnswerOption(new AnswerOption(2, "Australien"));
        q3_4.addAnswerOption(new AnswerOption(3, "Großbritannien"));
        q3_4.addAnswerOption(new AnswerOption(4, "USA"));
        questions.add(q3_4);

        Question q3_5 = new Question(36, 3, "Welches ikonische Outfit trug Britney im Musikvideo?", 2,
                "Es war ein Schuluniform-Look");
        q3_5.addAnswerOption(new AnswerOption(1, "Rotes Kleid"));
        q3_5.addAnswerOption(new AnswerOption(2, "Schuluniform mit Zöpfen"));
        q3_5.addAnswerOption(new AnswerOption(3, "Jeans und T-Shirt"));
        q3_5.addAnswerOption(new AnswerOption(4, "Schwarzer Anzug"));
        questions.add(q3_5);

        // ========== SONG 4: Rolling in the Deep by Adele ==========
        Question q4_1 = new Question(10, 4, "Vervollständige: 'We could have had it ___'", 2,
                "Es geht darum, was hätte sein können");
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

        Question q4_3 = new Question(12, 4, "In welchem Jahr wurde 'Rolling in the Deep' veröffentlicht?", 1,
                "Anfang der 2010er");
        q4_3.addAnswerOption(new AnswerOption(1, "2010"));
        q4_3.addAnswerOption(new AnswerOption(2, "2011"));
        q4_3.addAnswerOption(new AnswerOption(3, "2012"));
        q4_3.addAnswerOption(new AnswerOption(4, "2013"));
        questions.add(q4_3);

        Question q4_4 = new Question(37, 4, "Wer produzierte 'Rolling in the Deep'?", 2,
                "Er arbeitete häufig mit Adele");
        q4_4.addAnswerOption(new AnswerOption(1, "Mark Ronson"));
        q4_4.addAnswerOption(new AnswerOption(2, "Paul Epworth"));
        q4_4.addAnswerOption(new AnswerOption(3, "Greg Kurstin"));
        q4_4.addAnswerOption(new AnswerOption(4, "Finneas"));
        questions.add(q4_4);

        Question q4_5 = new Question(38, 4, "Welche Auszeichnung erhielt der Song?", 1, "Eine der wichtigsten Grammys");
        q4_5.addAnswerOption(new AnswerOption(1, "Grammy: Record of the Year"));
        q4_5.addAnswerOption(new AnswerOption(2, "Oscar"));
        q4_5.addAnswerOption(new AnswerOption(3, "Golden Globe"));
        q4_5.addAnswerOption(new AnswerOption(4, "BAFTA"));
        questions.add(q4_5);

        // ========== SONG 5: Blinding Lights by The Weeknd ==========
        Question q5_1 = new Question(13, 5, "Welches Genre beschreibt 'Blinding Lights' am besten?", 4,
                "Es hat einen Retro-80er-Sound");
        q5_1.addAnswerOption(new AnswerOption(1, "Rock"));
        q5_1.addAnswerOption(new AnswerOption(2, "Hip Hop"));
        q5_1.addAnswerOption(new AnswerOption(3, "Country"));
        q5_1.addAnswerOption(new AnswerOption(4, "Synthwave/Pop"));
        questions.add(q5_1);

        Question q5_2 = new Question(14, 5, "Vervollständige: 'I've been tryna call, I've been on my own for ___'", 3,
                "Es geht um Zeit");
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

        Question q5_4 = new Question(39, 5, "Welcher Stil prägt 'Blinding Lights'?", 4, "Retro-Synths der 80er");
        q5_4.addAnswerOption(new AnswerOption(1, "Blues"));
        q5_4.addAnswerOption(new AnswerOption(2, "Folk"));
        q5_4.addAnswerOption(new AnswerOption(3, "Reggae"));
        q5_4.addAnswerOption(new AnswerOption(4, "80er Synthwave"));
        questions.add(q5_4);

        Question q5_5 = new Question(40, 5, "Wer war Co-Produzent des Songs?", 1, "Hit-Produzent vieler Pop-Songs");
        q5_5.addAnswerOption(new AnswerOption(1, "Max Martin"));
        q5_5.addAnswerOption(new AnswerOption(2, "Rick Rubin"));
        q5_5.addAnswerOption(new AnswerOption(3, "Brian Eno"));
        q5_5.addAnswerOption(new AnswerOption(4, "Calvin Harris"));
        questions.add(q5_5);

        // ========== SONG 6: Imagine by John Lennon ==========
        Question q6_1 = new Question(16, 6, "Wer schrieb und sang 'Imagine'?", 3, "Er war Mitglied der Beatles");
        q6_1.addAnswerOption(new AnswerOption(1, "Paul McCartney"));
        q6_1.addAnswerOption(new AnswerOption(2, "George Harrison"));
        q6_1.addAnswerOption(new AnswerOption(3, "John Lennon"));
        q6_1.addAnswerOption(new AnswerOption(4, "Ringo Starr"));
        questions.add(q6_1);

        Question q6_2 = new Question(17, 6, "Vervollständige: 'Imagine there's no ___'", 1,
                "Der Song spricht von Frieden");
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

        Question q6_4 = new Question(41, 6, "Welches Instrument dominiert 'Imagine'?", 3,
                "Sehr melodisch und harmonisch");
        q6_4.addAnswerOption(new AnswerOption(1, "Gitarre"));
        q6_4.addAnswerOption(new AnswerOption(2, "Schlagzeug"));
        q6_4.addAnswerOption(new AnswerOption(3, "Klavier"));
        q6_4.addAnswerOption(new AnswerOption(4, "Bass"));
        questions.add(q6_4);

        Question q6_5 = new Question(42, 6, "Worum geht es thematisch in 'Imagine'?", 1,
                "Eine friedliche Welt ohne Grenzen");
        q6_5.addAnswerOption(new AnswerOption(1, "Frieden"));
        q6_5.addAnswerOption(new AnswerOption(2, "Krieg"));
        q6_5.addAnswerOption(new AnswerOption(3, "Partys"));
        q6_5.addAnswerOption(new AnswerOption(4, "Technologie"));
        questions.add(q6_5);

        // ========== SONG 7: Someone Like You by Adele ==========
        Question q7_1 = new Question(19, 7, "Welches Adele-Album enthält 'Someone Like You'?", 2, "Es ist eine Zahl");
        q7_1.addAnswerOption(new AnswerOption(1, "19"));
        q7_1.addAnswerOption(new AnswerOption(2, "21"));
        q7_1.addAnswerOption(new AnswerOption(3, "25"));
        q7_1.addAnswerOption(new AnswerOption(4, "30"));
        questions.add(q7_1);

        Question q7_2 = new Question(20, 7, "Vervollständige: 'Never mind, I'll find someone like ___'", 1,
                "Es ist im Titel");
        q7_2.addAnswerOption(new AnswerOption(1, "you"));
        q7_2.addAnswerOption(new AnswerOption(2, "me"));
        q7_2.addAnswerOption(new AnswerOption(3, "them"));
        q7_2.addAnswerOption(new AnswerOption(4, "us"));
        questions.add(q7_2);

        Question q7_3 = new Question(21, 7, "In welchem Jahr wurde 'Someone Like You' veröffentlicht?", 2,
                "Anfang der 2010er");
        q7_3.addAnswerOption(new AnswerOption(1, "2010"));
        q7_3.addAnswerOption(new AnswerOption(2, "2011"));
        q7_3.addAnswerOption(new AnswerOption(3, "2012"));
        q7_3.addAnswerOption(new AnswerOption(4, "2013"));
        questions.add(q7_3);

        Question q7_4 = new Question(43, 7, "Welcher Stil beschreibt den Song am besten?", 1, "Sanft und emotional");
        q7_4.addAnswerOption(new AnswerOption(1, "Klavier-Ballade"));
        q7_4.addAnswerOption(new AnswerOption(2, "Hard Rock"));
        q7_4.addAnswerOption(new AnswerOption(3, "EDM"));
        q7_4.addAnswerOption(new AnswerOption(4, "Hip Hop"));
        questions.add(q7_4);

        Question q7_5 = new Question(44, 7, "Wer schrieb den Song mit Adele?", 3, "Er war Mitglied von Semisonic");
        q7_5.addAnswerOption(new AnswerOption(1, "Greg Kurstin"));
        q7_5.addAnswerOption(new AnswerOption(2, "Finneas"));
        q7_5.addAnswerOption(new AnswerOption(3, "Dan Wilson"));
        q7_5.addAnswerOption(new AnswerOption(4, "Jack Antonoff"));
        questions.add(q7_5);

        // ========== SONG 8: Hotel California by Eagles ==========
        Question q8_1 = new Question(22, 8, "Was ist die berühmte Eröffnungszeile von 'Hotel California'?", 1,
                "Es beginnt mit einer Straße");
        q8_1.addAnswerOption(new AnswerOption(1, "On a dark desert highway"));
        q8_1.addAnswerOption(new AnswerOption(2, "Welcome to the Hotel California"));
        q8_1.addAnswerOption(new AnswerOption(3, "Last thing I remember"));
        q8_1.addAnswerOption(new AnswerOption(4, "Mirrors on the ceiling"));
        questions.add(q8_1);

        Question q8_2 = new Question(23, 8, "In welchem Jahr wurde 'Hotel California' veröffentlicht?", 3,
                "Mitte der 1970er");
        q8_2.addAnswerOption(new AnswerOption(1, "1975"));
        q8_2.addAnswerOption(new AnswerOption(2, "1976"));
        q8_2.addAnswerOption(new AnswerOption(3, "1977"));
        q8_2.addAnswerOption(new AnswerOption(4, "1978"));
        questions.add(q8_2);

        Question q8_3 = new Question(24, 8,
                "Vervollständige: 'You can check out any time you like, but you can never ___'", 2,
                "Es geht ums Verlassen");
        q8_3.addAnswerOption(new AnswerOption(1, "return"));
        q8_3.addAnswerOption(new AnswerOption(2, "leave"));
        q8_3.addAnswerOption(new AnswerOption(3, "escape"));
        q8_3.addAnswerOption(new AnswerOption(4, "go"));
        questions.add(q8_3);

        Question q8_4 = new Question(45, 8, "Was zeichnet das Gitarrensolo am Ende aus?", 4,
                "Zwei Gitarren im Gleichklang");
        q8_4.addAnswerOption(new AnswerOption(1, "Akustik-Only"));
        q8_4.addAnswerOption(new AnswerOption(2, "Slide-Gitarre"));
        q8_4.addAnswerOption(new AnswerOption(3, "Bass-Solo"));
        q8_4.addAnswerOption(new AnswerOption(4, "Dual-Gitarren-Harmonie"));
        questions.add(q8_4);

        Question q8_5 = new Question(46, 8, "Auf welchem Album ist der Song?", 1, "Album mit gleichem Titel");
        q8_5.addAnswerOption(new AnswerOption(1, "Hotel California"));
        q8_5.addAnswerOption(new AnswerOption(2, "Desperado"));
        q8_5.addAnswerOption(new AnswerOption(3, "The Long Run"));
        q8_5.addAnswerOption(new AnswerOption(4, "Eagles"));
        questions.add(q8_5);

        // ========== SONG 9: Don't Stop Believin' by Journey ==========
        Question q9_1 = new Question(25, 9, "Von welcher Band stammt 'Don't Stop Believin''?", 2,
                "Sie sind eine amerikanische Rockband");
        q9_1.addAnswerOption(new AnswerOption(1, "Boston"));
        q9_1.addAnswerOption(new AnswerOption(2, "Journey"));
        q9_1.addAnswerOption(new AnswerOption(3, "Foreigner"));
        q9_1.addAnswerOption(new AnswerOption(4, "Styx"));
        questions.add(q9_1);

        Question q9_2 = new Question(26, 9, "Vervollständige: 'Just a small town girl, living in a ___ world'", 1,
                "Es reimt sich auf 'girl'");
        q9_2.addAnswerOption(new AnswerOption(1, "lonely"));
        q9_2.addAnswerOption(new AnswerOption(2, "crazy"));
        q9_2.addAnswerOption(new AnswerOption(3, "busy"));
        q9_2.addAnswerOption(new AnswerOption(4, "pretty"));
        questions.add(q9_2);

        Question q9_3 = new Question(27, 9, "In welchem Jahr wurde 'Don't Stop Believin'' veröffentlicht?", 3,
                "Anfang der 1980er");
        q9_3.addAnswerOption(new AnswerOption(1, "1979"));
        q9_3.addAnswerOption(new AnswerOption(2, "1980"));
        q9_3.addAnswerOption(new AnswerOption(3, "1981"));
        q9_3.addAnswerOption(new AnswerOption(4, "1982"));
        questions.add(q9_3);

        Question q9_4 = new Question(47, 9, "Welches Instrument eröffnet den Song?", 1, "Sehr melodisch gespielt");
        q9_4.addAnswerOption(new AnswerOption(1, "Klavier"));
        q9_4.addAnswerOption(new AnswerOption(2, "Schlagzeug"));
        q9_4.addAnswerOption(new AnswerOption(3, "Gitarre"));
        q9_4.addAnswerOption(new AnswerOption(4, "Bass"));
        questions.add(q9_4);

        Question q9_5 = new Question(48, 9, "Welche Stadt wird im Song erwähnt?", 2,
                "Große Industriestadt im Mittleren Westen");
        q9_5.addAnswerOption(new AnswerOption(1, "Atlanta"));
        q9_5.addAnswerOption(new AnswerOption(2, "Detroit"));
        q9_5.addAnswerOption(new AnswerOption(3, "Seattle"));
        q9_5.addAnswerOption(new AnswerOption(4, "Miami"));
        questions.add(q9_5);

        // ========== SONG 10: Sweet Child O' Mine by Guns N' Roses ==========
        Question q10_1 = new Question(28, 10, "Wer ist der Leadsänger von Guns N' Roses?", 1,
                "Er ist bekannt für seine hohe Stimme");
        q10_1.addAnswerOption(new AnswerOption(1, "Axl Rose"));
        q10_1.addAnswerOption(new AnswerOption(2, "Slash"));
        q10_1.addAnswerOption(new AnswerOption(3, "Duff McKagan"));
        q10_1.addAnswerOption(new AnswerOption(4, "Izzy Stradlin"));
        questions.add(q10_1);

        Question q10_2 = new Question(29, 10, "Vervollständige: 'Sweet child o' mine, where do we go ___?'", 3,
                "Es fragt nach einer Richtung");
        q10_2.addAnswerOption(new AnswerOption(1, "today"));
        q10_2.addAnswerOption(new AnswerOption(2, "tonight"));
        q10_2.addAnswerOption(new AnswerOption(3, "now"));
        q10_2.addAnswerOption(new AnswerOption(4, "away"));
        questions.add(q10_2);

        Question q10_3 = new Question(30, 10, "Welches Album enthält 'Sweet Child O' Mine'?", 1,
                "Es ist ihr Debütalbum");
        q10_3.addAnswerOption(new AnswerOption(1, "Appetite for Destruction"));
        q10_3.addAnswerOption(new AnswerOption(2, "Use Your Illusion I"));
        q10_3.addAnswerOption(new AnswerOption(3, "Use Your Illusion II"));
        q10_3.addAnswerOption(new AnswerOption(4, "The Spaghetti Incident?"));
        questions.add(q10_3);

        Question q10_4 = new Question(49, 10, "Wer schrieb das ikonische Intro-Riff?", 2, "Lead-Gitarrist der Band");
        q10_4.addAnswerOption(new AnswerOption(1, "Axl Rose"));
        q10_4.addAnswerOption(new AnswerOption(2, "Slash"));
        q10_4.addAnswerOption(new AnswerOption(3, "Izzy Stradlin"));
        q10_4.addAnswerOption(new AnswerOption(4, "Duff McKagan"));
        questions.add(q10_4);

        Question q10_5 = new Question(50, 10, "In welchem Jahr erschien die Single?", 1, "Ende der 1980er");
        q10_5.addAnswerOption(new AnswerOption(1, "1987"));
        q10_5.addAnswerOption(new AnswerOption(2, "1988"));
        q10_5.addAnswerOption(new AnswerOption(3, "1989"));
        q10_5.addAnswerOption(new AnswerOption(4, "1990"));
        questions.add(q10_5);
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