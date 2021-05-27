public class SpaceShip extends GameObject{
    private boolean vulnerable = true;
    public SpaceShip() {
        Init();
    }
<<<<<<< HEAD



    public void init(){
||||||| 43c2ed9
    public void Init(){
=======
    public void init(){
>>>>>>> 56401b338ce9fdf59a1827d6ddd9af200ce13ffd
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
