import javafx.geometry.Point2D;

public class Asteroid extends GameObject{

    private Point2D newSpeed;
    private boolean needToChangeSpeed;
    private double invulnerabilyTimer;

    @Override
    public void init() {
        setTag(Tag.asteroid);
        setTag(Tag.teleFraggable);
    }

    @Override
    public void update() {
        if(needToChangeSpeed){
            needToChangeSpeed = false;
            setSpeed(newSpeed);
        }
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
        if(invulnerabilyTimer > 0)
            invulnerabilyTimer--;
    }

    //Если много склеек, можно делать move
    @Override
    public void onCollision(GameObject other){
        if(other.getTag().contains(Tag.asteroid)||other.getTag().contains(Tag.astronaut)){
            double newVelX = (getSpeed().getX() * (getHitBoxSize() - other.getHitBoxSize()) + (2 * other.getHitBoxSize() * other.getSpeed().getX())) / (getHitBoxSize() + other.getHitBoxSize());
            double newVelY = (getSpeed().getY() * (getHitBoxSize() - other.getHitBoxSize()) + (2 * other.getHitBoxSize() * other.getSpeed().getY())) / (getHitBoxSize() + other.getHitBoxSize());
            Point2D newVel = new Point2D(newVelX,newVelY);
            needToChangeSpeed = true;
            newSpeed = newVel;
        }else if(other.getTag().contains(Tag.bullet)&&invulnerabilyTimer <= 0){
            GameEngine.remove(other);
            int pickupChance = LevelManager.random.nextInt(2);
            if(pickupChance == 0 && LevelManager.getPickupSpawnCooldown() <= 0 && LevelManager.getManager().isCanSpawnPickup()){
                System.out.println("Spawned pickup");
                LevelManager.getManager().createPickup(getCentreX(),getCentreY());
                LevelManager.getManager().setCanSpawnPickup(false);
            }
            GameEngine.remove(this);
            if(getTag().contains(Tag.countsTowardsCap)){
                LevelManager.getManager().setAsteroidsNumber(LevelManager.getManager().getAsteroidsNumber() - 1);
            }
            //Levelmanager.addScore();
            if(getTag().contains(Tag.big)){
                double randomNum = LevelManager.random.nextInt(4);
                if(randomNum < 2){
                    int angle = LevelManager.random.nextInt(360);
                    Point2D additionalSpeed = new Point2D(Math.cos(Math.toRadians(angle)),Math.sin(Math.toRadians(angle)));
                    Asteroid part1 = new Asteroid();
                    part1.setTag(Tag.small);
                    part1.setSprite("smallPepesteroid.png");
                    part1.moveCentreTo(getCentreX() + additionalSpeed.getY()*getHitBoxSize(), getCentreY() + additionalSpeed.getY()*getHitBoxSize());
                    part1.setSpeed(new Point2D(getSpeed().getX()+additionalSpeed.getX(), getSpeed().getY()+additionalSpeed.getY()));
                    part1.getSprite().setFitWidth(50);
                    part1.getSprite().setFitHeight(50);
                    part1.setHitBoxSize(25);
                    part1.setInvulnerabilyTimer(20);

                    Asteroid part2 = new Asteroid();
                    part2.setTag(Tag.small);
                    part2.setSprite("smallPepesteroid.png");
                    part2.moveCentreTo(getCentreX() - additionalSpeed.getY()*getHitBoxSize(), getCentreY() - additionalSpeed.getY()*getHitBoxSize());
                    part2.setSpeed(new Point2D(getSpeed().getX()-additionalSpeed.getX(), getSpeed().getY()-additionalSpeed.getY()));
                    part2.getSprite().setFitWidth(50);
                    part2.getSprite().setFitHeight(50);
                    part2.setHitBoxSize(25);
                    part2.setInvulnerabilyTimer(20);
                }else if (randomNum == 2){
                    int angle = LevelManager.random.nextInt(360);
                    for (int i=0;i<3;i++){
                        Point2D additionalSpeed = new Point2D(Math.cos(Math.toRadians(angle+i*120)),Math.sin(Math.toRadians(angle+i*120)));
                        Asteroid part1 = new Asteroid();
                        part1.setTag(Tag.small);
                        part1.setSprite("smallPepesteroid.png");
                        part1.moveCentreTo(getCentreX() + additionalSpeed.getY()*getHitBoxSize(), getCentreY() + additionalSpeed.getY()*getHitBoxSize());
                        part1.setSpeed(new Point2D(getSpeed().getX()+additionalSpeed.getX(), getSpeed().getY()+additionalSpeed.getY()));
                        part1.getSprite().setFitWidth(50);
                        part1.getSprite().setFitHeight(50);
                        part1.setHitBoxSize(25);
                        part1.setInvulnerabilyTimer(20);
                    }
                }
            }

        }
    }

    private void setInvulnerabilyTimer(double timer){
        invulnerabilyTimer = timer;
    }
}
