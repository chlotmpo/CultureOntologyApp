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
		System.out.println("6 - A query that contains at least 2 alternatives and conjunctions");
		System.out.println("7 - A query that contains a CONSTRUCT query form");
		System.out.println("8 - A query that contains a ASK query form");
		System.out.println("9 - A query that contains a DESCRIBE query form");
		System.out.println("10 - Find the musees in the city you want");
		System.out.println("11 - Find the public libraries in the city you want");
		System.out.println("12 - Find the higher education libraries in the city you want");
		System.out.println("13 - Display the list of travel achieved by the traveller you want");
		System.out.println("14 - Observe the list of the POI visited by the traveller you want");


		

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
	
	public static void QueryModel() throws IOException {
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
		      System.out.println("Query wrote in query.txt.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static String SelectCity() {
		String city;
		Scanner sc2 = new Scanner (System.in);
		System.out.println("Your selected city is : ");
		city = sc2.next();
		return city;
	}
	
	
	public static String SelectName() {
		String name;
		System.out.println("Here are all the name of the traveller registered : ");
		System.out.println("1 - Paul");
		System.out.println("2 - Marie");
		System.out.println("3 - Alain");
		System.out.println("4 - Jeanne");
		System.out.println("5 - Marin");
		System.out.println("6 - Claude");
		System.out.println("7 - Kevin");
		System.out.println("8 - Lila");
		System.out.println("9 - Benjamin");
		System.out.println("10 - Ginette");
		Scanner sc4 = new Scanner (System.in);
		System.out.println("Your selected traveller is : (enter name with first letter in majuscule)");
		name = sc4.next();
		return name;
	}
	
	
	public static void main(String[] args) throws IOException {
		int response = 1;
		while (response == 1){
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
								+ "SELECT ?musee ?name ?lat ?long WHERE {\r\n"
								+ "			?musee rdf:type ex:Musee .\r\n"
								+ "			?musee ex:hasName ?name .\r\n"
								+ "			?musee ex:hasLatitude ?lat .\r\n"
								+ "			?musee ex:hasLongitude ?long \r\n"
								+ "}";
						break;
					
					case 2 : 
						query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?publicLibrary ?name ?lat ?long WHERE {\r\n"
							+ "			?publicLibrary rdf:type ex:PublicLibrary .\r\n"
							+ "			?publicLibrary ex:hasName ?name .\r\n"
							+ "			?publicLibrary ex:hasLatitude ?lat .\r\n"
							+ "			?publicLibrary ex:hasLongitude ?long \r\n"
							+ "}";
						break;
					case 3 : 
						query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
								+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
								+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
								+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
								+ "\r\n"
								+ "\r\n"
								+ "SELECT ?highereducationLibrary ?name ?lat ?long WHERE {\r\n"
								+ "			?highereducationLibrary rdf:type ex:HigherEducationLibrary .\r\n"
								+ "			?highereducationLibrary ex:hasName ?name . \r\n"
								+ "			?highereducationLibrary ex:hasLatitude ?lat .\r\n"
								+ "			?highereducationLibrary ex:hasLongitude ?long \r\n"
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
								+ "SELECT ?name ?city ?lat ?long WHERE {\r\n"
								+ "    ?musee rdf:type ex:Musee .\r\n"
								+ "    ?musee ex:hasName ?name . \r\n"
								+ "    ?musee ex:hasLatitude ?lat . \r\n"
								+ "    ?musee ex:hasLongitude ?long . \r\n"
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
								+ "SELECT ?name ?city ?lat ?long WHERE {\r\n"
								+ "    ?publicLibrary rdf:type ex:PublicLibrary .\r\n"
								+ "    ?publicLibrary ex:hasName ?name . \r\n"
								+ "    ?publicLibrary ex:hasLatitude ?lat . \r\n"
								+ "    ?publicLibrary ex:hasLongitude ?long . \r\n"
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
								+ "SELECT ?name ?city ?lat ?long WHERE {\r\n"
								+ "    ?highereducationLibrary rdf:type ex:HigherEducationLibrary .\r\n"
								+ "    ?highereducationLibrary ex:hasName ?name . \r\n"
								+ "    ?highereducationLibrary ex:hasLatitude ?lat . \r\n"
								+ "    ?highereducationLibrary ex:hasLongitude ?long . \r\n"
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
					System.out.println("List the name of travellers older than 51 years ");
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?name ?age WHERE {\r\n"
							+ "    ?traveller rdf:type ex:Traveller .\r\n"
							+ "    ?traveller ex:Name ?name . \r\n"
							+ "    ?traveller ex:Age ?age . \r\n"
							+ "    FILTER (?age>51)\r\n"
							+ "}";		
					WriteFile(query);
					QueryModel();
					break;
				case 5 :
					System.out.println("Option 5 is selected.");
					System.out.println("A query that contains at least 2 Optional Graph Patterns");
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?name ?adress ?age WHERE {\r\n"
							+ "    ?traveller rdf:type ex:Traveller .\r\n"
							+ "    ?traveller rdf:type ?name .\r\n"
							+ "    OPTIONAL { ?traveller ex:Adress ?adress }\r\n"
							+ "    OPTIONAL { ?traveller ex:Age ?age } \r\n"
							+ "}";		
					WriteFile(query);
					QueryModel();
					break;
				case 6 :
					System.out.println("Option 6 is selected.");
					System.out.println("A query that contains at least 2 alternatives and conjunctions");
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?trip WHERE {\r\n"
							+ "    {?trip ex:Arrival \"Paris\"}\r\n"
							+ "    UNION\r\n"
							+ "    {?trip ex:Arrival \"Lille\"}\r\n"
							+ "}";		
					WriteFile(query);
					QueryModel();
					break;
				case 7 :
					System.out.println("Option 7 is selected.");
					System.out.println("A query that contains a CONSTRUCT query form ");
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "CONSTRUCT {\r\n"
							+ "    ?trip rdf:type ex:Trip\r\n"
							+ "} WHERE {\r\n"
							+ "    ?trip ex:Departure \"Toulouse\" .\r\n"
							+ "    ?trip ex:Arrival \"Reims\" .\r\n"
							+ "    ?trip ex:Duration 230\r\n"
							+ "}";		
					WriteFile(query);
					QueryModel();
					break;
				case 8 :
					System.out.println("Option 8 is selected.");
					System.out.println("A query that contains a ASK query form");
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "ASK {\r\n"
							+ "    ?traveller ex:Age 18\r\n"
							+ "}";		
					WriteFile(query);
					QueryModel();
					break;
				case 9 :
					System.out.println("Option 9 is selected.");
					System.out.println("A query that contains a DESCRIBE query form");
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "DESCRIBE ?musee WHERE {\r\n"
							+ "    ?musee ex:hasName \"musée de la poterie\" \r\n"
							+ "}";		
					WriteFile(query);
					QueryModel();
					break;
				case 10 : 
					System.out.println("Option 10 is selected.");
					System.out.println("Find the musees in the city you want");
					String city = SelectCity();
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?name ?adress ?zipcode ?lat ?long WHERE {\r\n"
							+ "  ?musee rdf:type ex:Musee .\r\n"
							+ "  ?musee ex:hasName ?name .\r\n"
							+ "  ?musee ex:hasCommune " + "'" + city + "'" + " .\r\n"
							+ "  ?musee ex:hasAdress ?adress .\r\n"
							+ "  ?musee ex:hasZipCode ?zipcode .\r\n"
							+ "  ?musee ex:hasLatitude ?lat .\r\n"
							+ "  ?musee ex:hasLongitude ?long\r\n"
							+ "}";
					WriteFile(query);
					QueryModel();		
					break;
				case 11 :
					System.out.println("Option 11 is selected.");
					System.out.println("Find the public libraries in the city you want");
					city = SelectCity();
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?name ?adress ?zipcode ?lat ?long WHERE {\r\n"
							+ "  ?pl rdf:type ex:PublicLibrary .\r\n"
							+ "  ?pl ex:hasName ?name .\r\n"
							+ "  ?pl ex:hasCommune " + "'" + city + "'" + " .\r\n"
							+ "  ?pl ex:hasAdress ?adress .\r\n"
							+ "  ?pl ex:hasZipCode ?zipcode .\r\n"
							+ "  ?pl ex:hasLatitude ?lat .\r\n"
							+ "  ?pl ex:hasLongitude ?long\r\n"
							+ "}";
					WriteFile(query);
					QueryModel();		
					break;
				case 12 : 
					System.out.println("Option 12 is selected.");
					System.out.println("Find the higher education libraries in the city you want");
					city = SelectCity();
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?name ?adress ?zipcode ?lat ?long WHERE {\r\n"
							+ "  ?hel rdf:type ex:HigherEducationLibrary .\r\n"
							+ "  ?hel ex:hasName ?name .\r\n"
							+ "  ?hel ex:hasCommune " + "'" + city + "'" + " .\r\n"
							+ "  ?hel ex:hasAdress ?adress .\r\n"
							+ "  ?hel ex:hasZipCode ?zipcode .\r\n"
							+ "  ?hel ex:hasLatitude ?lat .\r\n"
							+ "  ?hel ex:hasLongitude ?long\r\n"
							+ "}";
					WriteFile(query);
					QueryModel();	
					
					break;
				case 13 : 
					System.out.println("Option 13 is selected.");
					System.out.println("Display the list of travel achieved by the traveller you want");
					String name = SelectName();
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT ?departure ?arrival ?duration WHERE {\r\n"
							+ "  ?traveller rdf:type ex:Traveller .\r\n"
							+ "  ?traveller ex:Name " + "'" + name + "'" + " .\r\n"
							+ "  ?traveller ex:Travel ?travel .\r\n"
							+ "  ?travel ex:Departure ?departure .\r\n"
							+ "  ?travel ex:Arrival ?arrival .\r\n"
							+ "  ?travel ex:Duration ?duration \r\n"
							+ "}";
					WriteFile(query);
					QueryModel();	
					
					break;
				case 14 : 
					System.out.println("Option 14 is selected.");
					System.out.println("Observe the list of the POI visited by the traveller you want");
					name = SelectName();
					query = "PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>\r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
							+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n"
							+ "PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "SELECT DISTINCT ?name ?city ?lat ?long WHERE {\r\n"
							+ "  ?traveller rdf:type ex:Traveller .\r\n"
							+ "  ?traveller ex:Name " + "'" + name + "'" + " .\r\n"
							+ "  ?traveller ex:Visit ?poi .\r\n"
							+ "  ?poi ex:hasName ?name .\r\n"
							+ "  ?poi ex:hasCommune ?city .\r\n"
							+ "  ?poi ex:hasLatitude ?lat .\r\n"
							+ "  ?poi ex:hasLongitude ?long . \r\n"
							+ "}";
					WriteFile(query);
					QueryModel();			
					break;
				default :
					break;
				}
			}
			while(userSelected > 14 || userSelected < 1);
			System.out.println("Do you want to test another query ?");
			System.out.println("1 - Yes");
			System.out.println("2 - No");
			Scanner sc3 = new Scanner (System.in);
			response = sc3.nextInt();			
		}
		System.out.println("Good bye!");
	}
}