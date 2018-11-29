#!/usr/bin/python

def rep(x, num):
    ret = []
    while num:
        q = num % x
        num = num // x
        ret.append((q))
    return ret


f = open('flag.enc', 'r')
flag = f.read().encode('hex')
flag = int(flag, 16)
print flag


c = 0
i = 1
ret = rep(3, flag)
ans = ''
print ret
cnt = 0
for num in list(reversed(ret)):
    cnt += 1
    num = int(num)
    if num == 1:
        ans += '1'
    elif num == 0:
        ans += '0'
    else:
        ans += '0'
try:
    pr = hex(int(ans, 2) // 2)[2:].rstrip('L').decode('hex')
except:
    pr = ('0'+hex(int(ans, 2) // 2)[2:].rstrip('L')).decode('hex')
f = open('test.png', 'w')
f.write(pr)
f.close()



'''
i = 3
while(1):
    i *= 3
    ret = rep(i, flag)
    sign = 0
    for j in ret[-3:]:
        if j not in [0, 1]:
            sign = 1
    print (i)
    print (len(ret))
    if sign:
        continue
    raw_input('#')
'''
