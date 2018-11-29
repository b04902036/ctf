#!/usr/bin/python
from Crypto.Cipher import AES
import string

printable = string.printable
f = open("ci.pher.text", "rb")
x = f.read().split(':')
f.close()


mac = int(x[0])
x = x[1][:-1].decode('hex')
MAC = 0
for i in range(0, len(x), 16):
    MAC ^= int(x[i:i+16].encode('hex'), 16)


pt = []
ans = [91, 204, 18]
tmp_key = hex(mac ^ MAC)[2:-1].decode('hex')
for i in range(0, len(x), 16):
    now_try = tmp_key[:-1]
    fake = []
    for j in range(256):
        j = ans[i//16]
        key = chr(j) + now_try
        cipher = AES.new(key, AES.MODE_ECB)
        fake_pt = cipher.decrypt(x[len(x)-16-i:len(x)-i])
        unpad = ord(fake_pt[-1])
        if(i == 0):
            fake_pt = fake_pt[:-1*unpad]
        fake.append(fake_pt)
        break
    num = ans[i//16]
    tmp_key = chr(num) + now_try
    pt.append(fake_pt)
# 19, 204, 18
print ''.join(list(reversed(pt)))
