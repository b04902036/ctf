#!/usr/bin/env python
import gmpy2
import numpy as np
# from secret import exp, key
exp = 3
ran = gmpy2.random_state()
key = gmpy2.mpz_rrandomb(ran, 512)
def encrypt(exp, num, key):
    assert key >> 512 <= 1
    num = num + key
    msg = bin(num)[2:][::-1]
    C, i = 0, 1
    cnt = 0
    now = exp
    now2 = -1
    for b in msg:
        cnt += 1
        C += int(b) * (now + now2)
        i += 1
        now *= exp
        now2 *= -1
        if cnt % 20000 == 0:
            print (cnt * 1.0 / len(msg))
    try:
        enc = hex(C)[2:].rstrip('L').decode('hex')
    except:
        enc = ('0' + hex(C)[2:].rstrip('L')).decode('hex')
    return enc

#-----------------------------
# Encryption:
#-----------------------------

flag = open('jason.png', 'r').read()
msg = int(flag.encode('hex'), 16)
enc = encrypt(exp, msg, key)

f = open('jason.enc', 'w')
f.write(enc)
f.close()
