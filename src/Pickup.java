/**
 * class which describes all three types of pickups and rockets pickup
 */
public class Pickup extends GameObject{

    public void init(){
        setTag(Tag.pickup);
        int type = LevelManager.random.nextInt(10);
        if(type == 0){
            setTag(Tag.rocketPickup);
            setSprite("Sprites\\rocketPickup.png");
            getSprite().setFitWidth(40);
            getSprite().setFitHeight(40);
            setHitBoxSize(33);
        }else if(type == 1){
            setTag(Tag.protectionPickup);
            setSprite("Sprites\\protectionPickup.png");
            getSprite().setFitWidth(40);
            getSprite().setFitHeight(40);
            setHitBoxSize(33);
        }else if(type == 2){
            setTag(Tag.tripleShotPickup);
            setSprite("Sprites\\tripleShotPickup.png");
            getSprite().setFitWidth(40);
            getSprite().setFitHeight(40);
            setHitBoxSize(33);
        }else{
            setTag(Tag.addOneRocketPickup);
            setSprite("Sprites\\rocket.png");
            getSprite().setFitWidth(18);
            getSprite().setFitHeight(24);
            setHitBoxSize(9);
        }


    }

    @Override
    public void update() {
        if(this.getX()<0||this.getX()>App.getWIDTH()||this.getY()<0||this.getY()>App.getHEIGHT()){
            this.remove();
            LevelManager.getManager().setCanSpawnPickup(true);
        }
    }

    public void onCollision(GameObject other) {

     }

}
