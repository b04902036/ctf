#!/usr/bin/python

def encode(string):
    a = []
    for i in range(len(string)):
        t = ord(string[i])
        for j in range(8):
            if (t >> j) & 1 == 1:
                a.append(1 + j + (len(string) - 1 - i) * 8)
    r = ''
    for i in range(len(a)):
        r += '-'*a[i] + '.'
    f = open('jason1', 'w')
    f.write(r)
    f.close()

encode('123456')
