import json

word = '''[
{
"word": "actions speak louder than words",
"phonetics": [
{}
],
"meanings": [
{
"definitions": [
{
"definition": "what someone actually does means more than what they say they will do.",
"synonyms": [],
"antonyms": []
}
]
}
]
}
]'''

jon = json.loads(word)
print(type(jon))
print(json.dumps(jon[0]['word']))

