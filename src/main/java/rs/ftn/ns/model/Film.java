package rs.ftn.ns.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a film with all its metadata.
 * This is a DTO (Data Transfer Object) used to transfer data
 * from the ontology to the user interface.
 */
public class Film {
    
    // Basic information
    private String id;
    private String title;
    private String originalTitle;
    private int releaseYear;
    private int duration; // in minutes
    private String country;
    private String language;
    
    // People involved
    private Director director;
    private List<Actor> actors;
    private List<Writer> writers;
    
    // Classification
    private List<String> genres;
    
    // Financial data
    private double budget;
    private double boxOffice;
    
    // Ratings
    private double imdbRating;
    private int imdbVotes;
    
    // Quality scores (for fuzzy logic system)
    private double directionScore;
    private double actingScore;
    private double screenplayScore;
    private double visualEffectsScore;
    private double culturalSignificanceScore;
    
    // Additional information
    private String synopsis;
    private List<String> awards;
    
    // Constructors
    public Film() {
        this.actors = new ArrayList<>();
        this.writers = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.awards = new ArrayList<>();
    }
    
    public Film(String id, String title, int releaseYear) {
        this();
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getOriginalTitle() {
        return originalTitle;
    }
    
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    
    public int getReleaseYear() {
        return releaseYear;
    }
    
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public Director getDirector() {
        return director;
    }
    
    public void setDirector(Director director) {
        this.director = director;
    }
    
    public List<Actor> getActors() {
        return actors;
    }
    
    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
    
    public void addActor(Actor actor) {
        this.actors.add(actor);
    }
    
    public List<Writer> getWriters() {
        return writers;
    }
    
    public void setWriters(List<Writer> writers) {
        this.writers = writers;
    }
    
    public void addWriter(Writer writer) {
        this.writers.add(writer);
    }
    
    public List<String> getGenres() {
        return genres;
    }
    
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    
    public void addGenre(String genre) {
        this.genres.add(genre);
    }
    
    public double getBudget() {
        return budget;
    }
    
    public void setBudget(double budget) {
        this.budget = budget;
    }
    
    public double getBoxOffice() {
        return boxOffice;
    }
    
    public void setBoxOffice(double boxOffice) {
        this.boxOffice = boxOffice;
    }
    
    public double getImdbRating() {
        return imdbRating;
    }
    
    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }
    
    public int getImdbVotes() {
        return imdbVotes;
    }
    
    public void setImdbVotes(int imdbVotes) {
        this.imdbVotes = imdbVotes;
    }
    
    public double getDirectionScore() {
        return directionScore;
    }
    
    public void setDirectionScore(double directionScore) {
        this.directionScore = directionScore;
    }
    
    public double getActingScore() {
        return actingScore;
    }
    
    public void setActingScore(double actingScore) {
        this.actingScore = actingScore;
    }
    
    public double getScreenplayScore() {
        return screenplayScore;
    }
    
    public void setScreenplayScore(double screenplayScore) {
        this.screenplayScore = screenplayScore;
    }
    
    public double getVisualEffectsScore() {
        return visualEffectsScore;
    }
    
    public void setVisualEffectsScore(double visualEffectsScore) {
        this.visualEffectsScore = visualEffectsScore;
    }
    
    public double getCulturalSignificanceScore() {
        return culturalSignificanceScore;
    }
    
    public void setCulturalSignificanceScore(double culturalSignificanceScore) {
        this.culturalSignificanceScore = culturalSignificanceScore;
    }
    
    public String getSynopsis() {
        return synopsis;
    }
    
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Film: ").append(title);
        if (releaseYear > 0) {
            sb.append(" (").append(releaseYear).append(")");
        }
        if (director != null) {
            sb.append("\n  Director: ").append(director.getName());
        }
        if (!genres.isEmpty()) {
            sb.append("\n  Genres: ").append(String.join(", ", genres));
        }
        if (imdbRating > 0) {
            sb.append("\n  IMDB Rating: ").append(imdbRating);
        }
        return sb.toString();
    }
}
