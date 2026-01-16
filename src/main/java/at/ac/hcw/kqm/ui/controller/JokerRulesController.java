package at.ac.hcw.kqm.ui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import at.ac.hcw.kqm.ui.SceneManager;

public class JokerRulesController {

    @FXML
    private Button skipButton;

    @FXML
    private ImageView backgroundImage;


    @FXML
    private void initialize() {
        // Load the JokerRules.png image programmatically
        try {
            String imagePath = "/at/ac/hcw/kqm/ui/fxml/assets/JokerRules.png";
            Image image = new Image(JokerRulesController.class.getResourceAsStream(imagePath));
            backgroundImage.setImage(image);
        } catch (Exception e) {
            System.err.println("Failed to load JokerRules.png: " + e.getMessage());
            e.printStackTrace();
        }

        // Nur durch Button-Klick weitergehen, keine automatische Weiterleitung
    }

    @FXML
    private void handleSkip(ActionEvent event) {
        System.out.println("Weiter button clicked - navigating to SongSelection");
        SceneManager.get().showSongSelection();
    }
}
