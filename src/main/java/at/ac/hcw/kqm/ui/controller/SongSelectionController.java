package at.ac.hcw.kqm.ui.controller;

import java.util.List;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.model.Song;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SongSelectionController {

    @FXML
    private Label currentPlayerLabel;
    @FXML
    private Button song1Button, song2Button, song3Button, song4Button, song5Button,
            song6Button, song7Button, song8Button, song9Button, song10Button;
    @FXML
    private Button selectButton;

    private List<Song> topTen;
    private int selectedIndex = -1;

    @FXML
    public void initialize() {
        try {
            topTen = AppState.get().getTopTenSongs();
            System.out.println("Loaded " + topTen.size() + " songs");

            // Set button labels
            setButton(song1Button, 0);
            setButton(song2Button, 1);
            setButton(song3Button, 2);
            setButton(song4Button, 3);
            setButton(song5Button, 4);
            setButton(song6Button, 5);
            setButton(song7Button, 6);
            setButton(song8Button, 7);
            setButton(song9Button, 8);
            setButton(song10Button, 9);

            // Disable already-picked songs if any
            refreshDisabledSongs();

            updateCurrentPlayerLabel();
            selectButton.setDisable(true);

            selectButton.setOnAction(e -> {
                selectButton.setText("WIRD GELADEN...");
                System.out.println("Select button clicked");
                try {
                    onSelect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                            javafx.scene.control.Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Fehler beim Starten");
                    alert.setContentText(ex.getMessage() + "\n\n" + java.util.Arrays.toString(ex.getStackTrace()));
                    alert.showAndWait();
                }
            });

            updateSelectButtonText();
        } catch (Exception ex) {
            System.err.println("Error in SongSelectionController.initialize: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void setButton(Button b, int idx) {
        Song s = topTen.get(idx);
        b.setText(s.getDisplayName());
        b.setOnAction(e -> {
            System.out.println("Song button clicked: " + s.getDisplayName() + " (index: " + idx + ")");
            selectedIndex = idx;
            System.out.println("Selected index set to: " + selectedIndex);
            selectButton.setDisable(false);
            // Visuelles Feedback
            highlightSelectedButton(b);
        });
    }

    private void updateCurrentPlayerLabel() {
        currentPlayerLabel.setText("Aktueller Spieler: " + AppState.get().getCurrentSelectionPlayer().getName());
    }

    private void updateSelectButtonText() {
        int currentPlayerIndex = AppState.get().getPlayers().indexOf(AppState.get().getCurrentSelectionPlayer());
        int totalPlayers = AppState.get().getPlayers().size();

        if (currentPlayerIndex < totalPlayers - 1) {
            // Noch nicht der letzte Spieler
            selectButton.setText("Weiter zum nächsten Spieler");
        } else {
            // Der letzte Spieler - nach diesem kommt das Quiz
            selectButton.setText("Quiz starten");
        }
    }

    private void highlightSelectedButton(Button selected) {
        // Alle Buttons zurücksetzen
        song1Button.setStyle("");
        song2Button.setStyle("");
        song3Button.setStyle("");
        song4Button.setStyle("");
        song5Button.setStyle("");
        song6Button.setStyle("");
        song7Button.setStyle("");
        song8Button.setStyle("");
        song9Button.setStyle("");
        song10Button.setStyle("");

        // Ausgewählten Button hervorheben
        selected.setStyle("-fx-border-color: #ffed4e; -fx-border-width: 3px;");
    }

    private void onSelect() {
        System.out.println("=== onSelect called ===");
        System.out.println("selectedIndex: " + selectedIndex);
        if (selectedIndex < 0) {
            System.out.println("No song selected");
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                    javafx.scene.control.Alert.AlertType.WARNING);
            alert.setTitle("Kein Song gewählt");
            alert.setHeaderText("Bitte Song auswählen");
            alert.setContentText(
                    "Du musst zuerst einen Song auswählen (auf einen Song-Button klicken), bevor du fortfahren kannst!");
            alert.showAndWait();
            return; // require a selection
        }
        try {
            Song chosen = topTen.get(selectedIndex);
            System.out.println("Player selected: " + chosen.getDisplayName());
            AppState.get().selectSongForCurrentPlayer(chosen);
            // Disable the chosen song for next players
            Button chosenBtn = buttonByIndex(selectedIndex);
            if (chosenBtn != null) {
                chosenBtn.setDisable(true);
                chosenBtn.setStyle(chosenBtn.getStyle() + "; -fx-opacity: 0.6;");
            }
            selectedIndex = -1;
            selectButton.setDisable(true);

            boolean finished = AppState.get().advanceSelectionPlayer();
            System.out.println("finished: " + finished);
            System.out.println("Selected songs count: " + AppState.get().getSelectedSongs().size());
            if (finished) {
                // All players selected songs
                System.out.println("All players done. Starting quiz...");
                selectButton.setText("Spiel starten...");
                System.out.println("About to call startQuizGame()");
                AppState.get().startQuizGame();
                System.out.println("startQuizGame() completed");
                selectButton.setText("Zeige Quiz...");
                System.out.println("About to call showQuiz()");
                SceneManager.get().showQuiz();
                System.out.println("showQuiz() completed");
            } else {
                System.out.println("Next player...");
                // Alle Buttons zurücksetzen
                song1Button.setStyle("");
                song2Button.setStyle("");
                song3Button.setStyle("");
                song4Button.setStyle("");
                song5Button.setStyle("");
                song6Button.setStyle("");
                song7Button.setStyle("");
                song8Button.setStyle("");
                song9Button.setStyle("");
                song10Button.setStyle("");
                // Keep already selected songs disabled
                refreshDisabledSongs();
                updateCurrentPlayerLabel();
                updateSelectButtonText();
            }
        } catch (Exception ex) {
            System.err.println("Error in onSelect: " + ex.getMessage());
            ex.printStackTrace();
        }
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

    private void refreshDisabledSongs() {
        List<Song> already = AppState.get().getSelectedSongs();
        for (int i = 0; i < topTen.size(); i++) {
            Song s = topTen.get(i);
            Button b = buttonByIndex(i);
            if (b == null)
                continue;
            if (already.contains(s)) {
                b.setDisable(true);
                b.setStyle(b.getStyle() + "; -fx-opacity: 0.6;");
            }
        }
    }
}
