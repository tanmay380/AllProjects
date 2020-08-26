def listBooks():

    global bookname
    global writer
    global price
    global quantity

    bookname = []
    writer = []
    price = []
    quantity = []

    with open("stock.txt", "r") as f:
        lines = f.readlines()
        lines = [x.strip("\n") for x in lines]
        for i in range(len(lines)):
            ind = 0
            for a in lines[i].split(","):
                if (ind == 0):
                    bookname.append(a)
                elif (ind == 1):
                    writer.append(a)
                elif (ind == 2):
                    quantity.append(a)
                elif (ind == 3):
                    price.append(a.strip('$'))
                ind += 1
