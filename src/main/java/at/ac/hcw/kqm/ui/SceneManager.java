package at.ac.hcw.kqm.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SceneManager {
    private static final Logger LOGGER = Logger.getLogger(SceneManager.class.getName());

    private static SceneManager instance;
    private final Stage primaryStage;

    private static final int BASE_WIDTH = 1200;
    private static final int BASE_HEIGHT = 800;

    private SceneManager(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setResizable(false);
    }

    public static void init(Stage stage) {
        instance = new SceneManager(stage);
    }

    public static SceneManager get() {
        if (instance == null) {
            throw new IllegalStateException("SceneManager not initialized. Call SceneManager.init(stage) first.");
        }
        return instance;
    }

    public void showStart() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/Start.fxml", "Karaoke Quiz Master – Start");
    }

    public void showSongSelection() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/SongSelection.fxml", "Song Auswahl");
    }

    public void showQuiz() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/Quiz.fxml", "Quiz");
    }

    public void showTieBreak() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/TieBreak.fxml", "Tie-Break");
    }

    /** Finales Ergebnis */
    public void showResult() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/Result.fxml", "Ergebnisse");
    }

    /** TieBreak-Intro: zeigt die Spieler im Gleichstand + Punkte */
    public void showTieBreakIntro() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/ResultTieBreak.fxml", "Tie-Break – Ergebnisse");
    }

    public void showKaraoke() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/Karaoke.fxml", "Karaoke");
    }

    public void showJokerRules() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/JokerRules.fxml", "Joker Regeln");
    }

    public void showJokerFill() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/JokerFill.fxml", "Fill & Jump Joker");
    }

    public void showJokerFifty() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/JokerFifty.fxml", "50/50 Joker");
    }

    public void showJokerHint() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/JokerHint.fxml", "Hint Joker");
    }

    private void loadAndShow(String fxmlPath, String title) {
        try {
            URL resource = getResource(fxmlPath);
            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();

            Scene scene = new Scene(root, BASE_WIDTH, BASE_HEIGHT);

            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
            primaryStage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException while loading " + fxmlPath, e);
            throw new RuntimeException("Failed to load FXML: " + fxmlPath, e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception while loading " + fxmlPath, e);
            throw new RuntimeException("Failed to load FXML: " + fxmlPath, e);
        }
    }

    private URL getResource(String path) {
        URL url = SceneManager.class.getResource(path);
        return Objects.requireNonNull(url, "Resource not found: " + path);
    }
}
