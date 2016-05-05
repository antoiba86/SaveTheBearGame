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
 * @author DAW13
 */
public class Coin extends Population{
    protected BearGame bearGame;
    protected Timeline timeline;
    protected double vX;
    protected double vY;
    protected int move = 0;
    
    public Coin(BearGame object, double xLocation, double yLocation, Image... spriteCels) {
        super(object, "M 19,2L 19,2 9,5 4,10 1,16 1,26 6,33 11,36 15,38 25,38 34,32 37,24 37,14 33,7 26,4 22,2 Z", xLocation, yLocation, spriteCels);
        bearGame = object;
        vX = vY = 0;
        setTime();
    }
    
    @Override
    public void update() {
        setImageState();
        setXYLocation();
        moveImage(iX, iY);
        
    }
    
    @Override
    public void setTime() {
        timeline = new Timeline(new KeyFrame(
        Duration.millis(200), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                changeImage();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    public void changeImage() {
        switch (move) {
            case 0: move++; break;
            case 1: move++; break;
            case 2: move++; break;
            case 3: move++; break;
            case 4: move++; spriteBound.setContent("M 19,0L 19,0 16,0 16,38 23,38 23,1 Z"); 
                    break;
            case 5: move++; spriteBound.setContent("M 19,2L 19,2 9,5 4,10 1,16 1,26 6,33 11,36 15,38 25,38 34,32 37,24 37,14 33,7 26,4 22,2 Z");
                    break;
            case 6: move++; break;
            default: move = 0;break;
        }
    }
    private void setImageState() {
        spriteFrame.setImage(imageStates.get(move));
        timeline.play();
    }
    
    private void setXYLocation() {
        iX -= vX;
        iY -= vY;
    }
}
