package at.ac.hcw.kqm.engine;

import at.ac.hcw.kqm.model.Game;
import at.ac.hcw.kqm.model.GamePhase;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Question;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private List<Question> tieBreakQuestions;

    private int tieBreakQuestionIndex = 0;
    private Game game;
    private GamePhase phase = GamePhase.NOT_STARTED;

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
        return game.getCurrentQuestion();
    }

    /**
     * If the player answers a question correct he gets and point until everyone has gotten 5 questions each
     */
    public void answerQuestion(int selectedOptionId) {

        if (phase != GamePhase.ASKING_QUESTION) return;

        if (currentQuestion().isCorrect(selectedOptionId)) {
            currentPlayer().addPoints(1);
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
        List<Player> ranking = new ArrayList<>(game.getPlayers());
        ranking.sort((a, b) -> b.getPoints() - a.getPoints());
        return ranking;
    }

    public boolean needsTieBreak() {
        List<Player> ranking = getRanking();
        return ranking.size() > 1 &&
                ranking.get(0).getPoints() == ranking.get(1).getPoints();
    }

    public void startTieBreak(List<Question> tieBreakQuestions) {
        List<Player> ranking = getRanking();
        int topScore = ranking.get(0).getPoints();

        List<Player> tied = new ArrayList<>();
        for (Player p : ranking) {
            if (p.getPoints() == topScore) {
                tied.add(p);
            }
        }

        game.startTieBreak(tied);
        this.tieBreakQuestions = tieBreakQuestions;
        this.tieBreakQuestionIndex = 0;
        phase = GamePhase.TIE_BREAK;
    }

    public void answerTieBreakQuestion(int selectedOptionId) {
        if (phase != GamePhase.TIE_BREAK) return;

        if (currentQuestion().isCorrect(selectedOptionId)) {
            currentPlayer().addPoints(1);
        }

        game.nextTieBreakTurn();
        tieBreakQuestionIndex++;

        if (tieBreakQuestionIndex >= tieBreakQuestions.size()) {
                phase = GamePhase.FINISHED;
        }
    }

}
