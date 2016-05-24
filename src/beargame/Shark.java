package beargame;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author Anto
 */
public class Shark extends Population{
    private Timeline timeJaws;
    
    public Shark() {
    }
    
    public Shark(BearGame object, double xLocation, double yLocation, double vX, double vY, Image... spriteCels) {
        super(object, "M 88,21 L 88,21 56,40 42,50 27,58 28,72 44,72 61,62 66,58 75,52 78,49 85,31 Z", xLocation, yLocation, spriteCels);
        this.vX = vX;
        this.vY = vY;
        setTime();
    }
    
    public void setJaws() {
        timeline.stop();
        timeJaws = new Timeline(new KeyFrame(
        Duration.millis(150), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                jaws();
                vX = vY = 0;
            }
        }));
        timeJaws.setCycleCount(Animation.INDEFINITE);
        timeJaws.play();
    }
    
    public void jaws() {
        if (move < 9) {
            move++;
        }
    }
    
    @Override
    public void setImageState() {
        if (move < 3) {
            spriteFrame.setImage(imageStates.get(move));
            timeline.play();
        }
        else if (move >= 3 && move < 9) {
            spriteFrame.setImage(imageStates.get(move));
        }
        else {
            timeJaws.stop();
            bearGame.getDisplay().addToRemovedObjects(this);
            bearGame.paneRoot.getChildren().remove(this.getSpriteFrame());
        }
    }
    
    public void soundShark() {
        String uriString = new File("shark.mp3").toURI().toString();
        MediaPlayer player = new MediaPlayer( new Media(uriString));
        player.setVolume(1.0);
        player.play();
    }
    
    
}
