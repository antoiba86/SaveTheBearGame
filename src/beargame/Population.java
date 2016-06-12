package beargame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Class of population, child of ObjectGame
 * @author Antonio Jesús Ibáñez García
 */
public class Population extends ObjectGame {
    protected BearGame bearGame;
    protected double vX;
    protected double vY;
    protected Timeline timeline;
    protected static final double SPRITE_PIXELS_X = 0;
    protected static final double SPRITE_PIXELS_Y = 0;
    protected static final double RIGHTBOUNDARY = Menu.WIDTH_PIXELS - SPRITE_PIXELS_X;
    protected static final double LEFTBOUNDARY = 0;
    protected static final double BOTTOMBOUNDARY = Menu.HEIGHT_PIXELS - SPRITE_PIXELS_Y;
    protected static final double TOPBOUNDARY = Slidding.HEIGTH_SKY;
    protected int move = 0;
    
    /**
     * Empty constructor of the object
     */
    public Population() {  
    }
    
    /**
     * Method to create a population object
     * @param object It is the Game object
     * @param SVGdata It is the SVG data of the object
     * @param xLocation It is the position of the object into the X axis
     * @param yLocation It is the position of the object into the Y axis
     * @param spriteCels They are the object's images
     */
    public Population(BearGame object, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        bearGame = object;
        setTime();
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
    }
    
    /**
     * Method to set a Timer to change the object's image displayed
     */
    @Override
    public void setTime() {
        timeline= new Timeline(new KeyFrame(
        Duration.millis(200), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                changeImage();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    /**
     * Method to change the object's image
     */
    public void changeImage() {
        switch (move) {
            case 0: move++;break;
            case 1: move++; break;
            case 2: move++; break;
            default: move = 0;break;
        }
    }
    
    /**
     * Method to change the object's image in the game
     */
    protected void setImageState() {
        spriteFrame.setImage(imageStates.get(move));
    }
    
    /**
     * Method to set the object frame and bounds inside the game loop
     * @param x It is the position in the X axis
     * @param y It is the position in the Y axis
     */
    public void moveImage(double x, double y) {
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
        spriteBound.setTranslateX(x);
        spriteBound.setTranslateY(y);
    }
    
    /**
     * Method to move the object inside the game loop
     */
    protected void setXYLocation() {
        iX += vX;
        iY += vY;
    }
    
    /**
     * Method to set the boundaries of the object
     */
    public void setBoundaries() {  
        if (iX <= LEFTBOUNDARY-50) bearGame.getPaneRoot().getChildren().remove(this.getSpriteFrame()); 
        //Cuando choca contra el "suelo" el barco sigue para arriba
        if (iY >= BOTTOMBOUNDARY) vY=-vY; 
        //Cuando choca contra el "techo" el barcho va para abajo
        if (iY <= TOPBOUNDARY) vY=-vY;
    }

    /**
     * Method to return the variable for the image state
     * @return It is the number of image to display at that moment
     */
    public int getMove() {
        return move;
    }

    /**
     * Method to set the variable for the image state
     * @param move It is the number of image
     */
    public void setMove(int move) {
        this.move = move;
    }

    public double getvX() {
        return vX;
    }

    public void setvX(double vX) {
        this.vX = vX;
    }

    public double getvY() {
        return vY;
    }

    public void setvY(double vY) {
        this.vY = vY;
    }
    
    
    
}
