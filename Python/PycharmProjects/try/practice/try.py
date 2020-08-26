def getKey(val):
    for value in dic.values():
        if val == value:
            return value
        else:
            return "Not Found"


dic = {"set": "A group of predefined data",
       "Mutable": "Which can be changed",
       "Immutable": "It cannot be changed",
       "Immutable2": "It",
       "Hello": 12}
key_list = list(dic.keys())
val_list = list(dic.values())

# print("Choices are tjus jhu s","Enter your choice",)
# print(key_list[val_list.index(input())])

print(getKey(12))

# print(dic.items(12))
