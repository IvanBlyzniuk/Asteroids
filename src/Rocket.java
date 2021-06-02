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

    @Override
    public void onCollision(GameObject other) {
        if(other.getTag().contains(Tag.asteroid)||other.getTag().contains(Tag.astronaut)){
            remove();
            Explosion explosion = new Explosion();
            explosion.moveCentreTo(getCentreX(),getCentreY());
        }
    }
}
