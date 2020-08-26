
fname = input("Enter file name: ")
if len(fname) < 1: fname = "waste.txt"

fh = open(fname)


print("There were", count, "lines in the file with From as the first word")
