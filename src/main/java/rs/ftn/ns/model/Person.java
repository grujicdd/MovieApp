package rs.ftn.ns.model;

/**
 * Base class for all person entities (Directors, Actors, Writers, etc.)
 */
public abstract class Person {
    
    protected String id;
    protected String name;
    protected String birthDate;
    protected String nationality;
    protected String biography;
    
    public Person() {
    }
    
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getBiography() {
        return biography;
    }
    
    public void setBiography(String biography) {
        this.biography = biography;
    }
    
    @Override
    public String toString() {
        return String.format("%s{id='%s', name='%s'}", 
                           this.getClass().getSimpleName(), id, name);
    }
}
