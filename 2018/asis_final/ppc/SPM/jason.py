import gmpy2
import random
start = 1024
while 1:
    p = gmpy2.next_prime(start)
    start = p
    a = random.randint(2, p)
    m = 1
    n = p - a
    x = int(n * p + a)
    print ('x = {}, a = {}, p = {}'.format(x, a, p))
    assert (pow(x, x, p) == a)
    raw_input('#')
