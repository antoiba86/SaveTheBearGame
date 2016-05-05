/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import static beargame.Hero.SPRITE_PIXELS_X;
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
public class Population extends ObjectGame {
    protected BearGame bearGame;
    protected double vX;
    protected double vY;
    protected double lifeSpan;
    protected double damage;
    protected double offsetX;
    protected double offsetY;
    protected Timeline timeline;
    protected Timeline timeline2;
    protected static final double SPRITE_PIXELS_X = 0;
    protected static final double SPRITE_PIXELS_Y = 0;
    protected static final double RIGHTBOUNDARY = BearGame.WIDTH_PIXELS - SPRITE_PIXELS_X;
    protected static final double LEFTBOUNDARY = 0;
    protected static final double BOTTOMBOUNDARY = BearGame.HEIGHT_PIXELS - SPRITE_PIXELS_Y;
    protected static final double TOPBOUNDARY = Slidding.HEIGTH_SKY;
    protected int move = 0;
    
    public Population() {
        
    }
    
    public Population(BearGame object, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        vX = vY = 2;
        bearGame = object;
        setTime();
    }

    @Override
    public void update() {
        moveImage(iX, iY);
        setXYLocation();
        setBoundaries();
        setImageState();
    }
    
    @Override
    public void setTime() {
        timeline= new Timeline(new KeyFrame(
        Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    public void setTimeMovement() {
        timeline2 = new Timeline(new KeyFrame(
        Duration.millis(200), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                changeImage();
            }
        }));
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();
    }
    
    public void moveImage(double x, double y) {
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
        spriteBound.setTranslateX(x);
        spriteBound.setTranslateY(y);
    }
    
    public void changeImage() {
        switch (move) {
            case 0: move++;break;
            case 1: move++; break;
            case 2: move++; break;
            default: move = 0;break;
        }
    }
    
    private void setXYLocation() {
        iX -= vX;
        iY -= vY;
    }
    public void setBoundaries() {  
        if (iX <= LEFTBOUNDARY-50) bearGame.root.getChildren().remove(this.getSpriteFrame());; 
        //Cuando choca contra el "suelo" el barco sigue para arriba
        if (iY >= BOTTOMBOUNDARY) vY=-vY; 
        //Cuando choca contra el "techo" el barcho va para abajo
        if (iY <= TOPBOUNDARY) vY=-vY;
    }
    private void setImageState() {
        spriteFrame.setImage(imageStates.get(move));
        timeline.play();
    }
}
