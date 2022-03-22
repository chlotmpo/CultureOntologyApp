# Culture Ontology App
  
## :bulb: Project
The aim of this little project is to build an application that integrates different **point of interest** (POI) which are geospatial data from mutiple sources, including dynamic data.

The 3 data sources were downloaded from the [public data plateform](https://www.data.gouv.fr/fr/) of France:
- **Museums** - https://www.data.gouv.fr/fr/datasets/liste-et-localisation-des-musees-de-france
- **Public Libraries** - https://www.data.gouv.fr/fr/datasets/adresses-des-bibliotheques-publiques-1
- **Higher Education Libraries** - https://www.data.gouv.fr/fr/datasets/bibliotheques-de-lenseignement-superieur

The ontology was modelled with [**Protégé**](!https://protege.stanford.edu/) then populated using rdf structure generated from the [json files](https://github.com/chlotmpo/CultureOntologyApp/tree/main/Datasets/Json)

SPARQL queries can be found [here](https://github.com/chlotmpo/CultureOntologyApp/blob/main/SPARQL-queries.md)

## :memo: How it works

This project is written in java and python:
- Java -> execute SPARQL queries, store the result in a json file and run the python script
- Python -> script loads the output.json file just generated and build a map corresponding to the query
The map is stored in [*map.html*](https://github.com/chlotmpo/CultureOntologyApp/blob/main/Map/map.html)


## :mag: How to use it

+ :one: Set up the environment:
    - Open the project in a Java IDE
    - Make sure to add [**Jena**](http://www-inf.it-sudparis.eu/~gaaloulw/KM/Labs/Lab3/jena-2.6.2.zip) and [json](https://github.com/stleary/JSON-java) libraries to the project.
+ :two: Set up path in [JenaEngine.java](https://github.com/chlotmpo/CultureOntologyApp/blob/main/AppJava/finalProject/src/tools/JenaEngine.java):
    - Line 140: ```FileWriter("PATH\TO\ ... Map\outputjena.json");```
    - Line 146: ```Runtime.getRuntime().exec(new String[] {"PATH\TO\python.exe", "PATH\TO\ ... \Map\map.py"});```
+ Get back to Java IDE and run *main.java* file
+ Select query in console
+ Be patient and go to Map folder
+ open *map.html* and enjoy :smile:

## :clap: Contributors
TEMPO Chloé, TRARIEUX Thibaud & THIBAUT Matthieu