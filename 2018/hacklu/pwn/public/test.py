#!/usr/bin/python
from pwn import *



host = 'localhost'
port = 4000

r = remote(host, port)
r.recvuntil(':')
r.sendline()
x = '\x1f0R;Uz\ta'

