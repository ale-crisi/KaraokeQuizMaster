package at.ac.hcw.kqm.ui.controller;

import at.ac.hcw.kqm.app.AppState;
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
import javafx.scene.layout.VBox;

import java.util.List;

public class ResultTieBreakController {

    @FXML private VBox tieBreakRankingContainer;
    @FXML private Button startBattleButtonImage;

    @FXML
    public void initialize() {
        List<Player> tiedPlayers = AppState.get().getEngine().getTiedPlayers();

        tieBreakRankingContainer.getChildren().clear();

        if (tiedPlayers == null || tiedPlayers.isEmpty()) {
            Label fallback = new Label("Kein Gleichstand gefunden.");
            fallback.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
            tieBreakRankingContainer.getChildren().add(fallback);
            return;
        }

        for (Player player : tiedPlayers) {
            Song song = AppState.get().getSongForPlayer(player);
            String songTitle = (song != null) ? song.getTitle() : "N/A";

            HBox row = new HBox(100);
            row.setAlignment(Pos.CENTER_LEFT);

            Label nameLabel = new Label(player.getName());
            nameLabel.setPrefWidth(150);
            nameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold;");

            Label songLabel = new Label(songTitle);
            songLabel.setPrefWidth(200);
            songLabel.setWrapText(true);
            songLabel.setStyle("-fx-text-fill: #FFF299; -fx-font-size: 15px; -fx-font-weight: bold;");

            Label pointsLabel = new Label(player.getPoints() + " PTS");
            pointsLabel.setPrefWidth(60);
            pointsLabel.setStyle("-fx-text-fill: #CCCCCC; -fx-font-size: 15px; -fx-font-weight: bold;");

            row.getChildren().addAll(nameLabel, songLabel, pointsLabel);
            tieBreakRankingContainer.getChildren().add(row);
        }

        startBattleButtonImage.setOnAction(e -> {
            TieBreakManager tieBreakManager = new TieBreakManager();
            List<Question> tieBreakQuestions = tieBreakManager.getProgrammingQuestions();
            AppState.get().getEngine().startTieBreak(tieBreakQuestions);
            SceneManager.get().showTieBreak();
        });
    }
}
