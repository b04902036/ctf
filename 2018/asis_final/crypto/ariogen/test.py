#!/usr/bin/python
from pwn import *
from hashlib import *
import string
poss = string.printable
host = '37.139.4.247'
port = 36032


def start(mode, want):
    for a in poss:
        for b in poss:
            for c in poss:
                for d in poss:
                    s = a+b+c+d
                    s2 = mode(s).hexdigest()[-6:]
                    if s2 == want:
                        return s, s2

r = remote(host, port)
r.recvuntil('Submit a printable string X, such that ')
mode = r.recvuntil('(X)')[:-3]
r.recvuntil('[-6:] = ')
want = r.recvuntil('\n').strip()
if mode == 'md5':
    ret, ret2 = start(md5, want)
elif mode == 'sha512':
    ret, ret2 = start(sha512, want)
elif mode == 'sha256':
    ret, ret2 = start(sha256, want)
elif mode == 'sha384':
    ret, ret2 = start(sha384, want)
elif mode == 'sha224':
    ret, ret2 = start(sha224, want)
elif mode == 'sha1':
    ret, ret2 = start(sha1, want)
else:
    print (mode)
    r.close()
print ('ret : ', ret)
print ('mode : ', mode)
print ('want : ', want)
print ('result : ', ret2)
r.sendline(ret)
r.interactive()
