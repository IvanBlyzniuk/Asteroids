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
        setHitBoxSize(33);

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
