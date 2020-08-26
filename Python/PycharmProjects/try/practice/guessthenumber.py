n=18

guesses=1

while(guesses<=9):
    number=int(input("Enter the number\t"))
    if(number<18):
        print("Original number is larger than this\n")
    elif number>18:
        print("Original number is smaller thn this\n")
    else:
        print("Number Guessed")
        break
    print("No. of GUesses left", 9 - guesses,"\n")
    guesses=guesses+1   

    if(guesses==9):
        print("Game Over")
