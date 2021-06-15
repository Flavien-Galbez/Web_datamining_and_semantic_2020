package applications;

import tools.JenaEngine;

import java.io.IOException;
import java.io.InputStream;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class More_function {
	private Model model;
	private String file;
	private String namespace;
	 
	More_function(String path){
		this.namespace = "";
		this.file = path;
		this.model = JenaEngine.readModel(path);
		if(model != null ){
			namespace = model.getNsPrefixURI("");
		}
	}
	
	public void famillyInformation() {
		String person = "Peter";
		displayName(person);
		displayFamily(person);
		displayHusband(person);
		possibleFutureHusband(person);
	}
	
	public void displayName(String person){
		
		String query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>" + 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" + 
				"SELECT ?name" + 
				"WHERE {" + 
				"	?per ns:name \""+ person +"\" ." + 
				"	?per ns:name ?name ." +  
				"}";
		System.out.println(JenaEngine.executeQuery(model, query));
	}
	
	public void displayFamily(String person){
		
		String query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>" + 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" + 
				"SELECT ?name" + 
				"WHERE {" + 
				"	?per2 ns:name \""+ person +"\" ." + 
				"	?per ns:isFatherOf ?per2 ." + 
				"	?per ns:name ?name ." +  
				"}";
		System.out.println(JenaEngine.executeQuery(model, query));
		query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>" + 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" + 
				"SELECT ?name" + 
				"WHERE {" + 
				"	?per2 ns:name \""+ person +"\" ." + 
				"	?per ns:isMotherOf ?per2 ." + 
				"	?per ns:name ?name ." +  
				"}";
		System.out.println(JenaEngine.executeQuery(model, query));
		query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>" + 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" + 
				"SELECT ?name" + 
				"WHERE {" + 
				"	?per2 ns:name \""+ person +"\" ." + 
				"	?per ns:isBrotherOf ?per2 ." + 
				"	?per ns:name ?name ." +  
				"}";
		System.out.println(JenaEngine.executeQuery(model, query));
		query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>" + 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" + 
				"SELECT ?name" + 
				"WHERE {" + 
				"	?per2 ns:name \""+ person +"\" ." + 
				"	?per ns:isSisterOf ?per2 ." + 
				"	?per ns:name ?name ." +  
				"}";
		System.out.println(JenaEngine.executeQuery(model, query));
	
	}
	
	public void displayHusband(String person){
		
		String query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>" + 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" + 
				"SELECT ?name" + 
				"WHERE {" + 
				"	?per2 ns:name \""+ person +"\" ." + 
				"	?per ns:isMarriedWith ?per2 ." + 
				"	?per ns:name ?name ." +  
				"}";
		System.out.println(JenaEngine.executeQuery(model, query));
		
	}
	
public void possibleFutureHusband(String person){
		
		String query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>" + 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" + 
				"SELECT DISTINCT ?name" + 
				"WHERE {" + 
				"	?per2 ns:name \""+ person +"\" ." + 
				"	?per ns:name ?name ." +
				"	?per rdf:type ?type . "+
				"	?per2 rdf:type ?type2 . "+
				"   ?per ns:age ?age ." +
				"   ?per2 ns:age ?age2 " +
				"FILTER (NOT EXISTS {"+
				"?per2 ns:isMarriedWith *} &&" +
				"NOT EXISTS {"+
				"?per ns:isMarriedWith *} &&" +
				"(?age < 5+?age2) && (?age > -5+?age2) &&" +
				"((?type == Male && ?type2 == Female)||(?type == Female && ?type2 == Male)" +
				"}";
		System.out.println(JenaEngine.executeQuery(model, query));
		
	
	}

	

}
