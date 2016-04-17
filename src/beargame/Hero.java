/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 *
 * @author DAW13
 */
public class Hero extends ObjectGame {
    protected BearGame bearGame;
    protected double vX;
    protected double vY;
    protected double lifeSpan;
    protected double damage;
    protected double offsetX;
    protected double offsetY;
    private static boolean up, down, left, right;
    protected static final double SPRITE_PIXELS_X = 145;
    protected static final double SPRITE_PIXELS_Y = 96;
    protected static final double rightBoundary = BearGame.WIDTH_PIXELS/2 - SPRITE_PIXELS_X/2;
    protected static final double leftBoundary = -(BearGame.WIDTH_PIXELS/2 - SPRITE_PIXELS_X/2);
    protected static final double bottomBoundary = BearGame.HEIGHT_PIXELS/2 - SPRITE_PIXELS_Y/2;
    protected static final double topBoundary = -(BearGame.HEIGHT_PIXELS/2 - SPRITE_PIXELS_Y/2);
    private int move = 0;
    Timeline timeline;
    
    public Hero(BearGame bearHero, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        lifeSpan = 1000;
        vX = vY =2;
        damage = offsetX = offsetY = 0;
        bearGame = bearHero;
        setTime();
        
    }

    @Override
    public void update() {
        setXYLocation();
        setBoundaries();
        setImageState();
        moveBear(iX, iY);
        checkCollision();
    }
    private void setXYLocation() {
        if(right) iX += vX;
        if(left) iX -= vX;
        if(down) iY += vY;
        if(up) iY -= vY;
    }
    /**
     * Method to set a Timer in order to change the image display of the Hero
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
    
    public void checkCollision() {
        for(int i=0; i< bearGame.display.getDISPLAYED_OBJECT().size(); i++) {
            ObjectGame object = bearGame.display.getDISPLAYED_OBJECT().get(i);
            collide(object);
        }
    }
    
    public void setBoundaries() {
        if (iX >= rightBoundary) { iX=rightBoundary; }
        if (iX <= leftBoundary) { iX=leftBoundary; }
        if (iY >= bottomBoundary) { iY=bottomBoundary; }
        if (iY <= topBoundary) { iY=topBoundary; }
    }
    
    private void moveBear(double x, double y) {
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
    }
    
    public void changeImage() {
        if (move == 0) move++;
        else if (move == 1) move++;
        else move = 0;
    }
    
    private void setImageState() {
        if(!isRight() && !isLeft() && !isDown() && !isUp() ) {
            move = 0;
            spriteFrame.setImage(imageStates.get(move));
            timeline.stop();
        }
        if(isRight()) {
            spriteFrame.setImage(imageStates.get(move));
            timeline.play();
        }
    }
    
    public boolean collide(ObjectGame object) {
        boolean collisionDetect = false;
        if ( BearGame.iHero.getSpriteFrame().getBoundsInParent().intersects(object.getSpriteFrame().getBoundsInParent())) {
            Shape intersection = SVGPath.intersect(BearGame.iHero.getSpriteBound(), object.getSpriteBound());
            if (intersection.getBoundsInLocal().getWidth() != -1) collisionDetect = true;
        }
        if(collisionDetect) {
            //invinciBagel.playiSound0();
            bearGame.display.addToRemovedObjects(object);
            bearGame.root.getChildren().remove(object.getSpriteFrame());
            bearGame.display.resetRemovedObjects();
        }
        return collisionDetect;
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

    public double getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(double lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
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
