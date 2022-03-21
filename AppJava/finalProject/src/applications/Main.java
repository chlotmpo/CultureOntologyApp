/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applications;

import com.hp.hpl.jena.rdf.model.Model;
import tools.JenaEngine;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author DO.ITSUDPARIS
 */


public class Main {
	/**
	 * @param args
	 *            the command line arguments
	 */
	
	
	
	public static int MenuData() {
		int selection;
		Scanner sc = new Scanner (System.in);
		System.out.println("Hi. Here you can execute somes queries on the musee/library ontology. What query do you want to execute ?");
		System.out.println("Select your option : ");
		System.out.println("-----------------------\n");
		System.out.println("1 - List the instances of the geolocated POI");
		System.out.println("2 - List the name of all POI, for each one, display its city");
		System.out.println("3 - List the name of the trip that have Paris (or any other chosen city) as destination. ");
		System.out.println("4 - List the name of travellers older than 51 years ");
		System.out.println("5 - A query that contains at least 2 Optional Graph Patterns");
		System.out.println("6 - A query that  contains at least 2 alternatives and conjunctions");
		System.out.println("7 - A query that contains a CONSTRUCT query form ");
		System.out.println("8 - A query that contains a ASK query form");
		System.out.println("9 - A query that contains a DESCRIBE query form ");
		

		System.out.println("Your selected option is : ");
		selection = sc.nextInt();
		return selection;
	}
	
	public static int MenuPOI() {
		int selection;
		Scanner sc = new Scanner(System.in);
		System.out.println("What type of POI do you want to choose ?");
		System.out.println("Select your option : ");
		System.out.println("-----------------------\n");
		System.out.println("1 - The musees");
		System.out.println("2 - The public libraries");
		System.out.println("3 - The higher education libraries");
		
		System.out.println("Your selected option is : ");
		selection = sc.nextInt();
		return selection;
	}
	
	public static void QueryModel() {
		String NS = "";
		// lire le model a partir d'une ontologie
		Model model = JenaEngine.readModel("data/ontology.owl");
		if (model != null) {
			//lire le Namespace de l'ontologie
			NS = model.getNsPrefixURI("");
			
			//apply owl rules on the model
			Model owlInferencedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/owlrules.txt");
			// apply our rules on the owlInferencedModel
			Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(owlInferencedModel, "data/rules.txt");
			// query on the model after inference
			System.out.println(JenaEngine.executeQueryFile(inferedModel,
					"data/query.txt"));
		} else {
			System.out.println("Error when reading model from ontology");
		}
	}
	
	public static void WriteFile(String content) {
		try {
		      FileWriter myWriter = new FileWriter("data/query.txt");
		      myWriter.write(content);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	
	
	public static void main(String[] args) {
		int userSelected;
		String query;
		do {
			userSelected = MenuData();
			switch(userSelected) {
			case 1 : 
				System.out.println("Option 1 is selected.");
				System.out.println("Let's execute the following query : List the instances of the geolocated POI");
				int poi = MenuPOI();
				switch(poi) {
				case 1 : 
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?musee WHERE {\r\n"
							+ "			?musee rdf:type ex:Musee \r\n"
							+ "}";
					break;
				
				case 2 : 
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
						+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
						+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
						+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "SELECT ?publicLibrary WHERE {\r\n"
						+ "			?publicLibrary rdf:type ex:PublicLibrary \r\n"
						+ "}";
					break;
				case 3 : 
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?highereducationLibrary WHERE {\r\n"
							+ "			?highereducationLibrary rdf:type ex:HigherEducationLibrary \r\n"
							+ "}";
					break;
				default : 
					query = "";
					break;
				}
				WriteFile(query);
				QueryModel();
				break;
			case 2 : 
				System.out.println("Option 2 is selected.");
				System.out.println("List the name of all POI, for each one, display its city");
				poi = MenuPOI();
				switch(poi) {
				case 1 : 
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?name ?city WHERE {\r\n"
							+ "    ?musee rdf:type ex:Musee .\r\n"
							+ "    ?musee ex:hasName ?name . \r\n"
							+ "    ?musee ex:hasCommune ?city \r\n"
							+ "}";
					break;
				
				case 2 : 
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?name ?city WHERE {\r\n"
							+ "    ?publicLibrary rdf:type ex:PublicLibrary .\r\n"
							+ "    ?publicLibrary ex:hasName ?name . \r\n"
							+ "    ?publicLibrary ex:hasCommune ?city \r\n"
							+ "}";
					break;
				case 3 : 
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?name ?city WHERE {\r\n"
							+ "    ?highereducationLibrary rdf:type ex:HigherEducationLibrary .\r\n"
							+ "    ?highereducationLibrary ex:hasName ?name . \r\n"
							+ "    ?highereducationLibrary ex:hasCommune ?city \r\n"
							+ "}";
					break;
				default : 
					query = "";
					break;
				}
				WriteFile(query);
				QueryModel();
				break;
			case 3 : 
				System.out.println("Option 3 is selected.");
				System.out.println("List the name of the trip that have Paris (or any other chosen city) as destination.");
				query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
						+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
						+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
						+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "SELECT DISTINCT ?trip WHERE {\r\n"
						+ "    ?trip rdf:type ex:Trip .\r\n"
						+ "    ?destination ex:Arrival \"Paris\"\r\n"
						+ "}\r\n"
						+ "ORDER BY ?trip";		
				WriteFile(query);
				QueryModel();
				break;
			case 4 :
				System.out.println("Option 4 is selected.");
				break;
			case 5 :
				System.out.println("Option 5 is selected.");
				break;
			case 6 :
				System.out.println("Option 6 is selected.");
				break;
			case 7 :
				System.out.println("Option 7 is selected.");
				break;
			case 8 :
				System.out.println("Option 8 is selected.");
				break;
			case 9 :
				System.out.println("Option 9 is selected.");
				break;
			default :
				break;
			}
		}
		while(userSelected > 5 || userSelected < 1);
		
	
		
	}
}

