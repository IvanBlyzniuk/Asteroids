import com.sun.javafx.tk.quantum.MasterTimer;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class GameEngine {

    public static final int frameLength = 500;

    private static GameEngine theEngine = new GameEngine();

    private static ArrayList<GameObject> gameObjects = new ArrayList<>();

    private static boolean end;

    private GameEngine(){

    }

    public static void startGame(){
        addGameObject(LevelManager.getManager());
        LevelManager.getManager().initGame();
        while(!end){
            System.out.println("1");
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

    private static void checkCollisions(GameObject obj) {
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
