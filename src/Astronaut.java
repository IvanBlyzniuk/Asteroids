import javafx.geometry.Point2D;

/**
 * Astronaut that can be picked up by player which gives him 5 points.
 * If killed by bullets or rockets, will decrease the score by 10
 * Collides with asteroids according to circle collision physics
 */
public class Astronaut extends GameObject{
    private Point2D newSpeed;
    private boolean needToChangeSpeed;

    @Override
    public void init() {
        setTag(Tag.astronaut);
        setTag(Tag.teleFraggable);
        setSprite("Sprites\\Astronaut.png");
        getSprite().setFitWidth(30);
        getSprite().setFitHeight(30);
        setHitBoxSize(15);
    }

    @Override
    public void update() {
        if(needToChangeSpeed){
            needToChangeSpeed = false;
            setSpeed(newSpeed);
        }

        if(getX()<-30){
            setX(App.getWIDTH()+30);
        }
        if(getX()>App.getWIDTH()+30){
            setX(-30);
        }
        if(getY()<-100){
            setY(App.getHEIGHT()+30);
        }
        if(getY()>App.getHEIGHT()+30){
            setY(-30);
        }
    }


    public void onCollision(GameObject other){
        if(other.getTag().contains(Tag.asteroid)||other.getTag().contains(Tag.astronaut)){
            double newVelX = (getSpeed().getX() * (getHitBoxSize() - other.getHitBoxSize()) + (2 * other.getHitBoxSize() * other.getSpeed().getX())) / (getHitBoxSize() + other.getHitBoxSize());
            double newVelY = (getSpeed().getY() * (getHitBoxSize() - other.getHitBoxSize()) + (2 * other.getHitBoxSize() * other.getSpeed().getY())) / (getHitBoxSize() + other.getHitBoxSize());
            Point2D newVel = new Point2D(newVelX,newVelY);
            needToChangeSpeed = true;
            newSpeed = newVel;
        }else if(other.getTag().contains(Tag.bullet)){
            remove();
            other.remove();
            LevelManager.getManager().decScore(10);
            LevelManager.getManager().setAstronautsNumber(LevelManager.getManager().getAstronautsNumber()-1);
        }else if(other.getTag().contains(Tag.player)){
            remove();
            LevelManager.getManager().incScore(5);
            LevelManager.getManager().setAstronautsNumber(LevelManager.getManager().getAstronautsNumber()-1);
        }else if(other.getTag().contains(Tag.explosion)){
            remove();
            LevelManager.getManager().decScore(10);
            LevelManager.getManager().setAstronautsNumber(LevelManager.getManager().getAstronautsNumber()-1);
        }
    }
}
