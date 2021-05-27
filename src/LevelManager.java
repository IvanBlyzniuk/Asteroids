import javafx.geometry.Point2D;

public class LevelManager extends GameObject{

    private SpaceShip player;
    private static LevelManager theManager;

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
        System.out.println("added player");
    }

    @Override
    public void update() {

    }

    public void onShootPressed(){

    }

    public void onUpPressed(){
        System.out.println("got input, vel = "+player.getSpeed().getX()+" ; "+player.getSpeed().getY());
        player.setSpeed(new Point2D(player.getSpeed().getX(),player.getSpeed().getY()+1));
//        player.setSpeed(new Point2D((float)(player.getSpeed().x+Math.cos(Math.toRadians(player.getRotation())*player.getAcceleration())),(float)(player.getSpeed().y+Math.sin(Math.toRadians(player.getRotation())*player.getAcceleration()))));
    }

    public void onDownPressed(){
        //player.setSpeed(new Point2D((float)(player.getSpeed().x-Math.cos(Math.toRadians(player.getRotation())*player.getAcceleration())),(float)(player.getSpeed().y-Math.sin(Math.toRadians(player.getRotation())*player.getAcceleration()))));
    }

    public void onLeftPressed(){

    }

    public void onRightPressed(){

    }
}
