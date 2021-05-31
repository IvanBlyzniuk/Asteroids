public class SpaceShip extends GameObject{
    private boolean vulnerable = true;
    private double acceleration = 1.1;
    private double speedLimit = 5;
    private double rechargeTimer = 0;

    public SpaceShip() {
    }



    public void init(){
//        setSprite("SpaceShacal.png");
        setSprite("SpaceShacal.png");
        setX(App.getWIDTH()/2);
        setY(App.getHEIGHT()/2);
        getSprite().setFitWidth(50);
        getSprite().setFitHeight(50);
        setTag(Tag.player);
    }

    @Override
    public void update() {
        if(rechargeTimer>0){
            rechargeTimer--;
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
            GameEngine.stopGame();
        }else if(other.getTag().contains(Tag.asteroid)){

        } else if(other.getTag().contains(Tag.astronaut)){
           // SceneManager.setScore(SceneManager.getScore()+5);
        }else if(other.getTag().contains(Tag.pickup)){
            ((Pickup)(other)).upgrade();
        }
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
