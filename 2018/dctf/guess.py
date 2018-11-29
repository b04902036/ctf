#!/usr/bin/python

from pwn import *
import numpy as np
x = input('#')
for i in range(256):
	y = (np.array([i ^ j for j in x]) < 16).all()
	if(y):
		print (i)
