largest = 0
smallest = 0
store = []
while True:
    num = input("Enter:-> ")
    if (num == 'done'): break
    n = int(num)

    store.append(n)

    for number in store:
        largest = max(largest, n)
        if (smallest == 0):
            smallest = n
        else:
            smallest = min(smallest, number)

print("Maximum", largest)
print("Minimum", smallest)
# largest = None
# smallest = None
#
# while True:
#     try:
#         num = input("Enter a number: ")
#         if num == 'done':
#             break;
#         n = int(num)
#         if largest<n or largest==None:
#             largest=n
#     except Exception as e:
#         print (e)
#
# print ("Maximum number is ", largest)
# print ("Minimum number is ", smallest)
