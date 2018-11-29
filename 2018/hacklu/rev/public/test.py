#!/usr/bin/python
from pwn import *
context.arch = 'amd64'

f = open('chall', 'r')
f.read(0x82)
x = f.read(0x97 - 0x82 + 1)
x = [i for i in x]
for i in range(len(x)):
    for j in range(8):
        tmp = x[i]
        x[i] = chr(ord(x[i]) ^ (2**j))
        print disasm(''.join(x))
        x[i] = tmp
        raw_input('#\n')
