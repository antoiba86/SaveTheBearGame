package beargame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

/**
 * Class of the pirate boat object
 * @author Antonio Jesús Ibáñez García
 */
public class PirateBoat extends Population{
    private Timeline timeExplosion;
    private AudioClip explosion;
    /**
     * Empty Constructor of the class
     */
    public PirateBoat() {
    }
    
    /**
     * Method to create a coin object
     * @param object It is the Game object
     * @param xLocation It is the position of the object into the X axis
     * @param yLocation It is the position of the object into the Y axis
     * @param vX It is the velocity in the X axis of the object
     * @param vY It is the velocity in the Y axis of the object
     * @param spriteCels They are the object's images
     */
    public PirateBoat(BearGame object, double xLocation, double yLocation, double vX, double vY, Image... spriteCels) {
        super(object, "M 82,10 L 82,10 74,68 61,35 47,59 12,73 46,89 56,100 66,104 105,104 122,95 128,72 129,55 122,54 112,52 113,32 98,31 93,56 90,11 Z", xLocation, yLocation, spriteCels);
        this.vX = vX;
        this.vY = vY;
        setTime();
        timeline.play();
        explosion = new AudioClip(this.getClass().getResource("/resources/Sound/explosion.mp3").toExternalForm());
    }
    
    /**
     * Method to set a Timer to change the object's image displayed when the enemy dies
     */
    public void setExplosion() {
        timeline.stop();
        timeExplosion = new Timeline(new KeyFrame(
        Duration.millis(200), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                explosion();
            }
        }));
        timeExplosion.setCycleCount(Animation.INDEFINITE);
        timeExplosion.play();
    }
    
    /**
     * Method to change the object's image
     */
    public void explosion() {
        if (move < 19) move++;
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
        else if (move >= 3 && move < 19) spriteFrame.setImage(imageStates.get(move));
        else {
            timeExplosion.stop();
            bearGame.getDisplay().addObjectToRemove(this);
            bearGame.getPaneRoot().getChildren().remove(this.getSpriteFrame());
            //bearGame.getDisplay().resetRemovedObjects();
        }
    }
    
    public AudioClip getExplosion() {
        return explosion;
    }

    public void setExplosion(AudioClip explosion) {
        this.explosion = explosion;
    }
}
