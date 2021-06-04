import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Class that describes a GameObject that has a sprite and can interact with other GameObjects, all particular objects should extend this class
 */
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

    /**
     * A method that is called when the GameObject is created, should be overwritten
     */
    public abstract void init();

    /**
     * A method called by GameEngine that updates the object and moves it accordingly to its speed
     */
    public final void outerUpdate(){
        update();
        move(speed);
    }

    /**
     * A method that is called by GameEngine every frame, should be overwritten
     */
    public abstract void update();

    public void move(Point2D speed) {
        if(sprite != null){
            sprite.setX(sprite.getX()+speed.getX());
            sprite.setY(sprite.getY()+speed.getY());
        }
    }

    /**
     * Sets a sprite to GameObject
     * @param url url to the sprite image in file system
     */
    public void setSprite(String url){
        sprite = new ImageView(new Image(url));
        App.getRoot().getChildren().add(sprite);
    }

    /**
     *
     * @param p new speed
     */
    public void setSpeed(Point2D p){
        speed = p;
    }

    /**
     * Sets speed vector according to its module and angle
     * @param module module of the vector
     * @param angle angle of the vector
     */
    public void setSpeedM(double module,int angle){
        speed = new Point2D(module * Math.cos(Math.toRadians(angle)),module * Math.sin(Math.toRadians(angle)));
        this.setRotation(angle);
    }

    /**
     * @return Point2D speed of the object in vector form
     */
    public Point2D getSpeed(){
        return speed;
    }

    /**
     * @param x new X coord of an object
     */
    public void setX(double x){
        sprite.setX(x);
    }

    /**
     * @param y new Y coord of an object
     */
    public void setY(double y){
        sprite.setY(y);
    }

    /**
     * @return the X coord of the centre of the object
     */
    public double getCentreX(){
        return sprite.getX()+sprite.getFitWidth()/2;
    }

    /**
     * @return the Y coord of the centre of the object
     */
    public double getCentreY(){
        return sprite.getY()+ sprite.getFitHeight()/2;
    }

    /**
     * @return X coord of an object
     */
    public double getX(){
        return sprite.getX();
    }

    /**
     *
     * @return Y coord of an object
     */
    public double getY(){
        return sprite.getY();
    }

    /**
     * @return rotation angle of an object in degrees
     */
    public double getRotation(){
        return sprite.getRotate();
    }

    /**
     * @param rotation new rotation angle of an object in degrees
     */
    public void setRotation(double rotation){
        sprite.setRotate(rotation);
    }

    /**
     * Called by GameObject when collision is happening with other GameObject, should be overwritten if interaction is needed
     * @param other the object that this object is colliding with
     */
    public void onCollision(GameObject other){

    }

    /**
     * Adds a new tag to the array of tags of the object
     * @param tag Tag to add
     */
    public void setTag(Tag tag){
        tags.add(tag);
    }

    /**
     * @return the ArrayList of tags the object has
     */
    public ArrayList<Tag> getTag(){
        return tags;
    }

    /**
     * @return radius of the hitbox
     */
    public double getHitBoxSize() {
        return hitBoxSize;
    }

    /**
     * @param hitBoxSize new radius of the hitbox
     */
    public void setHitBoxSize(double hitBoxSize) {
        this.hitBoxSize = hitBoxSize;
    }

    /**
     * removes this object and its sprite from the screen
     */
    public void remove(){
        GameEngine.remove(this);
    }

    /**
     * @return ImageViev sprite of the object
     */
    public ImageView getSprite() {
        return sprite;
    }

    /**
     * Moves centre to specified coordinates
     * @param x new X coord of the centre
     * @param y new Y coord of the centre
     */
    public void moveCentreTo(double x, double y){
        double deltaX = x - getCentreX();
        double deltaY = y - getCentreY();
        move(new Point2D(deltaX,deltaY));
    }

    /**
     * Remembers the last GameObject that was collided with
     * @param other
     */
    public void setLastCollision(GameObject other){
        lastCollision = other;
    }

    /**
     * @return the last GameObject that was collided with
     */
    public GameObject getLastCollision() {
        return lastCollision;
    }
}
