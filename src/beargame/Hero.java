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
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 * Class of the Hero
 * @author Antonio Jesús Ibáñez Garcia
 */
public class Hero extends ObjectGame {
    protected BearGame bearGame;
    protected double vX;
    protected double vY;
    private static boolean up, down, left, right;
    protected static final double SPRITE_PIXELS_X = 120;
    protected static final double SPRITE_PIXELS_Y = 73;
    protected static final double RIGHTBOUNDARY = Menu.WIDTH_PIXELS - SPRITE_PIXELS_X;
    protected static final double LEFTBOUNDARY = 0;
    protected static final double BOTTOMBOUNDARY = Menu.HEIGHT_PIXELS - SPRITE_PIXELS_Y;
    protected static final double TOPBOUNDARY = Slidding.HEIGTH_SKY;
    private int move = 0;
    Timeline timeline;
    Timeline timeExplosion;
    
    /**
     * Method to create a Hero object
     * @param bearHero It is the Game object
     * @param SVGdata It is the SVG data of the object
     * @param xLocation It is the position of the object into the X axis
     * @param yLocation It is the position of the object into the Y axis
     * @param spriteCels They are the object's images
     */
    public Hero(BearGame bearHero, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        vX = vY =2;
        bearGame = bearHero;
        setTime();
    }
    
     
     /**
     * Method to update the object
     */
    @Override
    public void update() {
        setXYLocation();
        setBoundaries();
        setImageState();
        moveBear(iX, iY);
        checkCollision();
    }
    
    /**
     * Method to set a Timer in order to change the Hero's image displayed
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
    public void changeImage() {
        switch (move) {
            case 0: move++;break;
            case 1: move++; break;
            case 2: move++; break;
            default: move = 0;break;
        }
    }
    
    /**
     * Method to set a Timer to change the object's image displayed when the Hero dies
     */
    public void setExplosion() {
        timeline.stop();
        timeExplosion = new Timeline(new KeyFrame(
        Duration.millis(200), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                explosion();
            }
        }));
        timeExplosion.setCycleCount(Animation.INDEFINITE);
        timeExplosion.play();
    }
    
    /**
     * Method to change the object's image
     */
    public void explosion() {
        if (move < 19) move++;
    }
    
    /**
     * Method to change the object's image in the game
     */
    private void setImageState() {
        if (move < 3) {
            spriteFrame.setImage(imageStates.get(move));
            timeline.play();
        }
        else if (move >= 3 && move < 19) spriteFrame.setImage(imageStates.get(move));
        else {
            timeExplosion.stop();
            bearGame.getDisplay().addToRemovedObjects(BearGame.iHero);
            bearGame.paneRoot.getChildren().remove(BearGame.iHero.getSpriteFrame());
            bearGame.getDisplay().resetRemovedObjects();
            bearGame.bearAlive();
        }
    }
    
    /**
     * Method to set the object frame and bounds inside the game loop
     * @param x It is the position in the X axis
     * @param y It is the position in the Y axis
     */
    private void moveBear(double x, double y) {
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
        spriteBound.setTranslateX(x);
        spriteBound.setTranslateY(y);
    }
    
    /**
     * Method to move the object inside the game loop
     */
    private void setXYLocation() {
        if(right) iX += vX;
        if(left) iX -= vX;
        if(down) iY += vY;
        if(up) iY -= vY;
    }
    
    /**
     * Method to set the boundaries of the object
     */
    public void setBoundaries() {
        if (iX >= RIGHTBOUNDARY) { iX=RIGHTBOUNDARY; }
        if (iX <= LEFTBOUNDARY) { iX=LEFTBOUNDARY; }
        if (iY >= BOTTOMBOUNDARY) { iY=BOTTOMBOUNDARY; }
        if (iY <= TOPBOUNDARY) { iY=TOPBOUNDARY; }
    }
    
    /**
     * Method to check the collision of the hero with other objects
     */
    public void checkCollision() {
        for(int i=0; i< bearGame.getDisplay().getDISPLAYED_OBJECT().size(); i++) {
            ObjectGame object = bearGame.getDisplay().getDISPLAYED_OBJECT().get(i);
            collide(object);
        }
    }
    
    /**
     * Method to detect collisions of the hero object
     * @param object 
     */
    public void collide(ObjectGame object) {
        boolean collisionDetect = false;
        if ( BearGame.iHero.getSpriteFrame().getBoundsInParent().intersects(object.getSpriteFrame().getBoundsInParent())) {
            Shape intersection = SVGPath.intersect(BearGame.iHero.getSpriteBound(), object.getSpriteBound());
            if (intersection.getBoundsInLocal().getWidth() != -1) collisionDetect = true;
        }
        if(collisionDetect) {
            //Quitar objetos del escenario
            if (object instanceof Coin) {
                bearGame.getDisplay().addToRemovedObjects(object);
                bearGame.paneRoot.getChildren().remove(object.getSpriteFrame());
                bearGame.getDisplay().resetRemovedObjects();
                if (Configuration.isSound()) Coin.musicCoin();
                Slidding.gameScore += 10;
            }
            else if (object instanceof Gemstone) {
                bearGame.getDisplay().addToRemovedObjects(object);
                bearGame.paneRoot.getChildren().remove(object.getSpriteFrame());
                bearGame.getDisplay().resetRemovedObjects();
                if (Configuration.isSound()) Coin.musicCoin();
                Slidding.gameScore += 50;
            }
            else if (object instanceof Shark) {
                Shark shark = (Shark)object;
                if (shark.getMove() < 3) {
                    shark.setMove(3);
                    if (Configuration.isSound()) shark.soundShark();
                    shark.setJaws();
                }
                if (move < 3) {
                    move = 4;
                    soundExplosion();
                    setExplosion();
                    vX = vY = 0;
                }
            }
            else if (object instanceof Missile) {
                if (move < 3) {
                    move = 4;
                    if (Configuration.isSound()) soundExplosion();
                    setExplosion();
                    vX = vY = 0;
                }
                Missile missile = (Missile)object;
                bearGame.getDisplay().addToRemovedObjects(missile);
                bearGame.paneRoot.getChildren().remove(missile.getSpriteFrame());
                bearGame.getDisplay().resetRemovedObjects();
            }
            else {
                if (move < 3) {
                    move = 4;
                    if (Configuration.isSound()) soundExplosion();
                    setExplosion();
                    vX = vY = 0;
                }
            }
        }
    }
    
    
     /**
     * Method to get the object sound when there is a collision
     */
    public static void soundExplosion() {
        String uriString = new File("explosion.mp3").toURI().toString();
        MediaPlayer player = new MediaPlayer( new Media(uriString));
        player.play();
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

    public static boolean isUp() {
        return up;
    }

    public static void setUp(boolean up) {
        Hero.up = up;
    }

    public static boolean isDown() {
        return down;
    }

    public static void setDown(boolean down) {
        Hero.down = down;
    }

    public static boolean isLeft() {
        return left;
    }

    public static void setLeft(boolean left) {
        Hero.left = left;
    }

    public static boolean isRight() {
        return right;
    }

    public static void setRight(boolean right) {
        Hero.right = right;
    }
}
