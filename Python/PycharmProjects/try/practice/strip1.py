# handle = open("waste.txt")
# # read1 = handle.readlines()
# store = {}
# got = []
# count = 0
# for x in handle:
#    # if x.startswith("From") and len(x.split()) > 2:
#         # got.append((x.split())[1])
#         words2 = x.split()
#
# for email in words2:
#     store[email] = store.get(email, 0) + 1
# #
# # bigcount=None
# # bigword=None
# #
# # for k,v in store.items():
# #     if bigcount is None or v>bigcount:
# #         bigcount=v
# #         bigword=k
# print(words2)
# print(store)
"""new code for tuples"""
# name = input("Enter file:")
# if len(name) < 1: name = "waste.txt"
# handle = open(name)
# words = {}
# for lines in handle:
#     if lines.startswith("From "):
#         lines = lines.split()
#         lines = lines[5]
#         lines = lines[:2]
#         words[lines] = words.get(lines, 0) + 1
# lst=[]
# for k,v in words.items():
#     lst.append((v,k))
# for k,v in sorted(words.items()):
#     print(k,v)

"""tuples code"""
try:
    fh = open("waste.txt")
except:
    print('Invalid Input.')
    quit()
l = list()
d = dict()
for line in fh:
    if not line.startswith('From '):
        continue
    line = line.rstrip()
    x = line.split()
    time = (x[5])
    stime = time.split(':')
    l.append(stime[0])
    for count in l:
        d[count] = d.get(count, 0) + 1
        for k, v in sorted(d.items()):
            tupple = (k, v)
            l.append(tupple)
            print(k, v)
quit()
