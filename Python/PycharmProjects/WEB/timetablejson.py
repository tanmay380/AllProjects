import json

person = '''{
  "Monday": {
    "OOT": {
      "9AM-10AM": {},
      "4Pm-5Pm": {}
    },
    "WebProgT": {
      "11Am-12Pm": {}
    },
    "Inro": {
      "1Pm-2Pm": {}
    }
  }
}'''
store = json.loads(person)
print(store["OOT"])