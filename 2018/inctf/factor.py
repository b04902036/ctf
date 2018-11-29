#!/usr/bin/python
import gmpy2
from gmpy2 import mpz
import sys
def factors(n):
    result = set()
    n = mpz(n)
    cnt = gmpy2.isqrt(n) + 1
    i = gmpy2.sqrt(cnt)
    while i < cnt:
        div, mod = divmod(n, i)
        if not mod:
            result |= {mpz(i), div}
            print i
        i += 1
    return result

print (gmpy2.isqrt(int(sys.argv[1])))
ret = factors(int(sys.argv[1]))
print (ret)
