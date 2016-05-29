package beargame;

import javafx.scene.image.Image;

/**
 * Class of the pirate boat object
 * @author Antonio Jesús Ibáñez García
 */
public class PirateBoat extends Population{
    
    /**
     * Empty Constructor of the class
     */
    public PirateBoat() {
    }
    
    /**
     * Method to create a coin object
     * @param object It is the Game object
     * @param xLocation It is the position of the object into the X axis
     * @param yLocation It is the position of the object into the Y axis
     * @param vX It is the velocity in the X axis of the object
     * @param vY It is the velocity in the Y axis of the object
     * @param spriteCels They are the object's images
     */
    public PirateBoat(BearGame object, double xLocation, double yLocation, double vX, double vY, Image... spriteCels) {
        super(object, "M 82,10 L 82,10 74,68 61,35 47,59 12,73 46,89 56,100 66,104 105,104 122,95 128,72 129,55 122,54 112,52 113,32 98,31 93,56 90,11 Z", xLocation, yLocation, spriteCels);
        this.vX = vX;
        this.vY = vY;
        setTime();
        timeline.play();
    }
}
