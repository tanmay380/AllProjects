import random

comp_win = 0
play_win = 0

play = 0

options = ["s", "p", "sc"]
s, p, sc = options[0], options[1], options[2]

while play < 9:
    comp_opt = random.choice(options)
    print(comp_opt)
    play_cho = input("Enter your choice:-\n 1.\ts for Stone\n 2.\tp for paper\n 3.\tsc for Scissors\n :-> ")
    if play_cho == comp_opt:
        print("Game is Draw")
    # when user enters S
    elif play_cho == 's' and comp_opt == p:  # stone paper
        print(f'\n{comp_opt} wins')
        comp_win += 1

    elif play_cho == 's' and comp_opt == sc:  # stone scissors
        print(f'\n{play_cho} wins')
        play_win += 1
        # when user enters Paper
    elif play_cho == 'p' and comp_opt == s:  # paper stone
        print(f'\n{play_cho} wins')
        play_win += 1

    elif play_cho == 'p' and comp_opt == sc:  # paper scissors
        print(f'\n{comp_opt} wins')
        comp_win += 1

        # when users enter scissors
    elif play_cho == 'sc' and comp_opt == s:  # scissor stone
        print(f'\n{comp_opt} wins')
        comp_win += 1

    elif play_cho == 'sc' and comp_opt == p:  # scissor apper
        print(f'\n{play_win} wins')
        play_win += 1
    else:
        print("WRONG INPUT")
        continue
    play += 1
    print(f'{9 - play} chances left out of 9 chances')

if comp_win > play_win:
    print("COMPUTER WINS")
elif comp_win < play_win:
    print("PLAYER WINS")
elif comp_win == play_win:
    print("GAME DRAW")
print(f"your point is {play_win} and computer point is {comp_win}")
