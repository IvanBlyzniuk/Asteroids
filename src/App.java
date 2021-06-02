import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

public class App extends Application {
    private static Pane root;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    Stage primaryStage = new Stage();
    ArrayList<String> inputs = new ArrayList<>();
    public App(){
        initMainMenu();
    }

    private static AnimationTimer timer;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createPanel());
        primaryStage.setScene(scene);
        primaryStage.show();



        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String code = event.getCode().toString();
                if(!inputs.contains(code))
                    inputs.add(code);
//                switch (event.getCode()) {
//                    case W: LevelManager.getManager().onUpPressed();break;
//                    case S: LevelManager.getManager().onDownPressed();break;
//                    case A: LevelManager.getManager().onLeftPressed();break;
//                    case D: LevelManager.getManager().onRightPressed();break;
//                    case SPACE: LevelManager.getManager().onShootPressed();break;
//                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                inputs.remove( code );
            }
        });

    }

    public static void stopGame(){
        timer.stop();
    }

    public void launch() throws Exception {
        start(primaryStage);
    }
    private Parent createPanel(){
        Rectangle bg = new Rectangle(1000,1000);
        root = new Pane();
        root.setPrefSize(WIDTH,HEIGHT);
        root.getChildren().add(bg);
        GameEngine.startGame();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(inputs.contains("W")) LevelManager.getManager().onUpPressed();
                if(inputs.contains("A")) LevelManager.getManager().onLeftPressed();
                if(inputs.contains("D")) LevelManager.getManager().onRightPressed();
                if(inputs.contains("S")) LevelManager.getManager().onDownPressed();
                if(inputs.contains("SPACE")) LevelManager.getManager().onShootPressed();
                if(inputs.contains("SHIFT")) LevelManager.getManager().onShiftPressed();
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

    public void initMainMenu(){
        Scene scene = new Scene(createMenu());
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public Parent createMenu(){
        Pane menu = new Pane();
        menu.setPrefSize(1000,700);
        Rectangle bg = new Rectangle(1000,1000);
        Font font = Font.font(72);
        Button start = new Button("Start");
        start.setPrefWidth(500);

        start.setTranslateX(250);
        start.setTranslateY(300);
        start.setFont(font);
        start.setOnAction(event -> {
            try {
                launch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        menu.getChildren().add(bg);
        menu.getChildren().add(start);
        return menu;
    }
}
