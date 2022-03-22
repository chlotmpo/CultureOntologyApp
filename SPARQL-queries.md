# Write SPARQL queries to response to the following:
## a. List the instances of the geolocated POI 
- Instances of the musees 
 ``` SPARQL
SELECT ?musee WHERE {
    ?musee rdf:type ex:Musee
}
 ```
 - Instances of the public libraries 
``` SPARQL
SELECT ?pl WHERE {
    ?pl rdf:type ex:PublicLibrary
}
```
- Instances of the higher education libraries
``` SPARQL
SELECT ?hel WHERE {
    ?hel rdf:type ex:HigherEducationLibrary
}
```

## b. List the name of all POI, for each one, display its city
- For the musees 
``` SPARQL
SELECT ?name ?city WHERE {
    ?musee rdf:type ex:Musee .
    ?musee ex:hasName ?name . 
    ?musee ex:hasCommune ?city
}
```
- For the public libraries 
``` SPARQL
SELECT ?name ?city WHERE {
    ?pl rdf:type ex:PublicLibrary .
    ?pl ex:hasName ?name . 
    ?pl ex:hasCommune ?city
}
```
- For the higher education libraries
``` SPARQL
SELECT ?name ?city WHERE {
    ?hel rdf:type ex:HigherEducationLibrary .
    ?hel ex:hasName ?name . 
    ?hel ex:hasCommune ?city
}
```

## c. List the name of the trip that have Paris (or any other chosen city) as destination. 
``` SPARQL
SELECT ?trip WHERE {
    ?trip rdf:type ex:Trip
    ?destination ex:Arrival "Paris"
}
```
## d. List the name of travellers older than 51 years 
``` SPARQL
SELECT ?name ?age WHERE {
    ?traveller rdf:type ex:Traveller .
    ?traveller ex:Name ?name . 
    ?traveller ex:Age ?age . 
    FILTER (?age>51)
}
```
# Propose 5 SPARQL queries: 
## a. A query that contains at least 2 Optional Graph Patterns
``` SPARQL


```
## b. A query that  contains at least 2 alternatives and conjunctions 
``` SPARQL
SELECT ?trip WHERE {
    {?trip ex:Arrival "Paris"}
    UNION
    {?trip ex:Arrival "Lille"}
}
```
## c. A query that contains a CONSTRUCT query form 
``` SPARQL
CONSTRUCT {
    ?trip rdf:type ex:Trip
} WHERE {
    ?trip ex:Departure "Toulouse" .
    ?trip ex:Arrival "Reims" .
    ?trip ex:Duration 230
}
```
## d. A query that contains a ASK query form 
``` SPARQL
ASK {
    ?traveller ex:Age 18
}
```
## e. A query that contains a DESCRIBE query form 
``` SPARQL
DESCRIBE ?musee WHERE {
    ?musee ex:hasName "musée de la poterie" 
}
```

# Define some SWRL rules