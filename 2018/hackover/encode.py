#!/usr/bin/python
from base64 import b64encode as e

f = open('main.so', 'rb')
x = f.read()
f.close()
x = e(x)
print (x)
