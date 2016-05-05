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
public class PirateBoat extends Population{
    
    public PirateBoat() {
        
    }
    
    public PirateBoat(BearGame object, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(object, SVGdata, xLocation, yLocation, spriteCels);
        vX = vY = 2;
        setTime();
        setTimeMovement();
    }
}
