#!/usr/bin/python

f = open('Password.txt', 'r')
x = f.read().replace(' ', '').replace('\n', '')[263:][162:][186:]
print (x)
for i in range(1, len(x)):
    tmp = x[:i]
    if hex(int(tmp))[:-1].endswith('00000'):
        print (i)
        print hex(int(tmp))
        print (tmp)
