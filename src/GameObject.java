import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {

    private Point2D speed = new Point2D(0,0);
    private ImageView sprite;
    private Tag tag;
    private double hitBoxSize;
    private Point2D centre = new Point2D(0,0);
    private double initialAngle;

    public GameObject(){
        init();
        if(sprite != null)
            centre = new Point2D(sprite.getFitWidth()/2,sprite.getFitWidth()/2);
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
        return centre.getX()+sprite.getX();
    }

    public double getCentreY(){
        return centre.getY()+sprite.getY();
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
        System.out.println("W = "+sprite.getFitWidth()+" H = "+sprite.getFitHeight());
        double r = Math.sqrt(sprite.getFitWidth()*sprite.getFitWidth()+sprite.getFitHeight()*sprite.getFitHeight())/2;
        centre = new Point2D(r*Math.cos(Math.toRadians(rotation)),r*Math.sin(Math.toRadians(rotation)));
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
