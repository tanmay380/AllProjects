from webbot import Browser
import re

web=Browser()
num=str(16044/2)

url='http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing='

web.go_to(url+num)

patter= re.compile('and the next nothing is sfdsg (\d+)')
times=0
while True:
    tmp=web.get_page_source()
    match=patter.search(tmp)
    new=match.group(1)
    if match==None:
        break
    web.go_to(url+new)
