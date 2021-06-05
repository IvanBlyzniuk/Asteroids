public class Dust extends GameObject{
    private int timeToLive;
    private static final int maxTimeToLive = 12;
    @Override
    public void init() {
        timeToLive = maxTimeToLive;
        setSprite("Sprites\\Dust_1.png");
    }

    @Override
    public void update() {
        if(timeToLive <= 0){
            remove();
        }else if(timeToLive==8){
            timeToLive--;
            setSprite("Sprites\\Dust_2.png");
        }else if(timeToLive==4){
            timeToLive--;
            setSprite("Sprites\\Dust_3.png");
        }else {
            timeToLive--;
        }
    }
}
