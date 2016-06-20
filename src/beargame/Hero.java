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
 * Class of the Hero
 * @author Antonio Jesús Ibáñez Garcia
 */
public class Hero extends ObjectGame {
    protected BearGame bearGame;
    private double vX;
    private double vY;
    private static boolean up, down, left, right, space;
    private static boolean allow_shoot = true;
    private static final double SPRITE_PIXELS_X = 120;
    private static final double SPRITE_PIXELS_Y = 73;
    private static final double RIGHTBOUNDARY = Menu.WIDTH_PIXELS - SPRITE_PIXELS_X;
    private static final double LEFTBOUNDARY = 0;
    private static final double BOTTOMBOUNDARY = Menu.HEIGHT_PIXELS - SPRITE_PIXELS_Y;
    private static final double TOPBOUNDARY = Slidding.HEIGTH_SKY;
    private int move = 0;
    private Image[] rock_image = new Image[2];
    private AudioClip explosion;
    Timeline timeline;
    Timeline timeExplosion;
    Timeline timeShoot;
    
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
        explosion = new AudioClip(this.getClass().getResource("/resources/Sound/explosion.mp3").toExternalForm());
        setTime();
        timeline.play();
        setShoot();
        imageRock();
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
    
    public void setShoot() {
        timeShoot = new Timeline(new KeyFrame(
        Duration.millis(400), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                setAllow_shoot(true);
                timeShoot.stop();
            }
        }));
        timeShoot.setCycleCount(Animation.INDEFINITE);
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
        if (move < 3) spriteFrame.setImage(imageStates.get(move));
        else if (move >= 3 && move < 19) spriteFrame.setImage(imageStates.get(move));
        else {
            timeExplosion.stop();
            bearGame.getDisplay().addToRemovedObjects(BearGame.iHero);
            bearGame.getPaneRoot().getChildren().remove(BearGame.iHero.getSpriteFrame());
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
        if(space) fire_rock();
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
        for(int i=0; i< bearGame.getDisplay().getObjectDisplayed().size(); i++) {
            ObjectGame object = bearGame.getDisplay().getObjectDisplayed().get(i);
            Collision.collide(object, this, bearGame);
        }
    }
    
    private void imageRock() {
        for (int i = 0; i < rock_image.length;i++) {
            rock_image[i] = new Image("resources/img/roca" + (i+1) + ".png", 30,28,true,false,true);
        }
    }
    
    /**
     * Method to fire a missile in the right position
     */
    private void fire_rock() {
        timeShoot.play();
        if (allow_shoot) {
            Rock rock = new Rock(bearGame, iX+90, iY+36.5, 5,0,rock_image[0],rock_image[1]);
            if(Configuration.isSound()) rock.rockSound();
            bearGame.getDisplay().addToRockToAdd(rock);
            bearGame.getDisplay().addToSpriteRockToAdd(rock.spriteFrame);
            allow_shoot = false;
        }
    }
    
    /**
     * Method to get the Hero's velocity in the X axis
     * @return The velocity in the X axis
     */
    public double getvX() {
        return vX;
    }

    /**
     * Method to set the Hero's velocity in the X axis
     * @param vX The velocity in the X axis
     */
    public void setvX(double vX) {
        this.vX = vX;
    }

    /**
     * Method to get the Hero's velocity in the Y axis
     * @return The velocity in the Y axis
     */
    public double getvY() {
        return vY;
    }

    /**
     * Method to set the Hero's velocity in the Y axis
     * @param vY The velocity in the Y axis
     */
    public void setvY(double vY) {
        this.vY = vY;
    }

    /**
     * Method to get when the Hero is moving up
     * @return The variable up to set the Hero moving
     */
    public static boolean isUp() {
        return up;
    }

    /**
     * Method to set the Hero moving up
     * @param up It is the direction
     */
    public static void setUp(boolean up) {
        Hero.up = up;
    }

    /**
     * Method to get when the Hero is moving down
     * @return The variable down to get the Hero moving
     */
    public static boolean isDown() {
        return down;
    }

    /**
     * Method to set the Hero moving down
     * @param down It is the direction
     */
    public static void setDown(boolean down) {
        Hero.down = down;
    }

    /**
     * Method to get when the Hero is moving left
     * @return The variable left to get the Hero moving
     */
    public static boolean isLeft() {
        return left;
    }

    /**
     * Method to set the Hero moving left
     * @param left It is the direction
     */
    public static void setLeft(boolean left) {
        Hero.left = left;
    }

    /**
     * Method to get when the Hero is moving right
     * @return The variable right to get the Hero moving
     */
    public static boolean isRight() {
        return right;
    }

    /**
     * Method to set the Hero moving right
     * @param right It is the direction
     */
    public static void setRight(boolean right) {
        Hero.right = right;
    }

    public static boolean isSpace() {
        return space;
    }

    public static void setSpace(boolean space) {
        Hero.space = space;
    }

    public BearGame getBearGame() {
        return bearGame;
    }

    public void setBearGame(BearGame bearGame) {
        this.bearGame = bearGame;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public AudioClip getExplosion() {
        return explosion;
    }

    public void setExplosion(AudioClip explosion) {
        this.explosion = explosion;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Timeline getTimeExplosion() {
        return timeExplosion;
    }

    public void setTimeExplosion(Timeline timeExplosion) {
        this.timeExplosion = timeExplosion;
    }

    public static boolean isAllow_shoot() {
        return allow_shoot;
    }

    public static void setAllow_shoot(boolean allow_shoot) {
        Hero.allow_shoot = allow_shoot;
    }
    
    
    
    
}
