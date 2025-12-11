package rs.ftn.ns.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a film director.
 */
public class Director extends Person {
    
    private List<String> awards;
    private String filmography;
    
    public Director() {
        super();
        this.awards = new ArrayList<>();
    }
    
    public Director(String id, String name) {
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
    
    public String getFilmography() {
        return filmography;
    }
    
    public void setFilmography(String filmography) {
        this.filmography = filmography;
    }
}
