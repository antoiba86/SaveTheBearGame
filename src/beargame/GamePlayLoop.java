/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;

/**
 *
 * @author DAW13
 */
public class GamePlayLoop extends AnimationTimer {
    
    private BearGame bearGame;
    private DisplayObject displayObject = new DisplayObject();
    
    public GamePlayLoop (BearGame bearGame) {
        this.bearGame = bearGame;
    }
    
    @Override
    public void handle(long now) {
        BearGame.iHero.update();
        //BearGame.petrol.update();
        updateSprites();
        Slidding.scoreText.setText(String.valueOf(Slidding.gameScore));
        //bearGame.hero2.update();
    }
    
    @Override
    public void start() {
        super.start();
    }
    
    @Override
    public void stop() {
        super.stop();
    }
    
    public void updateSprites() {
        //spriteManager es DisplayObject
        displayObject.getDISPLAYED_OBJECT().stream().forEach((object) -> {
            handleUpdate(object);
        });
    }
    
    /** Updates the sprite object's information to position on the game surface.
     * @param object
     */
    protected void handleUpdate(ObjectGame object) {
        //Sprite class es de objectGame o Population class, donde se origina los personajes
        if (object instanceof Population) {
            Population character = (Population) object;
            // advance the spheres velocity
            character.update();
            // bounce off the walls when outside of boundaries
            /*if (sphere.node.getTranslateX() > (getGameSurface().getWidth()  -
                sphere.node.getBoundsInParent().getWidth()) ||
                sphere.node.getTranslateX() < 0 ) {                 sphere.vX = sphere.vX * -1;             }             if (sphere.node.getTranslateY() > getGameSurface().getHeight()-
                sphere.node.getBoundsInParent().getHeight() ||
                sphere.node.getTranslateY() < 0) {
                sphere.vY = sphere.vY * -1;
            }*/
        }
    }
    
    /*
    Pos location;
    @Override
    public void handle(long now) {
        location = BearGame.buttonContainer.getAlignment();
            if (location == Pos.BOTTOM_LEFT) BearGame.buttonContainer.setAlignment(Pos.BOTTOM_RIGHT);
            else if (location == Pos.BOTTOM_RIGHT) BearGame.buttonContainer.setAlignment(Pos.TOP_RIGHT);
            else if (location == Pos.TOP_RIGHT) BearGame.buttonContainer.setAlignment(Pos.TOP_LEFT);
            else if (location == Pos.TOP_LEFT) BearGame.buttonContainer.setAlignment(Pos.BOTTOM_LEFT);
    }*/
    
}
