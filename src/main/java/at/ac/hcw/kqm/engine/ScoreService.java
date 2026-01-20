package at.ac.hcw.kqm.engine;

import at.ac.hcw.kqm.model.Player;

import java.util.ArrayList;
import java.util.List;
/**
 * Service-Klasse für Punkteberechnung und Ranglistenlogik.
 * Verwaltet die Punkte der Spieler,
 * die Rangfolge und erkennt Punktegleichstände.
 */

public class ScoreService {

    /**
     * Vergibt Punkte an einen Spieler
     * für eine richtige Antwort.
     *
     * @param player Der Spieler, der Punkte erhält
     * @param points Anzahl der zu vergebenden Punkte
     */

    public void awardPoints(Player player, int points) {
        player.addPoints(points);
    }

    /**
     * Berechnet und gibt die Rangliste der Spieler
     * basierend auf ihren Punkten zurück.
     * Die Spieler werden absteigend nach Punktzahl sortiert.
     *
     * @param players Liste der zu sortierenden Spieler
     * @return Sortierte Liste mit den Spielern
     *         mit der höchsten Punktzahl zuerst
     */

    public List<Player> calculateRanking(List<Player> players) {
        List<Player> ranking = new ArrayList<>(players);
        ranking.sort((a, b) -> b.getPoints() - a.getPoints());
        return ranking;
    }

    /**
     * Prüft, ob es einen Gleichstand
     * zwischen den besten Spielern gibt.
     *
     * @param players Liste der zu prüfenden Spieler
     * @return true, wenn mindestens zwei Spieler
     *         die höchste Punktzahl teilen
     */

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

    /**
     * Gibt alle Spieler zurück,
     * die die höchste Punktzahl erreicht haben.
     *
     * @param players Liste der Spieler
     * @return Liste der Spieler mit der höchsten Punktzahl
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

    public void resetScores(List<Player> players) {
        for (Player p : players) {
            p.setPoints(0);
        }
    }
}
