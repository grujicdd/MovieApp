package rs.ftn.ns.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a film actor.
 */
public class Actor extends Person {
    
    private List<String> awards;
    private String knownFor;
    
    public Actor() {
        super();
        this.awards = new ArrayList<>();
    }
    
    public Actor(String id, String name) {
        super(id, name);
        this.awards = new ArrayList<>();
    }
    
    public List<String> getAwards() {
        return awards;
    }
    
    public void setAwards(List<String> awards) {
        this.awards = awards;
    }
    
    public void addAward(String award) {
        this.awards.add(award);
    }
    
    public String getKnownFor() {
        return knownFor;
    }
    
    public void setKnownFor(String knownFor) {
        this.knownFor = knownFor;
    }
}
