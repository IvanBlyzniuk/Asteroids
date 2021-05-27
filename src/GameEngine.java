import com.sun.javafx.tk.quantum.MasterTimer;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class GameEngine {

    public static final int frameLength = 16;

    private static GameEngine theEngine = new GameEngine();

    private static ArrayList<GameObject> gameObjects = new ArrayList<>();

    private static boolean end;

    private GameEngine(){

    }

    public static void startGame(){
        addGameObject(LevelManager.getManager());
        while(!end){
            for (GameObject obj: gameObjects){
                obj.update();
                checkCollisions(obj);
                try{
                    Thread.sleep(frameLength);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static void checkCollisions(GameObject obj1) {
        for (GameObject obj2: gameObjects){
            if(obj1 != obj2 && distBetween(obj1,obj2) <= obj1.getHitBoxSize()+obj2.getHitBoxSize())
                obj1.onCollision(obj2);
        }
    }

    private static double distBetween(GameObject obj1, GameObject obj2){
        return Math.sqrt((obj1.getX()-obj2.getX())*(obj1.getX()-obj2.getX()) + (obj1.getY()-obj2.getY())*(obj1.getY()-obj2.getY()));
    }

    public static void stopGame(){
        end = true;
    }

    public static void remove(GameObject obj){
        gameObjects.remove(obj);
    }

     public static GameEngine getEngine(){
        return theEngine;
     }

    public static void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
