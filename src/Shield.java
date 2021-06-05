public class Shield extends GameObject{

    private int timeToLive;

    @Override
    public void init() {
        setSprite("Sprites\\shield.png");
        getSprite().setFitHeight(90);
        getSprite().setFitWidth(90);
        moveCentreTo(LevelManager.getManager().getPlayer().getCentreX(),LevelManager.getManager().getPlayer().getCentreY());
    }

    @Override
    public void update() {
        getSprite().toFront();
        timeToLive--;
        if(timeToLive <= 0)
            remove();
        moveCentreTo(LevelManager.getManager().getPlayer().getCentreX(),LevelManager.getManager().getPlayer().getCentreY());
    }

    public void setTimeTolive(int value){
        timeToLive = value;
    }
}
