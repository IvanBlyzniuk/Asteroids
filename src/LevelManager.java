import com.sun.javafx.geom.Point2D;

public class LevelManager extends GameObject{
    private SpaceShip player;

    private static LevelManager theManager = new LevelManager();

    private LevelManager(){}

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    public static LevelManager getManager(){
        return theManager;
    }

    public void initGame(){
        player = new SpaceShip();
    }

    public void onShootPressed(){

    }

    public void onUpPressed(){
        player.setSpeed(new Point2D((float)(player.getSpeed().x+Math.cos(Math.toRadians(player.getRotation())*player.getAcceleration())),(float)(player.getSpeed().y+Math.sin(Math.toRadians(player.getRotation())*player.getAcceleration()))));
    }

    public void onDownPressed(){
        player.setSpeed(new Point2D((float)(player.getSpeed().x-Math.cos(Math.toRadians(player.getRotation())*player.getAcceleration())),(float)(player.getSpeed().y-Math.sin(Math.toRadians(player.getRotation())*player.getAcceleration()))));
    }

    public void onLeftPressed(){

    }

    public void onRightPressed(){

    }
}
