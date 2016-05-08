package beargame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 *
 * @author Anto
 */
public class Shark extends Population{
    private Timeline timeJaws;
    
    public Shark() {
    }
    
    public Shark(BearGame object, double xLocation, double yLocation, Image... spriteCels) {
        super(object, "M 88,21 L 88,21 56,40 42,50 27,58 28,72 44,72 61,62 66,58 75,52 78,49 85,31 Z", xLocation, yLocation, spriteCels);
        vX = vY = 1;
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
            bearGame.display.addToRemovedObjects(this);
            bearGame.root.getChildren().remove(this.getSpriteFrame());
        }
    }
    
    public void soundShark() {
        
    }
    
    
}
