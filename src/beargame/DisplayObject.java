package beargame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class to display or delete the objects of the game
 * @author Antonio Jesús Ibáñez García
 */
public class DisplayObject {
    
    private List<ObjectGame> DISPLAYED_OBJECT, DISPLAYED_ROCKS;
    private List<ObjectGame> COLLIDE_CHECKLIST, COLLIDE_ROCKS;
    private Set<ObjectGame> REMOVED_OBJECTS, REMOVED_ROCKS;
    
    /**
     * Method to create the objects arrayList
     */
    public DisplayObject() {
        this.DISPLAYED_OBJECT = new ArrayList<>();
        this.COLLIDE_CHECKLIST = new ArrayList<>();
        this.REMOVED_OBJECTS = new HashSet<>();
        this.DISPLAYED_ROCKS = new ArrayList<>();
        this.COLLIDE_ROCKS = new ArrayList<>();
        this.REMOVED_ROCKS = new HashSet<>();
    }
    
    /**
     * Method to get the Object's displayed
     * @return The ArrayList of the objects displayed
     */
    public List<ObjectGame> getDISPLAYED_OBJECT() {
        return DISPLAYED_OBJECT;
    }
    
    /**
     * Method to add an object into the Array of Objects displayed
     * @param objectGames It is the object to display
     */
    public void addDisplayed_Object(ObjectGame... objectGames) {
        DISPLAYED_OBJECT.addAll(Arrays.asList(objectGames) );
    }
    
    /**
     * Method to add an object into the Array of Rocks displayed
     * @param rock It is the object to display
     */
    public void addDisplayed_Rock(Rock... rock) {
        DISPLAYED_ROCKS.addAll(Arrays.asList(rock) );
    }
    
    /**
     * Method to remove an object from the game
     * @param objectGames It is the object to remove
     */
    public void removeDisplayed_Object(ObjectGame... objectGames) {
        DISPLAYED_OBJECT.removeAll( Arrays.asList(objectGames) );
    }
    
    /**
     * Method to remove an object from the game
     * @param rock It is the object to remove
     */
    public void removeDisplayed_Rocks(Rock... rock) {
        DISPLAYED_ROCKS.removeAll( Arrays.asList(rock) );
    }
    
    /**
     * Method to clean the display_object array
     */
    public void resetDisplayed_Object() {
        DISPLAYED_OBJECT.clear();
    }
    
    /**
     * Method to clean the display_object array
     */
    public void resetDisplayed_Rock() {
        DISPLAYED_ROCKS.clear();
    }
    
    /**
     * Method to return the collision state
     * @return The variable of the collision check
     */
    public List getCollideCheckList() {
        return COLLIDE_CHECKLIST;
    }
    
    /**
     * Method to return the collision state
     * @return The variable of the collision check
     */
    public List getCollideRock() {
        return COLLIDE_ROCKS;
    }
    
    /**
     * Method to reset the collision array
     */
    public void resetCollideCheckList() {
        COLLIDE_CHECKLIST.clear();
        COLLIDE_CHECKLIST.addAll(DISPLAYED_OBJECT);
    }
    
    /**
     * Method to reset the collision array
     */
    public void resetCollideRock() {
        COLLIDE_ROCKS.clear();
        COLLIDE_ROCKS.addAll(DISPLAYED_ROCKS);
    }
    
    /**
     * Method to add the objects to remove from the game
     * @param objectGames It is the object to remove
     */
    public void addToRemovedObjects(ObjectGame... objectGames) {
        if (objectGames.length > 1) REMOVED_OBJECTS.addAll(Arrays.asList((ObjectGame[]) objectGames));
        else REMOVED_OBJECTS.add(objectGames[0]);
    }
    
    /**
     * Method to add the objects to remove from the game
     * @param rock It is the object to remove
     */
    public void addToRemovedRocks(Rock... rock) {
        if (rock.length > 1) REMOVED_ROCKS.addAll(Arrays.asList((Rock[]) rock));
        else REMOVED_ROCKS.add(rock[0]);
    }
    
    /**
     * Method to reset the object removed
     */
    public void resetRemovedObjects() {
        DISPLAYED_OBJECT.removeAll(REMOVED_OBJECTS);
        REMOVED_OBJECTS.clear();
    }

    /**
     * Method to reset the object removed
     */
    public void resetRemovedRocks() {
        DISPLAYED_ROCKS.removeAll(REMOVED_ROCKS);
        REMOVED_ROCKS.clear();
    }
    
    /**
     * Method to get the object whit who you can collide
     * @return 
     */
    public List<ObjectGame> getCOLLIDE_CHECKLIST() {
        return COLLIDE_CHECKLIST;
    }

    /**
     * Method to set the Collision checklist
     * @param COLLIDE_CHECKLIST 
     */
    public void setCOLLIDE_CHECKLIST(List<ObjectGame> COLLIDE_CHECKLIST) {
        this.COLLIDE_CHECKLIST = COLLIDE_CHECKLIST;
    }

    /**
     * Method to get the removed objects from the ArrayList
     * @return It its the object to remove
     */
    public Set<ObjectGame> getREMOVED_OBJECTS() {
        return REMOVED_OBJECTS;
    }
    
    /**
     * Method to set the removed objects in the ArrayList
     * @param REMOVED_OBJECTS It is the object to remove
     */
    public void setREMOVED_OBJECTS(Set<ObjectGame> REMOVED_OBJECTS) {
        this.REMOVED_OBJECTS = REMOVED_OBJECTS;
    }

    public List<ObjectGame> getDISPLAYED_ROCKS() {
        return DISPLAYED_ROCKS;
    }

    public void setDISPLAYED_ROCKS(List<ObjectGame> DISPLAYED_ROCKS) {
        this.DISPLAYED_ROCKS = DISPLAYED_ROCKS;
    }

    public List<ObjectGame> getCOLLIDE_ROCKS() {
        return COLLIDE_ROCKS;
    }

    public void setCOLLIDE_ROCKS(List<ObjectGame> COLLIDE_ROCKS) {
        this.COLLIDE_ROCKS = COLLIDE_ROCKS;
    }

    public Set<ObjectGame> getREMOVED_ROCKS() {
        return REMOVED_ROCKS;
    }

    public void setREMOVED_ROCKS(Set<ObjectGame> REMOVED_ROCKS) {
        this.REMOVED_ROCKS = REMOVED_ROCKS;
    }
    
    
    
}
