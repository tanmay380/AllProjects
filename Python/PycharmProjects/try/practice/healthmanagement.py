def gettime():
    import datetime
    return datetime.datetime.now().replace(microsecond=0)


client_list = {1: 'Harry', 2: 'me', 3: 'You'}
lock_list = {1: 'Excersise', 2: 'Diet'}

try:
    for k, v in client_list.items():
        print(f'Press {k} for {v}')
    client_name = int(input())

    print("Selected Client: ", client_list[client_name])

    print("What u want to do")
    print("1 for lock \n2 for retrive")
    op = int(input())

    if op == 1:
        for k,v in lock_list.items():
            print(f'Press {k} to lock {v}')
        lock=int(input())

        with open(client_list[client_name]+"_"+lock_list[lock]+".txt","a") as f:
            k='y'
            while k is not 'n':
                print("Enter ",lock_list[lock])
                mytext=input()
                f.write("["+str(gettime())+"]:\t"+mytext.capitalize()+'\n')
                k=input("ADD MORE y/n \t")
                continue
            print("Successfully written data")
    elif op==2:
        for k,v in lock_list.items():
            print(f'Press {k} to retrieve {v}')
        lock=int(input())
        with open(client_list[client_name]+'_'+lock_list[lock]+'.txt','r') as f:
            for lines in f.readlines():
                print(lines,end='')


except Exception as e:
    print("Data Doesn't exist")

