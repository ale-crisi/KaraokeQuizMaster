package at.ac.hcw.kqm.engine;

import at.ac.hcw.kqm.model.Game;
import at.ac.hcw.kqm.model.GamePhase;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Question;

import java.util.List;
/**
 * Steuert den Ablauf des Spiels und verbindet
 * Spiellogik, Punkteberechnung und Spielphasen.
 * Verarbeitet Antworten, wechselt Spielzustände
 * und behandelt den Tie-Break-Fall.
 */


public class GameEngine {

    private List<Question> tieBreakQuestions;
    private int tieBreakQuestionIndex = 0;

    private Game game;
    private GamePhase phase = GamePhase.NOT_STARTED;
    private final ScoreService scoreService = new ScoreService();

    public void startGame(List<Player> players, List<Question> questions) {
        this.game = new Game(players, questions);
        this.phase = GamePhase.ASKING_QUESTION;
    }

    public Player currentPlayer() {
        if (phase == GamePhase.TIE_BREAK) {
            return game.getCurrentTieBreakPlayer();
        }
        return game.getCurrentPlayer();
    }

    public Question currentQuestion() {
        if (phase == GamePhase.TIE_BREAK) {
            return tieBreakQuestions.get(tieBreakQuestionIndex);
        }
        if (game == null) {
            throw new RuntimeException("Game is null!");
        }
        return game.getCurrentQuestion();
    }

    public void answerQuestion(int selectedOptionId) {
        if (phase != GamePhase.ASKING_QUESTION) return;

        if (currentQuestion().isCorrect(selectedOptionId)) {
            scoreService.awardPoints(currentPlayer(), 1);
        }

        game.nextTurn();

        if (game.isFinished()) {
            phase = GamePhase.FINISHED;
        }
    }

    public boolean isGameFinished() {
        return phase == GamePhase.FINISHED;
    }

    public List<Player> getRanking() {
        return scoreService.calculateRanking(game.getPlayers());
    }

    public int getQuestionNumberForCurrentPlayer() {
        return game.getQuestionsForCurrentPlayer() + 1;
    }

    public boolean needsTieBreak() {
        return scoreService.hasTie(game.getPlayers());
    }

    /** Spieler, die im Top-Score Gleichstand sind (für TieBreak-Intro). */
    public List<Player> getTiedPlayers() {
        return scoreService.getTiedPlayers(game.getPlayers());
    }

    public void startTieBreak(List<Question> tieBreakQuestions) {
        List<Player> tied = scoreService.getTiedPlayers(game.getPlayers());

        game.startTieBreak(tied);
        this.tieBreakQuestions = tieBreakQuestions;
        this.tieBreakQuestionIndex = 0;
        phase = GamePhase.TIE_BREAK;
    }

    /**
     * @return true wenn diese Runde abgeschlossen ist (alle TieBreak-Spieler haben diese Frage beantwortet)
     */
    public boolean answerTieBreakQuestion(int selectedOptionId, long answerTimeMs) {
        if (phase != GamePhase.TIE_BREAK) return false;

        boolean isCorrect = currentQuestion().isCorrect(selectedOptionId);
        game.recordTieBreakAnswer(currentPlayer(), answerTimeMs, isCorrect);

        boolean allAnsweredThisRound = game.allTieBreakPlayersAnswered();

        if (allAnsweredThisRound) {
            awardTieBreakPoints();
        }

        game.nextTieBreakTurn();

        if (allAnsweredThisRound) {
            if (game.getTieBreakQuestionIndex() >= tieBreakQuestions.size() || hasWinner()) {
                phase = GamePhase.FINISHED;
            }
        }

        return allAnsweredThisRound;
    }

    private boolean hasWinner() {
        List<Player> tieBreakPlayers = game.getTieBreakPlayers();
        if (tieBreakPlayers.size() <= 1) return true;

        List<Player> sorted = tieBreakPlayers.stream()
                .sorted((a, b) -> b.getPoints() - a.getPoints())
                .toList();

        return sorted.get(0).getPoints() > sorted.get(1).getPoints();
    }

    private void awardTieBreakPoints() {
        var times = game.getTieBreakAnswerTimes();
        var correctness = game.getTieBreakAnswerCorrectness();

        List<Player> correctPlayers = times.keySet().stream()
                .filter(p -> correctness.getOrDefault(p, false))
                .sorted((p1, p2) -> Long.compare(times.get(p1), times.get(p2)))
                .toList();

        if (correctPlayers.size() >= 1) scoreService.awardPoints(correctPlayers.get(0), 2);
        if (correctPlayers.size() >= 2) scoreService.awardPoints(correctPlayers.get(1), 1);
    }

    public boolean allTieBreakPlayersAnswered() {
        return game.allTieBreakPlayersAnswered();
    }
}
