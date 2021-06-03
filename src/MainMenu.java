import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        App a = new App();
        a.initMainMenu();
    }
}
