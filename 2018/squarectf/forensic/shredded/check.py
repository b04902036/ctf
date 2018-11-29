#!/usr/bin/python

f = open('record', 'r')
x = f.read().split('\n')
for line in x:
    if 'NULL' not in line:
        print line
