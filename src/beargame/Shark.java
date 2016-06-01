package beargame;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Class of the shark object
 * @author Antonio Jesús Ibáñez García
 */
public class Shark extends Population{
    private Timeline timeJaws;
    
    /**
     * Empty constructor of the object
     */
    public Shark() {
    }
    
    /**
     * Method to create a shark object
     * @param object It is the Game object
     * @param xLocation It is the position of the object into the X axis
     * @param yLocation It is the position of the object into the Y axis
     * @param vX It is the velocity in the X axis of the object
     * @param vY It is the velocity in the Y axis of the object
     * @param spriteCels They are the object's images
     */
    public Shark(BearGame object, double xLocation, double yLocation, double vX, double vY, Image... spriteCels) {
        super(object, "M 88,21 L 88,21 56,40 42,50 27,58 28,72 44,72 61,62 66,58 75,52 78,49 85,31 Z", xLocation, yLocation, spriteCels);
        this.vX = vX;
        this.vY = vY;
        setTime();
    }
    
    /**
     * Method to set a Timer to change the object's image displayed
     */
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
    
    /**
     * Method to change the object's image
     */
    public void jaws() {
        if (move < 9) {
            move++;
        }
    }
    
    /**
     * Method to change the object's image in the game
     */
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
    
    /**
     * Method to get the object sound when there is a collision
     */
    public void soundShark() {
        AudioClip soundShark = new AudioClip(this.getClass().getResource("/shark.mp3").toExternalForm());
        soundShark.setVolume(1.0);
        soundShark.play();
    }
    
    
}
