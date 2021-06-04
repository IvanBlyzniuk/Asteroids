import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * class which describes the player`s spaceship
 */
public class SpaceShip extends GameObject{
    private boolean vulnerable = true;
    private double acceleration = 1.1;
    private double speedLimit = 3.5;
    private double rechargeTimer = 0;
    private double infiniteRocketsTimer = 0;
    private double tripleShotTimer = 0;
    private double protectionTimer = 0;
    private double rocketsNumber = 10;

    public void init(){
        setSprite("Sprites\\SpaceShip.png");
        getSprite().setFitWidth(50);
        getSprite().setFitHeight(50);
        setHitBoxSize(20);
        moveCentreTo(App.getWIDTH()/2,App.getHEIGHT()/2);
        setTag(Tag.player);
    }

    @Override
    public void update() {
        vulnerable = protectionTimer <= 0;

        if(rechargeTimer>0){
            rechargeTimer--;
        }
        if(tripleShotTimer>0){
            tripleShotTimer--;
        }
        if(infiniteRocketsTimer>0){
            infiniteRocketsTimer--;
        }
        if(protectionTimer>0){
            protectionTimer--;
        }

        if(getX()<-70){
            setX(App.getWIDTH()+70);
        }
        if(getX()>App.getWIDTH()+70){
            setX(-70);
        }
        if(getY()<-70){
            setY(App.getHEIGHT()+70);
        }
        if(getY()>App.getHEIGHT()+70){
            setY(-70);
        }
    }

    public void onCollision(GameObject other){
        if(other.getTag().contains(Tag.asteroid)&&vulnerable){
            LevelManager.getManager().removeLife();
            String musicFile = "Sounds\\Spaceship_crash.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(App.getVolume()*2);
            mediaPlayer.play();
        }else if(other.getTag().contains(Tag.asteroid)){

        } else if(other.getTag().contains(Tag.astronaut)){
            LevelManager.playPickupSound();
        }else if(other.getTag().contains(Tag.protectionPickup)){
            LevelManager.playPickupSound();
            LevelManager.getManager().setCanSpawnPickup(true);
            GameEngine.remove(other);
            LevelManager.setPickupSpawnCooldown(600);
            protectionTimer = 600;
        }else if(other.getTag().contains(Tag.rocketPickup)){
            LevelManager.playPickupSound();
            LevelManager.getManager().setCanSpawnPickup(true);
            GameEngine.remove(other);
            LevelManager.setPickupSpawnCooldown(600);
            infiniteRocketsTimer = 600;
        }else if(other.getTag().contains(Tag.tripleShotPickup)){
            LevelManager.playPickupSound();
            LevelManager.getManager().setCanSpawnPickup(true);
            GameEngine.remove(other);
            LevelManager.setPickupSpawnCooldown(600);
            tripleShotTimer = 600;
        }else if(other.getTag().contains(Tag.addOneRocketPickup)){
            LevelManager.playPickupSound();
            LevelManager.getManager().setCanSpawnPickup(true);
            GameEngine.remove(other);
            LevelManager.setPickupSpawnCooldown(600);
            rocketsNumber++;
        }
    }

    /**
     * @return infiniteRocketsTimer
     */
    public double getInfiniteRocketsTimer() {
        return infiniteRocketsTimer;
    }

    /**
     * @param infiniteRocketsTimer timer for infinite rockets
     */
    public void setInfiniteRocketsTimer(double infiniteRocketsTimer) {
        this.infiniteRocketsTimer = infiniteRocketsTimer;
    }

    /**
     * @return tripleShotTimer
     */
    public double getTripleShotTimer() {
        return tripleShotTimer;
    }

    /**
     * @return protectionTimer
     */
    public double getProtectionTimer() {
        return protectionTimer;
    }

    /**
     * @param protectionTimer protection time
     */
    public void setProtectionTimer(double protectionTimer) {
        this.protectionTimer = protectionTimer;
    }

    /**
     * @param tripleShotTimer
     */
    public void setTripleShotTimer(double tripleShotTimer) {
        this.tripleShotTimer = tripleShotTimer;
    }

    /**
     * @return rocketsNumber
     */
    public double getRocketsNumber() {
        return rocketsNumber;
    }

    /**
     * @param rocketsNumber number of rockets
     */
    public void setRocketsNumber(double rocketsNumber) {
        this.rocketsNumber = rocketsNumber;
    }

    /**
     * @return speed limit
     */
    public double getSpeedLimit() {
        return speedLimit;
    }

    /**
     * @return acceleration
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * @param acceleration acceleration value
     */
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * @return rechargeTimer
     */
    public double getRechargeTimer() {
        return rechargeTimer;
    }

    /**
     * @param livingTimer timer for fire recharge
     */
    public void setRechargeTimer(double livingTimer) {
        this.rechargeTimer = livingTimer;
    }
}
