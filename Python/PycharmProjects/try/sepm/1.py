import csv

with open('imp.txt', 'r') as file:
    file_reader = csv.reader(file)
    t = 0
    i=0
    g = input('Do you have all the same vlaue of factor:-> y,n ')
    if g == 'y':
        lvl = input("Enter the value, l,av,h:-> ")
        # for i in range(5):
        #     ei = input("enter ei:-> i,o,q,f,if")
        print("enter the values of ei,eo,eq,ilf,eif respectively... one at a time")
        for row in file_reader:
            # if row[0] == ei:

            while i<5:
                ei=int(input('value:-> '))
                if lvl == 'l':
                    t += ei*int(row[1])
                elif lvl == 'av':
                    t += ei*int(row[2])
                elif lvl == 'h':
                    t += ei*int(row[3])
                i+=1
                break
    else:

        print("enter the values of ei,eo,eq,ilf,eif respectively... one at a time")
        for row in file_reader:
            while i < 5:
                ei = int(input('value:-> '))
                lvl = input("Enter the value, l,av,h:-> ")
                if lvl == 'l':
                    t += ei * int(row[1])
                elif lvl == 'av':
                    t += ei * int(row[2])
                elif lvl == 'h':
                    t += ei * int(row[3])
                i += 1
                print(t)
                break
    file.seek(0)
print(t)
