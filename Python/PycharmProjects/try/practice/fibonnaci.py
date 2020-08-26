# c=input()
# o=''
# try:
#  i=c.index('write')+6
#  while c[i]!='.':o+=c[i];i+=1
# except:
#  print('Syntax Error')
#  exit()
# t='times'
# if t in c:
#  c=c.split()
#  print(c)
#  n=int(c[c.index(t)-1])
# else:
#  n=1
# for _ in' '*n:print(o)
# e=int(input())
# t=[]
# for i in input().split():
#     t.append(i)
'''86 => 8^2 + 6^2 = 64 + 36 = 100
100 => 1^2 + 0^2 + 0^2 = 1'''
hel = {1: '.----', 2: '..---', 3: '...--', 4: '....-', 5: '.....', 6: '-....', 7: '--...', 8: '---..', 9: '----.',
       0: '.-----'}
mn = input()
t = ''
if len(mn) == 10:
    for i in mn:
        if int(i) in hel:
            t += hel[int(i)]
            t += " "
print(t)
