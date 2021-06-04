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

    private static int pickupSpawnCooldown;

    private static final int shotRechargeTime = 20;

    private boolean canSpawnPickup = true;

    private int score = 0;

    MediaPlayer shipSoundPlayer = new MediaPlayer(new Media(new File("Sounds\\SpaceShip_move.mp3").toURI().toString()));

    public static LevelManager getManager(){
        return theManager;
    }

    public static void initManager() {
        theManager = new LevelManager();
        lives = 3;
    }

    @Override
    public void init() { player = new SpaceShip(); }

    public static int getPickupSpawnCooldown() {
        return pickupSpawnCooldown;
    }

    public static void setPickupSpawnCooldown(int pickupSpawnCooldown) {
        LevelManager.pickupSpawnCooldown = pickupSpawnCooldown;
    }

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
    public void createPickup(double x, double y){
        Pickup pickup = new Pickup();
        pickup.setX(x);
        pickup.setY(y);
    }
    public void gameOver(){
        Font font = Font.font(52);
        ImageView gameOverBackground = new ImageView(new Image("Sprites\\buttonBackground.jfif"));
        gameOverBackground.setFitWidth(App.getWIDTH());
        gameOverBackground.setFitHeight(App.getHEIGHT());
        gameOverBackground.setX(0);
        gameOverBackground.setY(0);
        App.getRoot().getChildren().add(gameOverBackground);
        Text gameOver = new Text("Game Over");
        gameOver.setFont(font);
        gameOver.setFill(Color.web("fabbff"));
        gameOver.setX(380);
        gameOver.setY(100);
        App.getRoot().getChildren().add(gameOver);
        BackgroundImage backgroundImage = new BackgroundImage( new Image( "Sprites\\buttonBackground.jfif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        javafx.scene.control.Button restart = new Button("Restart");
       // restart.setBackground(background);
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
       // exit.setBackground(background);
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

    public void onUpPressed(){
        shipSoundPlayer.setVolume(App.getVolume()/2);
        if(shipSoundPlayer.getCurrentTime().toMillis()>3400){
            shipSoundPlayer = new MediaPlayer(new Media(new File("Sounds\\SpaceShip_move.mp3").toURI().toString()));
        }
        shipSoundPlayer.play();
        if(Math.pow(Math.pow(player.getSpeed().getX(),2)+Math.pow(player.getSpeed().getY(),2),0.5)<player.getSpeedLimit()) {
            player.setSpeed(new Point2D(player.getSpeed().getX() - Math.cos(Math.toRadians(player.getRotation() + 90)) * player.getAcceleration(), player.getSpeed().getY() - Math.sin(Math.toRadians(player.getRotation() + 90)) * player.getAcceleration()));
        }
    }

    public void onUpReleased(){
        shipSoundPlayer.stop();
    }

    public void onDownPressed(){
        player.setSpeed(new Point2D(player.getSpeed().getX()*0.9,player.getSpeed().getY()*0.9));

    }

    public void onLeftPressed(){
        player.setRotation(player.getRotation()-3);

    }

    public void onRightPressed(){
        player.setRotation(player.getRotation()+3);

    }
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

    public void onEscPressed(){
        if(!timerStopped){
            App.getTimer().stop();
            timerStopped=true;
            ImageView bg = new ImageView(new Image("Sprites\\pause_screen.png"));
            bg.setFitHeight(App.getHEIGHT());
            bg.setFitWidth(App.getWIDTH());
            App.getRoot().getChildren().add(bg);
            Button cont = new Button("Continue");
            cont.setFont(new Font(50));
            cont.setPrefWidth(300);
            cont.setTranslateX(350);
            cont.setTranslateY(200);
            App.getRoot().getChildren().add(cont);
            Button exit = new Button("Exit");
            exit.setFont(new Font(50));
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
            ImageView volume = new ImageView(new Image("Sprites/volume.jpg"));
            volume.setFitWidth(50);
            volume.setFitHeight(50);
            volume.setX(100);
            volume.setY(540);
            cont.setOnAction(event -> {
                try {
                    App.getRoot().getChildren().remove(bg);
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


    public void setAsteroidsNumber(int asteroidsNumber) {
        this.asteroidsNumber = asteroidsNumber;
    }

    public int getAsteroidsNumber() {
        return asteroidsNumber;
    }

    public boolean isCanSpawnPickup() {
        return canSpawnPickup;
    }

    public void setCanSpawnPickup(boolean canSpawnPickup) {
        this.canSpawnPickup = canSpawnPickup;
    }

    public static void playShootingSound(){
        String musicFile = "Sounds\\Shoot.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(App.getVolume());
        mediaPlayer.play();
    }

    public static void playPickupSound(){
        String musicFile = "Sounds\\Pickup.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(App.getVolume());
        mediaPlayer.play();
    }

    public int getScore() {
        return score;
    }

    public void incScore(int toAdd) {
        score+=toAdd;
    }
    public void decScore(int toDec) {
        score-=toDec;
        if(score<0)
            score=0;
    }

    public int getAstronautsNumber() {
        return astronautsNumber;
    }

    public void setAstronautsNumber(int astronautsNumber) {
        this.astronautsNumber = astronautsNumber;
    }

    public int getRocketsNumber(){
        return (int)player.getRocketsNumber();
    }

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
    public void checkLevel(){
        if(score<50){
            level = 1;
            asteroidsMaxNumber = 5;
        }
        if(score>50 && level == 1){
            level = 2;
            asteroidsMaxNumber = 7;
            System.out.println(level);
            System.out.println(asteroidsMaxNumber);
        }
        if(score>100 && level == 2){
            level = 3;
            asteroidsMaxNumber = 10;
            System.out.println(level);
            System.out.println(asteroidsMaxNumber);
        }
        if(score>200 && level == 3){
            level = 4;
            asteroidsMaxNumber = 13;
            System.out.println(level);
            System.out.println(asteroidsMaxNumber);
        }
        if(score>350 && level == 4){
            level = 5;
            asteroidsMaxNumber = 15;
            System.out.println(level);
            System.out.println(asteroidsMaxNumber);
        }
    }

    public int getLevel() {
        return level;
    }
}
