package at.ac.hcw.kqm.ui.controller;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.engine.GameEngine;
import at.ac.hcw.kqm.engine.TieBreakManager;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Question;
import at.ac.hcw.kqm.model.Song;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ResultController {

    @FXML
    private StackPane mainContainer;
    @FXML
    private VBox normalResultContainer;
    @FXML
    private StackPane tieBreakBackground;
    @FXML
    private VBox rankingContainer;
    @FXML
    private Button startKaraokeButton;
    @FXML
    private VBox battleContainer;
    @FXML
    private Button startBattleButton;
    @FXML
    private VBox tieBreakRankingContainer;
    @FXML
    private VBox tieBreakPlayersContainer;
    @FXML
    private Button startBattleButtonImage;

    @FXML
    public void initialize() {
        System.out.println("ResultController.initialize() called");
        GameEngine engine = AppState.get().getEngine();
        List<Player> ranking = engine.getRanking();
        List<Player> players = AppState.get().getPlayers();

        // Check if tie-break is needed
        boolean needsTieBreak = engine.needsTieBreak();
        System.out.println("ResultController: needsTieBreak=" + needsTieBreak);
        System.out.println("Ranking:");
        for (Player p : ranking) {
            System.out.println("Player: " + p.getName() + ", Punkte: " + p.getPoints());
        }

        if (needsTieBreak) {
            // Show battle mode with background image
            System.out.println("ResultController: Showing battle mode");
            battleContainer.setVisible(true);
            showBattleMode(ranking);
            startBattleButton.setVisible(true);
            startBattleButtonImage.setVisible(true);
            startKaraokeButton.setVisible(false);
        } else {
            // Show normal ranking
            System.out.println("ResultController: Showing normal ranking");
            showNormalRanking(ranking, players);
            startKaraokeButton.setVisible(true);
        }
        System.out.println("ResultController.initialize() completed");
    }

    private void showBattleMode(List<Player> ranking) {
        // Hide normal container, show tie-break background
        normalResultContainer.setVisible(false);
        tieBreakBackground.setVisible(true);
        battleContainer.setVisible(true);
        startBattleButtonImage.setVisible(true);

        // Clear and populate ranking on tie-break background
        tieBreakRankingContainer.getChildren().clear();
        // Shift the ranking area: ~1cm down (~38px) and ~2cm left (~76px)
        tieBreakRankingContainer.setTranslateY(76); // ~2 cm down total
        tieBreakRankingContainer.setTranslateX(-266); // move 1 cm right from previous
        for (int i = 0; i < ranking.size(); i++) {
            Player player = ranking.get(i);
            Song song = AppState.get().getSongForPlayer(player);
            String songTitle = (song != null) ? song.getTitle() : "N/A";

            HBox row = new HBox(76); // ~2 cm spacing between fields
            row.setAlignment(Pos.CENTER);
            row.setTranslateY(-38); // Alle Zeilen: 1 cm nach oben

            // Name field (small box)
            Label nameLabel = new Label(player.getName());
            nameLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");
            nameLabel.setPrefWidth(150);
            nameLabel.setAlignment(Pos.CENTER);

            // Song field (small box)
            Label songLabel = new Label(songTitle);
            songLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFF299; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");
            songLabel.setPrefWidth(250);
            songLabel.setWrapText(true);
            songLabel.setAlignment(Pos.CENTER);

            // Points field (small box)
            Label pointsLabel = new Label(player.getPoints() + " PTS");
            pointsLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: #CCCCCC; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");
            pointsLabel.setPrefWidth(100);
            pointsLabel.setAlignment(Pos.CENTER);

            row.getChildren().addAll(nameLabel, songLabel, pointsLabel);
            tieBreakRankingContainer.getChildren().add(row);
        }

        // Set up battle button
        startBattleButton.setOnAction(e -> {
            TieBreakManager tieBreakManager = new TieBreakManager();
            List<Question> tieBreakQuestions = tieBreakManager.getProgrammingQuestions();
            AppState.get().getEngine().startTieBreak(tieBreakQuestions);
            SceneManager.get().showTieBreak();
        });
        startBattleButtonImage.setOnAction(e -> {
            TieBreakManager tieBreakManager = new TieBreakManager();
            List<Question> tieBreakQuestions = tieBreakManager.getProgrammingQuestions();
            AppState.get().getEngine().startTieBreak(tieBreakQuestions);
            SceneManager.get().showTieBreak();
        });
    }

    private void showNormalRanking(List<Player> ranking, List<Player> players) {
        // Show normal container, hide tie-break background
        normalResultContainer.setVisible(true);
        tieBreakBackground.setVisible(false);
        battleContainer.setVisible(false);
        startBattleButtonImage.setVisible(false);
        startKaraokeButton.setVisible(true);

        // Clear and populate ranking
        rankingContainer.getChildren().clear();
        for (int i = 0; i < ranking.size(); i++) {
            Player player = ranking.get(i);

            Song song = AppState.get().getSongForPlayer(player);
            String songTitle = (song != null) ? song.getTitle() : "N/A";

            HBox row = new HBox(76); // ~2 cm spacing between fields (like TieBreak)
            row.setAlignment(Pos.CENTER);

            // Apply consistent spacing: each row 1cm (38px) further down
            if (i > 0) {
                row.setTranslateY(i * 38);
            }

            // Name field
            Label nameLabel = new Label(player.getName());
            nameLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");
            nameLabel.setPrefWidth(150);
            nameLabel.setAlignment(Pos.CENTER);

            // Song field
            Label songLabel = new Label(songTitle);
            songLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFF299; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");
            songLabel.setPrefWidth(250);
            songLabel.setWrapText(true);
            songLabel.setAlignment(Pos.CENTER);

            // Points field
            Label pointsLabel = new Label(player.getPoints() + " PTS");
            pointsLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: #CCCCCC; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");
            pointsLabel.setPrefWidth(100);
            pointsLabel.setAlignment(Pos.CENTER);

            row.getChildren().addAll(nameLabel, songLabel, pointsLabel);
            rankingContainer.getChildren().add(row);
        }

        startKaraokeButton.setOnAction(e -> SceneManager.get().showKaraoke());
    }
}
