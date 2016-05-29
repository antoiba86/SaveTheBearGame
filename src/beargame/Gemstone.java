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
 * Class of the gem object
 * @author Antonio Jesús Ibáñez García
 */
public class Gemstone extends Population{
    protected BearGame bearGame;
    protected Timeline timeline;
    protected double vX;
    protected double vY;
    protected int move = 0;
    
      /**
     * Method to create a gem object
     * @param object It is the Game object
     * @param xLocation It is the position of the object into the X axis
     * @param yLocation It is the position of the object into the Y axis
     * @param spriteCels They are the object's images
     */
    public Gemstone(BearGame object, double xLocation, double yLocation, Image... spriteCels) {
        super(object, "M 20,1 L 20,1 9,1 0,11 0,30 7,39 31,39 39,31 39,10 31,1 Z", xLocation, yLocation, spriteCels);
        bearGame = object;
        vX = vY = 0;
        setTime();
    }
    
    /**
     * Method to update the object
     */
    @Override
    public void update() {
        setImageState();
        setXYLocation();
        moveImage(iX, iY);
        
    }
    
    /**
     * Method to set a Timer to change the object's image displayed
     */
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
    
    /**
     * Method to change the object's image
     */
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
    
    /**
     * Method to change the object's image in the game
     */
    @Override
    protected void setImageState() {
        spriteFrame.setImage(imageStates.get(move));
        timeline.play();
    }
    
    /**
     * Method to get the object sound when there is a collision
     */
    public static void musicCoin() {
        String uriString = new File("coin.mp3").toURI().toString();
        MediaPlayer player = new MediaPlayer( new Media(uriString));
        player.play();
    }
}
