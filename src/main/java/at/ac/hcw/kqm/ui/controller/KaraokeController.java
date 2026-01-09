package at.ac.hcw.kqm.ui.controller;

import java.util.List;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Song;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class KaraokeController {

    @FXML
    private Label playerLabel;
    @FXML
    private Label songLabel;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button playButton, pauseButton, stopButton, nextButton;

    private List<Player> ranking;
    private List<Song> selectedSongs;
    private int idx = 0;
    private MediaPlayer player;

    @FXML
    public void initialize() {
        ranking = AppState.get().getEngine().getRanking();
        selectedSongs = AppState.get().getSelectedSongs();
        showCurrent();

        playButton.setOnAction(e -> {
            if (player != null)
                player.play();
        });
        pauseButton.setOnAction(e -> {
            if (player != null)
                player.pause();
        });
        stopButton.setOnAction(e -> {
            if (player != null)
                player.stop();
        });
        nextButton.setOnAction(e -> {
            idx = (idx + 1) % ranking.size();
            showCurrent();
        });
    }

    private void showCurrent() {
        Player p = ranking.get(idx);
        Song s = AppState.get().getSongForPlayer(p);
        playerLabel.setText("Spieler: " + p.getName());
        songLabel.setText("Song: " + s.getDisplayName());

        // If audio path set, attach media; otherwise, leave empty
        if (s != null && s.getAudioFilePath() != null && !s.getAudioFilePath().isEmpty()) {
            Media media = new Media(s.getAudioFilePath());
            player = new MediaPlayer(media);
            mediaView.setMediaPlayer(player);
        } else {
            mediaView.setMediaPlayer(null);
            player = null;
        }
    }
}
