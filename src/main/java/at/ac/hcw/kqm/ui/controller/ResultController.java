package at.ac.hcw.kqm.ui.controller;

import java.util.List;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.engine.GameEngine;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Question;
import at.ac.hcw.kqm.model.Song;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResultController {

    @FXML
    private VBox rankingContainer;
    @FXML
    private Button startKaraokeButton;
    @FXML
    private VBox battleContainer;
    @FXML
    private Button startBattleButton;

    @FXML
    public void initialize() {
        GameEngine engine = AppState.get().getEngine();
        List<Player> ranking = engine.getRanking();
        List<Player> players = AppState.get().getPlayers();

        // Check if tie-break is needed
        boolean needsTieBreak = engine.needsTieBreak();

        if (needsTieBreak) {
            // Show battle mode
            showBattleMode(ranking);
        } else {
            // Show normal ranking
            showNormalRanking(ranking, players);
        }
    }

    private void showBattleMode(List<Player> ranking) {
        // Hide karaoke button, show battle section
        startKaraokeButton.setVisible(false);
        battleContainer.setVisible(true);

        // Get tied players (players with same top score)
        int topScore = ranking.get(0).getPoints();
        List<Player> tiedPlayers = ranking.stream()
                .filter(p -> p.getPoints() == topScore)
                .toList();

        // Clear ranking container and show all players first
        rankingContainer.getChildren().clear();
        for (Player player : ranking) {

            // Get the song for this player
            Song song = AppState.get().getSongForPlayer(player);
            String songTitle = (song != null) ? song.getTitle() : "N/A";

            // Create a row with: Songtitel | PLAYER | POINTS
            HBox row = new HBox(40);
            row.setAlignment(Pos.CENTER);
            row.setStyle(
                    "-fx-background-color: rgba(50, 50, 50, 0.7); -fx-padding: 15 30 15 30; -fx-background-radius: 5;");

            // Song title (left-aligned, gold)
            Label songLabel = new Label(songTitle);
            songLabel.setStyle("-fx-text-fill: #CC9900; -fx-font-size: 20px; -fx-font-weight: bold;");
            songLabel.setPrefWidth(300);

            // Player name (center, red)
            Label playerLabel = new Label(player.getName());
            playerLabel.setStyle("-fx-text-fill: #CC0000; -fx-font-size: 20px; -fx-font-weight: bold;");
            playerLabel.setPrefWidth(200);
            playerLabel.setAlignment(Pos.CENTER);

            // Points (right, gray)
            Label pointsLabel = new Label(player.getPoints() + " POINTS");
            pointsLabel.setStyle("-fx-text-fill: #999999; -fx-font-size: 20px; -fx-font-weight: bold;");
            pointsLabel.setPrefWidth(200);
            pointsLabel.setAlignment(Pos.CENTER_RIGHT);

            row.getChildren().addAll(songLabel, playerLabel, pointsLabel);
            rankingContainer.getChildren().add(row);
        }

        // Show "BATTLE TIME" message
        Label battleLabel = new Label("BATTLE TIME...");
        battleLabel.setStyle(
                "-fx-text-fill: #CC0000; -fx-font-size: 36px; -fx-font-weight: bold; -fx-font-family: 'Serif'; -fx-letter-spacing: 0.2em;");
        battleContainer.getChildren().add(battleLabel);

        // Show tied players
        VBox tiedPlayersBox = new VBox(10);
        tiedPlayersBox.setAlignment(Pos.CENTER);
        for (Player player : tiedPlayers) {
            Label playerLabel = new Label(player.getName());
            playerLabel.setStyle("-fx-text-fill: #CC9900; -fx-font-size: 24px; -fx-font-weight: bold;");
            tiedPlayersBox.getChildren().add(playerLabel);
        }
        battleContainer.getChildren().add(tiedPlayersBox);

        // Set up battle button
        startBattleButton.setOnAction(e -> {
            // Initialize tie-break
            List<Question> tieBreakQuestions = AppState.get().getQuestionRepo().getAllQuestions();
            AppState.get().getEngine().startTieBreak(tieBreakQuestions);
            SceneManager.get().showTieBreak();
        });
    }

    private void showNormalRanking(List<Player> ranking, List<Player> players) {
        // Hide battle section, show karaoke button
        battleContainer.setVisible(false);
        startKaraokeButton.setVisible(true);

        // Clear and populate ranking
        rankingContainer.getChildren().clear();
        for (int i = 0; i < ranking.size(); i++) {
            Player player = ranking.get(i);

            // Get the song for this player
            Song song = AppState.get().getSongForPlayer(player);
            String songTitle = (song != null) ? song.getTitle() : "N/A";

            // Create a row with: Songtitel | PLAYER | POINTS
            HBox row = new HBox(40);
            row.setAlignment(Pos.CENTER);
            row.setStyle(
                    "-fx-background-color: rgba(50, 50, 50, 0.7); -fx-padding: 15 30 15 30; -fx-background-radius: 5;");

            // Song title (left-aligned, gold)
            Label songLabel = new Label(songTitle);
            songLabel.setStyle("-fx-text-fill: #CC9900; -fx-font-size: 20px; -fx-font-weight: bold;");
            songLabel.setPrefWidth(300);

            // Player name (center, red)
            Label playerLabel = new Label(player.getName());
            playerLabel.setStyle("-fx-text-fill: #CC0000; -fx-font-size: 20px; -fx-font-weight: bold;");
            playerLabel.setPrefWidth(200);
            playerLabel.setAlignment(Pos.CENTER);

            // Points (right, gray)
            Label pointsLabel = new Label(player.getPoints() + " POINTS");
            pointsLabel.setStyle("-fx-text-fill: #999999; -fx-font-size: 20px; -fx-font-weight: bold;");
            pointsLabel.setPrefWidth(200);
            pointsLabel.setAlignment(Pos.CENTER_RIGHT);

            row.getChildren().addAll(songLabel, playerLabel, pointsLabel);
            rankingContainer.getChildren().add(row);
        }

        startKaraokeButton.setOnAction(e -> SceneManager.get().showKaraoke());
    }
}
