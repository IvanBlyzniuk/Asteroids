import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Explosion extends GameObject{

    private int timeToLive;
    private static final int maxTimeToLive = 10;

    @Override
    public void init() {
        timeToLive = maxTimeToLive;
        setSprite("explosion.png");
        getSprite().setFitWidth(200);
        getSprite().setFitHeight(200);
        setTag(Tag.explosion);
        setHitBoxSize(100);
        String musicFile = "Sounds\\Explosion.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(App.getVolume());
        mediaPlayer.play();
    }

    @Override
    public void update() {
        if(timeToLive < maxTimeToLive)
            setHitBoxSize(0);
        if(timeToLive <= 0){
            remove();
        }else {
            timeToLive--;
        }
    }
}
