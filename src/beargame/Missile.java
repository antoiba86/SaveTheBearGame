/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import javafx.scene.image.Image;

/**
 * Class of the missile object
 * @author Antonio Jesús Ibáñez García
 */
public class Missile extends Population {
    
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
    }
    
    /**
     * Method to set the boundaries of the object
     */
    @Override
    public void setBoundaries() {  
        if (iY >= BOTTOMBOUNDARY+50) bearGame.paneRoot.getChildren().remove(this.getSpriteFrame());
    }
}
