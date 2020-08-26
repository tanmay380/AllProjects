nor = int(input("Enter No. Of Rows:-> "))
boolean = bool(int(input("0 or 1")))
# print(boolean)

if (boolean == True):
    for i in range(0, nor ):
        for j in range(0, i+1 ):
            print("*", end=" ")
        print()
else:
    for i in range(nor,0,-1):
        for j in range(0, i):
            print("*", end=" ")
        print()
