#!/usr/bin/python


def ans(x):
    seq = [0, 1, 4, 3, 2, 6, 5, 7, 8]
    ret = ''
    ox = x
    while x:
        q = x % 10
        x //= 10
        ret += str(seq[q])
    y = int(ret)
    print (ox, y)
    print (y**2 - y) % (ox**2 + ox)
    print (ox**2 - ox) % (y**2 + y)
    return int(ret)

while 1:
    x = int(raw_input('#').strip())
    ans(x)
