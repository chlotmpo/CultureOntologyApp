# Culture Ontology App
  
## :bulb: Project
The aim of this little project is to build an application that integrates different **point of interest** (POI) which are geospatial data from mutiple sources.

The 3 data sources were downloaded from the [public data plateform](https://www.data.gouv.fr/fr/) of France:
- **Museums** - https://www.data.gouv.fr/fr/datasets/liste-et-localisation-des-musees-de-france
- **Public Libraries** - https://www.data.gouv.fr/fr/datasets/adresses-des-bibliotheques-publiques-1
- **Higher Education Libraries** - https://www.data.gouv.fr/fr/datasets/bibliotheques-de-lenseignement-superieur

The ontology was modelled with [**Protégé**](!https://protege.stanford.edu/) then populated using rdf structure generated from the [json files](https://github.com/chlotmpo/CultureOntologyApp/tree/main/Datasets/Json)

SPARQL queries can be found [here](https://github.com/chlotmpo/CultureOntologyApp/blob/main/SPARQL-queries.md)

## :memo: How it works

This project is written in java and python:
- Java -> execute SPARQL queries, store the result in a json file and run the python script
- Python -> script loads the output.json file just generated and build a map corresponding to the query (the map is stored in [*map.html*](https://github.com/chlotmpo/CultureOntologyApp/blob/main/Map/map.html))

## :mag: How to use it
+ :one: Set up the environment:
    - Open the project in a Java IDE (Eclipse for example)
    - Make sure to add [**Jena**](http://www-inf.it-sudparis.eu/~gaaloulw/KM/Labs/Lab3/jena-2.6.2.zip) and [**json**](https://github.com/stleary/JSON-java) libraries to the project.
+ :two: Set up path in [*JenaEngine.java*](https://github.com/chlotmpo/CultureOntologyApp/blob/main/AppJava/finalProject/src/tools/JenaEngine.java):
    - [Line 140](https://github.com/chlotmpo/CultureOntologyApp/blob/main/AppJava/finalProject/src/tools/JenaEngine.java#L140): ```FileWriter("ABSOLUTE\PATH\TO\...Map\output.json");```
    - [Line 146]((https://github.com/chlotmpo/CultureOntologyApp/blob/main/AppJava/finalProject/src/tools/JenaEngine.java#L146): ```Runtime.getRuntime().exec(new String[] {"PATH\TO\python.exe", "ABSOLUTE\PATH\TO\...Map\map.py"});```
+ :three: Set up path in [*map.py*](https://github.com/chlotmpo/CultureOntologyApp/blob/main/Map/map.py):
    - [Line 5](https://github.com/chlotmpo/CultureOntologyApp/blob/main/Map/map.py#L5): ```data = json.load(open(r"ABSOLUTE\PATH\TO\...Map\output.json"))```
    - [Line 44](https://github.com/chlotmpo/CultureOntologyApp/blob/main/Map/map.py#L44): ```map.save(r"ABSOLUTE\PATH\TO\...Map\map.html")```
+ :four: Make sure [**folium**](https://python-visualization.github.io/folium/) library is installed
+ :five: Get back to Java IDE and run *main.java* file
+ :six: Select one POI query in console
+ :seven: Be patient and go to Map folder
+ :eight: Open *map.html* 
+ :nine: Enjoy :smile:

## Queries:
When running the *main.java* file, the following menu appears:
+ 1 - List the instances of the geolocated POI
    - 1 - The musees
    - 2 - The public libraries
    - 3 - The higher education libraries
+ 2 - List the name of all POI, for each one, display its city
    - 1 - The musees
    - 2 - The public libraries
    - 3 - The higher education libraries
+ 3 - List the name of the trip that have Paris (or any other chosen city) as destination. 
+ 4 - List the name of travellers older than 51 years 
+ 5 - A query that contains at least 2 Optional Graph Patterns
+ 6 - A query that contains at least 2 alternatives and conjunctions
+ 7 - A query that contains a CONSTRUCT query form
+ 8 - A query that contains a ASK query form
+ 9 - A query that contains a DESCRIBE query form
+ 10 - Find the musees in the city you want
+ 11 - Find the public libraries in the city you want
+ 12 - Find the higher education libraries in the city you want

## Example
Let's take the public libraries of Paris for example.
The user choose the Query 11 and enters `Paris`
The following SPARQL query is written in *query.txt*
```SPARQL
PREFIX ns: <http://www.owl-ontologies.com/unnamed.owl#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX ex: <http://www.semanticweb.org/mt181547/ontologies/2022/2/untitled-ontology-13#>

SELECT ?name ?adress ?zipcode ?lat ?long WHERE {
  ?pl rdf:type ex:PublicLibrary .
  ?pl ex:hasName ?name .
  ?pl ex:hasCommune 'Paris' .
  ?pl ex:hasAdress ?adress .
  ?pl ex:hasZipCode ?zipcode .
  ?pl ex:hasLatitude ?lat .
  ?pl ex:hasLongitude ?long
}
```
The java program execute the query and store the result in a json file. <br>
Then, the python script is runned and produces the following map
<p align="center">
  <img src="https://github.com/chlotmpo/CultureOntologyApp/blob/24f6b6494c92ba0d9104813d20eb1ef1dd506b99/Map/Paris%20public%20libraries.png" width="1000" />
</p>

## :clap: Contributors
+ TEMPO Chloé - [@chlotmpo](https://github.com/chlotmpo)
+ TRARIEUX Thibaud - [@thibaudtrx](https://github.com/thibaudtrx)
+ THIBAUT Matthieu - [@MatthieuThib](https://github.com/MatthieuThib)
