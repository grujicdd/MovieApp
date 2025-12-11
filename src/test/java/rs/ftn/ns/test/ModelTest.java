package rs.ftn.ns.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import rs.ftn.ns.model.*;

/**
 * Unit tests to verify that model classes work correctly.
 */
public class ModelTest {
    
    private Film film;
    private Director director;
    private Actor actor;
    private Writer writer;
    
    @Before
    public void setUp() {
        // Create test objects before each test
        film = new Film("film001", "The Shawshank Redemption", 1994);
        director = new Director("dir001", "Frank Darabont");
        actor = new Actor("act001", "Tim Robbins");
        writer = new Writer("wrt001", "Stephen King");
    }
    
    @Test
    public void testFilmCreation() {
        assertEquals("film001", film.getId());
        assertEquals("The Shawshank Redemption", film.getTitle());
        assertEquals(1994, film.getReleaseYear());
        System.out.println("✓ Film creation test passed");
    }
    
    @Test
    public void testFilmWithDirector() {
        film.setDirector(director);
        
        assertNotNull(film.getDirector());
        assertEquals("Frank Darabont", film.getDirector().getName());
        System.out.println("✓ Film with director test passed");
    }
    
    @Test
    public void testFilmWithActors() {
        Actor actor2 = new Actor("act002", "Morgan Freeman");
        
        film.addActor(actor);
        film.addActor(actor2);
        
        assertEquals(2, film.getActors().size());
        assertEquals("Tim Robbins", film.getActors().get(0).getName());
        assertEquals("Morgan Freeman", film.getActors().get(1).getName());
        System.out.println("✓ Film with actors test passed");
    }
    
    @Test
    public void testFilmWithGenres() {
        film.addGenre("Drama");
        film.addGenre("Crime");
        
        assertEquals(2, film.getGenres().size());
        assertTrue(film.getGenres().contains("Drama"));
        assertTrue(film.getGenres().contains("Crime"));
        System.out.println("✓ Film with genres test passed");
    }
    
    @Test
    public void testFilmToString() {
        film.setDirector(director);
        film.addGenre("Drama");
        film.setImdbRating(9.3);
        
        String result = film.toString();
        
        assertTrue(result.contains("The Shawshank Redemption"));
        assertTrue(result.contains("1994"));
        assertTrue(result.contains("Frank Darabont"));
        assertTrue(result.contains("Drama"));
        
        System.out.println("\n" + result);
        System.out.println("\n✓ Film toString test passed");
    }
    
    @Test
    public void testDirectorProperties() {
        director.setNationality("American");
        director.setBirthDate("1959-01-28");
        director.addAward("Saturn Award");
        
        assertEquals("American", director.getNationality());
        assertEquals("1959-01-28", director.getBirthDate());
        assertEquals(1, director.getAwards().size());
        System.out.println("✓ Director properties test passed");
    }
    
    @Test
    public void testCompleteFilm() {
        // Build a complete film object
        film.setDuration(142);
        film.setCountry("USA");
        film.setLanguage("English");
        film.setDirector(director);
        film.addActor(actor);
        film.addWriter(writer);
        film.addGenre("Drama");
        film.setBudget(25000000);
        film.setBoxOffice(58300000);
        film.setImdbRating(9.3);
        film.setSynopsis("Two imprisoned men bond over years.");
        
        // Verify everything
        assertNotNull(film.getId());
        assertNotNull(film.getTitle());
        assertNotNull(film.getDirector());
        assertFalse(film.getActors().isEmpty());
        assertFalse(film.getWriters().isEmpty());
        assertFalse(film.getGenres().isEmpty());
        assertTrue(film.getBudget() > 0);
        assertTrue(film.getImdbRating() > 0);
        
        System.out.println("✓ Complete film object test passed");
    }
}
