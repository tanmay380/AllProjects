import os
os.system("attrib +h hello.txt")
fileexitst = os.path.isfile("hello.txt")
print(fileexitst)