import java.util.ArrayList;

public class GameEngine {

    private static GameEngine theEngine = new GameEngine();

    ArrayList<GameObject> gameObjects = new ArrayList<>();

    private GameEngine(){

    }

     public static GameEngine getEngine(){
        return theEngine;
     }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
