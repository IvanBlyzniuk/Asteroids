public class Bullet extends GameObject{

    @Override
    public void init() {
        setSprite("bullet.png");
        setTag(Tag.bullet);
    }

    @Override
    public void update() {
        if(this.getX()<App.getWIDTH()||this.getX()>App.getWIDTH()||this.getY()<App.getHEIGHT()||this.getY()>App.getHEIGHT()){
            this.remove();
        }

        System.out.println("Updating bullet");
    }
}
