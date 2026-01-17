package at.ac.hcw.kqm.ui.controller;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StartController {

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
        player1Input.setPromptText("");
        player2Input.setPromptText("");
        player3Input.setPromptText("");
        player4Input.setPromptText("");
    }

    @FXML
    private void startButtonClicked() {
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

        // Initialize players only if not already initialized
        if (AppState.get().getPlayers().isEmpty()) {
            AppState.get().initPlayersWithNames(playerNames);
        }

        System.out.println("Game starting with " + playerNames.size() + " players");
        // Zuerst JokerRules für 8 Sekunden anzeigen, dann zur SongSelection
        SceneManager.get().showJokerRules();
    }
}
