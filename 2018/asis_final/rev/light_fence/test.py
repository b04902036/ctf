#!/usr/bin/python
import sys
import os

f = open('test.txt', 'w')
f.write(sys.argv[1].strip())
f.close()
os.system('./ligh* -e test.txt')
f = open('test.txt.enc', 'r')
x = f.read()
print [ord(i) for i in x][0x107:]
print len(x)
