/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import javafx.scene.image.Image;

/**
 *
 * @author Anto
 */
public class Plane extends Population{
    private boolean reverse = false;
    public Plane() {
        
    }   
    
    public Plane(BearGame object, double xLocation, double yLocation, Image... spriteCels){
        super(object, "M 88,21 L 88,21 56,40 42,50 27,58 28,72 44,72 61,62 66,58 75,52 78,49 85,31 Z", xLocation, yLocation, spriteCels);
        vX = 1;
        setTime();
        timeline.play();
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
    
    @Override
    public void setBoundaries() {  
        if (iX <= LEFTBOUNDARY-50) bearGame.root.getChildren().remove(this.getSpriteFrame());
    }
}
