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
import javafx.util.Duration;

/**
 *
 * @author Anto
 */
public class Plane extends Population{
    private boolean reverse = false;
    private static final double HALF_OF_IMAGE_WIDTH = 42;
    private static final double HALF_OF_IMAGE_HEIGHT = 32;
    private Image[] missile_image = new Image[4];
    private Timeline timeFire;
    
    public Plane() {
        
    }   
    
    public Plane(BearGame object, double xLocation, double yLocation, double vX, double vY, Image... spriteCels){
        super(object, "M 88,21 L 88,21 56,40 42,50 27,58 28,72 44,72 61,62 66,58 75,52 78,49 85,31 Z", xLocation, yLocation, spriteCels);
        this.vX = vX;
        setTime();
        setTimeFire();
        timeline.play();
        imageMissile();
        timeFire.play();
    }
    
    @Override
    public void changeImage() {
        if (move < 8 && !reverse) {
            move++;
            if (move == 8) reverse = true;
        }
        else if (move <= 8 && reverse) {
            move--;
            if (move == 0) reverse = false;
        }
    }
    
    public void setTimeFire() {
        timeFire= new Timeline(new KeyFrame(
        Duration.millis(5000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                fire_missile();
            }
        }));
        timeFire.setCycleCount(Animation.INDEFINITE);
    }
    
    @Override
    public void setBoundaries() {  
        if (iX <= LEFTBOUNDARY-50) bearGame.paneRoot.getChildren().remove(this.getSpriteFrame());
    }
    
    private void imageMissile() {
        for (int i = 0; i < missile_image.length;i++) {
            missile_image[i] = new Image("missile" + (i+1) + ".png", 13,40,true,false,true);
        }
    }
    
    private void fire_missile() {
        Missile missile = new Missile(bearGame, iX+13, iY+40, 0,1,missile_image[0],missile_image[1],missile_image[2],missile_image[3]);
        bearGame.getDisplay().addDisplayed_Object(missile);
        bearGame.paneRoot.getChildren().add(missile.spriteFrame);
    }

    public Timeline getTimeFire() {
        return timeFire;
    }

    public void setTimeFire(Timeline timeFire) {
        this.timeFire = timeFire;
    }
    
    
}
