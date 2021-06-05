import javafx.geometry.Point2D;

import java.util.ArrayList;

import java.util.Stack;

/**
 * GameEngine class responsible for creating, updating, deleting GameObjects, checking for collisions
 */
public class GameEngine {

    public static final int frameLength = 16;

    private static GameEngine theEngine = new GameEngine();

    private static ArrayList<GameObject> gameObjects = new ArrayList<>();

    private static Stack<GameObject> toDelete = new Stack<>();
    private static Stack<GameObject> toAdd = new Stack<>();

    private static LevelManager manager;

    private static boolean needToCleanScreen;

    public static boolean isPlaying = true;

    private GameEngine(){

    }

    /**
     * Starts the engine, starting the game process, should be called when the start button is pressed
     */
    public static void startGame(){
        isPlaying = true;
        needToCleanScreen = false;
        LevelManager.initManager();
        manager = LevelManager.getManager();
    }

    /**
     * updates, adds, deletes GameObjects and checks for collisions, should be called every frame
     */
    public static void update(){
        for (GameObject obj: gameObjects){
            obj.outerUpdate();
        }
        for(GameObject obj: gameObjects){
            if(obj != manager)
                checkCollisions(obj);
        }
        while (toAdd.size() > 0){
            GameObject g = toAdd.pop();
            gameObjects.add(g);
        }
        while(toDelete.size() > 0){
            GameObject g = toDelete.pop();
            gameObjects.remove(g);
            App.getRoot().getChildren().remove(g.getSprite());
        }
        if(needToCleanScreen) cleanScreen();
    }

    /**
     * Setter for needToCleanScreen variable should be called when the player exits to main menu or dies
     * @param needToCleanScreen
     */
    public static void setNeedToCleanScreen(boolean needToCleanScreen) {
        GameEngine.needToCleanScreen = needToCleanScreen;
    }

    /**
     * checks collisions for obj1 by measuring the distance between it and all other GameObjects and comparing it to sum of their HitBoxSizes
     * @param obj1
     */
    private static void checkCollisions(GameObject obj1) {
        if(gameObjects.size()>0) {
            boolean foundCollision = false;
            for (GameObject obj2 : gameObjects) {
                if (obj2 != manager && obj1 != obj2 && distBetween(obj1, obj2) <= obj1.getHitBoxSize() + obj2.getHitBoxSize()) {
                    if (obj2 == obj1.getLastCollision() && obj1.getTag().contains(Tag.teleFraggable) && obj2.getTag().contains(Tag.teleFraggable)) {
                        if (obj1.getY() > obj2.getY()) {
                            obj1.move(new Point2D(0, 1));
                            obj2.move(new Point2D(0, -1));
                        } else {
                            obj1.move(new Point2D(0, -1));
                            obj2.move(new Point2D(0, 1));
                        }
                    }
                    foundCollision = true;
                    obj1.onCollision(obj2);
                    obj1.setLastCollision(obj2);
                }
            }
            if (!foundCollision) obj1.setLastCollision(null);
        }

    }

    /**
     * measures distance between obj1 and obj2 centres
     * @param obj1 first object
     * @param obj2 second object
     * @return distance between obj1 and obj2 centres
     */
    private static double distBetween(GameObject obj1, GameObject obj2){
        return Math.sqrt((obj1.getCentreX()-obj2.getCentreX())*(obj1.getCentreX()-obj2.getCentreX()) + (obj1.getCentreY()-obj2.getCentreY())*(obj1.getCentreY()-obj2.getCentreY()));
    }

    /**
     * Removes an object
     * @param obj object that needs to be removed
     */
    public static void remove(GameObject obj){
//        gameObjects.remove(obj);
        toDelete.add(obj);
    }

    /**
     *
     * @return the GameEngine object
     */
     public static GameEngine getEngine(){
        return theEngine;
     }

    /**
     * Adds an object
     * @param gameObject object that needs to be added
     */
    public static void addGameObject(GameObject gameObject) {
        toAdd.add(gameObject);
    }

    /**
     * Cleans screen removing all the object sprites
     */
    public static void cleanScreen(){
        isPlaying = false;
        for (GameObject obj: gameObjects) {
            if(!toDelete.contains(obj));
            remove(obj);
        }
        while(toDelete.size() > 0){
            GameObject g = toDelete.pop();
            gameObjects.remove(g);
            App.getRoot().getChildren().remove(g.getSprite());
        }
    }
}
