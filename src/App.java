import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    private Pane root;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createPanel());
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                    case S:
                    case A:
                    case D:
                    case SPACE:
                }
            }
        });
    }
    private Parent createPanel(){
        root = new Pane();
        root.setPrefSize(WIDTH,HEIGHT);
        return root;

    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
