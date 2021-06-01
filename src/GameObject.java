import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class GameObject {

    private Point2D speed = new Point2D(0,0);
    private ImageView sprite;
    private ArrayList<Tag> tags = new ArrayList<>();
    private double hitBoxSize;
    private GameObject lastCollision;

    public GameObject(){
        init();
        GameEngine.getEngine().addGameObject(this);
    }

    public abstract void init();

    public final void outerUpdate(){
        update();
        move(speed);
    }

    public abstract void update();

    public void move(Point2D speed) {
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

    public void setSpeedM(double module,int angle){
        speed = new Point2D(module * Math.cos(Math.toRadians(angle)),module * Math.sin(Math.toRadians(angle)));
        this.setRotation(angle);
    }

    public Point2D getSpeed(){
        return speed;
    }

    public void setX(double x){
        sprite.setX(x);
    }

    public double getCentreX(){
        return sprite.getX()+sprite.getFitWidth()/2;
    }

    public double getCentreY(){
        return sprite.getY()+ sprite.getFitHeight()/2;
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
        tags.add(tag);
    }

    public ArrayList<Tag> getTag(){
        return tags;
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

    public void moveCentreTo(double x, double y){
        double deltaX = x - getCentreX();
        double deltaY = y - getCentreY();
        move(new Point2D(deltaX,deltaY));
    }

    public void setLastCollision(GameObject other){
        lastCollision = other;
    }

    public GameObject getLastCollision() {
        return lastCollision;
    }
}
