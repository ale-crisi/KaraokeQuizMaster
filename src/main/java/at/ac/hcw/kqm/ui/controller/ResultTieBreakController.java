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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.util.List;

public class ResultTieBreakController {
    @FXML private StackPane tieBreakBackground;
    @FXML private VBox tieBreakRankingContainer;
    @FXML private Button startBattleButtonImage;
    @FXML private Button showFinalResultButton;

    @FXML
    public void initialize() {
        List<Player> ranking = AppState.get().getEngine().getRanking();
        tieBreakRankingContainer.getChildren().clear();
        for (Player player : ranking) {
            Song song = AppState.get().getSongForPlayer(player);
            String songTitle = (song != null) ? song.getTitle() : "N/A";
            HBox row = new HBox(76);
            row.setAlignment(Pos.CENTER);
            Label nameLabel = new Label(player.getName());
            nameLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");
            nameLabel.setPrefWidth(150);
            nameLabel.setAlignment(Pos.CENTER);
            Label songLabel = new Label(songTitle);
            songLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFF299; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");
            songLabel.setPrefWidth(250);
            songLabel.setWrapText(true);
            songLabel.setAlignment(Pos.CENTER);
            Label pointsLabel = new Label(player.getPoints() + " PTS");
            pointsLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: #CCCCCC; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 20 10 20;");
            pointsLabel.setPrefWidth(100);
            pointsLabel.setAlignment(Pos.CENTER);
            row.getChildren().addAll(nameLabel, songLabel, pointsLabel);
            tieBreakRankingContainer.getChildren().add(row);
        }
        startBattleButtonImage.setOnAction(e -> {
            TieBreakManager tieBreakManager = new TieBreakManager();
            List<Question> tieBreakQuestions = tieBreakManager.getProgrammingQuestions();
            AppState.get().getEngine().startTieBreak(tieBreakQuestions);
            SceneManager.get().showTieBreak();
        });
        showFinalResultButton.setOnAction(e -> {
            SceneManager.get().showResult();
        });
    }
}
