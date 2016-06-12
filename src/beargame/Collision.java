/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

/**
 *
 * @author Anto
 */
public class Collision {
    
    /**
     * Method to detect collisions of the hero object
     * @param object 
     * @param hero 
     * @param bearGame 
     */
    public static void collide (ObjectGame object, ObjectGame character, BearGame bearGame) {
        boolean collisionDetect = false;
        if (character.getSpriteFrame().getBoundsInParent().intersects(object.getSpriteFrame().getBoundsInParent())) {
            Shape intersection = SVGPath.intersect(character.getSpriteBound(), object.getSpriteBound());
            if (intersection.getBoundsInLocal().getWidth() != -1) collisionDetect = true;
        }
        if(collisionDetect) {
            Hero iHero = null;
            boolean notRock = false;
            if (object instanceof Rock) notRock = true;
            if (character instanceof Hero && !notRock) {
                iHero = (Hero)character;
                collisionHero(object, iHero, bearGame);
            }
        }
    }
    
    public static void collisionHero (ObjectGame object, Hero hero, BearGame bearGame) {
        if (object instanceof Coin || object instanceof Gemstone) {
            bearGame.getDisplay().addToRemovedObjects(object);
            bearGame.getPaneRoot().getChildren().remove(object.getSpriteFrame());
            bearGame.getDisplay().resetRemovedObjects();
            if (object instanceof Gemstone) {
                Gemstone gem = (Gemstone)object;
                if (Configuration.isSound()) gem.musicCoin();
                Slidding.gameScore += 50;
            }
            else {
                Coin coin = (Coin)object;
                if (Configuration.isSound()) coin.musicCoin();
                Slidding.gameScore += 10;
            }
        }
        else {
            if (object instanceof Shark) {
                Shark shark = (Shark)object;
                if (shark.getMove() < 3) {
                    shark.setMove(3);
                    if (Configuration.isSound()) shark.soundShark();
                    shark.setJaws();
                }
            }
            else if (object instanceof Missile) {
                Missile missile = (Missile)object;
                bearGame.getDisplay().addToRemovedObjects(missile);
                bearGame.getPaneRoot().getChildren().remove(missile.getSpriteFrame());
                bearGame.getDisplay().resetRemovedObjects();
            }
            if (hero.getMove() < 3) {
                hero.setMove(4);
                if (Configuration.isSound()) hero.getExplosion().play();
                hero.setExplosion();
                hero.setvX(0);
                hero.setvY(0);
            }
        }
    }
    
}
