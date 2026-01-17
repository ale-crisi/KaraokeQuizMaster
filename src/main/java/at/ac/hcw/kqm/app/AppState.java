package at.ac.hcw.kqm.app;

import at.ac.hcw.kqm.engine.GameEngine;
import at.ac.hcw.kqm.model.JokerType;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Question;
import at.ac.hcw.kqm.model.Song;
import at.ac.hcw.kqm.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AppState {

    private static AppState INSTANCE;

    private final GameEngine engine = new GameEngine();
    private final SongRepository songRepo = new InMemorySongRepository();
    private final QuestionRepository questionRepo = new InMemoryQuestionRepository();
    private final FillJumpQuestionRepository fillJumpRepo = new FillJumpQuestionRepository();

    private final List<Player> players = new ArrayList<>();
    private final List<Song> selectedSongs = new ArrayList<>();

    private int currentSelectionPlayerIndex = 0;

    // Joker state for current question/session navigation
    private JokerType pendingJoker = null;
    private boolean usedFiftyThisQuestion = false;
    private boolean usedHintThisQuestion = false;
    private boolean usedFillThisQuestion = false;

    private AppState() {
    }

    public static AppState get() {
        if (INSTANCE == null) {
            INSTANCE = new AppState();
        }
        return INSTANCE;
    }

    // Players
    public void initDefaultPlayers() {
        players.clear();
        players.add(new Player(1, "Player I"));
        players.add(new Player(2, "Player II"));
        players.add(new Player(3, "Player III"));
        players.add(new Player(4, "Player IV"));
    }

    public void initPlayersWithNames(List<String> playerNames) {
        players.clear();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(i + 1, playerNames.get(i)));
        }
        System.out.println("Initialized " + players.size() + " players: " +
                players.stream().map(Player::getName).collect(Collectors.joining(", ")));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentSelectionPlayer() {
        return players.get(currentSelectionPlayerIndex);
    }

    public boolean advanceSelectionPlayer() {
        currentSelectionPlayerIndex++;
        if (currentSelectionPlayerIndex >= players.size()) {
            currentSelectionPlayerIndex = players.size() - 1; // Bleibe beim letzten Spieler
            return true;
        }
        return false;
    }

    public void resetSelectionFlow() {
        currentSelectionPlayerIndex = 0;
        selectedSongs.clear();
    }

    // Songs
    public List<Song> getTopTenSongs() {
        // Use the first ten available songs
        return songRepo.getAllSongs().stream()
                .filter(Song::isAvailable)
                .limit(10)
                .collect(Collectors.toList());
    }

    public void selectSongForCurrentPlayer(Song song) {
        selectedSongs.add(song);
    }

    public List<Song> getSelectedSongs() {
        return selectedSongs;
    }

    public Song getSongForPlayer(Player p) {
        int i = players.indexOf(p);
        if (i >= 0 && i < selectedSongs.size()) {
            return selectedSongs.get(i);
        }
        return null;
    }

    // Game
    public void startQuizGame() {
        List<Question> all = new ArrayList<>();
        // Build 5 questions for EACH player's selected song separately
        for (int i = 0; i < players.size(); i++) {
            Song song = selectedSongs.get(i);
            List<Question> qs = questionRepo.getQuestionsBySongId(song.getId());
            // Exclude Fill&Jump lyric completion questions from the normal pool
            List<Question> normal = qs.stream()
                    .filter(q -> !isFillJumpQuestion(q))
                    .toList();

            List<Question> source = normal.isEmpty() ? qs : normal;
            List<Question> five = new ArrayList<>();
            int count = Math.min(5, source.size());
            for (int j = 0; j < count; j++) {
                five.add(source.get(j));
            }
            // Fill up to 5 if not enough questions
            for (int j = count; j < 5; j++) {
                five.add(source.get(j % source.size()));
            }
            all.addAll(five);
        }
        System.out.println("Total questions loaded: " + all.size() + " for " + players.size() + " players");
        engine.startGame(players, all);
    }

    private boolean isFillJumpQuestion(Question q) {
        String t = q.getQuestionText();
        if (t == null)
            return false;
        String lower = t.toLowerCase();
        // Nur Liedtext-Vervollständigungen filtern, nicht allgemeine
        // "Vervollständige"-Fragen
        return (lower.contains("vervollständige den text") || lower.contains("vervollstaendige den text")
                || lower.contains("vervollständige den liedtext") || lower.contains("vervollstaendige den liedtext")
                || lower.contains("vervollständige: \"") || lower.contains("vervollstaendige: \""));
    }

    public GameEngine getEngine() {
        return engine;
    }

    public QuestionRepository getQuestionRepo() {
        return questionRepo;
    }

    public SongRepository getSongRepo() {
        return songRepo;
    }

    public FillJumpQuestionRepository getFillJumpRepo() {
        return fillJumpRepo;
    }

    // Joker APIs
    public void setPendingJoker(JokerType type) {
        this.pendingJoker = type;
    }

    public JokerType consumePendingJoker() {
        JokerType t = this.pendingJoker;
        this.pendingJoker = null;
        return t;
    }

    public void setJokerUsed(JokerType type) {
        if (type == null)
            return;
        switch (type) {
            case FIFTY_FIFTY -> this.usedFiftyThisQuestion = true;
            case HINT -> this.usedHintThisQuestion = true;
            case FILL_AND_JUMP -> this.usedFillThisQuestion = true;
        }
    }

    public boolean isJokerUsed(JokerType type) {
        if (type == null)
            return false;
        return switch (type) {
            case FIFTY_FIFTY -> usedFiftyThisQuestion;
            case HINT -> usedHintThisQuestion;
            case FILL_AND_JUMP -> usedFillThisQuestion;
        };
    }

    public void resetJokersForNewQuestion() {
        usedFiftyThisQuestion = false;
        usedHintThisQuestion = false;
        usedFillThisQuestion = false;
        pendingJoker = null;
    }
}
