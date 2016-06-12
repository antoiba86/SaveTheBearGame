package beargame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Class of the plane object
 * @author Antonio Jesús Ibáñez García
 */
public class Plane extends Population{
    private boolean reverse = false;
    private Image[] missile_image = new Image[4];
    private Image[] explosion = new Image[15];
    private Timeline timeFire;
    
    /**
     * Empty constructor of the object
     */
    public Plane() {
    }   
    
    /**
     * Method to create a plane object
     * @param object It is the Game object
     * @param xLocation It is the position of the object into the X axis
     * @param yLocation It is the position of the object into the Y axis
     * @param vX It is the velocity in the X axis of the object
     * @param vY It is the velocity in the Y axis of the object
     * @param spriteCels They are the object's images
     */
    public Plane(BearGame object, double xLocation, double yLocation, double vX, double vY, Image... spriteCels){
        super(object, "M 88,21 L 88,21 56,40 42,50 27,58 28,72 44,72 61,62 66,58 75,52 78,49 85,31 Z", xLocation, yLocation, spriteCels);
        this.vX = vX;
        setTime();
        setTimeFire();
        timeline.play();
        imageMissile();
        timeFire.play();
    }
    
    /**
     * Method to change the object's image
     */
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
    
    /**
     * Method to set a Timer to fire a missile object
     */
    public void setTimeFire() {
        timeFire= new Timeline(new KeyFrame(
        Duration.millis(5000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                fire_missile();
            }
        }));
        timeFire.setCycleCount(Animation.INDEFINITE);
    }
    
    /**
     * Method to set the boundaries of the object
     */
    @Override
    public void setBoundaries() {  
        if (iX <= LEFTBOUNDARY-50) bearGame.getPaneRoot().getChildren().remove(this.getSpriteFrame());
    }
    
    /**
     * Method to load the images of the missile object
     */
    private void imageMissile() {
        for (int i = 0; i < missile_image.length;i++) {
            missile_image[i] = new Image("resources/missile" + (i+1) + ".png", 13,40,true,false,true);
        }
        for (int i=0; i< explosion.length;i++) {
            explosion[i] = new Image("resources/explosion" + (i+1) + ".png", 120,73,true,false,true);
        }
    }
    
    /**
     * Method to fire a missile in the right position
     */
    private void fire_missile() {
        Missile missile = new Missile(bearGame, iX+13, iY+40, 0,1,missile_image[0],missile_image[1],missile_image[2],missile_image[3],
        explosion[0],explosion[1],explosion[2],explosion[3],
                explosion[4],explosion[5],explosion[6],explosion[7],explosion[8],
                explosion[9],explosion[10],explosion[11],explosion[12],explosion[13],
                explosion[14]);
        bearGame.getDisplay().addDisplayed_Object(missile);
        bearGame.getPaneRoot().getChildren().add(missile.spriteFrame);
    }
    
    /**
     * Method to get the timer of the shoot of the missile
     * @return The timer of the shoot
     */
    public Timeline getTimeFire() {
        return timeFire;
    }

    /**
     * Method to set the timer of the shot of the missile
     * @param timeFire It is the timer of the shot 
     */
    public void setTimeFire(Timeline timeFire) {
        this.timeFire = timeFire;
    }
    
    
}
