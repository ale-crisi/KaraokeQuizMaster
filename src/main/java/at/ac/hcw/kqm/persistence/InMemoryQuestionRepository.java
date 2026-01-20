package at.ac.hcw.kqm.persistence;

import at.ac.hcw.kqm.model.AnswerOption;
import at.ac.hcw.kqm.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Fragen werden im Speicher gehalten (keine Datei/Datenbank).
 * Pro Song gibt es 5 Multiple-Choice-Fragen.
 */
public class InMemoryQuestionRepository implements QuestionRepository {

    private final List<Question> questions = new ArrayList<>();

    public InMemoryQuestionRepository() {
        initializeQuestions();
    }

    private void initializeQuestions() {

        // Song 1: Simarik – Tarkan (songId=1)
        Question q1_1 = new Question(1, 1, "Aus welchem Land stammt der Künstler Tarkan?", 3,
                "Tarkan ist ein sehr bekannter Star aus Istanbul.");
        q1_1.addAnswerOption(new AnswerOption(1, "Syrien"));
        q1_1.addAnswerOption(new AnswerOption(2, "Griechenland"));
        q1_1.addAnswerOption(new AnswerOption(3, "Türkei"));
        q1_1.addAnswerOption(new AnswerOption(4, "Ägypten"));
        questions.add(q1_1);

        Question q1_2 = new Question(2, 1, "In welchem Jahrzehnt wurde „Simarik“ international bekannt?", 2,
                "Der große internationale Durchbruch war Ende der 1990er.");
        q1_2.addAnswerOption(new AnswerOption(1, "1980er"));
        q1_2.addAnswerOption(new AnswerOption(2, "1990er"));
        q1_2.addAnswerOption(new AnswerOption(3, "2000er"));
        q1_2.addAnswerOption(new AnswerOption(4, "2010er"));
        questions.add(q1_2);

        Question q1_3 = new Question(3, 1, "Welche Musikrichtung passt am besten zu „Simarik“?", 1,
                "Der Song ist ein typischer türkischer Pop-Hit.");
        q1_3.addAnswerOption(new AnswerOption(1, "Turkish Pop"));
        q1_3.addAnswerOption(new AnswerOption(2, "Heavy Metal"));
        q1_3.addAnswerOption(new AnswerOption(3, "Klassik"));
        q1_3.addAnswerOption(new AnswerOption(4, "Techno"));
        questions.add(q1_3);

        Question q1_4 = new Question(31, 1, "Worum geht es thematisch in „Simarik“ am ehesten?", 1,
                "Der Song ist romantisch und verspielt, nicht politisch.");
        q1_4.addAnswerOption(new AnswerOption(1, "Liebe/Flirt"));
        q1_4.addAnswerOption(new AnswerOption(2, "Politik"));
        q1_4.addAnswerOption(new AnswerOption(3, "Sport"));
        q1_4.addAnswerOption(new AnswerOption(4, "Wissenschaft"));
        questions.add(q1_4);

        Question q1_5 = new Question(32, 1, "Welche Eigenschaft macht „Simarik“ besonders karaoke-tauglich?", 4,
                "Karaoke klappt gut, wenn viele Leute den Refrain schnell mitsingen können.");
        q1_5.addAnswerOption(new AnswerOption(1, "Sehr lange Instrumentalpassagen"));
        q1_5.addAnswerOption(new AnswerOption(2, "Extrem komplizierte Melodie"));
        q1_5.addAnswerOption(new AnswerOption(3, "Keine Wiederholungen"));
        q1_5.addAnswerOption(new AnswerOption(4, "Eingängiger Refrain"));
        questions.add(q1_5);


        // Song 2: It's My Life – Bon Jovi (songId=2)
        Question q2_1 = new Question(4, 2, "In welchem Jahr wurde „It's My Life“ veröffentlicht?", 1,
                "Der Song erschien im Jahr des sogenannten Millennium-Wechsels.");
        q2_1.addAnswerOption(new AnswerOption(1, "2000"));
        q2_1.addAnswerOption(new AnswerOption(2, "2001"));
        q2_1.addAnswerOption(new AnswerOption(3, "2002"));
        q2_1.addAnswerOption(new AnswerOption(4, "2003"));
        questions.add(q2_1);

        Question q2_2 = new Question(5, 2, "Aus welchem Land stammt die Band Bon Jovi?", 4,
                "Die Band kommt aus New Jersey.");
        q2_2.addAnswerOption(new AnswerOption(1, "Kanada"));
        q2_2.addAnswerOption(new AnswerOption(2, "Großbritannien"));
        q2_2.addAnswerOption(new AnswerOption(3, "Australien"));
        q2_2.addAnswerOption(new AnswerOption(4, "USA"));
        questions.add(q2_2);

        Question q2_3 = new Question(6, 2, "Welche Aussage passt am besten zur Botschaft von „It's My Life“?", 1,
                "Es geht darum, sein Leben selbst in die Hand zu nehmen.");
        q2_3.addAnswerOption(new AnswerOption(1, "Selbstbestimmung und Durchhalten"));
        q2_3.addAnswerOption(new AnswerOption(2, "Nur Party ohne Aussage"));
        q2_3.addAnswerOption(new AnswerOption(3, "Politischer Protest"));
        q2_3.addAnswerOption(new AnswerOption(4, "Schlaflied"));
        questions.add(q2_3);

        Question q2_4 = new Question(33, 2, "Welches Genre passt am besten zu Bon Jovi und „It's My Life“?", 2,
                "Bon Jovi ist eine klassische Rockband.");
        q2_4.addAnswerOption(new AnswerOption(1, "Jazz"));
        q2_4.addAnswerOption(new AnswerOption(2, "Rock"));
        q2_4.addAnswerOption(new AnswerOption(3, "Klassik"));
        q2_4.addAnswerOption(new AnswerOption(4, "Reggae"));
        questions.add(q2_4);

        Question q2_5 = new Question(34, 2, "Warum eignet sich „It's My Life“ gut als Karaoke-Song?", 3,
                "Ein klarer Refrain hilft, wenn viele mitsingen.");
        q2_5.addAnswerOption(new AnswerOption(1, "Fast nur Instrumental"));
        q2_5.addAnswerOption(new AnswerOption(2, "Sehr unregelmäßiges Tempo"));
        q2_5.addAnswerOption(new AnswerOption(3, "Klarer Refrain und Mitsing-Stellen"));
        q2_5.addAnswerOption(new AnswerOption(4, "Keine Wiederholungen"));
        questions.add(q2_5);


        // Song 3: ...Baby One More Time – Britney Spears (songId=3)
        Question q3_1 = new Question(7, 3, "In welchem Jahr wurde „...Baby One More Time“ veröffentlicht?", 2,
                "Britneys Durchbruch-Single war Ende der 90er – genau 1998.");
        q3_1.addAnswerOption(new AnswerOption(1, "1997"));
        q3_1.addAnswerOption(new AnswerOption(2, "1998"));
        q3_1.addAnswerOption(new AnswerOption(3, "1999"));
        q3_1.addAnswerOption(new AnswerOption(4, "2000"));
        questions.add(q3_1);

        Question q3_2 = new Question(8, 3, "Aus welchem Land stammt Britney Spears?", 4,
                "Sie kommt aus Louisiana (Bundesstaat in den USA).");
        q3_2.addAnswerOption(new AnswerOption(1, "Kanada"));
        q3_2.addAnswerOption(new AnswerOption(2, "Australien"));
        q3_2.addAnswerOption(new AnswerOption(3, "Großbritannien"));
        q3_2.addAnswerOption(new AnswerOption(4, "USA"));
        questions.add(q3_2);

        Question q3_3 = new Question(9, 3, "Worum geht es im Song „...Baby One More Time“ am ehesten?", 1,
                "Es geht um Sehnsucht und den Wunsch, wieder Kontakt zu haben.");
        q3_3.addAnswerOption(new AnswerOption(1, "Sehnsucht nach einer Beziehung"));
        q3_3.addAnswerOption(new AnswerOption(2, "Reisebericht"));
        q3_3.addAnswerOption(new AnswerOption(3, "Technologie und Zukunft"));
        q3_3.addAnswerOption(new AnswerOption(4, "Sport und Wettkampf"));
        questions.add(q3_3);

        Question q3_4 = new Question(35, 3, "Welches Genre passt am besten zu „...Baby One More Time“?", 2,
                "Das ist ein typischer Pop-Hit der späten 90er.");
        q3_4.addAnswerOption(new AnswerOption(1, "Country"));
        q3_4.addAnswerOption(new AnswerOption(2, "Pop"));
        q3_4.addAnswerOption(new AnswerOption(3, "Metal"));
        q3_4.addAnswerOption(new AnswerOption(4, "Reggae"));
        questions.add(q3_4);

        Question q3_5 = new Question(36, 3, "Was machte das Musikvideo besonders bekannt?", 3,
                "Das Video spielt in einer Schule und hat einen ikonischen Outfit-Look.");
        q3_5.addAnswerOption(new AnswerOption(1, "Unterwasser-Szenen"));
        q3_5.addAnswerOption(new AnswerOption(2, "Animationsfilm-Stil"));
        q3_5.addAnswerOption(new AnswerOption(3, "Schulsetting/Uniform-Look"));
        q3_5.addAnswerOption(new AnswerOption(4, "Western-Setting"));
        questions.add(q3_5);


        // Song 4: Rolling in the Deep – Adele (songId=4)
        Question q4_1 = new Question(10, 4, "Von welchem Album stammt „Rolling in the Deep“?", 2,
                "Das Album heißt wie eine Zahl: 21.");
        q4_1.addAnswerOption(new AnswerOption(1, "19"));
        q4_1.addAnswerOption(new AnswerOption(2, "21"));
        q4_1.addAnswerOption(new AnswerOption(3, "25"));
        q4_1.addAnswerOption(new AnswerOption(4, "30"));
        questions.add(q4_1);

        Question q4_2 = new Question(11, 4, "In welchem Jahr wurde „Rolling in the Deep“ veröffentlicht?", 1,
                "Die Single kam Ende 2010 heraus (Album 21 wurde 2011 riesig).");
        q4_2.addAnswerOption(new AnswerOption(1, "2010"));
        q4_2.addAnswerOption(new AnswerOption(2, "2011"));
        q4_2.addAnswerOption(new AnswerOption(3, "2012"));
        q4_2.addAnswerOption(new AnswerOption(4, "2013"));
        questions.add(q4_2);

        Question q4_3 = new Question(12, 4, "Welche Stimmung passt am besten zu „Rolling in the Deep“?", 4,
                "Der Song wirkt kraftvoll, nicht fröhlich oder neutral.");
        q4_3.addAnswerOption(new AnswerOption(1, "Fröhlich und verspielt"));
        q4_3.addAnswerOption(new AnswerOption(2, "Komplett neutral"));
        q4_3.addAnswerOption(new AnswerOption(3, "Humorvoll"));
        q4_3.addAnswerOption(new AnswerOption(4, "Enttäuscht und kraftvoll"));
        questions.add(q4_3);

        Question q4_4 = new Question(37, 4, "Welche Musikrichtung passt am ehesten zu Adele und diesem Song?", 3,
                "Adele ist bekannt für Pop mit Soul-Einflüssen.");
        q4_4.addAnswerOption(new AnswerOption(1, "Hardcore-Techno"));
        q4_4.addAnswerOption(new AnswerOption(2, "Reggae"));
        q4_4.addAnswerOption(new AnswerOption(3, "Pop/Soul"));
        q4_4.addAnswerOption(new AnswerOption(4, "Klassik"));
        questions.add(q4_4);

        Question q4_5 = new Question(38, 4, "Wodurch wirkt der Refrain besonders stark?", 2,
                "Der Song lebt von Adeles Stimme und Dynamik, nicht von Instrumentalpassagen.");
        q4_5.addAnswerOption(new AnswerOption(1, "Nur Flüstergesang"));
        q4_5.addAnswerOption(new AnswerOption(2, "Kraftvoller Gesang und starke Dynamik"));
        q4_5.addAnswerOption(new AnswerOption(3, "Sehr langsames Tempo ohne Höhepunkt"));
        q4_5.addAnswerOption(new AnswerOption(4, "Fast nur Instrumental"));
        questions.add(q4_5);


        // Song 5: Blinding Lights – The Weeknd (songId=5)
        Question q5_1 = new Question(13, 5, "Welches Genre beschreibt „Blinding Lights“ am besten?", 4,
                "Der Song klingt nach Retro-Synthesizern und Pop.");
        q5_1.addAnswerOption(new AnswerOption(1, "Rock"));
        q5_1.addAnswerOption(new AnswerOption(2, "Hip Hop"));
        q5_1.addAnswerOption(new AnswerOption(3, "Country"));
        q5_1.addAnswerOption(new AnswerOption(4, "Synthwave/Pop"));
        questions.add(q5_1);

        Question q5_2 = new Question(14, 5, "Welches Jahrzehnt beeinflusst den Sound am stärksten?", 2,
                "Neon-Ära: Synthesizer-Sound wie in den 80ern.");
        q5_2.addAnswerOption(new AnswerOption(1, "1960er"));
        q5_2.addAnswerOption(new AnswerOption(2, "1980er"));
        q5_2.addAnswerOption(new AnswerOption(3, "2000er"));
        q5_2.addAnswerOption(new AnswerOption(4, "2020er"));
        questions.add(q5_2);

        Question q5_3 = new Question(15, 5, "Welches Album enthält „Blinding Lights“?", 2,
                "Das Album heißt wie eine Tageszeit nach Mitternacht: After Hours.");
        q5_3.addAnswerOption(new AnswerOption(1, "Starboy"));
        q5_3.addAnswerOption(new AnswerOption(2, "After Hours"));
        q5_3.addAnswerOption(new AnswerOption(3, "Beauty Behind the Madness"));
        q5_3.addAnswerOption(new AnswerOption(4, "Dawn FM"));
        questions.add(q5_3);

        Question q5_4 = new Question(39, 5, "Welche Elemente prägen den Sound besonders?", 1,
                "Typisch: Synthesizer und elektronische Drums.");
        q5_4.addAnswerOption(new AnswerOption(1, "Synthesizer und elektronische Drums"));
        q5_4.addAnswerOption(new AnswerOption(2, "Nur Akustikgitarre"));
        q5_4.addAnswerOption(new AnswerOption(3, "Orchester und Harfe"));
        q5_4.addAnswerOption(new AnswerOption(4, "Dudelsack und Banjo"));
        questions.add(q5_4);

        Question q5_5 = new Question(40, 5, "Warum wurde „Blinding Lights“ ein riesiger Streaming-Hit?", 3,
                "Der Song ist extrem wiedererkennbar und wurde überall genutzt (Radio, Social Media, Clips).");
        q5_5.addAnswerOption(new AnswerOption(1, "Er lief kaum irgendwo"));
        q5_5.addAnswerOption(new AnswerOption(2, "Es gibt ihn nur live"));
        q5_5.addAnswerOption(new AnswerOption(3, "Eingängige Hook + Retro-Sound + breite Mediennutzung"));
        q5_5.addAnswerOption(new AnswerOption(4, "Reines Instrumentalstück"));
        questions.add(q5_5);


        // Song 6: Imagine – John Lennon (songId=6)
        Question q6_1 = new Question(16, 6, "Wer schrieb und sang „Imagine“?", 3,
                "Er war ein Beatles-Mitglied und später Solokünstler.");
        q6_1.addAnswerOption(new AnswerOption(1, "Paul McCartney"));
        q6_1.addAnswerOption(new AnswerOption(2, "George Harrison"));
        q6_1.addAnswerOption(new AnswerOption(3, "John Lennon"));
        q6_1.addAnswerOption(new AnswerOption(4, "Ringo Starr"));
        questions.add(q6_1);

        Question q6_2 = new Question(17, 6, "In welchem Jahr wurde „Imagine“ veröffentlicht?", 2,
                "Frühe 70er – genau 1971.");
        q6_2.addAnswerOption(new AnswerOption(1, "1970"));
        q6_2.addAnswerOption(new AnswerOption(2, "1971"));
        q6_2.addAnswerOption(new AnswerOption(3, "1972"));
        q6_2.addAnswerOption(new AnswerOption(4, "1973"));
        questions.add(q6_2);

        Question q6_3 = new Question(18, 6, "Welche Botschaft vermittelt „Imagine“ hauptsächlich?", 1,
                "Es geht um eine friedliche Welt ohne Grenzen.");
        q6_3.addAnswerOption(new AnswerOption(1, "Frieden und Einheit"));
        q6_3.addAnswerOption(new AnswerOption(2, "Reichtum und Luxus"));
        q6_3.addAnswerOption(new AnswerOption(3, "Sport und Wettkampf"));
        q6_3.addAnswerOption(new AnswerOption(4, "Technologie und Raumfahrt"));
        questions.add(q6_3);

        Question q6_4 = new Question(41, 6, "Welches Instrument steht besonders im Vordergrund?", 3,
                "Der Song beginnt und trägt sich vor allem über ein Klavier.");
        q6_4.addAnswerOption(new AnswerOption(1, "Gitarre"));
        q6_4.addAnswerOption(new AnswerOption(2, "Schlagzeug"));
        q6_4.addAnswerOption(new AnswerOption(3, "Klavier"));
        q6_4.addAnswerOption(new AnswerOption(4, "Saxophon"));
        questions.add(q6_4);

        Question q6_5 = new Question(42, 6, "Welche Band machte John Lennon vor seiner Solo-Karriere berühmt?", 4,
                "Eine der berühmtesten Bands der Musikgeschichte.");
        q6_5.addAnswerOption(new AnswerOption(1, "Queen"));
        q6_5.addAnswerOption(new AnswerOption(2, "The Rolling Stones"));
        q6_5.addAnswerOption(new AnswerOption(3, "Pink Floyd"));
        q6_5.addAnswerOption(new AnswerOption(4, "The Beatles"));
        questions.add(q6_5);


        // Song 7: Someone Like You – Adele (songId=7)
        Question q7_1 = new Question(19, 7, "Welches Album von Adele enthält „Someone Like You“?", 2,
                "Das Album heißt wie eine Zahl: 21.");
        q7_1.addAnswerOption(new AnswerOption(1, "19"));
        q7_1.addAnswerOption(new AnswerOption(2, "21"));
        q7_1.addAnswerOption(new AnswerOption(3, "25"));
        q7_1.addAnswerOption(new AnswerOption(4, "30"));
        questions.add(q7_1);

        Question q7_2 = new Question(20, 7, "In welchem Jahr wurde „Someone Like You“ veröffentlicht?", 2,
                "Das Album 21 (und der Song) wurden 2011 weltbekannt – das ist das Jahr.");
        q7_2.addAnswerOption(new AnswerOption(1, "2010"));
        q7_2.addAnswerOption(new AnswerOption(2, "2011"));
        q7_2.addAnswerOption(new AnswerOption(3, "2012"));
        q7_2.addAnswerOption(new AnswerOption(4, "2013"));
        questions.add(q7_2);

        Question q7_3 = new Question(21, 7, "Welche Stimmung beschreibt „Someone Like You“ am besten?", 1,
                "Es ist eine ruhige, emotionale Ballade.");
        q7_3.addAnswerOption(new AnswerOption(1, "Melancholisch und emotional"));
        q7_3.addAnswerOption(new AnswerOption(2, "Witzig und albern"));
        q7_3.addAnswerOption(new AnswerOption(3, "Aggressiv und laut"));
        q7_3.addAnswerOption(new AnswerOption(4, "Sehr schnell und hektisch"));
        questions.add(q7_3);

        Question q7_4 = new Question(43, 7, "Welches Instrument begleitet den Song besonders stark?", 3,
                "Die Aufnahme ist berühmt für das einfache Klavier-Arrangement.");
        q7_4.addAnswerOption(new AnswerOption(1, "E-Gitarre"));
        q7_4.addAnswerOption(new AnswerOption(2, "Synthesizer"));
        q7_4.addAnswerOption(new AnswerOption(3, "Klavier"));
        q7_4.addAnswerOption(new AnswerOption(4, "Trompete"));
        questions.add(q7_4);

        Question q7_5 = new Question(44, 7, "Warum ist „Someone Like You“ für Karaoke gut geeignet?", 4,
                "Langsam + klarer Gesang ist leichter mitzusingen als schneller Rap.");
        q7_5.addAnswerOption(new AnswerOption(1, "Extrem schneller Rap"));
        q7_5.addAnswerOption(new AnswerOption(2, "Kein Refrain vorhanden"));
        q7_5.addAnswerOption(new AnswerOption(3, "Nur Instrumental"));
        q7_5.addAnswerOption(new AnswerOption(4, "Langsames Tempo und klare Melodie"));
        questions.add(q7_5);


        // Song 8: Hotel California – Eagles (songId=8)
        Question q8_1 = new Question(22, 8, "Von welcher Band stammt „Hotel California“?", 2,
                "Eine US-Rockband aus den 1970ern: Eagles.");
        q8_1.addAnswerOption(new AnswerOption(1, "Fleetwood Mac"));
        q8_1.addAnswerOption(new AnswerOption(2, "Eagles"));
        q8_1.addAnswerOption(new AnswerOption(3, "ABBA"));
        q8_1.addAnswerOption(new AnswerOption(4, "U2"));
        questions.add(q8_1);

        Question q8_2 = new Question(23, 8, "In welchem Jahr wurde „Hotel California“ veröffentlicht?", 3,
                "Als Single-Hit ist es 1977 bekannt (Album kam 1976).");
        q8_2.addAnswerOption(new AnswerOption(1, "1975"));
        q8_2.addAnswerOption(new AnswerOption(2, "1976"));
        q8_2.addAnswerOption(new AnswerOption(3, "1977"));
        q8_2.addAnswerOption(new AnswerOption(4, "1978"));
        questions.add(q8_2);

        Question q8_3 = new Question(24, 8, "Welche Aussage passt zu einer häufigen Interpretation des Songs?", 1,
                "Viele sehen den Song als Symbol: Luxus, Verlockung, aber kein Entkommen.");
        q8_3.addAnswerOption(new AnswerOption(1, "Luxus, Verlockung und das Gefühl, nicht entkommen zu können"));
        q8_3.addAnswerOption(new AnswerOption(2, "Ein reines Kinderlied"));
        q8_3.addAnswerOption(new AnswerOption(3, "Ein Wissenschaftssong über Computer"));
        q8_3.addAnswerOption(new AnswerOption(4, "Ein Sportlied über Baseball"));
        questions.add(q8_3);

        Question q8_4 = new Question(45, 8, "Wofür ist „Hotel California“ musikalisch besonders bekannt?", 4,
                "Am Ende gibt es ein langes und sehr berühmtes Gitarrensolo.");
        q8_4.addAnswerOption(new AnswerOption(1, "Nur Schlagzeugsolo"));
        q8_4.addAnswerOption(new AnswerOption(2, "Operngesang"));
        q8_4.addAnswerOption(new AnswerOption(3, "Beatboxing"));
        q8_4.addAnswerOption(new AnswerOption(4, "Langes, ikonisches Gitarrensolo"));
        questions.add(q8_4);

        Question q8_5 = new Question(46, 8, "Welches Genre passt am besten zu „Hotel California“?", 2,
                "Klassischer US-Rock (kein Pop/Techno).");
        q8_5.addAnswerOption(new AnswerOption(1, "Techno"));
        q8_5.addAnswerOption(new AnswerOption(2, "Rock"));
        q8_5.addAnswerOption(new AnswerOption(3, "K-Pop"));
        q8_5.addAnswerOption(new AnswerOption(4, "Reggae"));
        questions.add(q8_5);


        // Song 9: Don't Stop Believin' – Journey (songId=9)
        Question q9_1 = new Question(25, 9, "Von welcher Band stammt „Don't Stop Believin'“?", 2,
                "Journey ist eine US-Rockband.");
        q9_1.addAnswerOption(new AnswerOption(1, "Boston"));
        q9_1.addAnswerOption(new AnswerOption(2, "Journey"));
        q9_1.addAnswerOption(new AnswerOption(3, "Foreigner"));
        q9_1.addAnswerOption(new AnswerOption(4, "Styx"));
        questions.add(q9_1);

        Question q9_2 = new Question(26, 9, "In welchem Jahr wurde „Don't Stop Believin'“ veröffentlicht?", 3,
                "Der Song ist vom Journey-Album „Escape“ (1981).");
        q9_2.addAnswerOption(new AnswerOption(1, "1979"));
        q9_2.addAnswerOption(new AnswerOption(2, "1980"));
        q9_2.addAnswerOption(new AnswerOption(3, "1981"));
        q9_2.addAnswerOption(new AnswerOption(4, "1982"));
        questions.add(q9_2);

        Question q9_3 = new Question(27, 9, "Welche Botschaft passt am besten zu „Don't Stop Believin'“?", 1,
                "Es ist ein typischer Motivationssong: dranbleiben, nicht aufgeben.");
        q9_3.addAnswerOption(new AnswerOption(1, "Hoffnung und Durchhaltevermögen"));
        q9_3.addAnswerOption(new AnswerOption(2, "Aufgeben und Resignation"));
        q9_3.addAnswerOption(new AnswerOption(3, "Nur Humor ohne Aussage"));
        q9_3.addAnswerOption(new AnswerOption(4, "Technisches Handbuch"));
        questions.add(q9_3);

        Question q9_4 = new Question(47, 9, "Welches Instrument prägt den Anfang besonders?", 1,
                "Der Song beginnt mit einem sehr bekannten Keyboard-/Klavier-Riff.");
        q9_4.addAnswerOption(new AnswerOption(1, "Klavier/Keyboard"));
        q9_4.addAnswerOption(new AnswerOption(2, "Tuba"));
        q9_4.addAnswerOption(new AnswerOption(3, "Violine"));
        q9_4.addAnswerOption(new AnswerOption(4, "Saxophon"));
        questions.add(q9_4);

        Question q9_5 = new Question(48, 9, "Warum wird der Song oft als Mitsing-/Stadion-Song genutzt?", 4,
                "Ein großer Refrain + einfache Mitsing-Teile sind dafür typisch.");
        q9_5.addAnswerOption(new AnswerOption(1, "Kein Refrain vorhanden"));
        q9_5.addAnswerOption(new AnswerOption(2, "Sehr leise ohne Höhepunkte"));
        q9_5.addAnswerOption(new AnswerOption(3, "Nur Instrumental"));
        q9_5.addAnswerOption(new AnswerOption(4, "Großer Refrain und hohe Wiedererkennbarkeit"));
        questions.add(q9_5);


        // Song 10: Sweet Child O' Mine – Guns N' Roses (songId=10)
        Question q10_1 = new Question(28, 10, "Wer ist der Leadsänger von Guns N' Roses?", 1,
                "Axl Rose ist die bekannteste Stimme der Band.");
        q10_1.addAnswerOption(new AnswerOption(1, "Axl Rose"));
        q10_1.addAnswerOption(new AnswerOption(2, "Slash"));
        q10_1.addAnswerOption(new AnswerOption(3, "Duff McKagan"));
        q10_1.addAnswerOption(new AnswerOption(4, "Izzy Stradlin"));
        questions.add(q10_1);

        Question q10_2 = new Question(29, 10, "Welches Genre passt am besten zu „Sweet Child O' Mine“?", 2,
                "Guns N' Roses sind eine Hard-Rock-Band aus den 80ern.");
        q10_2.addAnswerOption(new AnswerOption(1, "Disco"));
        q10_2.addAnswerOption(new AnswerOption(2, "Hard Rock"));
        q10_2.addAnswerOption(new AnswerOption(3, "Klassik"));
        q10_2.addAnswerOption(new AnswerOption(4, "Reggae"));
        questions.add(q10_2);

        Question q10_3 = new Question(30, 10, "Welches Album enthält „Sweet Child O' Mine“?", 1,
                "Das Album heißt „Appetite for Destruction“ (Debütalbum).");
        q10_3.addAnswerOption(new AnswerOption(1, "Appetite for Destruction"));
        q10_3.addAnswerOption(new AnswerOption(2, "Use Your Illusion I"));
        q10_3.addAnswerOption(new AnswerOption(3, "Use Your Illusion II"));
        q10_3.addAnswerOption(new AnswerOption(4, "The Spaghetti Incident?"));
        questions.add(q10_3);

        Question q10_4 = new Question(49, 10, "Wer ist vor allem für das ikonische Gitarren-Intro bekannt?", 2,
                "Der berühmte Gitarrist der Band ist Slash.");
        q10_4.addAnswerOption(new AnswerOption(1, "Axl Rose"));
        q10_4.addAnswerOption(new AnswerOption(2, "Slash"));
        q10_4.addAnswerOption(new AnswerOption(3, "Izzy Stradlin"));
        q10_4.addAnswerOption(new AnswerOption(4, "Duff McKagan"));
        questions.add(q10_4);

        Question q10_5 = new Question(50, 10, "In welchem Jahr erschien „Sweet Child O' Mine“ als Single (ungefähr)?", 1,
                "Späte 80er – genau 1987 (Appetite for Destruction ist auch von 1987).");
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
        for (Question q : questions) {
            if (q.getId() == id) return q;
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
