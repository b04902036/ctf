#!/usr/bin/python
from pwn import *
from hashlib import *
import string
import gmpy2
poss = string.printable
host = '146.185.131.214'
port = 28416

black_list = set([631])

def ans(inp):
    seq = [0, 1, 4, 3, 2, 6, 5, 7, 8]
    ret = ''
    while inp:
        q = inp % 10
        inp //= 10
        ret += str(seq[q])
    return int(ret)

def check(x, y):
    return ((y**2-y)%(x**2+x))==0 and ((y - x) * ((y - 1) % x) * ((y - 1) % (x + 1)) * (y % x) * (y % (x + 1)) > 0)

def recv_end():
    ans = ''
    while 1:
        ans += r.recvn(1)
        if ans[-1] == '\n':
            r.unrecv('\n')
            return ans

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
jason_cnt = 0

while 1:
    try:
        jason_cnt += 1
        print ('now solving [{}]'.format(jason_cnt))
        banner = r.recvuntil('Send the Sierpinski pair x, y separated by comma, such that ')
        condition = r.recvuntil('\n')
        if condition.find(', and the x cordinate smaller than ') >= 0:
            # gcd(x, y) = 45, and the x cordinate smaller than 9295
            condition = condition.split(' ')
            base = int(condition[3][:-1])
            biggest = int(condition[-1])
            y = base * 2
            sign = 1
            while sign:
                y += base
                print ('now trying y == {}'.format(y))
                for x in range(base*2, y, base):
                    if x >= biggest:
                        break
                    if not check(x, y):
                        continue
                    if gmpy2.gcd(x, y) == base:
                        send = ', '.join([str(x), str(y)])
                        print (send)
                        r.sendline(send)
                        sign = 0
                        break
        elif condition.find(', and the x cordinate larger than ') >= 0:
            # gcd(x, y) = 45, and the x cordinate larger than 9295
            condition = condition.split(' ')
            base = int(condition[3][:-1])
            smallest = int(condition[-1])
            # gcd == m * n + k
            start = 2
            while 1:
                start = gmpy2.next_prime(start)
                if base % start == 0:
                    continue
                m = start
                k = start // 2
                inv = gmpy2.invert(m, base)
                n = (base - k) * inv
                if (m * n + k) % 2 == 0 and base % 2 == 0:
                    print ('now (m * n + k) == {}, continue searching...'.format((m*n+k)))
                    continue
                while (m * n + k) <= smallest or (m * n + k) % 2 == 0 or (m * n + k) == base:
                    n += 3 * base
                    # print (m * n + k, n)
                x = (m * n + k) * 2
                break
            print ('m * n + k\n{} * {} + {}'.format(m, n, k))
            y = x * (x + 1) // (2 * m)
            tmpy = y
            while not (tmpy-1) % (2 * m) == 0:
                start = gmpy2.next_prime(start)
                tmpy = y * start
                # print ('now trying {}, testing if it is multiple of {}'.format(tmpy, 2 * m))
            y = tmpy
            send = ', '.join([str(x), str(y)])
            print (send)
            r.sendline(send)
            '''
            sign = 1
            while sign:
                y += base
                for x in range(smallest, y, base):
                    if not check(x, y):
                        continue
                    if gmpy2.gcd(x, y) == base:
                        send = ', '.join([str(x), str(y)])
                        print (send)
                        r.sendline(send)
                        sign = 0
                        break
            '''

        elif condition.find(', and the y cordinate larger than ') >= 0:
            # gcd(x, y) = 45, and the x cordinate larger than 9295
            condition = condition.split(' ')
            base = int(condition[3][:-1])
            smallest = int(condition[-1])
            smallest = base * ((smallest // base) + 1)
            # gcd == m * n + k
            start = 2
            while 1:
                start = gmpy2.next_prime(start)
                if base % start == 0:
                    continue
                m = start
                k = start // 2
                inv = gmpy2.invert(m, base)
                n = (base - k) * inv
                if (m * n + k) % 2 == 0 and base % 2 == 0:
                    print ('now (m * n + k) == {}, continue searching...'.format((m*n+k)))
                    continue
                while (m * n + k) % 2 == 0 or (m * n + k) == base:
                    n += 3 * base
                    print (m * n + k, n)
                x = (m * n + k) * 2
                break
            print ('m * n + k\n{} * {} + {}'.format(m, n, k))
            y = x * (x + 1) // (2 * m)
            tmpy = y
            while tmpy <= smallest or not (tmpy-1) % (2 * m) == 0:
                start = gmpy2.next_prime(start)
                tmpy = y * start
                # print ('now trying {}, testing if it is multiple of {}'.format(tmpy, 2 * m))
            y = tmpy
            send = ', '.join([str(x), str(y)])
            print (send)
            r.sendline(send)


        elif condition.find(', and the y cordinate smaller than ') > 0:
            # gcd(x, y) = 45, and the x cordinate smaller than 9295
            condition = condition.split(' ')
            base = int(condition[3][:-1])
            biggest = int(condition[-1])
            y = base * 2
            while y < biggest:
                y += base
                print ('now trying y == {}'.format(y))
                for x in range(base*2, y, base):
                    if not check(x, y):
                        continue
                    if gmpy2.gcd(x, y) == base:
                        send = ', '.join([str(x), str(y)])
                        print (send)
                        r.sendline(send)
                        break

        elif condition.find('x cordinate larger than ') > 0:
            # x cordinate larger than
            condition = condition.split(' ')
            smallest = int(condition[-1])
            x = 2 + (36 * ((smallest // 36) + 1))
            y = x * (x + 1) // 6
            send = ', '.join([str(x), str(y)])
            print (send)
            r.sendline(send)
        elif condition.find('y cordinate larger than ') > 0:
            # x cordinate larger than
            condition = condition.split(' ')
            smallest = int(condition[-1])
            x = 2 + (36 * ((smallest // 36) + 1))
            y = x * (x + 1) // 6
            send = ', '.join([str(x), str(y)])
            print (send)
            r.sendline(send)

        else:
            print 'wtf??'
            condition = condition.split(' ')
            r.interactive()
        print ' '.join(condition)
    except:
        print 'bug'
        print banner
        print condition
        print ' '.join(condition)
        r.interactive()
        exit()
r.interactive()

