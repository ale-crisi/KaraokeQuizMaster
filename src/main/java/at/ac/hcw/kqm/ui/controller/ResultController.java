package at.ac.hcw.kqm.ui.controller;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Song;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class ResultController {

    @FXML private VBox rankingContainer;
    @FXML private Button startKaraokeButton;

    @FXML
    public void initialize() {
        // Defensive: falls FXML/Injection nicht passt, sofort klare Fehlermeldung
        if (rankingContainer == null || startKaraokeButton == null) {
            throw new IllegalStateException("FXML Injection fehlgeschlagen: rankingContainer oder startKaraokeButton ist null. Prüfe fx:id in Result.fxml.");
        }

        List<Player> ranking = AppState.get().getEngine().getRanking();

        rankingContainer.getChildren().clear();

        for (Player player : ranking) {
            Song song = AppState.get().getSongForPlayer(player);
            String songTitle = (song != null) ? song.getTitle() : "N/A";

            HBox row = new HBox(90);
            row.setAlignment(Pos.CENTER_LEFT);

            Label nameLabel = new Label(player.getName());
            nameLabel.setPrefWidth(100);
            nameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold;");

            Label songLabel = new Label(songTitle);
            songLabel.setPrefWidth(200);
            songLabel.setWrapText(true);
            songLabel.setStyle("-fx-text-fill: #FFF299; -fx-font-size: 15px; -fx-font-weight: bold;");

            Label pointsLabel = new Label(player.getPoints() + " PTS");
            pointsLabel.setPrefWidth(60);
            pointsLabel.setStyle("-fx-text-fill: #CCCCCC; -fx-font-size: 15px; -fx-font-weight: bold;");

            row.getChildren().addAll(nameLabel, songLabel, pointsLabel);
            rankingContainer.getChildren().add(row);
        }

        startKaraokeButton.setOnAction(e -> SceneManager.get().showKaraoke());
    }
}
