import java.util.ArrayList;

public class GameEngine {

    private static GameEngine theEngine = new GameEngine();

    ArrayList<GameObject> gameObjects = new ArrayList<>();

    private static boolean end;

    private GameEngine(){

    }

    public static void startGame(){
        while(!end){

        }
    }

    public static void stopGame(){
        end = true;
    }

     public static GameEngine getEngine(){
        return theEngine;
     }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
