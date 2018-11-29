#!/usr/bin/python
from base64 import b64decode as d
import sys
f = open('dump', 'r')
x = f.read()
f.close()
print (d(x))
