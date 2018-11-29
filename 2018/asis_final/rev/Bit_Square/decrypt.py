#!/usr/bin/python

f = open('flag.enc', 'r')
x = [ord(i) for i in f.read()]
cnt = 0
idx = 0
ans = ''
# we should first recover v9
# we can achieve this since PNG files have specific header "\x89PNG"
v9 = 96
while idx < len(x):
    if cnt % 4 == 0:
        v8 = (x[idx] - 114) & 0xff
        v7 = x[idx+1]
        ans += chr(v7 ^ v8 ^ ((v9+cnt) & 0xff))
        idx += 2
    elif cnt % 4 == 1:
        v7 = (x[idx] + 40) & 0xff
        v8 = x[idx+1]
        ans += chr(v7 ^ v8 ^ ((v9+cnt) & 0xff))
        idx += 2
    elif cnt % 4 == 2:
        v6 = cnt ** 3
        if v6 > 0x27:
            v8 = x[idx]
            v7 = x[idx+1]
        else:
            v7 = x[idx]
            v8 = x[idx+1]
        ans += chr(v7 ^ v8 ^ ((v9+cnt) & 0xff))
        idx += 3
    else:
        v7 = x[idx]
        v8 = x[idx+1]
        ans += chr(v7 ^ v8 ^ ((v9+cnt) & 0xff))
        idx += 3
    cnt += 1
f = open('origin.png', 'w')
f.write(ans)
f.close()
