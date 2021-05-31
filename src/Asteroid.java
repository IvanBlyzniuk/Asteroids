public class Asteroid extends GameObject{
    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    //Если не работеет, можно делать update и checkForCollisions в GameEngine 2 разными циклами
    @Override
    public void onCollision(GameObject other){
        if(other.getTag() == Tag.asteroid){

        }
    }
}
