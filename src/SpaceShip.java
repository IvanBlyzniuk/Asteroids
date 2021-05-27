public class SpaceShip extends GameObject{
    private boolean vulnerable = true;
    private double acceleration = 1;
    private double speedLimit = 10;

    public SpaceShip() {
    }



    public void init(){
        setSprite("SpaceShacal.png");
       // setRotation(90);
        setX(App.getWIDTH()/2);
        setY(App.getHEIGHT()/2);
        setTag(Tag.player);

    }

    @Override
    public void update() {

    }

    public void onCollision(GameObject other){
        if(other.getTag()==Tag.asteroid&&vulnerable){
            GameEngine.stopGame();
        }else if(other.getTag()==Tag.asteroid){

        } else if(other.getTag()==Tag.astronaut){
           // SceneManager.setScore(SceneManager.getScore()+5);
        }else if(other.getTag()==Tag.pickup){
            ((Pickup)(other)).upgrade();
        }
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }
}
