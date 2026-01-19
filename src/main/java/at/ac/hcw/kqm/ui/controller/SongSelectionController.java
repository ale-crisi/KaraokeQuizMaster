package at.ac.hcw.kqm.ui.controller;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Song;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.List;

public class SongSelectionController {

    @FXML
    private Label currentPlayerLabel;

    @FXML
    private Button song1Button, song2Button, song3Button, song4Button, song5Button,
            song6Button, song7Button, song8Button, song9Button, song10Button;

    private List<Song> topTen;

    @FXML
    public void initialize() {
        // Grundvalidierung: Gibt es Spieler?
        if (AppState.get().getPlayers().isEmpty()) {
            showErrorAndGoToStart(
                    "Keine Spieler initialisiert",
                    "Es wurden noch keine Spieler angelegt. Bitte starte das Spiel erneut im Start-Screen."
            );
            return;
        }

        // Songs laden
        topTen = AppState.get().getTopTenSongs();
        if (topTen == null || topTen.isEmpty()) {
            showErrorAndGoToStart(
                    "Keine Songs verfügbar",
                    "Es sind keine verfügbaren Songs vorhanden. Bitte prüfe dein SongRepository."
            );
            return;
        }

        // UI vorbereiten
        updateCurrentPlayerLabel();

        // Buttons konfigurieren (nur so viele, wie Songs verfügbar sind)
        configureSongButton(song1Button, 0);
        configureSongButton(song2Button, 1);
        configureSongButton(song3Button, 2);
        configureSongButton(song4Button, 3);
        configureSongButton(song5Button, 4);
        configureSongButton(song6Button, 5);
        configureSongButton(song7Button, 6);
        configureSongButton(song8Button, 7);
        configureSongButton(song9Button, 8);
        configureSongButton(song10Button, 9);

        // Bereits gewählte Songs deaktivieren (weil Screen neu geladen wird pro Spieler)
        refreshDisabledSongs();
    }

    private void configureSongButton(Button button, int index) {
        if (button == null) {
            return;
        }

        // Wenn weniger Songs vorhanden sind als Buttons: Button deaktivieren/ausblenden
        if (index >= topTen.size()) {
            button.setText("-");
            button.setDisable(true);
            button.setVisible(false); // optional: ausblenden statt deaktivieren
            return;
        }

        Song song = topTen.get(index);
        button.setText(song.getTitle());

        // Falls Song bereits gewählt wurde: deaktivieren
        if (AppState.get().getSelectedSongs().contains(song)) {
            disablePickedButton(button);
            return;
        }

        // Ein Klick = sofort auswählen
        button.setOnAction(e -> handleSongChosen(index, button));
    }

    private void handleSongChosen(int index, Button clickedButton) {
        // Sicherheitscheck
        if (index < 0 || index >= topTen.size()) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Ungültige Songauswahl", "Der ausgewählte Song ist ungültig.");
            return;
        }

        Song chosen = topTen.get(index);

        // Doppel-Auswahl verhindern (auch wenn ein Button mal nicht korrekt disabled wäre)
        if (AppState.get().getSelectedSongs().contains(chosen)) {
            showAlert(Alert.AlertType.WARNING, "Schon gewählt", "Song bereits gewählt",
                    "Dieser Song wurde bereits gewählt. Bitte wähle einen anderen.");
            disablePickedButton(clickedButton);
            return;
        }

        // Song für aktuellen Spieler speichern
        Player current = AppState.get().getCurrentSelectionPlayer();
        AppState.get().selectSongForCurrentPlayer(chosen);

        // Button sofort deaktivieren (visuelles Feedback)
        disablePickedButton(clickedButton);

        // Nächster Spieler / oder Quiz starten
        boolean finished = AppState.get().advanceSelectionPlayer();

        if (finished) {
            // Alle haben gewählt → Quiz starten
            try {
                AppState.get().startQuizGame();
                SceneManager.get().showQuiz();
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Fehler", "Quiz konnte nicht gestartet werden", ex.getMessage());
                // Optional: zurück zur SongSelection oder Start
                SceneManager.get().showSongSelection();
            }
        } else {
            // Weiter zum nächsten Spieler: Screen neu laden, damit Label/Disabled-Buttons passen
            SceneManager.get().showSongSelection();
        }
    }

    private void updateCurrentPlayerLabel() {
        Player current = AppState.get().getCurrentSelectionPlayer();
        currentPlayerLabel.setText(current != null ? current.getName() : "-");
    }

    private void refreshDisabledSongs() {
        List<Song> already = AppState.get().getSelectedSongs();
        for (int i = 0; i < topTen.size() && i < 10; i++) {
            Song s = topTen.get(i);
            if (already.contains(s)) {
                Button b = buttonByIndex(i);
                if (b != null) {
                    disablePickedButton(b);
                }
            }
        }
    }

    private void disablePickedButton(Button b) {
        b.setDisable(true);
        // Optional: leichte "ausgegraut"-Optik
        b.setStyle((b.getStyle() == null ? "" : b.getStyle()) + "; -fx-opacity: 0.6;");
    }

    private Button buttonByIndex(int idx) {
        return switch (idx) {
            case 0 -> song1Button;
            case 1 -> song2Button;
            case 2 -> song3Button;
            case 3 -> song4Button;
            case 4 -> song5Button;
            case 5 -> song6Button;
            case 6 -> song7Button;
            case 7 -> song8Button;
            case 8 -> song9Button;
            case 9 -> song10Button;
            default -> null;
        };
    }

    private void showErrorAndGoToStart(String header, String content) {
        showAlert(Alert.AlertType.ERROR, "Fehler", header, content);
        SceneManager.get().showStart();
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
    }
}
