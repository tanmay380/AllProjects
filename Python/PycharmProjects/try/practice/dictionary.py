string = {}
countlist=['a','b','c','d','a','a','a','a','b']
count = 0
for al in countlist:
    if al in string:
        string[al]=string[al]+1
    else:
        string[al]=1
print(string)
