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
     * @param character 
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
            Rock rock = null;
            boolean notRock = true;
            if (object instanceof Rock) notRock = false;
            if (character instanceof Hero && notRock) {
                iHero = (Hero)character;
                collisionHero(object, iHero, bearGame);
            }
            if (character instanceof Rock) {
                rock = (Rock)character;
                collisionRock(object, rock, bearGame);
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
                hero.setMove(3);
                if (Configuration.isSound()) hero.getExplosion().play();
                hero.setExplosion();
                hero.setvX(0);
                hero.setvY(0);
            }
        }
    }
    
    public static void collisionRock (ObjectGame object, Rock rock, BearGame bearGame) {
        if (object instanceof Gemstone || object instanceof Coin) {
            
        }
        else {
            if (object instanceof Shark) {
                Shark shark = (Shark)object;
                if (shark.getMove() < 4) {
                    shark.setMove(10);
                    bearGame.getDisplay().addToRemovedObjects(shark);
                    if (Configuration.isSound()) shark.getExplosion().play();
                    shark.setExplosion();
                    shark.setvX(0);
                    shark.setvY(0);
                }
            }
            else if (object instanceof Missile) {
                Missile missile = (Missile)object;
                missile.setMove(3);
                bearGame.getDisplay().addToRemovedObjects(missile);
                if (Configuration.isSound()) missile.getExplosion().play();
                missile.setExplosion();
                missile.setvX(0);
                missile.setvY(0);
            }
            else if (object instanceof PirateBoat) {
                PirateBoat pirateBoat = (PirateBoat)object;
                pirateBoat.setMove(3);
                bearGame.getDisplay().addToRemovedObjects(pirateBoat);
                if (Configuration.isSound()) pirateBoat.getExplosion().play();
                pirateBoat.setExplosion();
                pirateBoat.setvX(0);
                pirateBoat.setvY(0);
            }
            bearGame.getDisplay().addToRemovedRocks(rock);
            bearGame.getPaneRoot().getChildren().remove(rock.getSpriteFrame());
        }
    }
    
}
