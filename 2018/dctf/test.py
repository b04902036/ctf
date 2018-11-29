#!/usr/bin/python
from server import *
from Crypto.Cipher import AES


aes = AES.new(AES_KEY, AES.MODE_ECB)
print (aes.decrypt('\x00'*16))
exit()
print (tsb_decrypt(aes, 'flag'))
print (len(pad('flag')))
print list(bytes(tsb_encrypt(aes, pad('flag'))))
print len(list(bytes(tsb_encrypt(aes, pad('flag')))))
print list(bytes(tsb_decrypt(aes, tsb_encrypt(aes, pad('flag')))))
