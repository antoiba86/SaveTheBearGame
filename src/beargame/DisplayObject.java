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
    
    private List<ObjectGame> DISPLAYED_OBJECT;
    private List<ObjectGame> COLLIDE_CHECKLIST;
    private Set<ObjectGame> REMOVED_OBJECTS;
    
    public DisplayObject() {
        this.DISPLAYED_OBJECT = new ArrayList<>();
        this.COLLIDE_CHECKLIST = new ArrayList<>();
        this.REMOVED_OBJECTS = new HashSet<>();
    }
    
    public List<ObjectGame> getDISPLAYED_OBJECT() {
        return DISPLAYED_OBJECT;
    }
    
    public void addDisplayed_Object(ObjectGame... objectGames) {
        DISPLAYED_OBJECT.addAll(Arrays.asList(objectGames) );
    }
    
    public void removeDisplayed_Object(ObjectGame... objectGames) {
        DISPLAYED_OBJECT.removeAll( Arrays.asList(objectGames) );
    }
    
    public void resetDisplayed_Object() {
        DISPLAYED_OBJECT.clear();
    }
    
    public List getCollideCheckList() {
        return COLLIDE_CHECKLIST;
    }
    
    public void resetCollideCheckList() {
        COLLIDE_CHECKLIST.clear();
        COLLIDE_CHECKLIST.addAll(DISPLAYED_OBJECT);
    }
    
    public void addToRemovedObjects(ObjectGame... objectGames) {
        if (objectGames.length > 1) REMOVED_OBJECTS.addAll(Arrays.asList((ObjectGame[]) objectGames));
        else REMOVED_OBJECTS.add(objectGames[0]);
    }
    
    public void resetRemovedObjects() {
        DISPLAYED_OBJECT.removeAll(REMOVED_OBJECTS);
        REMOVED_OBJECTS.clear();
    }

    public List<ObjectGame> getCOLLIDE_CHECKLIST() {
        return COLLIDE_CHECKLIST;
    }

    public void setCOLLIDE_CHECKLIST(List<ObjectGame> COLLIDE_CHECKLIST) {
        this.COLLIDE_CHECKLIST = COLLIDE_CHECKLIST;
    }

    public Set<ObjectGame> getREMOVED_OBJECTS() {
        return REMOVED_OBJECTS;
    }

    public void setREMOVED_OBJECTS(Set<ObjectGame> REMOVED_OBJECTS) {
        this.REMOVED_OBJECTS = REMOVED_OBJECTS;
    }
    
}
