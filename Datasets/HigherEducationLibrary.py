import json 

# Public library json 
with open('higher_education_library_json-ld.json') as higher_education_library_json:
    higher_education_library_json = json.load(higher_education_library_json)

jsonfile = open('higher_education_library_json-ld-cleaned.json', 'w')


i = 0

for key,value in higher_education_library_json.items():
    
    if i == 0:
        jsonfile.write(json.dumps({key:value}, indent=4)[:-1] + ',')
    else:
        if('geometry' in value.keys()):
            if 'com_nom' in value['fields'].keys(): value['Commune'] = value['fields']['com_nom'].replace("'", " ")
            value['Nom'] = value['fields']['nomlong'].replace("'", " ")
            if 'adresse_adresse' in value['fields'].keys(): value['Adresse'] = value['fields']['adresse_adresse'].replace("'", " ")
            if 'adresse_codepostal' in value['fields'].keys(): value['Code Postal'] = value['fields']['adresse_codepostal']
            value['Latitude'] = value['geometry']['coordinates'][1]
            value['Longitude'] = value['geometry']['coordinates'][0]
            
            del value['fields']
            del value['datasetid']
            del value['geometry']
            del value['record_timestamp']
            

            jsonfile.write(json.dumps({key:value}, indent=4, ensure_ascii=False)[1:-1] + ',')
    i = i + 1

jsonfile.write('}')

jsonfile.close()
