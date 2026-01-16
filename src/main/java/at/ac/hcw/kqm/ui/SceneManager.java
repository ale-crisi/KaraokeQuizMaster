package at.ac.hcw.kqm.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SceneManager {

    private static SceneManager instance;
    private final Stage primaryStage;

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
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/Start.fxml", 1200, 800, "Karaoke Quiz Master – Start");
    }

    public void showSongSelection() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/SongSelection.fxml", 1200, 800, "Song Auswahl");
    }

    public void showQuiz() {
        System.out.println("SceneManager.showQuiz() called");
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/Quiz.fxml", 1200, 800, "Quiz");
        System.out.println("SceneManager.showQuiz() finished");
    }

    public void showTieBreak() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/TieBreak.fxml", 900, 700, "Tie-Break");
    }

    public void showResult() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/Result.fxml", 900, 600, "Ergebnisse");
    }

    public void showKaraoke() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/Karaoke.fxml", 1000, 700, "Karaoke");
    }


    public void showJokerRules() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/JokerRules.fxml", 1200, 800, "Joker Regeln");
    }

    public void showKing() {
        loadAndShow("/at/ac/hcw/kqm/ui/fxml/King.fxml", 1200, 800, "King Joker");
    }

    /**
     * Loads an FXML file from the classpath and shows it on the primary stage.
     */
    public void loadAndShow(String fxmlPath, int width, int height, String title) {
        try {
            System.out.println("SceneManager: Loading FXML: " + fxmlPath);
            URL resource = getResource(fxmlPath);
            System.out.println("SceneManager: Resource found: " + resource);
            FXMLLoader loader = new FXMLLoader(resource);
            System.out.println("SceneManager: FXMLLoader created");
            Parent root = loader.load();
            System.out.println("SceneManager: FXML loaded successfully");
            Scene scene = new Scene(root, width, height);
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("SceneManager: Scene shown successfully");
        } catch (IOException e) {
            System.err.println("SceneManager: IOException while loading " + fxmlPath);
            e.printStackTrace();
            throw new RuntimeException("Failed to load FXML: " + fxmlPath, e);
        } catch (Exception e) {
            System.err.println("SceneManager: Exception while loading " + fxmlPath);
            e.printStackTrace();
            throw new RuntimeException("Failed to load FXML: " + fxmlPath, e);
        }
    }

    private URL getResource(String path) {
        URL url = SceneManager.class.getResource(path);
        return Objects.requireNonNull(url, "Resource not found: " + path);
    }
}
