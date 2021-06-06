/**
 * class which describes the bullet which can destroy asteroids and kill astronauts
 */
public class Bullet extends GameObject{
    @Override
    public void init() {
        setSprite("Sprites\\bullet.png");
        setTag(Tag.bullet);
        getSprite().setFitWidth(10);
        getSprite().setFitHeight(10);
        setHitBoxSize(5);
    }

    @Override
    public void update() {
        if(this.getX()<0||this.getX()>App.getWIDTH()||this.getY()<0||this.getY()>App.getHEIGHT()){
            this.remove();
        }
    }
    public void onCollisions(GameObject other){

    }

}
