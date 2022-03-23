import folium
import json

# load data
data = json.load(open(r"ABSOLUTE\PATH\TO\...Map\output.json"))
data = data['results']['bindings']

# convert into geojson format
geojson = {
    "type": "FeatureCollection",
    "features": [
    {
        "type": "Feature",
        "geometry" : {
            "type": "Point",
            "name": element["name"]["value"].replace('Ã©', 'é').replace('Ã¨', 'è').capitalize(),
            "coordinates": [float(element["lat"]["value"]), float(element["long"]["value"])],
            },
        "properties" : element,
     } for element in data]
}

# create map
zoom_location = geojson["features"][0]["geometry"]["coordinates"]
zoom_start = 12

map = folium.Map(
    location = zoom_location,
    zoom_start = zoom_start)

# custom map tiles
folium.TileLayer('cartodbpositron').add_to(map)

# fill the map with markers
for item in geojson["features"]:
    geo = item["geometry"]
    folium.Marker(
        location = geo["coordinates"], 
        tooltip = geo["name"], 
        icon = folium.Icon(color='darkblue',icon='book',prefix = 'fa')
        ).add_to(map)

# save map as html
map.save(r"ABSOLUTE\PATH\TO\...Map\map.html")