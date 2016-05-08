/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Gemstone extends Population{
    protected BearGame bearGame;
    protected Timeline timeline;
    protected double vX;
    protected double vY;
    protected int move = 0;
    
    public Gemstone(BearGame object, double xLocation, double yLocation, Image... spriteCels) {
        super(object, "M 20,1 L 20,1 9,1 0,11 0,30 7,39 31,39 39,31 39,10 31,1 Z", xLocation, yLocation, spriteCels);
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
    
    @Override
    public void changeImage() {
        switch (move) {
            case 0: move++; break;
            case 1: move++; break;
            case 2: move++; spriteBound.setContent("M 20,0 L 20,0 15,0 9,11 9,29 14,39 25,40 29,31 29,10 24,0 Z"); 
                break;
            case 3: move++; move++; spriteBound.setContent("M 20,1 L 20,1 9,1 0,11 0,30 7,39 31,39 39,31 39,10 31,1 Z"); 
                break;
            case 4: move++; break;
            default: move = 0;break;
        }
    }
    @Override
    protected void setImageState() {
        spriteFrame.setImage(imageStates.get(move));
        timeline.play();
    }
    
    public static void musicCoin() {
        String uriString = new File("coin.mp3").toURI().toString();
        MediaPlayer player = new MediaPlayer( new Media(uriString));
        player.play();
    }
}
