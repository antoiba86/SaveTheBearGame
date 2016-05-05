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
import javafx.scene.paint.Color;
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
    int duration = 100;
    private static boolean up, down, left, right;
    protected static final double SPRITE_PIXELS_X = 120;
    protected static final double SPRITE_PIXELS_Y = 73;
    protected static final double RIGHTBOUNDARY = BearGame.WIDTH_PIXELS - SPRITE_PIXELS_X;
    protected static final double LEFTBOUNDARY = 0;
    protected static final double BOTTOMBOUNDARY = BearGame.HEIGHT_PIXELS - SPRITE_PIXELS_Y;
    protected static final double TOPBOUNDARY = Slidding.HEIGTH_SKY;
    private int move = 0;
    Timeline timeline;
    Timeline timeline2;
    
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
            timeline = new Timeline(new KeyFrame(
            Duration.millis(200), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ae) {
                    changeImage();
                    
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
        
    }
    
    public void setExplosion() {
        timeline= new Timeline(new KeyFrame(
            Duration.millis(10), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ae) {
                    explosion();
                    vX = vY = 0;
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
        if (iX >= RIGHTBOUNDARY) { iX=RIGHTBOUNDARY; }
        if (iX <= LEFTBOUNDARY) { iX=LEFTBOUNDARY; }
        if (iY >= BOTTOMBOUNDARY) { iY=BOTTOMBOUNDARY; }
        if (iY <= TOPBOUNDARY) { iY=TOPBOUNDARY; }
    }
    
    private void moveBear(double x, double y) {
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
        spriteBound.setTranslateX(x);
        spriteBound.setTranslateY(y);
    }
    
    public void changeImage() {
        switch (move) {
            case 0: move++;break;
            case 1: move++; break;
            case 2: move++; break;
            default: move = 0;break;
        }
    }
    public void explosion() {
            //poner timer y move++ y al final remove
        timeline2 = new Timeline(new KeyFrame(
        Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                move++;
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline2.play();
    }
    
    private void setImageState() {
        if (move < 19) spriteFrame.setImage(imageStates.get(move));
        else if (move == 19) bearGame.root.getChildren().remove(BearGame.iHero.getSpriteFrame());
        else {
            timeline.stop();
            timeline2.stop();
        }
        timeline.play();
    }
    
    public boolean collide(ObjectGame object) {
        boolean collisionDetect = false;
        if ( BearGame.iHero.getSpriteFrame().getBoundsInParent().intersects(object.getSpriteFrame().getBoundsInParent())) {
            Shape intersection = SVGPath.intersect(BearGame.iHero.getSpriteBound(), object.getSpriteBound());
            if (intersection.getBoundsInLocal().getWidth() != -1) collisionDetect = true;
        }
        if(collisionDetect) {
            //invinciBagel.playiSound0();
            //Quitar objetos del escenario
            if (object instanceof Coin) {
                bearGame.display.addToRemovedObjects(object);
                bearGame.root.getChildren().remove(object.getSpriteFrame());
                bearGame.display.resetRemovedObjects();
                Slidding.gameScore += 10;
            }
            else {
                bearGame.display.addToRemovedObjects(object);
                bearGame.root.getChildren().remove(object.getSpriteFrame());
                bearGame.display.resetRemovedObjects();
            }
            
            /*move = 3;
            setExplosion();*/
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
