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
//            App.stopGame();
//            GameEngine.setNeedToCleanScreen(true);
//            LevelManager.getManager().gameOver();
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

    public double getInfiniteRocketsTimer() {
        return infiniteRocketsTimer;
    }

    public void setInfiniteRocketsTimer(double infiniteRocketsTimer) {
        this.infiniteRocketsTimer = infiniteRocketsTimer;
    }

    public double getTripleShotTimer() {
        return tripleShotTimer;
    }

    public double getProtectionTimer() {
        return protectionTimer;
    }

    public void setProtectionTimer(double protectionTimer) {
        this.protectionTimer = protectionTimer;
    }

    public void setTripleShotTimer(double tripleShotTimer) {
        this.tripleShotTimer = tripleShotTimer;
    }

    public double getRocketsNumber() {
        return rocketsNumber;
    }

    public void setRocketsNumber(double rocketsNumber) {
        this.rocketsNumber = rocketsNumber;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getRechargeTimer() {
        return rechargeTimer;
    }

    public void setRechargeTimer(double livingTimer) {
        this.rechargeTimer = livingTimer;
    }
}
