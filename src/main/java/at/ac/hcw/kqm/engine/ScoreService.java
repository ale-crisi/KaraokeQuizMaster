package at.ac.hcw.kqm.engine;

import at.ac.hcw.kqm.model.Player;

import java.util.ArrayList;
import java.util.List;



 // Player scoring, ranking, and tie detection.
    public class ScoreService {

    public void awardPoints(Player player, int points) {
        player.addPoints(points);
    }

    //Player ranking based on points.

    public List<Player> calculateRanking(List<Player> players) {
        List<Player> ranking = new ArrayList<>(players);
        ranking.sort((a, b) -> b.getPoints() - a.getPoints());
        return ranking;
    }

    //Checks if there is a tie between the top players.

    public boolean hasTie(List<Player> players) {
        if (players.size() < 2) {
            return false;
        }

        List<Player> ranking = calculateRanking(players);
        int topScore = ranking.get(0).getPoints();
        int count = 0;
        for (Player p : ranking) {
            if (p.getPoints() == topScore) {
                count++;
            } else {
                break;
            }
        }
        return count > 1;
    }

    //Returns all players who share the highest score.
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
}
