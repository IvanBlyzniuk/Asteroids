public class LevelManager extends GameObject{

    private static LevelManager theManager = new LevelManager();

    private LevelManager(){}

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    public static LevelManager getManager(){
        return theManager;
    }

    public void initGame(){
        SpaceShip player = new SpaceShip();
    }

    public void onShootPressed(){

    }

    public void onUpPressed(){

    }

    public void onDownPressed(){

    }

    public void onLeftPressed(){

    }

    public void onRightPressed(){

    }
}
