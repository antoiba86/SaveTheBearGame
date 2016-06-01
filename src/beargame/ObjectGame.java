package beargame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;

/**
 * Class of the father object
 * @author Antonio Jesús Ibáñez García
 */
public abstract class ObjectGame {
    protected List<Image> imageStates = new ArrayList<>();
    protected ImageView spriteFrame;
    protected SVGPath spriteBound;
    protected double iX;
    protected double iY;
    
    /**
     * Empty Constructor of the class
     */
    public ObjectGame() {
        
    }
    
    /**
     * Method to create a objectGame
     * @param SVGdata It is the SVG data of the object
     * @param xLocation It is the position of the object into the X axis
     * @param yLocation It is the position of the object into the Y axis
     * @param spriteCels They are the object's images
     */
    public ObjectGame(String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        spriteBound = new SVGPath();
        spriteBound.setContent(SVGdata);
        spriteFrame = new ImageView(spriteCels[0]);
        imageStates.addAll(Arrays.asList(spriteCels));
        iX = xLocation;
        iY = yLocation;
    }
    
    /**
     * Method abstract of the update
     */
    public abstract void update();
    
    /**
     * Empty method for the children to set a Timer
     */
    public void setTime() {
    }
    
    /**
     * Method to get the arrayList of the object image state
     * @return The object's array of images
     */
    public List<Image> getImageStates() {
        return imageStates;
    }

    /**
     * Method to set the array image state
     * @param imageStates It is the array with the images
     */
    public void setImageStates(List<Image> imageStates) {
        this.imageStates = imageStates;
    }

    /**
     * Method to get the object's sprite
     * @return The object's sprite
     */
    public ImageView getSpriteFrame() {
        return spriteFrame;
    }

    /**
     * Method to set the object's sprite
     * @param spriteFrame It is the object's sprite
     */
    public void setSpriteFrame(ImageView spriteFrame) {
        this.spriteFrame = spriteFrame;
    }

    /**
     * Method to get the object's bound
     * @return The object's bound
     */
    public SVGPath getSpriteBound() {
        return spriteBound;
    }

    /**
     * Method to set the object's bound
     * @param spriteBound It is the object's bound
     */
    public void setSpriteBound(SVGPath spriteBound) {
        this.spriteBound = spriteBound;
    }
    
    /**
     * Method to get the object's X position
     * @return The object's X position
     */
    public double getiX() {
        return iX;
    }

    /**
     * Method to set the object's X position
     * @param iX It is the object's X position
     */
    public void setiX(double iX) {
        this.iX = iX;
    }

    /**
     * Method to get the object's Y position
     * @return The object's Y position
     */
    public double getiY() {
        return iY;
    }

    /**
     * Method to set the object's Y position
     * @param iY It is the object's Y position
     */
    public void setiY(double iY) {
        this.iY = iY;
    }
    
    
}
