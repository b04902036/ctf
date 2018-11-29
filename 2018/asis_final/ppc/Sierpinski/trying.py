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

def check(inp):
    inp = str(inp)
    for i in inp:
        if i not in set(['1', '2', '3', '4', '5', '6', '7', '8']):
            return False
    if inp[-2] == '8':
        return False
    return True

def recv_end():
    ans = ''
    while 1:
        ans += r.recvn(1)
        if ans[-1] == '\n':
            r.unrecv('\n')
            return ans


while 1:
    inp = raw_input('#')
    start = inp.find('Send the Sierpinski pair x, y separated by comma, such that ') + len('Send the Sierpinski pair x, y separated by comma, such that ')
    condition = inp[start:]
    if condition.find(', and the x cordinate smaller than ') >= 0:
        # gcd(x, y) = 45, and the x cordinate smaller than 9295
        condition = condition.split(' ')
        base = int(condition[3][:-1])
        biggest = int(condition[-1])
        x = 0
        while x < biggest:
            x += base
            if not check(x):
                continue
            y = ans(x)
            # print ('{}\t{}\t{}'.format(x, y, gmpy2.gcd(x, y)))
            if gmpy2.gcd(x, y) == base and x not in black_list:
                send = ', '.join([str(x), str(y)])
                print (send)
                break
    elif condition.find(', and the y cordinate smaller than ') > 0:
        # gcd(x, y) = 45, and the x cordinate smaller than 9295
        condition = condition.split(' ')
        base = int(condition[3][:-1])
        biggest = int(condition[-1])
        print (base)
        print (biggest)
        x = 0
        while x < biggest:
            x += base
            if not check(x):
                continue
            y = ans(x)
            if gmpy2.gcd(x, y) == base and x not in black_list:
                send = ', '.join([str(y), str(x)])
                print (send)
                break

    elif condition.find(', and the x cordinate larger than ') >= 0:
        # gcd(x, y) = 45, and the x cordinate larger than 9295
        condition = condition.split(' ')
        base = int(condition[3][:-1])
        smallest = int(condition[-1])
        x = base
        while x <= smallest:
            x += base
        x -= base
        while 1:
            x += base
            if not check(x):
                continue
            y = ans(x)
            if gmpy2.gcd(x, y) == base and x not in black_list:
                send = ', '.join([str(x), str(y)])
                print (send)
                break

    elif condition.find(', and the y cordinate larger than ') >= 0:
        # gcd(x, y) = 45, and the x cordinate larger than 9295
        condition = condition.split(' ')
        base = int(condition[3][:-1])
        smallest = int(condition[-1])
        x = base
        while x <= smallest:
            x += base
        x -= base
        while 1:
            x += base
            if not check(x):
                continue
            y = ans(x)
            if gmpy2.gcd(x, y) == base and x not in black_list:
                send = ', '.join([str(y), str(x)])
                print (send)
                break


    elif condition.find('x cordinate larger than ') > 0:
        # x cordinate larger than
        condition = condition.split(' ')
        smallest = int(condition[-1])
        x = smallest + 1
        while not check(x) or x in black_list:
            x += 1
        send = ', '.join([str(x), str(ans(x))])
        print (send)
    elif condition.find('y cordinate larger than ') > 0:
        # x cordinate larger than
        condition = condition.split(' ')
        smallest = int(condition[-1])
        y = smallest + 1
        while not check(y) or y in black_list:
            y += 1
        send = ', '.join([str(ans(y)), str(y)])
        print (send)

    else:
        print 'wtf??'
        condition = condition.split(' ')
        r.interactive()


