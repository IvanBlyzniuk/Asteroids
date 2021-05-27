import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    private static Pane root;
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
                    case W: LevelManager.getManager().onUpPressed();
                    case S: LevelManager.getManager().onDownPressed();
                    case A: LevelManager.getManager().onLeftPressed();
                    case D: LevelManager.getManager().onRightPressed();
                    case SPACE: LevelManager.getManager().onShootPressed();
                }
            }
        });

    }
    private Parent createPanel(){
        root = new Pane();
        root.setPrefSize(WIDTH,HEIGHT);
        GameEngine.startGame();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GameEngine.update();
            }
        };
        timer.start();

        return root;

    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static Pane getRoot() {
        return root;
    }
}
