import json 


# Let's have the json data from the 3 ressources 
# Musees json
with open('musee.json') as musees_json:
    musees = json.load(musees_json)

# Public library json 
with open('adress-public-library.json') as public_library_json:
    public_libraries = json.load(public_library_json)

# Higher education library
with open('higher-education-library.json') as higher_education_library_json:
    higher_education_libraries = json.load(higher_education_library_json)



# Now let's recover the json context that we have created for these 3 ressources
# Musees json context 
with open('context_musee.json') as context_musees_json:
    context_musees = json.load(context_musees_json)

# Public library json context 
with open('context_public_library.json') as context_public_library_json:
    context_public_library = json.load(context_public_library_json)

# Higher education library json context 
with open('context_HigherEducationLibrary.json') as context_higher_education_library_json:
    context_higher_education_library = json.load(context_higher_education_library_json)

# Let's create a new file in the format JSON-LD for the 3 ressources 
# Musees 
jsonfile = open('musee_json-ld.json', 'w')
jsonfile.write(json.dumps(context_musees)[:-1] + ', ')
for musee in musees:
    index = musees.index(musee)
    key = '"' + 'musee_' + str(index) + '"'
    if index != len(musees) - 1 : element = str(key) + ' : ' + json.dumps(musee) + ', '
    else : element = str(key) + ' : ' + json.dumps(musee)
    #jsonString = json.dumps(element)
    jsonfile.write(element)
jsonfile.write('}')
jsonfile.close()

# Public library
jsonfile = open('public_library_json-ld.json', 'w')
jsonfile.write(json.dumps(context_public_library)[:-1] + ', ')
for pl in public_libraries:
    index = public_libraries.index(pl)
    key = '"' + 'public_library_' + str(index) + '"'
    if index != len(public_libraries) - 1 : element = str(key) + ' : ' + json.dumps(pl) + ', '
    else : element = str(key) + ' : ' + json.dumps(pl)
    #jsonString = json.dumps(element)
    jsonfile.write(element)
jsonfile.write('}')
jsonfile.close()

# Higher education library 
jsonfile = open('higher_education_library_json-ld.json', 'w')
jsonfile.write(json.dumps(context_higher_education_library)[:-1] + ', ')
for hel in higher_education_libraries:
    index = higher_education_libraries.index(hel)
    key = '"' + 'public_library_' + str(index) + '"'
    if index != len(higher_education_libraries) - 1 : element = str(key) + ' : ' + json.dumps(hel) + ', '
    else : element = str(key) + ' : ' + json.dumps(hel)
    #jsonString = json.dumps(element)
    jsonfile.write(element)
jsonfile.write('}')
jsonfile.close()