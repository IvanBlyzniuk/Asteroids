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
    private static Stack<GameObject> toAdd = new Stack<>();

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
    }

    private static void checkCollisions(GameObject obj1) {
        for (GameObject obj2: gameObjects){
            if(obj2 != manager && obj1 != obj2 && distBetween(obj1,obj2) <= obj1.getHitBoxSize()+obj2.getHitBoxSize())
                obj1.onCollision(obj2);
        }
    }

    private static double distBetween(GameObject obj1, GameObject obj2){
        return Math.sqrt((obj1.getCentreX()-obj2.getCentreX())*(obj1.getCentreX()-obj2.getCentreX()) + (obj1.getCentreY()-obj2.getCentreY())*(obj1.getCentreY()-obj2.getCentreY()));
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
        toAdd.add(gameObject);
//        gameObjects.add(gameObject);
    }
}
