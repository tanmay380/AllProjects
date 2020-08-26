x = 100


def func1():
    x = 20

    def func2():
        global x
        x = 88
        print("sdfsdf",x)

    print("Before calling func2", x)
    func2()
    print("after calling func2", x)


func1()
print(x)
