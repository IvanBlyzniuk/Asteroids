import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main Class that creates a window
 */
public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.getApp().getPrimaryStage().setResizable(false);
        App.getApp().initMainMenu();

    }
}
