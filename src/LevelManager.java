import javafx.geometry.Point2D;

import java.util.Random;

public class LevelManager extends GameObject{

    private SpaceShip player;
    private static LevelManager theManager;
    final Random random = new Random();
    private int asteroidsNumber = 0;
    private int asteroidsMaxNumber = 5;



    private LevelManager(){}

    public static LevelManager getManager(){
        return theManager;
    }

    public static void initManager() {
        theManager = new LevelManager();
    }

    @Override
    public void init() {
        player = new SpaceShip();
    }

    @Override
    public void update() {
        player.setSpeed(new Point2D(player.getSpeed().getX()*0.98,player.getSpeed().getY()*0.98));
        int rand = random.nextInt(100);
        if(rand == 0 && asteroidsNumber<asteroidsMaxNumber){
            createAsteroid();
            asteroidsNumber++;
        }
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
        asteroid.setSprite("pepesteroid.png");
        asteroid.getSprite().setFitWidth(100);
        asteroid.getSprite().setFitHeight(100);
        asteroid.setRotation(angle);
        asteroid.setX(x);
        asteroid.setY(y);
        asteroid.setSpeed(new Point2D(speed*Math.cos(Math.toRadians(asteroid.getRotation())),speed*Math.sin(Math.toRadians(asteroid.getRotation()))));
    }

    public void onShootPressed(){
        if(player.getRechargeTimer()<=0) {
            Bullet bullet = new Bullet();
            player.setRechargeTimer(50);
            bullet.getSprite().setFitWidth(10);
            bullet.getSprite().setFitHeight(10);
            bullet.moveCentreTo(player.getCentreX(), player.getCentreY());
            bullet.setRotation(player.getRotation());
            bullet.setSpeed(new Point2D(15 * Math.cos(Math.toRadians(bullet.getRotation() - 90)), 15 * Math.sin(Math.toRadians(bullet.getRotation() - 90))));
        }
    }

    public void onUpPressed(){
        if(Math.pow(Math.pow(player.getSpeed().getX(),2)+Math.pow(player.getSpeed().getY(),2),0.5)<player.getSpeedLimit()) {
            player.setSpeed(new Point2D(player.getSpeed().getX() - Math.cos(Math.toRadians(player.getRotation() + 90)) * player.getAcceleration(), player.getSpeed().getY() - Math.sin(Math.toRadians(player.getRotation() + 90)) * player.getAcceleration()));
        }
    }

    public void onDownPressed(){
        player.setSpeed(new Point2D(player.getSpeed().getX()*0.9,player.getSpeed().getY()*0.9));

    }

    public void onLeftPressed(){
        player.setRotation(player.getRotation()-5);

    }

    public void onRightPressed(){
        player.setRotation(player.getRotation()+5);

    }


}
