import com.sun.javafx.tk.quantum.MasterTimer;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class GameEngine {

    public static final int frameLength = 16;

    private static GameEngine theEngine = new GameEngine();

    private static ArrayList<GameObject> gameObjects = new ArrayList<>();

    private static Stack<GameObject> toDelete = new Stack<>();

    private static LevelManager manager;

    private static boolean end;

    private static int iter = 0;

    private GameEngine(){

    }

    public static void startGame(){
        LevelManager.initManager();
        manager = LevelManager.getManager();
    }

    public static void update(){
//        System.out.println("Iter: "+iter++);
        for (GameObject obj: gameObjects){
//            System.out.println(obj.getClass());
            obj.outerUpdate();
            if(obj != manager)
                checkCollisions(obj);
        }
        while(toDelete.size() > 0){
            GameObject g = toDelete.pop();
            gameObjects.remove(g);
        }
    };

    private static void checkCollisions(GameObject obj1) {
        for (GameObject obj2: gameObjects){
            if(obj2 != manager && obj1 != obj2 && distBetween(obj1,obj2) <= obj1.getHitBoxSize()+obj2.getHitBoxSize())
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
//        gameObjects.remove(obj);
        toDelete.add(obj);
    }

     public static GameEngine getEngine(){
        return theEngine;
     }

    public static void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
