#!/usr/bin/python
from pwn import *
from base64 import b64decode as d

x1 = d('4mLapilvtKNatsOQOW1hgi4XL52fAcuYuTI273t/mVcn1o8H4NOuwHurykovk3pL4TmqN8lLcmsCML5FppIZfQ==')
x2 = d('2meRvngvfE5w2krQC1mHy/jWRF32FiiBJSvewkzMLOsPyq5AJNjZb+XGzeiqyzoyEfzfxSwpaovt7ZrRFmHrCw==')

print (len((x1)))
print (len((x2)))
print (x1[0])
print (ord(x1[0]) ^ ord(x2[0]))
print (ord(x1[1]) ^ ord(x2[1]))
