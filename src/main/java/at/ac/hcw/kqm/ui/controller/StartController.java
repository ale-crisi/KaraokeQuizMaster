package at.ac.hcw.kqm.ui.controller;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StartController {

    @FXML
    private Button player1Button;
    @FXML
    private Button player2Button;
    @FXML
    private Button player3Button;
    @FXML
    private Button player4Button;
    @FXML
    private TextField player1Input;
    @FXML
    private TextField player2Input;
    @FXML
    private TextField player3Input;
    @FXML
    private TextField player4Input;
    @FXML
    private Button startButton;

    @FXML
    public void initialize() {
        // Don't init default players yet - will be created based on input

        // Set placeholder texts
        player1Input.setPromptText("Name von Spieler 1");
        player2Input.setPromptText("Name von Spieler 2");
        player3Input.setPromptText("Name von Spieler 3");
        player4Input.setPromptText("Name von Spieler 4");

        startButton.setOnAction(e -> {
            System.out.println("Starting game...");

            // Collect only non-empty player names
            java.util.List<String> playerNames = new java.util.ArrayList<>();
            String name1 = player1Input.getText().trim();
            String name2 = player2Input.getText().trim();
            String name3 = player3Input.getText().trim();
            String name4 = player4Input.getText().trim();

            if (!name1.isEmpty())
                playerNames.add(name1);
            if (!name2.isEmpty())
                playerNames.add(name2);
            if (!name3.isEmpty())
                playerNames.add(name3);
            if (!name4.isEmpty())
                playerNames.add(name4);

            // Require at least 1 player
            if (playerNames.size() < 1) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.WARNING);
                alert.setTitle("Zu wenig Spieler");
                alert.setHeaderText("Mindestens 1 Spieler erforderlich");
                alert.setContentText("Bitte gib mindestens 1 Spielernamen ein!");
                alert.showAndWait();
                return;
            }

            // Initialize players with entered names
            AppState.get().initPlayersWithNames(playerNames);
            AppState.get().resetSelectionFlow();

            System.out.println("Game starting with " + playerNames.size() + " players");
            SceneManager.get().showSongSelection();
        });
    }

    private void updateNameFromInput(int idx, TextField input) {
        // Not needed anymore - names are collected on start
    }

    private void updatePlayerButtons() {
        // Not needed anymore - buttons removed or not used
    }
}
