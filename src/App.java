import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

/**
 * class that creates the main menu panel and starts the game
 */
public class App extends Application  {
    public static App app = new App();
    private static Pane root;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private Stage primaryStage = new Stage();
    private ArrayList<String> inputs = new ArrayList<>();
    private static double volume;
    MediaPlayer mediaPlayer;

    private static ArrayList<ImageView> lives;

    private static Text scoreText;
    private static Text rocketsCountText;
    private static AnimationTimer timer;

    /**
     * constructor which creates the media player and sets the starting value for the volume
     */
    public App(){
        //TODO: delete later ----------------------------------------------------
        setVolume(0.1);

        String musicFile = "Sounds\\Soundtrack.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(App.getVolume()/2);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    /**
     * stops the animation timer
     */
    public static void stopGame(){
        timer.stop();
    }

    /**
     * starts the gaming process
     * @throws Exception exception
     */
    public void launch() throws Exception {
        Scene scene = new Scene(createPanel());
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String code = event.getCode().toString();
                if(!inputs.contains(code))
                    inputs.add(code);
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if(code.equals("W"))
                    LevelManager.getManager().onUpReleased();
                inputs.remove( code );
            }
        });
    }

    /**
     * creates the main gaming screen
     * @return gaming panel
     */
    private Parent createPanel(){
        Rectangle bg = new Rectangle(1000,1000);
        root = new Pane();
        root.setPrefSize(WIDTH,HEIGHT);
        root.getChildren().add(bg);

        scoreText = new Text("Score: ");
        scoreText.setX(getWIDTH()/2-110);
        scoreText.setY(40);
        scoreText.setFont(new Font(25));
        scoreText.setFill(Color.web("ffcc00"));
        root.getChildren().add(scoreText);

        ImageView rocketsIcon = new ImageView(new Image("Sprites\\RocketsIcon.png"));
        rocketsIcon.setFitHeight(40);
        rocketsIcon.setFitWidth(40);
        rocketsIcon.setX(getWIDTH()-100);
        rocketsIcon.setY(getHEIGHT()-50);
        root.getChildren().add(rocketsIcon);

        lives = new ArrayList<>();
        for (int i=0;i<3;i++){
            lives.add(new ImageView(new Image("Sprites\\SpaceShip.png")));
            lives.get(i).setFitWidth(30);
            lives.get(i).setFitHeight(30);
            lives.get(i).setY(getHEIGHT()-40);
            lives.get(i).setX(10 + 40*i);
            root.getChildren().add(lives.get(i));
        }

        rocketsCountText = new Text("0");
        rocketsCountText.setX(getWIDTH() - 50);
        rocketsCountText.setY(getHEIGHT()-20);
        rocketsCountText.setFont(new Font(30));
        rocketsCountText.setFill(Color.web("ffcc00"));
        root.getChildren().add(rocketsCountText);

        GameEngine.startGame();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(inputs.contains("ESCAPE")) LevelManager.getManager().onEscPressed();
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

    /**
     * @return window width
     */
    public static int getWIDTH() {
        return WIDTH;
    }

    /**
     * @return window height
     */
    public static int getHEIGHT() {
        return HEIGHT;
    }

    /**
     * @return main pane
     */
    public static Pane getRoot() {
        return root;
    }

    /**
     * causes creating of the main menu
     */
    public void initMainMenu(){
        Scene scene = new Scene(createMenu());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * creates the main menu screen
     * @return main menu screen
     */
    public Parent createMenu(){
        Pane menu = new Pane();
        menu.setPrefSize(1000,700);
        Rectangle bg = new Rectangle(1000,1000);
        Font font = Font.font(52);
        Button start = new Button("Start");
        BackgroundImage backgroundImage = new BackgroundImage( new Image( "Sprites\\buttonBackground.jfif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        start.setBackground(background);
        start.setPrefWidth(300);
        start.setTranslateX(350);
        start.setTranslateY(350);
        start.setFont(font);
        start.setOnAction(event -> {
            try {
                launch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Button exit = new Button("Exit");
        exit.setBackground(background);
        exit.setPrefWidth(300);
        exit.setTranslateX(350);
        exit.setTranslateY(490);
        exit.setFont(font);
        exit.setOnAction(event -> {
            try {
                primaryStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Button plus = new Button("+");
        //plus.setBackground(background);
        plus.setPrefWidth(50);
        plus.setPrefHeight(50);
        plus.setTranslateX(155);
        plus.setTranslateY(540);
        plus.setFont(new Font(20));
        plus.setOnAction(event -> {
            try {
                if(getVolume()<1) {
                    setVolume(getVolume() + 0.1);
                    mediaPlayer.setVolume(App.getVolume()/2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Button minus = new Button("-");
        //minus.setBackground(background);
        minus.setPrefWidth(50);
        minus.setPrefHeight(50);
        minus.setTranslateX(45);
        minus.setTranslateY(540);
        minus.setFont(new Font(20));
        minus.setOnAction(event -> {
            try {
                if(getVolume()>0) {
                    setVolume(getVolume() - 0.1);
                    mediaPlayer.setVolume(App.getVolume()/2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ImageView logo = new ImageView(new Image("Sprites\\logo.jfif"));
        logo.setFitWidth(500);
        logo.setFitHeight(200);
        logo.setX(250);
        logo.setY(50);
        ImageView volume = new ImageView(new Image("Sprites\\volume.jpg"));
        volume.setFitWidth(50);
        volume.setFitHeight(50);
        volume.setX(100);
        volume.setY(540);
        menu.getChildren().add(bg);
        menu.getChildren().add(plus);
        menu.getChildren().add(minus);
        menu.getChildren().add(start);
        menu.getChildren().add(exit);
        menu.getChildren().add(logo);
        menu.getChildren().add(volume);
        return menu;
    }

    /**
     * @return current volume value
     */
    public static double getVolume() {
        return volume;
    }

    /**
     * @param volume new volume value
     */
    public static void setVolume(double volume) {
        App.volume = volume;
    }

    /**
     * @return App class object
     */
    public static App getApp() {
        return app;
    }

    /**
     * @return AnimationTimer
     */
    public static AnimationTimer getTimer() {
        return timer;
    }

    /**
     * @return MediaPlayer
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * changes the score on the screen
     */
    public static void updateScore(){
        scoreText.setText("Score: "+LevelManager.getManager().getScore()+"     Lvl: "+LevelManager.getManager().getLevel());
    }

    /**
     * changes the rockets counter on the screen
     */
    public static void updateRocketsCount(){
        rocketsCountText.setText(Integer.toString(LevelManager.getManager().getRocketsNumber()));
    }

    /**
     * remove one life
     */
    public static void removeLife(){
        App.getRoot().getChildren().remove(lives.get(lives.size()-1));
        lives.remove(lives.size()-1);
    }

    /**
     * @return primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
