/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Anto
 */
public class DisplayObject {
    
    private List<ObjectGame> DISPLAYED_OBJECT = new ArrayList<>();
    private List<ObjectGame> COLLIDE_CHECKLIST = new ArrayList<>();
    private Set<ObjectGame> REMOVED_OBJECTS = new HashSet<>();
    
    public DisplayObject() {
        this.DISPLAYED_OBJECT = new ArrayList<>();
        this.COLLIDE_CHECKLIST = new ArrayList<>();
        this.REMOVED_OBJECTS = new HashSet<>();
    }
    
    public List<ObjectGame> getDISPLAYED_OBJECT() {
        return DISPLAYED_OBJECT;
    }
    
    public void addDisplayed_Object(ObjectGame... objectGames) {
        DISPLAYED_OBJECT.addAll( Arrays.asList(objectGames) );
    }
    
    public void removeDisplayed_Object(ObjectGame... objectGames) {
        DISPLAYED_OBJECT.removeAll( Arrays.asList(objectGames) );
    }
    
    public void resetDisplayed_Object(ObjectGame... objectGames) {
        DISPLAYED_OBJECT.clear();
    }
    
    public List getCollideCheckList() {
        return COLLIDE_CHECKLIST;
    }
    
    public void resetCollideCheckList() {
        COLLIDE_CHECKLIST.clear();
        COLLIDE_CHECKLIST.addAll(DISPLAYED_OBJECT);
    }
    
    public void addToRemovedActors(ObjectGame... objectGames) {
        if (objectGames.length > 1) REMOVED_OBJECTS.addAll(Arrays.asList((ObjectGame[]) objectGames));
        else REMOVED_OBJECTS.add(objectGames[0]);
    }
    
    public void resetRemovedActors() {
        DISPLAYED_OBJECT.removeAll(REMOVED_OBJECTS);
        REMOVED_OBJECTS.clear();
    }
    
}
