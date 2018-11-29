#!/usr/bin/python
from base64 import b64decode as d

f = open('./see', 'r')

for i in range(1009):
    f.readline()
x = f.readline()[102:-len('codecs.decode(love, joy)magicintlensumiterlongnameopenreadreprsitelevelrangeformatlocalsxrange__all____cmp____doc__compileglobalsinspect__dict____exit____file____iter____main____name____path__exc_typefromlist__class____enter__bytearrayexc_value__import____module____delattr____getattr____setat')]


x1 = x.find('base64rot13joycodecs.decode(destiny, joy)striposgod<string>')
x1 = x[:x1]


x2_start = x.find('TVZKelUyNU9hVkpHV21oVmJGcDNZMnhzVjFaWWFHcGlWVFZIV1ZWYVlWUnNXWGxoU0d4WFlXdEtjbFY2Um1Gak1YQkpWRzFvVTJKclNsbFdWM2hoWkRKSmVGcEdaRmRpVlZwWFZGWmFkMWRHVl')
x2_end = x.find('exectrust \t<module>')

x2 = x[x2_start:x2_end]


x3_start = x.find('rUIeFIWAI3OVBIATrHSMExqCn0xjEIqSFwIhJwA5G0IVrHgnFJAJFQSAIycFZHySrTqUGKuWqHjlH1EVrQSHE0bkI0xkL0MnE1AdpyIWqHMYrKqSFUEeEHyOoRyWDIIiZyZkERuwIaSHL0SnFRyYEKqCAHSVFTcjFRSFEyW5FaO5EKqVHx11Exb1H3WHp')
x3_end = x.find('sysE25uI0ujFxyHEGN5G0IXH0gWrQOeERceI0LmrHySF3H1EmAFnaWUEJgWIRSbEKu1E01uGmElF01bJyW4ZUOFqH9WZ1pmEySCnxuurHgTHH9OFKuAIaOWqJkTIKEeEJS1qxSVZTcjFaSJJy')

#print (x3_start, x3_end)
x3 = x[x3_start:x3_end]


x4_start = x.find('E25uI0ujFxyHEGN5G0IXH0gWrQOeERceI0LmrHySF3H1EmAFnaWUEJgWIRSbEKu1E01uGmElF01bJyW4ZUOFqH9WZ1pmEySCnxuurHgTHH9OFKuAIaOWqJkTIKEeEJS1qxSVZTcjFaSJJy')
x4_end = x.find('codecs.decode(love')
#print (x4_start, x4_end)
x4 = x[x4_start:x4_end]
print d(x1) + d(x2) + d(x3.decode('rot13')) + d(x4.decode('rot13'))
