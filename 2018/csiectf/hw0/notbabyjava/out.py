#!/usr/bin/python

x = [37, 5, 118, 90, -112, -13, -34, 7, 106, 102, -115, -20, -51, 0, 80, 84, -115, -3, -34, 2, 121, 84, -87, -8]
ans = ''
for i in range(len(x)):
	ans += chr((x[i] ^ (i * 42 + 1 ^ 66)) & 255)
print ans
