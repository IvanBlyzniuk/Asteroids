import javafx.geometry.Point2D;

public class Asteroid extends GameObject{
    private Tag size;
    @Override
    public void init() {
        setTag(Tag.asteroid);
        int size = 0;
        size = LevelManager.random.nextInt(3);
        if(size == 0){
            setSize(Tag.small);
            setSprite("smallPepesteroid.png");
            getSprite().setFitWidth(50);
            getSprite().setFitHeight(50);
            setHitBoxSize(25);
        }else{
            setSize(Tag.big);
            setSprite("pepesteroid.png");
            getSprite().setFitWidth(100);
            getSprite().setFitHeight(100);
            setHitBoxSize(50);
        }

    }

    @Override
    public void update() {
        if(getX()<-100){
            setX(App.getWIDTH()+100);
        }
        if(getX()>App.getWIDTH()+100){
            setX(-100);
        }
        if(getY()<-100){
            setY(App.getHEIGHT()+100);
        }
        if(getY()>App.getHEIGHT()+100){
            setY(-100);
        }
    }

    //Если не работеет, можно делать update и checkForCollisions в GameEngine 2 разными циклами
    @Override
    public void onCollision(GameObject other){
        if(other.getTag() == Tag.asteroid){
            double newVelX = (getSpeed().getX() * (getHitBoxSize() - other.getHitBoxSize()) + (2 * other.getHitBoxSize() * other.getSpeed().getX())) / (getHitBoxSize() + other.getHitBoxSize());
            double newVelY = (getSpeed().getY() * (getHitBoxSize() - other.getHitBoxSize()) + (2 * other.getHitBoxSize() * other.getSpeed().getY())) / (getHitBoxSize() + other.getHitBoxSize());
            Point2D newVel = new Point2D(newVelX,newVelY);
            setSpeed(newVel);
            move(newVel);
        }
    }

    public void setSize(Tag size) {
        this.size = size;
    }
}
