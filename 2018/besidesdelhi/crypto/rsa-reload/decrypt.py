#!/usr/bin/python
from Crypto.PublicKey import RSA
from Crypto.Util.number import *
import gmpy2
from gmpy2 import *

f1 = open('publickey1.pem', 'rb')
f2 = open('publickey2.pem', 'rb')


e = 65537
k1 = RSA.importKey(f1.read())
k2 = RSA.importKey(f2.read())
f1 = bytes_to_long('flag{F3rm@t_&_s0me_t1nk3')
p2=34943163978391913490204654305109869295969157488468663432729901906249731064212045067569040629711809459931937454973699790926994065010763031849790674858604991174643121365163423162775788932612813130638817575733125991407600361279872314029744419625686687155697263385630614699236173841815497058736893083857633953613
q2=34943163978391913490204654305109869295969157488468663432729901906249731064212045067569040629711809459931937454973699790926994065010763031849790674858604991174643121365163423162775788932612813130638817575733125991407600361279872314029744419625686687155697263385630614699236173841815497058736893083857633916533
assert (k2.n == q2 * p2)

x = q2 - f1
while(1):
    try:
        x -= 1
        if(is_prime(x + f1)):
            break
        # p - 2 * q = x
        # p * q = k1.n
        # => (2q + x) ** 2 = 4 * k1.n + x ** 2
        ret = (iroot(4 * mpz(k1.n) + (mpz(x) ** 2), 2))
        assert (ret[1])
        print ('success 1 !!')
        q1 = (ret[0] + x) / 2
        p1 = k1.n / q1
        assert (k1.n % q1 == 0)
        print ('success 2 !!')
        raise SyntaxError
    except SyntaxError:
        break
    except:
        continue
assert (p1 * q1 == k1.n)
assert (p2 * q2 == k2.n)
d1 = invert(k1.e, (p1-1)*(q1-1))
assert ((d1 * k1.e) % ((p1-1)*(q1-1)) == 1)
f = open('ciphertext1.txt', 'rb')
x = int(f.read())
f.close()
x = long_to_bytes(pow(x, d1, p1*q1))
print 'flag : ', long_to_bytes(f1) + x

