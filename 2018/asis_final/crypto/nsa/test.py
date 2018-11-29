#!/usr/bin/python
from pwn import *
from hashlib import *
import string
poss = string.printable
host = '146.185.131.214'
port = 52709


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
if 'md5' in mode:
    ret, ret2 = start(md5, want)
elif 'sha512' in mode:
    ret, ret2 = start(sha512, want)
elif 'sha256' in mode:
    ret, ret2 = start(sha256, want)
elif 'sha384' in mode:
    ret, ret2 = start(sha384, want)
elif 'sha224' in mode:
    ret, ret2 = start(sha224, want)
elif 'sha1' in mode:
    ret, ret2 = start(sha1, want)
    r.close()
print ('ret : ', ret)
print ('mode : ', mode)
print ('want : ', want)
print ('result : ', ret2)
r.sendline(ret)
def laxt(expr, num):
    ops = ' %&()*+-/<>^|~'
    nude = expr.translate(None, ops)
    try:
        flag, val = True, eval(expr)
    except:
        flag, val = False, None
    return set(nude) == set(num) and flag, val
r.interactive()
