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
    }

    @Override
    public void update() {
        player.setSpeed(new Point2D(player.getSpeed().getX()*0.98,player.getSpeed().getY()*0.98));
    }

    public void onShootPressed(){
        Bullet bullet = new Bullet();
        bullet.getSprite().setFitWidth(10);
        bullet.getSprite().setFitHeight(20);
        bullet.setX(player.getCentreX());
        bullet.setY(player.getCentreY());
        bullet.setRotation(player.getRotation());
        bullet.setSpeed(new Point2D(30* Math.cos(Math.toRadians(bullet.getRotation() - 90)),30*Math.sin(Math.toRadians(bullet.getRotation() - 90))));
//        System.out.println("Bullet speed x:"+bullet.getSpeed().getX()+" y:"+bullet.getSpeed().getY());
//        System.out.println(player.getX());
//        System.out.println(player.getY());
    }

    public void onUpPressed(){
        if(Math.pow(Math.pow(player.getSpeed().getX(),2)+Math.pow(player.getSpeed().getY(),2),0.5)<player.getSpeedLimit()) {
            player.setSpeed(new Point2D(player.getSpeed().getX() - Math.cos(Math.toRadians(player.getRotation() + 90)) * player.getAcceleration(), player.getSpeed().getY() - Math.sin(Math.toRadians(player.getRotation() + 90)) * player.getAcceleration()));
        }
    }

    public void onDownPressed(){
        player.setSpeed(new Point2D(player.getSpeed().getX()*0.9,player.getSpeed().getY()*0.9));
        //player.setSpeed(new Point2D(player.getSpeed().getX()+Math.cos(Math.toRadians(player.getRotation()+90))*player.getAcceleration(),player.getSpeed().getY()+Math.sin(Math.toRadians(player.getRotation()+90))*player.getAcceleration()));
    }

    public void onLeftPressed(){
        player.setRotation(player.getRotation()-5);
    //    выбрать один из вдух концептов поворотов
//        double vector = Math.pow(Math.pow(player.getSpeed().getX(),2)+Math.pow(player.getSpeed().getY(),2),0.5);
//        player.setSpeed(new Point2D(Math.cos(Math.toRadians(player.getRotation() - 90))*vector,Math.sin(Math.toRadians(player.getRotation() - 90))*vector));
    }

    public void onRightPressed(){
        player.setRotation(player.getRotation()+5);
//        double vector = Math.pow(Math.pow(player.getSpeed().getX(),2)+Math.pow(player.getSpeed().getY(),2),0.5);
//        player.setSpeed(new Point2D(Math.cos(Math.toRadians(player.getRotation() - 90))*vector,Math.sin(Math.toRadians(player.getRotation() - 90))*vector));
    }

}
