public class Pickup extends GameObject{

    public void init(){
        setTag(Tag.pickup);
        int type = LevelManager.random.nextInt(3);
        if(type == 0){
            setTag(Tag.rocketPickup);
            setSprite("rocketPickup.png");
        }
        if(type == 1){
            setTag(Tag.protectionPickup);
            setSprite("protectionPickup.png");
        }
        if(type == 2){
            setTag(Tag.tripleShotPickup);
            setSprite("tripleShotPickup.png");
        }
        getSprite().setFitWidth(40);
        getSprite().setFitHeight(40);
        setHitBoxSize(20);

    }

    @Override
    public void update() {

    }

    public void onCollision(GameObject other) {

     }

}
