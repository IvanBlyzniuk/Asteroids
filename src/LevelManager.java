import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Random;

/**
 * class which rules the gaming process and creates different game objects
 */
public class LevelManager extends GameObject{
    private SpaceShip player;
    private static LevelManager theManager;
    public static final Random random = new Random();
    private int asteroidsNumber = 0;
    private boolean timerStopped = false;
    private int asteroidsMaxNumber = 5;
    private int astronautsNumber = 0;
    private int astronautsMaxNumber = 1;
    private static int lives = 3;
    private int level = 1;

    private String spaceship_flying_1 = "Sprites\\spaceship_flying_1.png";
    private String spaceship_flying_2 = "Sprites\\spaceship_flying_2.png";

    private static int pickupSpawnCooldown;

    private static final int shotRechargeTime = 20;

    private boolean canSpawnPickup = true;

    private int score = 0;

    MediaPlayer shipSoundPlayer = new MediaPlayer(new Media(new File("Sounds\\SpaceShip_move.mp3").toURI().toString()));

    public static LevelManager getManager(){
        return theManager;
    }

    /**
     * creates the level manager
     */
    public static void initManager() {
        theManager = new LevelManager();
        lives = 3;
    }

    /**
     * creates the player
     */
    @Override
    public void init() { player = new SpaceShip(); }

    /**
     * @return pickupSpawnCooldown in milliseconds
     */
    public static int getPickupSpawnCooldown() {
        return pickupSpawnCooldown;
    }

    /**
     * sets the pickup spawn cooldown
     * @param pickupSpawnCooldown time in milliseconds
     */
    public static void setPickupSpawnCooldown(int pickupSpawnCooldown) {
        LevelManager.pickupSpawnCooldown = pickupSpawnCooldown;
    }

    /**
     * <p>updates the game creating asteroids and astronauts </p>
     * <p>updates the score and levels</p>
     * <p>updates the rockets counter</p>
     */
    @Override
    public void update() {
        App.updateScore();
        App.updateRocketsCount();
        checkLevel();

        if(pickupSpawnCooldown > 0){
            pickupSpawnCooldown--;
        }

        player.setSpeed(new Point2D(player.getSpeed().getX()*0.98,player.getSpeed().getY()*0.98));
        int rand = random.nextInt(100);
        if(rand == 0 && asteroidsNumber<asteroidsMaxNumber){
            createAsteroid();
            asteroidsNumber++;
        }
        rand = random.nextInt(500);
        if(rand == 0 && astronautsNumber<astronautsMaxNumber){
            createAstronaut();
            astronautsNumber++;
        }
    }

    /**
     * creates new astronaut
     */
    public void createAstronaut(){
        double x = 0;
        double y = 0;
        int line = random.nextInt(4);
        if(line == 0){
            x = -100;
            y = random.nextInt(App.getHEIGHT());
        }
        if(line == 1){
            y = -100;
            x = random.nextInt(App.getWIDTH());
        }
        if(line == 2){
            y = App.getHEIGHT()+100;
            x = random.nextInt(App.getWIDTH());
        }
        if(line == 3){
            x = App.getWIDTH()+100;
            y = random.nextInt(App.getHEIGHT());
        }
        int angle = random.nextInt(360);
        double speed = random.nextDouble()+0.5;
        Astronaut astronaut = new Astronaut();
        astronaut.setX(x);
        astronaut.setY(y);
        astronaut.setSpeedM(speed,angle);
    }

    /**
     * creates new asteroid
     */
    public void createAsteroid(){
        double x = 0;
        double y = 0;
        int line = random.nextInt(4);
        if(line == 0){
             x = -100;
             y = random.nextInt(App.getHEIGHT());
        }
        if(line == 1){
             y = -100;
             x = random.nextInt(App.getWIDTH());
        }
        if(line == 2){
             y = App.getHEIGHT()+100;
             x = random.nextInt(App.getWIDTH());
        }
        if(line == 3){
             x = App.getWIDTH()+100;
             y = random.nextInt(App.getHEIGHT());
        }
        int angle = random.nextInt(361);
        int speed = random.nextInt(2)+1;
        Asteroid asteroid = new Asteroid();
        asteroid.setTag(Tag.countsTowardsCap);
        int size = LevelManager.random.nextInt(3);
        if(size == 0){
            asteroid.setTag(Tag.small);
            asteroid.setSprite("Sprites\\smallAsteroid.png");
            asteroid.getSprite().setFitWidth(50);
            asteroid.getSprite().setFitHeight(50);
            asteroid.setHitBoxSize(25);
        }else{
            asteroid.setTag(Tag.big);
            asteroid.setSprite("Sprites\\Asteroid.png");
            asteroid.getSprite().setFitWidth(100);
            asteroid.getSprite().setFitHeight(100);
            asteroid.setHitBoxSize(50);
        }
        asteroid.setX(x);
        asteroid.setY(y);
        asteroid.setSpeedM(speed,angle);
    }

