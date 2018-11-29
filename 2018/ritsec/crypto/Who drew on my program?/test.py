#!/usr/bin/python
from Crypto.Cipher import AES
import string

printable = string.printable

key = "9aF738g9AkI112"

def decrypt(msg, passwd, iv='\x00'*16):
    aes = AES.new(passwd, AES.MODE_CBC, iv)
    return aes.decrypt(msg)

def str_xor(msg1, msg2):
    ans = ''
    for i in range(len(msg1)):
        ans += chr(ord(msg1[i]) ^ ord(msg2[i]))
    return ans

start = chr(0x9e)
end = chr(0x43) + chr(0x6a)

ct = '808e200a54806b0e94fb9633db9d67f0'.decode('hex')
pt1 = 'The message is p'
pt2 = 'rotected by AES!'

for i in printable:
    for j in printable:
        now_key = key + i + j
        new_iv = str_xor(decrypt(ct, now_key), pt2)
        if new_iv.endswith(end) and new_iv.startswith(start):
            acc_iv = str_xor(decrypt(new_iv, now_key), pt1)
            print (acc_iv)


