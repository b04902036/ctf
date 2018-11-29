#!/usr/bin/python
import sys
f = open('1944.zip', 'rb')
x = f.read()
f.close()
f = open('real.zip', 'wb')
f.write(x[:8] + chr(int(sys.argv[1])) + x[9:0x92] + chr(int(sys.argv[1])) + x[0x93:])
f.close()
