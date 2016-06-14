package beargame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

/**
 * Class of the coin object
 * @author Antonio Jesús Ibáñez García
 */
public class Coin extends Population{
    protected BearGame bearGame;
    protected Timeline timeline;
    protected double vX;
    protected double vY;
    protected int move = 0;
    
    /**
     * Method to create a coin object
     * @param object It is the Game object
     * @param xLocation It is the position of the object into the X axis
     * @param yLocation It is the position of the object into the Y axis
     * @param spriteCels They are the object's images
     */
    public Coin(BearGame object, double xLocation, double yLocation, Image... spriteCels) {
        super(object, "M 19,2L 19,2 9,5 4,10 1,16 1,26 6,33 11,36 15,38 25,38 34,32 37,24 37,14 33,7 26,4 22,2 Z", xLocation, yLocation, spriteCels);
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
            case 2: move++; break;
            case 3: move++; break;
            case 4: move++; spriteBound.setContent("M 19,0L 19,0 16,0 16,38 23,38 23,1 Z"); 
                    break;
            case 5: move++; spriteBound.setContent("M 19,2L 19,2 9,5 4,10 1,16 1,26 6,33 11,36 15,38 25,38 34,32 37,24 37,14 33,7 26,4 22,2 Z");
                    break;
            case 6: move++; break;
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
    public void musicCoin () {
        AudioClip soundCoin = new AudioClip(this.getClass().getResource("/resources/Sound/coin.mp3").toExternalForm());
        soundCoin.play();
    }
}
