import javafx.geometry.Point2D;

public class Asteroid extends GameObject{
    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    //Если не работеет, можно делать update и checkForCollisions в GameEngine 2 разными циклами
    @Override
    public void onCollision(GameObject other){
        if(other.getTag() == Tag.asteroid){
            double newVelX = (getSpeed().getX() * (getHitBoxSize() - other.getHitBoxSize()) + (2 * other.getHitBoxSize() * other.getSpeed().getX())) / (getHitBoxSize() + other.getHitBoxSize());
            double newVelY = (getSpeed().getY() * (getHitBoxSize() - other.getHitBoxSize()) + (2 * other.getHitBoxSize() * other.getSpeed().getY())) / (getHitBoxSize() + other.getHitBoxSize());
            setSpeed(new Point2D(newVelX,newVelY));
        }
    }
}
