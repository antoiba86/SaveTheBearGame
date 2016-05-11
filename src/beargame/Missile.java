/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import javafx.scene.image.Image;

/**
 *
 * @author antoiba86
 */
public class Missile extends Population {
    
    public Missile() {
    }
    
    public Missile(BearGame object, double xLocation, double yLocation, double vX, double vY, Image... spriteCels){
        super(object, "M 7,11 L 7,11 3,14 2,17 0,21 0,24 2,25 2,33 6,40 11,33 11,25 13,22 11,19 10,14 Z", xLocation, yLocation, spriteCels);
        this.vY = vY;
        setTime();
        timeline.play();
    }
    
    @Override
    public void setBoundaries() {  
        if (iY >= BOTTOMBOUNDARY+50) bearGame.paneRoot.getChildren().remove(this.getSpriteFrame());
    }
    
    /*public void setBoundaries() {  
        if (iX <= LEFTBOUNDARY-50) bearGame.root.getChildren().remove(this.getSpriteFrame()); 
        //Cuando choca contra el "suelo" el barco sigue para arriba
        if (iY >= BOTTOMBOUNDARY) vY=-vY; 
        //Cuando choca contra el "techo" el barcho va para abajo
        if (iY <= TOPBOUNDARY) vY=-vY;
    }*/
}
