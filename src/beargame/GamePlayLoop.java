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
     * @param now It is the actual time in milliseconds
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
     * Method to update the object's sprite
     */
    public void updateSprites() {
        //spriteManager es DisplayObject
        bearGame.getDisplay().getObjectDisplayed().stream().forEach(this::handleUpdate);
        bearGame.getDisplay().addToRemovedObjects(bearGame.getDisplay().getObjectRemoved().stream().toArray(ObjectGame[]::new));
        bearGame.getDisplay().resetRemovedObjects();
        bearGame.getDisplay().addDisplayed_Object(bearGame.getDisplay().getObjectToAdd().stream().toArray(ObjectGame[]::new));
        bearGame.getDisplay().resetObjectToAdd();
        bearGame.getDisplay().getRockDisplayed().stream().forEach(this::handleRock);
        bearGame.getDisplay().addToRemovedRocks(bearGame.getDisplay().getRocksRemoved().stream().toArray(Rock[]::new));
        bearGame.getDisplay().resetRemovedRocks();
        bearGame.getDisplay().addDisplayed_Rock(bearGame.getDisplay().getRockToAdd().stream().toArray(Rock[]::new));
        bearGame.getDisplay().resetRockToAdd();
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
    
    /** 
     * Updates the sprite object's information to position on the game surface.
     * @param object It is the object
     */
    protected void handleRock(ObjectGame rock) {
        //Sprite class es de Population class, donde se origina los personajes

        rock.update();
        
    }
    
}
