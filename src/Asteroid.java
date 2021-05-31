import javafx.geometry.Point2D;

public class Asteroid extends GameObject{
    @Override
    public void init() {
        setTag(Tag.asteroid);
        setHitBoxSize(50);
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
}
