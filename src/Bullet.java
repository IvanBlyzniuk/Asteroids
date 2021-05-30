public class Bullet extends GameObject{
    @Override
    public void init() {
        setSprite("bullet.png");
        setTag(Tag.bullet);
    }

    @Override
    public void update() {
        if(this.getX()<0||this.getX()>App.getWIDTH()||this.getY()<0||this.getY()>App.getHEIGHT()){
            this.remove();
        }

        System.out.println("Updating bullet");
    }

}
