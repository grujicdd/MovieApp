package rs.ftn.ns.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rs.ftn.ns.model.Film;
import rs.ftn.ns.ontology.FilmQueryService;
import rs.ftn.ns.ontology.OntologyManager;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for SPARQL queries on the film ontology.
 */
public class SPARQLTest {
    
    private FilmQueryService queryService;
    
    @Before
    public void setUp() {
        System.out.println("\n=== Setting up SPARQL Test ===");
        queryService = new FilmQueryService();
    }
    
    @After
    public void tearDown() {
        queryService.close();
    }
    
    @Test
    public void testGetAllFilms() {
        System.out.println("\n--- Test: Get All Films ---");
        
        List<Film> films = queryService.getAllFilms();
        
        assertNotNull("Film list should not be null", films);
        assertFalse("Film list should not be empty", films.isEmpty());
        
        System.out.println("Total films found: " + films.size());
        
        // Print first 5 films
        System.out.println("\nFirst 5 films:");
        for (int i = 0; i < Math.min(5, films.size()); i++) {
            Film film = films.get(i);
            System.out.println("  - " + film.getTitle() + " (" + film.getReleaseYear() + ")");
        }
        
        assertTrue("Should have at least 20 films", films.size() >= 20);
    }
    
    @Test
    public void testFindFilmsByGenre() {
        System.out.println("\n--- Test: Find Films by Genre (Drama) ---");
        
        List<Film> dramaFilms = queryService.findFilmsByGenre("Drama");
        
        assertNotNull("Drama films list should not be null", dramaFilms);
        assertFalse("Should find at least one drama film", dramaFilms.isEmpty());
        
        System.out.println("Drama films found: " + dramaFilms.size());
        
        // Print all drama films
        System.out.println("\nDrama films:");
        for (Film film : dramaFilms) {
            System.out.println("  - " + film.getTitle() + " (" + film.getReleaseYear() + ")");
        }
    }
    
    @Test
    public void testFindFilmsByGenreAction() {
        System.out.println("\n--- Test: Find Films by Genre (Action) ---");
        
        List<Film> actionFilms = queryService.findFilmsByGenre("Action");
        
        assertNotNull("Action films list should not be null", actionFilms);
        
        System.out.println("Action films found: " + actionFilms.size());
        
        // Print all action films
        System.out.println("\nAction films:");
        for (Film film : actionFilms) {
            System.out.println("  - " + film.getTitle() + " (" + film.getReleaseYear() + ")");
        }
    }
    
    @Test
    public void testFindFilmsByDirector() {
        System.out.println("\n--- Test: Find Films by Director (Christopher Nolan) ---");
        
        List<Film> nolanFilms = queryService.findFilmsByDirector("Christopher Nolan");
        
        assertNotNull("Nolan films list should not be null", nolanFilms);
        assertFalse("Should find at least one Nolan film", nolanFilms.isEmpty());
        
        System.out.println("Christopher Nolan films found: " + nolanFilms.size());
        
        // Print all Nolan films
        System.out.println("\nChristopher Nolan films:");
        for (Film film : nolanFilms) {
            System.out.println("  - " + film.getTitle() + " (" + film.getReleaseYear() + ")");
            if (film.getDirector() != null) {
                System.out.println("    Director: " + film.getDirector().getName());
            }
        }
        
        // Should have at least 3 Nolan films (Inception, Interstellar, The Dark Knight, The Prestige)
        assertTrue("Should have multiple Nolan films", nolanFilms.size() >= 3);
    }
    
    @Test
    public void testFindFilmsByYearRange() {
        System.out.println("\n--- Test: Find Films by Year Range (2000-2010) ---");
        
        List<Film> films = queryService.findFilmsByYearRange(2000, 2010);
        
        assertNotNull("Films list should not be null", films);
        
        System.out.println("Films from 2000-2010 found: " + films.size());
        
        // Print all films in range
        System.out.println("\nFilms from 2000-2010:");
        for (Film film : films) {
            System.out.println("  - " + film.getTitle() + " (" + film.getReleaseYear() + ")");
        }
        
        // Verify all films are in range
        for (Film film : films) {
            assertTrue("Film year should be >= 2000", film.getReleaseYear() >= 2000);
            assertTrue("Film year should be <= 2010", film.getReleaseYear() <= 2010);
        }
    }
    
    @Test
    public void testFindFilmsByYearRange1990s() {
        System.out.println("\n--- Test: Find Films by Year Range (1990-1999) ---");
        
        List<Film> films = queryService.findFilmsByYearRange(1990, 1999);
        
        assertNotNull("Films list should not be null", films);
        
        System.out.println("Films from 1990s found: " + films.size());
        
        // Print all films in range
        System.out.println("\nFilms from 1990s:");
        for (Film film : films) {
            System.out.println("  - " + film.getTitle() + " (" + film.getReleaseYear() + ")");
        }
    }
    
    @Test
    public void testSpecificFilm() {
        System.out.println("\n--- Test: Find Specific Film (The Shawshank Redemption) ---");
        
        List<Film> films = queryService.getAllFilms();
        
        Film shawshank = null;
        for (Film film : films) {
            if (film.getTitle().equals("The Shawshank Redemption")) {
                shawshank = film;
                break;
            }
        }
        
        assertNotNull("Should find The Shawshank Redemption", shawshank);
        assertEquals("Year should be 1994", 1994, shawshank.getReleaseYear());
        
        System.out.println("Found: " + shawshank.getTitle() + " (" + shawshank.getReleaseYear() + ")");
    }
    
    @Test
    public void debugOntology() {
        OntologyManager manager = new OntologyManager();
        manager.printOntologyInfo();
        manager.close();
    }
}