#!/usr/bin/python
from pwn import *
import string
from base64 import b64encode
from hashlib import md5
poss = string.printable
host = '39.105.151.182'
port = 9999

r = remote(host, port)


def start():
    for a in poss:
        for b in poss:
            for c in poss:
                for d in poss:
                    s = a+b+c+d+salt
                    if md5(s).hexdigest()[:4] == want:
                        return a+b+c+d

f = open('./a.out', 'rb')
content = b64encode(f.read())
f.close()

r.recvuntil('MD5(key+\"')
salt = r.recvuntil('\"')[:-1]
r.recvuntil('==')
want = r.recvuntil('\n').strip()
r.recvuntil(':')
r.sendline(start())
r.recvuntil('!')
r.sendline(content)
r.interactive()
