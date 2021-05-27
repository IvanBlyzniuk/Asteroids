public class SpaceShip extends GameObject{
    private boolean vulnerable = true;
    public SpaceShip() {
        init();
    }



    public void init(){
        setSprite("SpaceShacal.png");
        setX(App.getWIDTH()/2);
        setY(App.getHEIGHT()/2);
        setTag(Tag.player);

    }

    @Override
    public void update() {

    }

    public void onCollision(GameObject other){
        if(other.getTag()==Tag.asteroid&&vulnerable){
            GameEngine.stopGame();
        }else if(other.getTag()==Tag.asteroid){

        } else if(other.getTag()==Tag.astronaut){
            SceneManager.setScore(SceneManager.getScore()+5);
        }else if(other.getTag()==Tag.pickup){
            ((Pickup)(other)).upgrade();
        }
    }

}
