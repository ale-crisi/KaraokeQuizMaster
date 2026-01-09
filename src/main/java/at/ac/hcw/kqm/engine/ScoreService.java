package at.ac.hcw.kqm.engine;

import java.util.ArrayList;
import java.util.List;

import at.ac.hcw.kqm.model.Player;

/**
 * Service class for score calculation and ranking logic.
 * Handles player scoring, ranking, and tie detection.
 */
public class ScoreService {

    /**
     * Awards points to a player for a correct answer.
     * 
     * @param player The player to award points to
     * @param points Number of points to award
     */
    public void awardPoints(Player player, int points) {
        player.addPoints(points);
    }

    /**
     * Calculates and returns the player ranking based on points.
     * Players are sorted in descending order by score.
     * 
     * @param players List of players to rank
     * @return Sorted list with highest scoring players first
     */
    public List<Player> calculateRanking(List<Player> players) {
        List<Player> ranking = new ArrayList<>(players);
        ranking.sort((a, b) -> b.getPoints() - a.getPoints());
        return ranking;
    }

    /**
     * Checks if there is a tie between the top players.
     * 
     * @param players List of players to check
     * @return true if at least two players share the highest score
     */
    public boolean hasTie(List<Player> players) {
        if (players.size() < 2) {
            return false;
        }

        List<Player> ranking = calculateRanking(players);
        return ranking.get(0).getPoints() == ranking.get(1).getPoints();
    }

    /**
     * Returns all players who share the highest score.
     * 
     * @param players List of players
     * @return List of tied players with top score
     */
    public List<Player> getTiedPlayers(List<Player> players) {
        List<Player> ranking = calculateRanking(players);
        int topScore = ranking.get(0).getPoints();

        List<Player> tied = new ArrayList<>();
        for (Player p : ranking) {
            if (p.getPoints() == topScore) {
                tied.add(p);
            } else {
                break; // No need to check further
            }
        }

        return tied;
    }

    /**
     * Resets all player scores to zero.
     * 
     * @param players List of players to reset
     */
    public void resetScores(List<Player> players) {
        for (Player p : players) {
            p.setPoints(0);
        }
    }
}
