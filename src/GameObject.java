import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {

    private Point2D speed = new Point2D(0,0);
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
        if(sprite != null){
            sprite.setX(sprite.getX()+speed.getX());
            sprite.setY(sprite.getY()+speed.getY());
        }
    }

    public void setSprite(String url){
        sprite = new ImageView(new Image(url));
        App.getRoot().getChildren().add(sprite);
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

    public double getCentreX(){

        return sprite.getX()+(25);
    }

    public double getCentreY(){

        return sprite.getY()+(35);
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

    public void remove(){
        GameEngine.remove(this);
    }

    public ImageView getSprite() {
        return sprite;
    }
}
