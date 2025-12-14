package rs.ftn.ns.ontology;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import rs.ftn.ns.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for querying films from the ontology using SPARQL.
 */
public class FilmQueryService {
    
    private OntologyManager ontologyManager;
    private String dataNs;   // namespace for instances
    private String schemaNs; // namespace for properties

    public FilmQueryService() {
        this.ontologyManager = new OntologyManager();
        this.dataNs = ontologyManager.getDataNamespace();
        this.schemaNs = ontologyManager.getSchemaNamespace();
    }
    
    /**
     * Get all films from the ontology
     * 
     * @return List of Film objects
     */
    public List<Film> getAllFilms() {
        String queryString = 
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
            "PREFIX data: <" + dataNs + "> " +
            "PREFIX schema: <" + schemaNs + "> " +
            "SELECT ?film ?title ?year " +
            "WHERE { " +
            "  ?film rdf:type schema:Film . " +
            "  ?film schema:hasTitle ?title . " +
            "  OPTIONAL { ?film schema:releaseYear ?year } " +
            "}";
        
        List<Film> films = new ArrayList<>();
        ResultSet results = ontologyManager.executeQuery(queryString);
        
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            
            Film film = new Film();
            film.setId(extractLocalName(solution.get("film").toString()));
            film.setTitle(solution.getLiteral("title").getString());
            
            if (solution.contains("year")) {
                film.setReleaseYear(solution.getLiteral("year").getInt());
            }
            
            films.add(film);
        }
        
        return films;
    }
    
    /**
     * Find films by genre
     * 
     * @param genreName The genre to search for (e.g., "Action", "Drama")
     * @return List of Film objects
     */
    public List<Film> findFilmsByGenre(String genreName) {
        String queryString = 
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
            "PREFIX data: <" + dataNs + "> " +
            "PREFIX schema: <" + schemaNs + "> " +
            "SELECT ?film ?title ?year " +
            "WHERE { " +
            "  ?film rdf:type schema:Film . " +
            "  ?film schema:hasTitle ?title . " +
            "  ?film schema:hasGenre ?genre . " +
            "  ?genre schema:genreName \"" + genreName + "\" . " +
            "  OPTIONAL { ?film schema:releaseYear ?year } " +
            "}";
        
        List<Film> films = new ArrayList<>();
        ResultSet results = ontologyManager.executeQuery(queryString);
        
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            
            Film film = new Film();
            film.setId(extractLocalName(solution.get("film").toString()));
            film.setTitle(solution.getLiteral("title").getString());
            
            if (solution.contains("year")) {
                film.setReleaseYear(solution.getLiteral("year").getInt());
            }
            
            films.add(film);
        }
        
        return films;
    }
    
    /**
     * Find films by director
     * 
     * @param directorName The director's name
     * @return List of Film objects
     */
    public List<Film> findFilmsByDirector(String directorName) {
        String queryString = 
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
            "PREFIX data: <" + dataNs + "> " +
            "PREFIX schema: <" + schemaNs + "> " +
            "SELECT ?film ?title ?year ?directorName " +
            "WHERE { " +
            "  ?film rdf:type schema:Film . " +
            "  ?film schema:hasTitle ?title . " +
            "  ?film schema:directedBy ?director . " +
            "  ?director schema:hasName ?directorName . " +
            "  FILTER(CONTAINS(LCASE(?directorName), LCASE(\"" + directorName + "\"))) . " +
            "  OPTIONAL { ?film schema:releaseYear ?year } " +
            "}";
        
        List<Film> films = new ArrayList<>();
        ResultSet results = ontologyManager.executeQuery(queryString);
        
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            
            Film film = new Film();
            film.setId(extractLocalName(solution.get("film").toString()));
            film.setTitle(solution.getLiteral("title").getString());
            
            if (solution.contains("year")) {
                film.setReleaseYear(solution.getLiteral("year").getInt());
            }
            
            // Create director
            Director director = new Director();
            director.setName(solution.getLiteral("directorName").getString());
            film.setDirector(director);
            
            films.add(film);
        }
        
        return films;
    }
    
    /**
     * Find films by year range
     * 
     * @param startYear Start year (inclusive)
     * @param endYear End year (inclusive)
     * @return List of Film objects
     */
    public List<Film> findFilmsByYearRange(int startYear, int endYear) {
        String queryString = 
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
            "PREFIX data: <" + dataNs + "> " +
            "PREFIX schema: <" + schemaNs + "> " +
            "SELECT ?film ?title ?year " +
            "WHERE { " +
            "  ?film rdf:type schema:Film . " +
            "  ?film schema:hasTitle ?title . " +
            "  ?film schema:releaseYear ?year . " +
            "  FILTER(?year >= " + startYear + " && ?year <= " + endYear + ") " +
            "} ORDER BY ?year";
        
        List<Film> films = new ArrayList<>();
        ResultSet results = ontologyManager.executeQuery(queryString);
        
        while (results.hasNext()) {
            QuerySolution solution = results.nextSolution();
            
            Film film = new Film();
            film.setId(extractLocalName(solution.get("film").toString()));
            film.setTitle(solution.getLiteral("title").getString());
            film.setReleaseYear(solution.getLiteral("year").getInt());
            
            films.add(film);
        }
        
        return films;
    }
    
    /**
     * Helper method to extract local name from URI
     * Example: http://example.org/Film#Inception -> Inception
     */
    private String extractLocalName(String uri) {
        if (uri.contains("#")) {
            return uri.substring(uri.indexOf("#") + 1);
        } else if (uri.contains("/")) {
            return uri.substring(uri.lastIndexOf("/") + 1);
        }
        return uri;
    }
    
    /**
     * Close the ontology manager
     */
    public void close() {
        ontologyManager.close();
    }
}