package at.ac.hcw.kqm.model;

import java.util.List;

public class Game {

    private List<Player> players;
    private List<Question> questions;
    private List<Player> tieBreakPlayers;

    private int tieBreakIndex = 0;
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
    }

    public Player getCurrentTieBreakPlayer() {
        return tieBreakPlayers.get(tieBreakIndex);
    }

    public void nextTieBreakTurn() {
        tieBreakIndex = (tieBreakIndex + 1) % tieBreakPlayers.size();
    }

    public List<Player> getTieBreakPlayers() {
        return tieBreakPlayers;
    }
}
