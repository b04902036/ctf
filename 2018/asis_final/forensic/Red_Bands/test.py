#!/usr/bin/python
f = open('5', 'rb')
x = f.read()
f.close()

idx = 0
while 1:
    place = x.find('PNG')
    if place <= 0:
        break
    f = open('_' + str(idx) + '.png', 'wb')
    x = x[place:]
    f.write('\x89' + x)
    f.close()
    x = x[3:]
    idx += 1
