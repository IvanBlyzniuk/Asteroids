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
        player.setSpeed(new Point2D(player.getSpeed().getX()*0.98,player.getSpeed().getY()*0.98));
    }

    public void onShootPressed(){

    }

    public void onUpPressed(){
        player.setSpeed(new Point2D(player.getSpeed().getX()-Math.cos(Math.toRadians(player.getRotation()+90))*player.getAcceleration(),player.getSpeed().getY()-Math.sin(Math.toRadians(player.getRotation()+90))*player.getAcceleration()));
    }

    public void onDownPressed(){
        player.setSpeed(new Point2D(player.getSpeed().getX()+Math.cos(Math.toRadians(player.getRotation()+90))*player.getAcceleration(),player.getSpeed().getY()+Math.sin(Math.toRadians(player.getRotation()+90))*player.getAcceleration()));
    }

    public void onLeftPressed(){
        player.setRotation(player.getRotation()-5);
    }

    public void onRightPressed(){
        player.setRotation(player.getRotation()+5);
    }
}
