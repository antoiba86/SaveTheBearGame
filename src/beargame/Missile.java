/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * Class of the missile object
 * @author Antonio Jesús Ibáñez García
 */
public class Missile extends Population {
    Timeline timeExplosion;
    private AudioClip explosion;
    
    /**
     * Empty constructor of the class
     */
    public Missile() {
    }
    
    /**
     * Method to create a missile object
     * @param object It is the Game object
     * @param xLocation It is the position of the object into the X axis
     * @param yLocation It is the position of the object into the Y axis
     * @param vX It is the velocity in the X axis of the object
     * @param vY It is the velocity in the Y axis of the object
     * @param spriteCels They are the object's images
     */
    public Missile(BearGame object, double xLocation, double yLocation, double vX, double vY, Image... spriteCels){
        super(object, "M 7,11 L 7,11 3,14 2,17 0,21 0,24 2,25 2,33 6,40 11,33 11,25 13,22 11,19 10,14 Z", xLocation, yLocation, spriteCels);
        this.vY = vY;
        setTime();
        timeline.play();
        explosion = new AudioClip(this.getClass().getResource("/resources/Sound/explosion.mp3").toExternalForm());
    }
    
    /**
     * Method to set the boundaries of the object
     */
    @Override
    public void setBoundaries() {  
        if (iY >= BOTTOMBOUNDARY+50) bearGame.getPaneRoot().getChildren().remove(this.getSpriteFrame());
    }
    
    /**
     * Method to set a Timer to change the object's image displayed when the Hero dies
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
        if (move < 4) {
            spriteFrame.setImage(imageStates.get(move));
        }
        else if (move >= 4 && move < 19) spriteFrame.setImage(imageStates.get(move));
        else {
            timeExplosion.stop();
            bearGame.getDisplay().addObjectToRemove(this);
            bearGame.getPaneRoot().getChildren().remove(this.getSpriteFrame());
           // bearGame.getDisplay().resetRemovedObjects();
        }
    }
    
    public AudioClip getExplosion() {
        return explosion;
    }

    public void setExplosion(AudioClip explosion) {
        this.explosion = explosion;
    }
}
