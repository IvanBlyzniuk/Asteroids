public class Astronaut extends GameObject{
    public Astronaut(){
        Init();
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    public void Init(){

    }
    public void onCollisions(GameObject other){
        if(other.getTag()==Tag.asteroid){

        }else if(other.getTag()==Tag.bullet){

        }else if(other.getTag()==Tag.pickup){

        }else if(other.getTag()==Tag.astronaut);
    }
}
