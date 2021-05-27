import com.sun.javafx.geom.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {

    private Point2D speed;
    private ImageView sprite;
    private Tag tag;
    private double hitBoxSize;

    public GameObject(){
        init();
        GameEngine.getEngine().addGameObject(this);
    }

    public abstract void init();

    public final void outerUpdate(){
        move(speed);
        update();
    }

    public abstract void update();

    private void move(Point2D speed) {
        sprite.setTranslateX(sprite.getX()+speed.x);
        sprite.setTranslateY(sprite.getY()+speed.y);
    }

    public void setSprite(String url){
        sprite = new ImageView(new Image(url));
    }

    public void setSpeed(Point2D p){
        speed = p;
    }

    public Point2D getSpeed(){
        return speed;
    }

    public void setX(double x){
        sprite.setX(x);
    }

    public void setY(double y){
        sprite.setY(y);
    }

    public double getX(){
        return sprite.getX();
    }

    public double getY(){
        return sprite.getY();
    }

    public double getRotation(){
        return sprite.getRotate();
    }

    public void setRotation(double rotation){
        sprite.setRotate(rotation);
    }

    public void onCollision(GameObject other){

    }

    public void setTag(Tag tag){
        this.tag = tag;
    }

    public Tag getTag(){
        return tag;
    }

    public double getHitBoxSize() {
        return hitBoxSize;
    }

    public void setHitBoxSize(double hitBoxSize) {
        this.hitBoxSize = hitBoxSize;
    }
}
