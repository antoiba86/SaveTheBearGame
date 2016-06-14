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
    
    private List<ObjectGame> objectDisplayed, rockDisplayed, objectToAdd, rockToAdd, objectAddToRemove, rockAddToRemove ;
    private Set<ObjectGame> objectRemoved, rocksRemoved ;
    
    /**
     * Method to create the objects arrayList
     */
    public DisplayObject() {
        this.objectDisplayed = new ArrayList<>();
        this.objectToAdd = new ArrayList<>();
        this.objectRemoved = new HashSet<>();
        this.objectAddToRemove = new ArrayList<>();
        this.rockDisplayed = new ArrayList<>();
        this.rockToAdd = new ArrayList<>();
        this.rocksRemoved = new HashSet<>();
        this.rockAddToRemove = new ArrayList<>();
    }
    
    /**
     * Method to get the Object's displayed
     * @return The ArrayList of the objects displayed
     */
    public List<ObjectGame> getObjectDisplayed() {
        return objectDisplayed;
    }

    public List<ObjectGame> getObjectToAdd() {
        return objectToAdd;
    }
    
    /**
     * Method to add an object into the Array of Objects displayed
     * @param objectGames It is the object to display
     */
    public void addDisplayed_Object(ObjectGame... objectGames) {
        objectDisplayed.addAll(Arrays.asList(objectGames));
    }
    
    public void addToObjectToAdd(ObjectGame... objectGames) {
        objectToAdd.addAll(Arrays.asList(objectGames));
    }
    
    /**
     * Method to add an object into the Array of Rocks displayed
     * @param rock It is the object to display
     */
    public void addDisplayed_Rock(Rock... rock) {
        rockDisplayed.addAll(Arrays.asList(rock) );
    }
    
    /**
     * Method to add an object into the Array of Rocks displayed
     * @param rock It is the object to display
     */
    public void addToRockToAdd(Rock... rock) {
        rockToAdd.addAll(Arrays.asList(rock) );
    }
    
    /**
     * Method to remove an object from the game
     * @param objectGames It is the object to remove
     */
    public void removeDisplayed_Object(ObjectGame... objectGames) {
        objectDisplayed.removeAll( Arrays.asList(objectGames) );
    }
    
    /**
     * Method to remove an object from the game
     * @param objectGames It is the object to remove
     */
    public void addObjectToRemove(ObjectGame... objectGames) {
        objectAddToRemove.addAll(Arrays.asList(objectGames) );
    }
    
    /**
     * Method to remove an object from the game
     * @param rock It is the object to remove
     */
    public void removeDisplayed_Rocks(Rock... rock) {
        rockDisplayed.removeAll( Arrays.asList(rock) );
    }
    
    /**
     * Method to remove an object from the game
     * @param rocks It is the object to remove
     */
    public void addRockToRemove(Rock... rocks) {
        rockAddToRemove.addAll(Arrays.asList(rocks) );
    }
    
    /**
     * Method to clean the display_object array
     */
    public void resetDisplayed_Object() {
        objectDisplayed.clear();
    }
    
    /**
     * Method to clean the display_object array
     */
    public void resetDisplayed_Rock() {
        rockDisplayed.clear();
    }
    

    
    /**
     * Method to add the objects to remove from the game
     * @param objectGames It is the object to remove
     */
    public void addToRemovedObjects(ObjectGame... objectGames) {
        objectDisplayed.removeAll(Arrays.asList(objectGames));
        objectRemoved.addAll(Arrays.asList(objectGames));
        
    }
    
    /**
     * Method to add the objects to remove from the game
     * @param rocks It is the object to remove
     */
    public void addToRemovedRocks(Rock... rocks) {
        rockDisplayed.removeAll(Arrays.asList(rocks));
        rocksRemoved.addAll(Arrays.asList(rocks));
    }
    
    /**
     * Method to reset the object removed
     */
    public void resetObjectAddToRemove() {
        objectAddToRemove.clear();
    }

    /**
     * Method to reset the object removed
     */
    public void resetRemovedRocks() {
        rockAddToRemove.clear();
    }

    /**
     * Method to get the removed objects from the ArrayList
     * @return It its the object to remove
     */
    public Set<ObjectGame> getObjectRemoved() {
        return objectRemoved;
    }
    
    /**
     * Method to set the removed objects in the ArrayList
     * @param objectRemoved It is the object to remove
     */
    public void setObjectRemoved(Set<ObjectGame> objectRemoved) {
        this.objectRemoved = objectRemoved;
    }

    public List<ObjectGame> getRockDisplayed() {
        return rockDisplayed;
    }
    
    public List<ObjectGame> getRockToAdd() {
        return rockToAdd;
    }

    public void setRockDisplayed(List<ObjectGame> rockDisplayed) {
        this.rockDisplayed = rockDisplayed;
    }


    public Set<ObjectGame> getRocksRemoved() {
        return rocksRemoved;
    }

    public void setRocksRemoved(Set<ObjectGame> rocksRemoved) {
        this.rocksRemoved = rocksRemoved;
    }

    void resetObjectToAdd() {
        objectToAdd.clear();
    }
    
    void resetRockToAdd() {
        rockToAdd.clear();
    }

    public List<ObjectGame> getObjectAddToRemove() {
        return objectAddToRemove;
    }

    public List<ObjectGame> getRockAddToRemove() {
        return rockAddToRemove;
    }
        
    
    
    
    
}
