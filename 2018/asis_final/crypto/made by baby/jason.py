#!/usr/bin/python


f = open('test.png', 'r')
x = f.read()
f.close()
print int(x[2356:2360], 16)

