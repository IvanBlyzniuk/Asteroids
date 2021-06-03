import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Rocket extends GameObject{

    private MediaPlayer mediaPlayer;

    @Override
    public void init() {
        setSprite("rocket.png");
        setTag(Tag.rocket);
        getSprite().setFitWidth(15);
        getSprite().setFitHeight(15);
        setHitBoxSize(7.5);

        String musicFile = "Sounds\\Rocket.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(App.getVolume()/2);
        mediaPlayer.play();
    }

    @Override
    public void update() {
        if(this.getX()<0||this.getX()>App.getWIDTH()||this.getY()<0||this.getY()>App.getHEIGHT()){
            this.remove();
        }
    }

    @Override
    public void onCollision(GameObject other) {
        if(other.getTag().contains(Tag.asteroid)||other.getTag().contains(Tag.astronaut)){
            remove();
            mediaPlayer.stop();
            Explosion explosion = new Explosion();
            explosion.moveCentreTo(getCentreX(),getCentreY());
        }
    }

}
