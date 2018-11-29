#!/usr/bin/python

from pwn import *
import string
#host = 'bwv2342.ctf.hackover.de'
#port = 1337
host = 'localhost'
port = 4001
poss = string.ascii_letters + string.punctuation + string.digits

def guess(trying):
    r.recvuntil('guess:\n')
    r.sendline(trying)
    ret = r.recvuntil('\n')
    return ret

r = remote(host, port)
r.interactive()
now = 'hackover18'
while(1):
    ret1 = guess(now + 'a')
    ret2 = guess(now + 'b')
    ret3 = guess(now + 'c')
    if(ret1 == ret2):
        guessing = ret1
    elif(ret1 == ret3):
        now += 'b'
        continue
    else:
        now += 'a'
        continue
    for i in poss:
        print (now + i)
        new_ret = guess(now + i)
        if(new_ret != guessing):
            now += i
            break

