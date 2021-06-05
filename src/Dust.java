/**
 * class which describes the dust
 */
public class Dust extends GameObject{
    private int timeToLive;
    private static final int maxTimeToLive = 20;
    @Override
    public void init() {
        timeToLive = maxTimeToLive;
        setSprite("Sprites\\Dust_1.png");
    }

    @Override
    public void update() {
        if(timeToLive <= 0){
            remove();
        }else {
            getSprite().setOpacity(0.05*timeToLive);
            timeToLive--;
        }
    }
}
