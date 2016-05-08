package beargame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 *
 * @author anto
 */
public class Population extends ObjectGame {
    protected BearGame bearGame;
    protected double vX;
    protected double vY;
    protected Timeline timeline;
    protected static final double SPRITE_PIXELS_X = 0;
    protected static final double SPRITE_PIXELS_Y = 0;
    protected static final double RIGHTBOUNDARY = BearGame.WIDTH_PIXELS - SPRITE_PIXELS_X;
    protected static final double LEFTBOUNDARY = 0;
    protected static final double BOTTOMBOUNDARY = BearGame.HEIGHT_PIXELS - SPRITE_PIXELS_Y;
    protected static final double TOPBOUNDARY = Slidding.HEIGTH_SKY;
    protected int move = 0;
    
    public Population() {
        
    }
    
    public Population(BearGame object, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        bearGame = object;
        setTime();
    }

    @Override
    public void update() {
        moveImage(iX, iY);
        setXYLocation();
        setBoundaries();
        setImageState();
    }
    
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
    
    public void changeImage() {
        switch (move) {
            case 0: move++;break;
            case 1: move++; break;
            case 2: move++; break;
            default: move = 0;break;
        }
    }
    
    protected void setImageState() {
        spriteFrame.setImage(imageStates.get(move));
    }
    
    public void moveImage(double x, double y) {
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
        spriteBound.setTranslateX(x);
        spriteBound.setTranslateY(y);
    }
    
    protected void setXYLocation() {
        iX -= vX;
        iY -= vY;
    }
    public void setBoundaries() {  
        if (iX <= LEFTBOUNDARY-50) bearGame.root.getChildren().remove(this.getSpriteFrame()); 
        //Cuando choca contra el "suelo" el barco sigue para arriba
        if (iY >= BOTTOMBOUNDARY) vY=-vY; 
        //Cuando choca contra el "techo" el barcho va para abajo
        if (iY <= TOPBOUNDARY) vY=-vY;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }
    
    
    
}
