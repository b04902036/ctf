#!/usr/bin/python
from Crypto.PublicKey import RSA
from Crypto.Util.number import *
import math
import decimal
decimal.getcontext.prec = 1000000000000
def is_prime(n):
    if n % 2 == 0 and n > 2:
        return False
    return all(n % i for i in range(3, int(math.sqrt(n)) + 1, 2))


key = RSA.importKey(open("public.pem").read())
n = key.n
e = key.e
print (n)
print (e)
flag = bytes_to_long(open("flag.txt").read())
ct = long_to_bytes(pow(flag, e, n))
open("tmp.txt",'w').write(ct)
