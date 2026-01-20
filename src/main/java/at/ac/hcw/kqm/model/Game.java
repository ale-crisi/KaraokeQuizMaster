package at.ac.hcw.kqm.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Steuert den Spielablauf und speichert den aktuellen Spielzustand.
 * Bestimmt den aktuellen Spieler, die aktuelle Frage
 * und behandelt den Tie-Break bei Gleichstand.
 */

public class Game {

    private List<Player> players; //Liste aller Spieler, die mitspielen.
    private List<Question> questions; //Liste der Fragen, die im Spiel verwendet werden
    private List<Player> tieBreakPlayers;

    private int tieBreakIndex = 0;
    private int tieBreakQuestionIndex = 0;
    private Map<Player, Long> tieBreakAnswerTimes = new HashMap<>();
    private Map<Player, Boolean> tieBreakAnswerCorrectness = new HashMap<>();
    private int currentPlayerIndex = 0;
    private int currentQuestionIndex = 0;
    private int questionsForCurrentPlayer = 0;

    public Game(List<Player> players, List<Question> questions) {
        this.players = players;
        this.questions = questions;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    /**
     * After 5 Questions the next Players turn starts
     */
    public void nextTurn() {
        questionsForCurrentPlayer++;
        currentQuestionIndex++;

        if (questionsForCurrentPlayer >= 5) {
            questionsForCurrentPlayer = 0;
            currentPlayerIndex++;
        }
    }

    /**
     * Checks if currentPlayerIndex is the same or bigger than the player.size
     */
    public boolean isFinished() {
        return currentPlayerIndex >= players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void startTieBreak(List<Player> tiedPlayers) {
        this.tieBreakPlayers = tiedPlayers;
        this.tieBreakIndex = 0;
        this.tieBreakQuestionIndex = 0;
        this.tieBreakAnswerTimes.clear();
        this.tieBreakAnswerCorrectness.clear();
    }

    public Player getCurrentTieBreakPlayer() {
        return tieBreakPlayers.get(tieBreakIndex);
    }

    public void nextTieBreakTurn() {
        tieBreakIndex++;
        // If all players answered the current question, reset for next question
        if (tieBreakIndex >= tieBreakPlayers.size()) {
            tieBreakIndex = 0;
            tieBreakQuestionIndex++;
            tieBreakAnswerTimes.clear();
            tieBreakAnswerCorrectness.clear();
        }
    }

    public void recordTieBreakAnswer(Player player, long answerTimeMs, boolean isCorrect) {
        tieBreakAnswerTimes.put(player, answerTimeMs);
        tieBreakAnswerCorrectness.put(player, isCorrect);
    }

    public Map<Player, Long> getTieBreakAnswerTimes() {
        return new HashMap<>(tieBreakAnswerTimes);
    }

    public Map<Player, Boolean> getTieBreakAnswerCorrectness() {
        return new HashMap<>(tieBreakAnswerCorrectness);
    }

    public boolean allTieBreakPlayersAnswered() {
        return tieBreakAnswerTimes.size() == tieBreakPlayers.size();
    }

    public List<Player> getTieBreakPlayers() {
        return tieBreakPlayers;
    }

    public int getQuestionsForCurrentPlayer() {
        return questionsForCurrentPlayer;
    }

    public int getTieBreakQuestionIndex() {
        return tieBreakQuestionIndex;
    }
}