    /**
     * creates new pickup
     * @param x coordinate
     * @param y coordinate
     */
    public void createPickup(double x, double y){
        Pickup pickup = new Pickup();
        pickup.setX(x);
        pickup.setY(y);
    }

    /**
     * <p>creates the game over screen with two active buttons</p>
     * <p>restart button starts the new game</p>
     * <p>exit button returns us to the main menu</p>
     */
    public void gameOver(){
        Font font = Font.font(52);
        ImageView gameOverBackground = new ImageView(new Image("Sprites\\Background.png"));
        gameOverBackground.setFitWidth(App.getWIDTH());
        gameOverBackground.setFitHeight(App.getHEIGHT());
        gameOverBackground.setX(0);
        gameOverBackground.setY(0);
        App.getRoot().getChildren().add(gameOverBackground);
        ImageView gameOver = new ImageView(new Image("Sprites\\GameOver.png"));
        gameOver.setFitWidth(700);
        gameOver.setFitHeight(150);
        gameOver.setX((App.getWIDTH()-gameOver.getFitWidth())/2);
        gameOver.setY(50);
        App.getRoot().getChildren().add(gameOver);
        BackgroundImage backgroundImage = new BackgroundImage( new Image( "Sprites\\Button_bg.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(300,100,false,false,false,false));
        Background background = new Background(backgroundImage);
        javafx.scene.control.Button restart = new Button("Restart");
        restart.setBackground(background);
        restart.setTextFill(Color.web("ffffff"));
        restart.setPrefWidth(300);
        restart.setTranslateX(350);
        restart.setTranslateY(320);
        restart.setFont(font);
        restart.setOnAction(event -> {
            try {
               App.getApp().launch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        javafx.scene.control.Button exit = new Button("Exit");
        exit.setBackground(background);
        exit.setTextFill(Color.web("ffffff"));
        exit.setPrefWidth(300);
        exit.setTranslateX(350);
        exit.setTranslateY(440);
        exit.setFont(font);
        exit.setOnAction(event -> {
            try {
                App.getApp().initMainMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        App.getRoot().getChildren().add(restart);
        App.getRoot().getChildren().add(exit);
    }

    /**
     * creates bullets if space is pressed
     */
    public void onShootPressed(){
        if(player.getRechargeTimer()<=0) {
            playShootingSound();
            Bullet bullet = new Bullet();
            player.setRechargeTimer(shotRechargeTime);
            bullet.moveCentreTo(player.getCentreX(), player.getCentreY());
            bullet.setRotation(player.getRotation());
            bullet.setSpeed(new Point2D(15 * Math.cos(Math.toRadians(bullet.getRotation() - 90)), 15 * Math.sin(Math.toRadians(bullet.getRotation() - 90))));
            if(player.getTripleShotTimer()>0){
                Bullet bullet1 = new Bullet();
                bullet1.moveCentreTo(player.getCentreX(), player.getCentreY());
                bullet1.setRotation(player.getRotation()+15);
                bullet1.setSpeed(new Point2D(15 * Math.cos(Math.toRadians(bullet1.getRotation() - 90)), 15 * Math.sin(Math.toRadians(bullet1.getRotation() - 90))));
                Bullet bullet2 = new Bullet();
                bullet2.moveCentreTo(player.getCentreX(), player.getCentreY());
                bullet2.setRotation(player.getRotation()-15);
                bullet2.setSpeed(new Point2D(15 * Math.cos(Math.toRadians(bullet2.getRotation() - 90)), 15 * Math.sin(Math.toRadians(bullet2.getRotation() - 90))));
            }
        }
    }

    private boolean fireState_1;
    private int timeAfterStateChanged;

    /**
     * makes the player move
     */
    public void onUpPressed(){
        shipSoundPlayer.setVolume(App.getVolume()/2);
        if(timeAfterStateChanged > 3){
            timeAfterStateChanged = 0;
            if(fireState_1){
                player.setSprite(spaceship_flying_2);
            }else{
                player.setSprite(spaceship_flying_1);
            }
            fireState_1 = !fireState_1;
        }
        timeAfterStateChanged++;

        if(shipSoundPlayer.getCurrentTime().toMillis()>3400){
            shipSoundPlayer = new MediaPlayer(new Media(new File("Sounds\\SpaceShip_move.mp3").toURI().toString()));
        }
        shipSoundPlayer.play();
        if(Math.pow(Math.pow(player.getSpeed().getX(),2)+Math.pow(player.getSpeed().getY(),2),0.5)<player.getSpeedLimit()) {
            player.setSpeed(new Point2D(player.getSpeed().getX() - Math.cos(Math.toRadians(player.getRotation() + 90)) * player.getAcceleration(), player.getSpeed().getY() - Math.sin(Math.toRadians(player.getRotation() + 90)) * player.getAcceleration()));
        }
    }

    /**
     * stops moving sound and moving
     */
    public void onUpReleased(){
//        player.getSprite().setFitHeight(50);
        shipSoundPlayer.stop();
        if(GameEngine.isPlaying)
            player.setSprite("Sprites\\spaceship_new.png");
    }

    /**
     * stops the player
     */
    public void onDownPressed(){
        player.setSpeed(new Point2D(player.getSpeed().getX()*0.9,player.getSpeed().getY()*0.9));

    }

    /**
     * rotate player to left
     */
    public void onLeftPressed(){
        player.setRotation(player.getRotation()-3);

    }

    /**
     * rotate player to right
     */
    public void onRightPressed(){
        player.setRotation(player.getRotation()+3);

    }

    /**
     * creates a new rocket if "SHIFT" is pressed
     */
    public void onShiftPressed(){
        if(player.getRechargeTimer()<=0){
            if(player.getInfiniteRocketsTimer()>0||player.getRocketsNumber()>0) {
                if(player.getInfiniteRocketsTimer()<=0) {
                    player.setRocketsNumber(player.getRocketsNumber() - 1);
                }
                Rocket rocket = new Rocket();
                player.setRechargeTimer(50);
                rocket.moveCentreTo(player.getCentreX(), player.getCentreY());
                rocket.setRotation(player.getRotation());
                rocket.setSpeed(new Point2D(11 * Math.cos(Math.toRadians(rocket.getRotation() - 90)), 11 * Math.sin(Math.toRadians(rocket.getRotation() - 90))));
            }
        }
    }

    /**
     * stop the game and creates the pause menu
     */
    public void onEscPressed(){
        if(!timerStopped){
            App.getTimer().stop();
            timerStopped=true;
            ImageView bg = new ImageView(new Image("Sprites\\pause_screen.png"));
            BackgroundImage backgroundImage = new BackgroundImage( new Image( "Sprites\\Button_bg.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(300,100,false,false,false,false));
            Background background = new Background(backgroundImage);
            bg.setFitHeight(App.getHEIGHT());
            bg.setFitWidth(App.getWIDTH());
            App.getRoot().getChildren().add(bg);
            ImageView pause = new ImageView(new Image("Sprites\\Pause.png"));
            pause.setFitWidth(400);
            pause.setFitHeight(150);
            pause.setX((App.getWIDTH()-pause.getFitWidth())/2);
            pause.setY(30);
            App.getRoot().getChildren().add(pause);
            Button cont = new Button("Continue");
            cont.setBackground(background);
            cont.setTextFill(Color.web("ffffff"));
            cont.setFont(new Font(50));
            cont.setPrefWidth(300);
            cont.setTranslateX(350);
            cont.setTranslateY(250);
            App.getRoot().getChildren().add(cont);
            Button exit = new Button("Exit");
            exit.setFont(new Font(50));
            exit.setBackground(background);
            exit.setTextFill(Color.web("ffffff"));
            exit.setPrefWidth(300);
            exit.setTranslateX(350);
            exit.setTranslateY(400);
            App.getRoot().getChildren().add(exit);
            Button plus = new Button("+");
            //plus.setBackground(background);
            plus.setPrefWidth(50);
            plus.setPrefHeight(50);
            plus.setTranslateX(155);
            plus.setTranslateY(540);
            plus.setStyle("-fx-background-color: Orange");
            plus.setFont(new Font(20));
            plus.setOnAction(event -> {
                try {
                    if(App.getVolume()<1) {
                        App.setVolume(App.getVolume() + 0.1);
                        App.getApp().getMediaPlayer().setVolume(App.getVolume()/2);
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
            minus.setStyle("-fx-background-color: Orange");
            minus.setFont(new Font(20));
            minus.setOnAction(event -> {
                try {
                    if(App.getVolume()>0) {
                        App.setVolume(App.getVolume() - 0.1);
                        App.getApp().getMediaPlayer().setVolume(App.getVolume()/2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            ImageView volume = new ImageView(new Image("Sprites/Sound.png"));
            volume.setFitWidth(50);
            volume.setFitHeight(50);
            volume.setX(100);
            volume.setY(540);
            cont.setOnAction(event -> {
                try {
                    App.getRoot().getChildren().remove(bg);
                    App.getRoot().getChildren().remove(pause);
                    App.getRoot().getChildren().remove(cont);
                    App.getRoot().getChildren().remove(exit);
                    App.getRoot().getChildren().remove(plus);
                    App.getRoot().getChildren().remove(minus);
                    App.getRoot().getChildren().remove(volume);
                    App.getTimer().start();
                    timerStopped=false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            exit.setOnAction(event -> {
                try {
                    GameEngine.cleanScreen();
                    App.getApp().initMainMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            App.getRoot().getChildren().add(volume);
            App.getRoot().getChildren().add(plus);
            App.getRoot().getChildren().add(minus);

        }

    }

    /**
     * @param asteroidsNumber number of asteroids
     */
    public void setAsteroidsNumber(int asteroidsNumber) {
        this.asteroidsNumber = asteroidsNumber;
    }

    /**
     * @return number of asteroids
     */
    public int getAsteroidsNumber() {
        return asteroidsNumber;
    }

    /**
     * @return true if we can spawn a new pickup
     */
    public boolean isCanSpawnPickup() {
        return canSpawnPickup;
    }

    /**
     * @param canSpawnPickup boolean which determines if we can spawn a pickup
     */
    public void setCanSpawnPickup(boolean canSpawnPickup) {
        this.canSpawnPickup = canSpawnPickup;
    }

    /**
     * plays the shooting sound
     */
    public static void playShootingSound(){
        String musicFile = "Sounds\\Shoot.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(App.getVolume());
        mediaPlayer.play();
    }
    /**
     * plays the pickup sound
     */
    public static void playPickupSound(){
        String musicFile = "Sounds\\Pickup.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(App.getVolume());
        mediaPlayer.play();
    }

    /**
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param toAdd number to add to score
     */
    public void incScore(int toAdd) {
        score+=toAdd;
    }

    /**
     * @param toDec number to lower the score
     */
    public void decScore(int toDec) {
        score-=toDec;
        if(score<0)
            score=0;
    }

    /**
     * @return number of astronauts
     */
    public int getAstronautsNumber() {
        return astronautsNumber;
    }

    /**
     * @param astronautsNumber number of astronauts
     */
    public void setAstronautsNumber(int astronautsNumber) {
        this.astronautsNumber = astronautsNumber;
    }

    /**
     * @return number of rockets
     */
    public int getRocketsNumber(){
        return (int)player.getRocketsNumber();
    }

    /**
     * remove one life
     */
    public void removeLife(){


        if(lives > 0){
            player.setProtectionTimer(60);
            App.removeLife();
            lives--;
        }else{
            App.stopGame();
            GameEngine.setNeedToCleanScreen(true);
            LevelManager.getManager().gameOver();
        }
    }

    /**
     * checks the current level
     */
    public void checkLevel(){
        if(score<50){
            level = 1;
            asteroidsMaxNumber = 5;
        }
        if(score>50 && level == 1){
            level = 2;
            asteroidsMaxNumber = 7;
        }
        if(score>100 && level == 2){
            level = 3;
            asteroidsMaxNumber = 10;
        }
        if(score>200 && level == 3){
            level = 4;
            asteroidsMaxNumber = 13;
        }
        if(score>350 && level == 4){
            level = 5;
            asteroidsMaxNumber = 15;
        }
    }

    /**
     * @return the current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the player
     */
    public SpaceShip getPlayer(){
        return player;
    }
}
