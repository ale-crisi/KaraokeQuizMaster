package at.ac.hcw.kqm.app;

import at.ac.hcw.kqm.ui.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneManager.init(primaryStage);
        SceneManager.get().showStart();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
