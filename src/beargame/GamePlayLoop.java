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
    Pos location;
    @Override
    public void handle(long now) {
        location = BearGame.buttonContainer.getAlignment();
            if (location == Pos.BOTTOM_LEFT) BearGame.buttonContainer.setAlignment(Pos.BOTTOM_RIGHT);
            else if (location == Pos.BOTTOM_RIGHT) BearGame.buttonContainer.setAlignment(Pos.TOP_RIGHT);
            else if (location == Pos.TOP_RIGHT) BearGame.buttonContainer.setAlignment(Pos.TOP_LEFT);
            else if (location == Pos.TOP_LEFT) BearGame.buttonContainer.setAlignment(Pos.BOTTOM_LEFT);
    }
    
}
