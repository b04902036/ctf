#!/usr/bin/python
from pwn import *

f = open('command.log', 'r')
x = f.read().split('\n')[:-1]
f.close()
f = open('real_command', 'w')
for line in x:
	x1 = line.split(':')[1].split('\t')[1:]
	r1 = x1[0][10:]
	r2 = x1[0][2:10]
	r3 = x1[1][10:]
	r4 = x1[1][2:10]
	f.write('\n'.join([r1, r2, r3, r4]) + '\n')
f.close()
