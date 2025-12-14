package rs.ftn.ns.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import rs.ftn.ns.model.Film;

/**
 * Evaluates film quality using fuzzy logic system.
 * Uses jFuzzyLogic library with FCL rules.
 */
public class FilmQualityEvaluator {
    
    private FIS fis;
    private FunctionBlock functionBlock;
    private static final String FCL_FILE_PATH = "src/main/resources/fuzzy/film_quality.fcl";
    
    /**
     * Constructor - loads the fuzzy system
     */
    public FilmQualityEvaluator() {
        loadFuzzySystem();
    }
    
    /**
     * Loads the fuzzy inference system from FCL file
     */
    private void loadFuzzySystem() {
        try {
            // Load FCL file
            fis = FIS.load(FCL_FILE_PATH, true);
            
            if (fis == null) {
                throw new RuntimeException("Could not load fuzzy system from: " + FCL_FILE_PATH);
            }
            
            // Get the function block
            functionBlock = fis.getFunctionBlock("film_quality_assessment");
            
            if (functionBlock == null) {
                throw new RuntimeException("Function block 'film_quality_assessment' not found");
            }
            
            System.out.println("Fuzzy system loaded successfully!");
            
        } catch (Exception e) {
            System.err.println("Error loading fuzzy system: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Evaluates the quality of a film using fuzzy logic
     * 
     * @param film The film to evaluate
     * @return Quality score (0-10)
     */
    public double evaluateFilm(Film film) {
        // Set input variables
        functionBlock.setVariable("direction", film.getDirectionScore());
        functionBlock.setVariable("acting", film.getActingScore());
        functionBlock.setVariable("screenplay", film.getScreenplayScore());
        functionBlock.setVariable("visualEffects", film.getVisualEffectsScore());
        functionBlock.setVariable("culturalSignificance", film.getCulturalSignificanceScore());
        
        // Evaluate the fuzzy system
        functionBlock.evaluate();
        
        // Get output
        double quality = functionBlock.getVariable("quality").getValue();
        
        return quality;
    }
    
    /**
     * Evaluates film quality with custom scores
     * 
     * @param direction Direction score (0-10)
     * @param acting Acting score (0-10)
     * @param screenplay Screenplay score (0-10)
     * @param visualEffects Visual effects score (0-10)
     * @param culturalSignificance Cultural significance score (0-10)
     * @return Quality score (0-10)
     */
    public double evaluateFilm(double direction, double acting, double screenplay, 
                               double visualEffects, double culturalSignificance) {
        // Set input variables
        functionBlock.setVariable("direction", direction);
        functionBlock.setVariable("acting", acting);
        functionBlock.setVariable("screenplay", screenplay);
        functionBlock.setVariable("visualEffects", visualEffects);
        functionBlock.setVariable("culturalSignificance", culturalSignificance);
        
        // Evaluate
        functionBlock.evaluate();
        
        // Get output
        double quality = functionBlock.getVariable("quality").getValue();
        
        return quality;
    }
    
    /**
     * Evaluates film and returns quality category
     * 
     * @param film The film to evaluate
     * @return Quality category as string
     */
    public String evaluateFilmCategory(Film film) {
        double quality = evaluateFilm(film);
        return getQualityCategory(quality);
    }
    
    /**
     * Converts quality score to category
     * 
     * @param quality Quality score (0-10)
     * @return Category string
     */
    public String getQualityCategory(double quality) {
        if (quality < 3) {
            return "Bad";
        } else if (quality < 5) {
            return "Mediocre";
        } else if (quality < 7) {
            return "Good";
        } else {
            return "Excellent";
        }
    }
    
    /**
     * Gets detailed evaluation with explanation
     * 
     * @param film The film to evaluate
     * @return Detailed evaluation string
     */
    public String getDetailedEvaluation(Film film) {
        double quality = evaluateFilm(film);
        String category = getQualityCategory(quality);
        
        StringBuilder sb = new StringBuilder();
        sb.append("Film: ").append(film.getTitle()).append("\n");
        sb.append("=====================================\n");
        sb.append("Input Scores:\n");
        sb.append("  Direction: ").append(film.getDirectionScore()).append("/10\n");
        sb.append("  Acting: ").append(film.getActingScore()).append("/10\n");
        sb.append("  Screenplay: ").append(film.getScreenplayScore()).append("/10\n");
        sb.append("  Visual Effects: ").append(film.getVisualEffectsScore()).append("/10\n");
        sb.append("  Cultural Significance: ").append(film.getCulturalSignificanceScore()).append("/10\n");
        sb.append("-------------------------------------\n");
        sb.append("Fuzzy Logic Evaluation:\n");
        sb.append("  Overall Quality Score: ").append(String.format("%.2f", quality)).append("/10\n");
        sb.append("  Quality Category: ").append(category).append("\n");
        sb.append("=====================================\n");
        
        return sb.toString();
    }
    
    /**
     * Gets the fuzzy inference system
     * 
     * @return FIS object
     */
    public FIS getFIS() {
        return fis;
    }
    
//    /**
//     * Displays the fuzzy system chart (for debugging)
//     */
//    public void showChart() {
//        if (fis != null) {
//            fis.chart();
//        }
//    }
}