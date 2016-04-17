/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

/**
 *
 * @author Anto
 */
public class GamePiece {
    private static final String TYPE = "Bear";
    
    int lifeIndex = 0;
    int hitsIndex = 0;
    String directionFacing = "";
    boolean currentlyMoving = false;
    private int characterX = 0; // X screen location of the GamePiece
    private int characterY = 0; // Y screen location of the GamePiece
    private String bearOrientation = "side"; // Defines bear orientation (front, side, top)
    //CHANGE
    public String movementType = "idle"; // Type of movement (idle, fly, run, jump)
    
    public GamePiece() {
        characterX = 0;
        characterY = 0;
        bearOrientation = "side"; 
        lifeIndex = 1000; // Defines units of lifespan
        hitsIndex = 0; // Defines units of damage ("hits" on the object)
        directionFacing = "E"; // Direction that the object is facing
        currentlyMoving = false; // Flag showing if the object is in motion
    }
    
    public GamePiece(int x, int y, String orientation, int lifeIndex, String directionFacing) {
        characterX = x;
        characterY = y;
        bearOrientation = orientation;
        this.lifeIndex = lifeIndex; // Defines units of lifespan
        hitsIndex = 0; // Defines units of damage ("hits" on the object)
        this.directionFacing = directionFacing; // Direction that the object is facing
        currentlyMoving = false; // Flag showing if the object is in motion
    }
    
    public void moveCharacter(int x, int y) {
        currentlyMoving = true;
        characterX = x;
        characterY = y;
    }

    public int getLifeIndex() {
        return lifeIndex;
    }

    public void setLifeIndex(int lifeIndex) {
        this.lifeIndex = lifeIndex;
    }

    public int getHitsIndex() {
        return hitsIndex;
    }

    public void setHitsIndex(int hitsIndex) {
        this.hitsIndex = hitsIndex;
    }

    public String getDirectionFacing() {
        return directionFacing;
    }

    public void setDirectionFacing(String directionFacing) {
        this.directionFacing = directionFacing;
    }

    public boolean isCurrentlyMoving() {
        return currentlyMoving;
    }

    public void setCurrentlyMoving(boolean currentlyMoving) {
        this.currentlyMoving = currentlyMoving;
    }

    public String getBearOrientation() {
        return bearOrientation;
    }

    public void setBearOrientation(String bearOrientation) {
        this.bearOrientation = bearOrientation;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }
    
    
}
