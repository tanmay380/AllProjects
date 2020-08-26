# 45*3=555, 56+9=77, 56/6=4

operator= input("Enter the operator ")
num1 = int(input("Enter one number "))
num2 = int(input("Enter second number "))

if (num1 == 45 and num2 == 3 and operator=="*"):
    print("The anser is:-", "555")
elif (num1 == 45 and num2 == 3 and operator=="+"):
    print("The anser is:-", "77")
elif(num1==45 and num2==3and operator=="/"):
    print("The anser is:-","4" )
else:
    print("The answer is",num1(operator)+num2)
