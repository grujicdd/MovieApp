package rs.ftn.ns.test;

import org.junit.Before;
import org.junit.Test;
import rs.ftn.ns.fuzzy.FilmQualityEvaluator;
import rs.ftn.ns.model.Film;

import static org.junit.Assert.*;

/**
 * Test class for fuzzy logic film quality evaluation.
 */
public class FuzzyLogicTest {
    
    private FilmQualityEvaluator evaluator;
    
    @Before
    public void setUp() {
        System.out.println("\n=== Setting up Fuzzy Logic Test ===");
        evaluator = new FilmQualityEvaluator();
    }
    
    @Test
    public void testExcellentFilm() {
        System.out.println("\n--- Test: Excellent Film ---");
        
        Film film = new Film("test001", "Excellent Film", 2020);
        film.setDirectionScore(9.5);
        film.setActingScore(9.0);
        film.setScreenplayScore(9.0);
        film.setVisualEffectsScore(8.5);
        film.setCulturalSignificanceScore(9.0);
        
        double quality = evaluator.evaluateFilm(film);
        String category = evaluator.getQualityCategory(quality);
        
        System.out.println("Quality Score: " + quality);
        System.out.println("Category: " + category);
        
        assertTrue("Quality should be high for excellent scores", quality > 7.0);
        assertEquals("Should be categorized as Excellent", "Excellent", category);
    }
    
    @Test
    public void testPoorFilm() {
        System.out.println("\n--- Test: Poor Film ---");
        
        Film film = new Film("test002", "Poor Film", 2020);
        film.setDirectionScore(3.0);
        film.setActingScore(3.0);
        film.setScreenplayScore(2.5);
        film.setVisualEffectsScore(3.0);
        film.setCulturalSignificanceScore(2.0);
        
        double quality = evaluator.evaluateFilm(film);
        String category = evaluator.getQualityCategory(quality);
        
        System.out.println("Quality Score: " + quality);
        System.out.println("Category: " + category);
        
        assertTrue("Quality should be low for poor scores", quality < 5.0);
        assertTrue("Should be Bad or Mediocre", 
                   category.equals("Bad") || category.equals("Mediocre"));
    }
    
    @Test
    public void testAverageFilm() {
        System.out.println("\n--- Test: Average Film ---");
        
        Film film = new Film("test003", "Average Film", 2020);
        film.setDirectionScore(6.0);
        film.setActingScore(6.0);
        film.setScreenplayScore(6.0);
        film.setVisualEffectsScore(6.0);
        film.setCulturalSignificanceScore(6.0);
        
        double quality = evaluator.evaluateFilm(film);
        String category = evaluator.getQualityCategory(quality);
        
        System.out.println("Quality Score: " + quality);
        System.out.println("Category: " + category);
        
        assertTrue("Quality should be moderate", quality >= 4.0 && quality <= 7.0);
    }
    
    @Test
    public void testCulturalSignificanceBoost() {
        System.out.println("\n--- Test: Cultural Significance Boost ---");
        
        // Film with average scores but very high cultural significance
        Film film = new Film("test004", "Culturally Important Film", 1960);
        film.setDirectionScore(6.0);
        film.setActingScore(6.0);
        film.setScreenplayScore(6.0);
        film.setVisualEffectsScore(4.0); // Old film, weak VFX
        film.setCulturalSignificanceScore(9.5); // Very high!
        
        double quality = evaluator.evaluateFilm(film);
        String category = evaluator.getQualityCategory(quality);
        
        System.out.println("Quality Score: " + quality);
        System.out.println("Category: " + category);
        
        assertTrue("Cultural significance should boost quality", quality > 5.0);
        System.out.println("Cultural significance successfully boosted the rating!");
    }
    
    @Test
    public void testDetailedEvaluation() {
        System.out.println("\n--- Test: Detailed Evaluation ---");
        
        Film film = new Film("test005", "The Shawshank Redemption", 1994);
        film.setDirectionScore(9.0);
        film.setActingScore(9.5);
        film.setScreenplayScore(9.0);
        film.setVisualEffectsScore(7.0);
        film.setCulturalSignificanceScore(9.5);
        
        String evaluation = evaluator.getDetailedEvaluation(film);
        System.out.println(evaluation);
        
        assertNotNull("Evaluation should not be null", evaluation);
        assertTrue("Evaluation should contain film title", 
                   evaluation.contains("The Shawshank Redemption"));
    }
    
    @Test
    public void testCustomScores() {
        System.out.println("\n--- Test: Custom Scores ---");
        
        double quality = evaluator.evaluateFilm(8.0, 8.5, 7.5, 9.0, 8.0);
        String category = evaluator.getQualityCategory(quality);
        
        System.out.println("Custom scores quality: " + quality);
        System.out.println("Category: " + category);
        
        assertTrue("Should return valid quality score", quality >= 0 && quality <= 10);
    }
}