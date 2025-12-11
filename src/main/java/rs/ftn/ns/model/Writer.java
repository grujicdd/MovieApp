package rs.ftn.ns.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a screenplay writer/screenwriter.
 */
public class Writer extends Person {
    
    private List<String> awards;
    private String notableWorks;
    
    public Writer() {
        super();
        this.awards = new ArrayList<>();
    }
    
    public Writer(String id, String name) {
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
    
    public String getNotableWorks() {
        return notableWorks;
    }
    
    public void setNotableWorks(String notableWorks) {
        this.notableWorks = notableWorks;
    }
}
