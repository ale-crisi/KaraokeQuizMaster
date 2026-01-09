package at.ac.hcw.kqm.engine;

import java.util.List;

import at.ac.hcw.kqm.model.Game;
import at.ac.hcw.kqm.model.GamePhase;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Question;

public class GameEngine {

    private List<Question> tieBreakQuestions;

    private int tieBreakQuestionIndex = 0;
    private Game game;
    private GamePhase phase = GamePhase.NOT_STARTED;
    private final ScoreService scoreService = new ScoreService();

    public void startGame(List<Player> players, List<Question> questions) {
        System.out.println("GameEngine.startGame() called with " + players.size() + " players and " + questions.size()
                + " questions");
        this.game = new Game(players, questions);
        System.out.println("Game object created");
        this.phase = GamePhase.ASKING_QUESTION;
        System.out.println("GameEngine.startGame() completed");
    }

    public Player currentPlayer() {
        if (phase == GamePhase.TIE_BREAK) {
            return game.getCurrentTieBreakPlayer();
        }
        return game.getCurrentPlayer();
    }

    public Question currentQuestion() {
        System.out.println("GameEngine.currentQuestion() called, phase=" + phase);
        if (phase == GamePhase.TIE_BREAK) {
            System.out.println("TIE_BREAK phase");
            return tieBreakQuestions.get(tieBreakQuestionIndex);
        }
        System.out.println("Normal phase, game=" + game);
        if (game == null) {
            throw new RuntimeException("Game is null!");
        }
        Question q = game.getCurrentQuestion();
        System.out.println("Current question: " + (q != null ? q.getQuestionText() : "NULL"));
        return q;
    }

    /**
     * If the player answers a question correct he gets and point until everyone has
     * gotten 5 questions each
     */
    public void answerQuestion(int selectedOptionId) {

        if (phase != GamePhase.ASKING_QUESTION)
            return;

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
        // 1-based number for display
        return game.getQuestionsForCurrentPlayer() + 1;
    }

    public boolean needsTieBreak() {
        return scoreService.hasTie(game.getPlayers());
    }

    public void startTieBreak(List<Question> tieBreakQuestions) {
        List<Player> tied = scoreService.getTiedPlayers(game.getPlayers());

        game.startTieBreak(tied);
        this.tieBreakQuestions = tieBreakQuestions;
        this.tieBreakQuestionIndex = 0;
        phase = GamePhase.TIE_BREAK;
    }

    public void answerTieBreakQuestion(int selectedOptionId, long answerTimeMs) {
        if (phase != GamePhase.TIE_BREAK)
            return;

        boolean isCorrect = currentQuestion().isCorrect(selectedOptionId);
        game.recordTieBreakAnswer(currentPlayer(), answerTimeMs, isCorrect);

        // Check if all players have answered this question
        if (game.allTieBreakPlayersAnswered()) {
            // Award points based on speed ranking
            awardTieBreakPoints();
        }

        game.nextTieBreakTurn();

        // Check if we've exhausted questions or have a clear winner
        if (game.getTieBreakQuestionIndex() >= tieBreakQuestions.size()) {
            phase = GamePhase.FINISHED;
        }
    }

    private void awardTieBreakPoints() {
        var times = game.getTieBreakAnswerTimes();
        var correctness = game.getTieBreakAnswerCorrectness();

        // Filter only correct answers and sort by time
        List<Player> correctPlayers = times.keySet().stream()
                .filter(p -> correctness.getOrDefault(p, false))
                .sorted((p1, p2) -> Long.compare(times.get(p1), times.get(p2)))
                .toList();

        // Award points: 2 for fastest, 1 for second fastest, 0 for others
        if (correctPlayers.size() >= 1) {
            scoreService.awardPoints(correctPlayers.get(0), 2);
        }
        if (correctPlayers.size() >= 2) {
            scoreService.awardPoints(correctPlayers.get(1), 1);
        }
        // Others get 0 points (no action needed)
    }

    public boolean allTieBreakPlayersAnswered() {
        return game.allTieBreakPlayersAnswered();
    }

}
