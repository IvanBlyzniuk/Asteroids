public class Rocket extends GameObject{
    @Override
    public void init() {
        setSprite("rocket.png");
        setTag(Tag.rocket);
        getSprite().setFitWidth(15);
        getSprite().setFitHeight(15);
        setHitBoxSize(7.5);
    }

    @Override
    public void update() {
        if(this.getX()<0||this.getX()>App.getWIDTH()||this.getY()<0||this.getY()>App.getHEIGHT()){
            this.remove();
        }
    }
}
