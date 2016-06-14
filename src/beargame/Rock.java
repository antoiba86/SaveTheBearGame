/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 *
 * @author Anto
 */
public class Rock extends Population{
    
    /**
     * Empty constructor of the class
     */
    public Rock() {
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
    public Rock(BearGame object, double xLocation, double yLocation, double vX, double vY, Image... spriteCels){
        super(object, "M 17,4 L 17,4 10,4 4,9 4,18 10,24 20,24 26,18 26,10 19,4 Z", xLocation, yLocation, spriteCels);
        this.vX = vX;
        setTime();
        timeline.play();
    }
    
    /**
     * Method to update the object
     */
    @Override
    public void update() {
        moveImage(iX, iY);
        setXYLocation();
        setBoundaries();
        setImageState();
        checkCollision();
    }
    
    /**
     * Method to set the boundaries of the object
     */
    @Override
    public void setBoundaries() {  
        if (iX >= RIGHTBOUNDARY-50) {
            bearGame.getDisplay().addRockToRemove(this);
            bearGame.getPaneRoot().getChildren().remove(this.getSpriteFrame());
        }
    }
    
    /**
     * Method to check the collision of the hero with other objects
     */
    public void checkCollision() {
        for(int i=0; i< bearGame.getDisplay().getObjectDisplayed().size(); i++) {
            ObjectGame object = bearGame.getDisplay().getObjectDisplayed().get(i);
            Collision.collide(object, this, bearGame);
        }
    }
    
    /**
     * Method to change the object's image
     */
    @Override
    public void changeImage() {
        if (move == 0) move = 1;
        else move = 0;
    }
    
    /**
     * Method to get the object sound when there is a collision
     */
    public void rockSound() {
        AudioClip soundCoin = new AudioClip(this.getClass().getResource("/resources/Sound/rockthrown.mp3").toExternalForm());
        soundCoin.play();
    }
}
