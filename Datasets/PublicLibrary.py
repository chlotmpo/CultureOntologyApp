import json 

# Public library json 
with open('public_library_json-ld.json') as public_library_json:
    public_libraries = json.load(public_library_json)

jsonfile = open('public_library_json-ld-cleaned.json', 'w')


i = 0

for key,value in public_libraries.items():
    if i == 0:
        jsonfile.write(json.dumps({key:value}, indent=4)[:-1] + ',')
    else:
        if('geometry' in value.keys()):
            if 'adresse_ville' in value['fields'].keys(): value['Commune'] = value['fields']['adresse_ville'].replace("'", " ")
            value['Nom'] = value['fields']['libelle1'].replace("'", " ")
            if 'voie' in value['fields'].keys(): value['Adresse'] = value['fields']['voie'].replace("'", " ")
            value['Code Postal'] = value['fields']['cp']
            value['Latitude'] = value['geometry']['coordinates'][1]
            value['Longitude'] = value['geometry']['coordinates'][0]
            
            del value['fields']
            del value['datasetid']
            del value['geometry']
            del value['record_timestamp']
            

            jsonfile.write(json.dumps({key:value}, indent=4, ensure_ascii=False)[1:-1] + ',')
    i = i + 1

jsonfile.write('}')