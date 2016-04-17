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
public class Populate extends ObjectGame {
    protected BearGame bearGame;
    protected double vX;
    protected double vY;
    protected double lifeSpan;
    protected double damage;
    protected double offsetX;
    protected double offsetY;
    protected static final double SPRITE_PIXELS_X = 187;
    protected static final double SPRITE_PIXELS_Y = 164;
    protected static final double rightBoundary = BearGame.WIDTH_PIXELS/2 - SPRITE_PIXELS_X/2;
    protected static final double leftBoundary = -(BearGame.WIDTH_PIXELS/2 - SPRITE_PIXELS_X/2);
    protected static final double bottomBoundary = BearGame.HEIGHT_PIXELS/2 - SPRITE_PIXELS_Y/2;
    protected static final double topBoundary = -(BearGame.HEIGHT_PIXELS/2 - SPRITE_PIXELS_Y/2);
    Timeline timeline;
    
    public Populate() {
        
    }
    
    public Populate(BearGame object, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        vX = vY = 2;
        iX = xLocation;
        iY = yLocation;
        bearGame = object;
        setTime();
    }

    @Override
    public void update() {
        moveImage(iX, iY);
    }
    
    @Override
    public void setTime() {
        timeline= new Timeline(new KeyFrame(
        Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                setXYLocation();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    public void moveImage(double x, double y) {
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
    }
    private void setXYLocation() {
        iX -= vX;
        iY -= vY;
    }
    
}
