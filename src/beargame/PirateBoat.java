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
    
    public PirateBoat(BearGame object, double xLocation, double yLocation, double vX, double vY, Image... spriteCels) {
        super(object, "M 82,10 L 82,10 74,68 61,35 47,59 12,73 46,89 56,100 66,104 105,104 122,95 128,72 129,55 122,54 112,52 113,32 98,31 93,56 90,11 Z", xLocation, yLocation, spriteCels);
        this.vX = vX;
        this.vY = vY;
        setTime();
        timeline.play();
    }
}
