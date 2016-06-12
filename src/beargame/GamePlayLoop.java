package beargame;

import javafx.animation.AnimationTimer;

/**
 * Class of the game loop
 * @author Antonio Jesús Ibáñez García
 */
public class GamePlayLoop extends AnimationTimer {
    BearGame bearGame;
    
    /**
     * Method to get the game object
     * @param bearGame It is the game object
     */
    public GamePlayLoop (BearGame bearGame) {
        this.bearGame = bearGame;
    }
    
    /**
     * Method to update the game
     * @param now It is the actual time in miliseconds
     */
    @Override
    public void handle(long now) {
        BearGame.iHero.update();
        updateSprites();
        Slidding.scoreText.setText("SCORE: " + String.valueOf(Slidding.gameScore));
    }
    
    /**
     * Method to start the game loop
     */
    @Override
    public void start() {
        super.start();
    }
    
    /**
     * Method to stop the game loop
     */
    @Override
    public void stop() {
        super.stop();
    }
    
    /**
     * Method to update the object's sprites
     */
    public void updateSprites() {
        //spriteManager es DisplayObject
        bearGame.getDisplay().getDISPLAYED_OBJECT().stream().forEach((object) -> {
            this.handleUpdate(object);
        });
    }
    
    /** 
     * Updates the sprite object's information to position on the game surface.
     * @param object It is the object
     */
    protected void handleUpdate(ObjectGame object) {
        //Sprite class es de objectGame o Population class, donde se origina los personajes
        if (object instanceof Population) {
            Population character = (Population) object;
            character.update();
        }
    }
    
}
