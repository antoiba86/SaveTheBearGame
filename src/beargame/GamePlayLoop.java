/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import javafx.animation.AnimationTimer;

/**
 *
 * @author DAW13
 */
public class GamePlayLoop extends AnimationTimer {
    BearGame bearGame;
    
    public GamePlayLoop (BearGame bearGame) {
        this.bearGame = bearGame;
    }
    
    @Override
    public void handle(long now) {
        BearGame.iHero.update();
        updateSprites();
        Slidding.scoreText.setText("SCORE: " + String.valueOf(Slidding.gameScore));
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
        bearGame.getDisplay().getDISPLAYED_OBJECT().stream().forEach((object) -> {
            handleUpdate(object);
        });
    }
    
    /** 
     * Updates the sprite object's information to position on the game surface.
     * @param object
     */
    protected void handleUpdate(ObjectGame object) {
        //Sprite class es de objectGame o Population class, donde se origina los personajes
        if (object instanceof Population) {
            Population character = (Population) object;
            character.update();
        }
    }
    
}
