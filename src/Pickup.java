import com.sun.javafx.geom.Point2D;

 abstract class Pickup extends GameObject{
    public Pickup(double x,double y){
        Init(x,y);
    }
    public void init(double x, double y){
        setTag(Tag.pickup);
        setX(x);
        setY(y);
        setSprite("Pickup.png");
    }
    abstract void upgrade();
     public void onCollision(GameObject other) {

     }

}
