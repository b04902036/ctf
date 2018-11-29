#!/usr/bin/python
import os
import string
printable = string.printable

all_set = dict()

for i in printable:
    for j in printable:
        for k in printable:
            for l in printable:
                inp = i+j+k+l
                f = open('inp', 'wb')
                f.write(inp)
                f.close()
                os.system('./hash.py < inp > out')
                f = open('out', 'r')
                x = f.read().strip()
                f.close()
                try:
                    print [ord(jason) for jason in all_set[x]]
                    print [ord(jason) for jason in inp]
                    raw_input('#')
                except:
                    all_set[x] = inp
