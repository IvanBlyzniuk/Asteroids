public class Explosion extends GameObject{

    private int timeToLive;
    private static final int maxTimeToLive = 10;

    @Override
    public void init() {
        timeToLive = maxTimeToLive;
        setSprite("explosion.png");
        getSprite().setFitWidth(200);
        getSprite().setFitHeight(200);
        setTag(Tag.explosion);
        setHitBoxSize(100);
    }

    @Override
    public void update() {
        if(timeToLive < maxTimeToLive)
            setHitBoxSize(0);
        if(timeToLive <= 0){
            remove();
        }else {
            timeToLive--;
        }
    }
}
