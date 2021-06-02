import javafx.geometry.Point2D;

import java.util.Random;

public class LevelManager extends GameObject{

    private SpaceShip player;
    private static LevelManager theManager;
    public static final Random random = new Random();
    private int asteroidsNumber = 0;

    private int asteroidsMaxNumber = 7;
    private int astronautsNumber = 0;
    private int astronautsMaxNumber = 1;

    private static int pickupSpawnCooldown;

    private static final int shotRechargeTime = 20;

    private boolean canSpawnPickup = true;

    private int score = 0;



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

    public static int getPickupSpawnCooldown() {
        return pickupSpawnCooldown;
    }

    public static void setPickupSpawnCooldown(int pickupSpawnCooldown) {
        LevelManager.pickupSpawnCooldown = pickupSpawnCooldown;
    }

    @Override
    public void update() {
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
            asteroid.setSprite("smallAsteroid.png");
            asteroid.getSprite().setFitWidth(50);
            asteroid.getSprite().setFitHeight(50);
            asteroid.setHitBoxSize(25);
        }else{
            asteroid.setTag(Tag.big);
            asteroid.setSprite("Asteroid.png");
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

    public void onShootPressed(){
        if(player.getRechargeTimer()<=0) {
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
        if(Math.pow(Math.pow(player.getSpeed().getX(),2)+Math.pow(player.getSpeed().getY(),2),0.5)<player.getSpeedLimit()) {
            player.setSpeed(new Point2D(player.getSpeed().getX() - Math.cos(Math.toRadians(player.getRotation() + 90)) * player.getAcceleration(), player.getSpeed().getY() - Math.sin(Math.toRadians(player.getRotation() + 90)) * player.getAcceleration()));
        }
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
                Rocket rocket = new Rocket();
                player.setRechargeTimer(50);
                rocket.moveCentreTo(player.getCentreX(), player.getCentreY());
                rocket.setRotation(player.getRotation());
                rocket.setSpeed(new Point2D(11 * Math.cos(Math.toRadians(rocket.getRotation() - 90)), 11 * Math.sin(Math.toRadians(rocket.getRotation() - 90))));
            }
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAstronautsNumber() {
        return astronautsNumber;
    }

    public void setAstronautsNumber(int astronautsNumber) {
        this.astronautsNumber = astronautsNumber;
    }
}
