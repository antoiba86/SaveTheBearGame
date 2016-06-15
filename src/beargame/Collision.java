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
    static boolean collisionAnimation = false;
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
        if(collisionDetect && !collisionAnimation) {
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
            bearGame.getDisplay().addObjectToRemove(object);
            bearGame.getPaneRoot().getChildren().remove(object.getSpriteFrame());
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
                if (shark.getMove() < 3) shark.setMove(3);
                if (Configuration.isSound()) shark.soundShark();
                if (shark.getMove() == 3 ) shark.setJaws();
            }
            else if (object instanceof Missile) {
                Missile missile = (Missile)object;
                bearGame.getDisplay().addObjectToRemove(object);
                bearGame.getPaneRoot().getChildren().remove(missile.getSpriteFrame());
            }
            collisionAnimation = true;
            if (hero.getMove() < 3) hero.setMove(3);
            if (Configuration.isSound()) hero.getExplosion().play();
            if (hero.getMove() == 3) hero.setExplosion();
            hero.setvX(0);
            hero.setvY(0);
            
        }
    }
    
    public static void collisionRock (ObjectGame object, Rock rock, BearGame bearGame) {
        if (object instanceof Shark || object instanceof PirateBoat || object instanceof Missile) {
            if (object instanceof Shark) {
                Shark shark = (Shark)object;
                if (shark.getMove() < 4) {
                    shark.setMove(9);
                    Slidding.gameScore += 20;
                }
                if (Configuration.isSound()) shark.getExplosion().play();
                shark.setExplosion();
                shark.setvX(0);
                shark.setvY(0);
            }
            else if (object instanceof Missile) {
                Missile missile = (Missile)object;
                if (missile.getMove() < 4) {
                    missile.setMove(4);
                    Slidding.gameScore += 100;
                }
                if (Configuration.isSound()) missile.getExplosion().play();
                missile.setExplosion();
                missile.setvX(0);
                missile.setvY(0);
            }
            else if (object instanceof PirateBoat) {
                PirateBoat pirateBoat = (PirateBoat)object;
                if (pirateBoat.getMove() <= 3) {
                    pirateBoat.setMove(3);
                    Slidding.gameScore += 10;
                }
                if (Configuration.isSound()) pirateBoat.getExplosion().play();
                pirateBoat.setExplosion();
                pirateBoat.setvX(0);
                pirateBoat.setvY(0);
                
            }
            object.getSpriteBound().setContent("");
            bearGame.getDisplay().addRockToRemove(rock);
            bearGame.getPaneRoot().getChildren().remove(rock.getSpriteFrame());
        }
    }

    public static boolean isCollisionAnimation() {
        return collisionAnimation;
    }

    public static void setCollisionAnimation(boolean collisionAnimation) {
        Collision.collisionAnimation = collisionAnimation;
    }
    
    
    
}
