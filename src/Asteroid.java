public class Asteroid extends GameObject{
    @Override
    public void init() {
        setTag(Tag.asteroid);
    }

    @Override
    public void update() {
        if(getX()<-100){
            setX(App.getWIDTH()+100);
        }
        if(getX()>App.getWIDTH()+100){
            setX(-100);
        }
        if(getY()<-100){
            setY(App.getHEIGHT()+100);
        }
        if(getY()>App.getHEIGHT()+100){
            setY(-100);
        }
    }
}
