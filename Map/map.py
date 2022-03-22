#imports
import folium
import json

#load data
data= json.load(open("output.json"))
data = data['results']['bindings']

# convert into geojson format
from sys import argv
from os.path import exists

geojson = {
    "type": "FeatureCollection",
    "features": [
    {
        "type": "Feature",
        "geometry" : {
            "type": "Point",
            "name": d["name"]["value"],
            "coordinates": [float(d["lat"]["value"]), float(d["long"]["value"])],
            },
        "properties" : d,
     } for d in data]
}
jsonfile = open('query-geo.json', 'w')
jsonfile.write(json.dumps(geojson, indent=4, ensure_ascii=False))
jsonfile.close()

#create map
map = folium.Map(location = [48.856578, 2.351828], zoom_start = 12)

#fill the map with markers
for x in geojson["features"]:
    geo = x["geometry"]
    folium.Marker(geo["coordinates"]).add_to(map)

print(map)

#save map as html
map.save(r'C:\Users\mt181547\OneDrive - De Vinci\Bureau\ttt\map.html')