package rs.ftn.ns.ontology;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.query.*;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

/**
 * Manages loading and querying the film ontology.
 */
public class OntologyManager {
    
    private OntModel ontologyModel;
    private static final String ONTOLOGY_PATH = "src/main/resources/ontology/film-data.owl";
 // Namespace for instances (films, actors, directors, etc.)
    private static final String DATA_NAMESPACE = "http://www.semanticweb.org/dusan/ontologies/2025/11/film-ontology/data#";

    // Namespace for properties (hasTitle, directedBy, etc.) 
    private static final String SCHEMA_NAMESPACE = "http://www.semanticweb.org/dusan/ontologies/2025/11/movie-app#";
    
    /**
     * Constructor - loads the ontology
     */
    public OntologyManager() {
        loadOntology();
    }
    
    /**
     * Loads the ontology from the OWL file
     */
    private void loadOntology() {
        try {
            // Create an ontology model
            ontologyModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
            
            // Load the ontology file
            InputStream in = FileManager.get().open(ONTOLOGY_PATH);
            if (in == null) {
                throw new IllegalArgumentException("File not found: " + ONTOLOGY_PATH);
            }
            
            // Read the ontology
            ontologyModel.read(in, null);
            
            System.out.println("Ontology loaded successfully!");
            System.out.println("Number of statements: " + ontologyModel.size());
            
        } catch (Exception e) {
            System.err.println("Error loading ontology: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Executes a SPARQL query and returns results
     * 
     * @param queryString The SPARQL query to execute
     * @return ResultSet containing query results
     */
    public ResultSet executeQuery(String queryString) {
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, ontologyModel);
        return qexec.execSelect();
    }
    
    /**
     * Executes a SPARQL query and prints results to console
     * 
     * @param queryString The SPARQL query to execute
     */
    public void executeAndPrintQuery(String queryString) {
        ResultSet results = executeQuery(queryString);
        ResultSetFormatter.out(System.out, results);
    }
    
    /**
     * Gets the ontology model
     * 
     * @return The OntModel
     */
    public OntModel getOntologyModel() {
        return ontologyModel;
    }
    
    /**
     * Gets the namespace of the ontology
     * 
     * @return The namespace string
     */
    public String getDataNamespace() {
        return DATA_NAMESPACE;
    }

    public String getSchemaNamespace() {
        return SCHEMA_NAMESPACE;
    }
    
    /**
     * Closes the ontology model
     */
    public void close() {
        if (ontologyModel != null) {
            ontologyModel.close();
        }
    }
    
    public void printOntologyInfo() {
        System.out.println("\n=== Ontology Debug Info ===");
        System.out.println("Total statements: " + ontologyModel.size());
        System.out.println("Ontology URI: " + ontologyModel.getNsPrefixURI(""));
        
        // Print all namespaces
        System.out.println("\nNamespaces:");
        ontologyModel.getNsPrefixMap().forEach((prefix, uri) -> {
            System.out.println("  " + prefix + " -> " + uri);
        });
        
        // Print first 10 statements to see structure
        System.out.println("\nFirst 10 statements:");
        ontologyModel.listStatements().toList().stream()
            .limit(10)
            .forEach(stmt -> System.out.println("  " + stmt));
    }
}