# v1=2
# v4=4
#
# if v1<v4:
#     print("hello")
# else:
#     print("Bhag 🤦‍♀")\
age=int(input("Enter your age:- "))
if age>18 and age<100:
    print("You can drive")
elif age<18 and age>7:
    print("You can not drive")
elif age==18:
    print("You'll have to go for a driving test")
else:
    print("Enter proper age")