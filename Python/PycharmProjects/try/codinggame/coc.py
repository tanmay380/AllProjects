# d=[]
# for i in range(int(input())):
#     word = input()
#     d.append((word[0]+':',word))
# for k,v in d:
#     print(k,v)
# s = input()
# t = 0
# s1 = ''
# for i in s:
#     if i.isalpha():
#         s1 += chr(ord(i) + t)
#     else:
#         s1 += i
#     t += 1
#
# print(s1)
# text = input()
# o = ""
# for k in range(len(text)):
#     l = text[k].lower()
#     p = l
#     if l in "abcdefghijklmnopqrstuvwxyz":
#         p = chr((ord(l) - ord('a') + k) % 26 + ord('a'))
#         u = text[k].isupper()
#         if u: p = p.upper()
#     o += p
# print(o)
# a,m = input().split(' ',1)
#
# i=1
# o=a[0]
#
# for x in m:
#     if x==' ':
#         o+=x+a[i]
#         i += 1
#     else:
#         o+=x
#
# print(o)
# a=input()
# t=''
# for i in range(len(a)//2+1):
#     if len(a)==len((t)):
#         break
#     t+=a[i]+a[len(a)-i -1]
#     # t+=a[len(a)-i -1]
#     print(t)
# print(t)
# x,n=map(int,input().split('x^'))
# print(str(x*n)+'x' if n-1==0 or n==2 else str(x*n)+'x'+'^'+str((n-1)))
#
for i in range(1,20):
    print(i)