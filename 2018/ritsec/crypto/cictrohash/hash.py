#!/usr/bin/python
import numpy as np

def rotate_r(inp):
    inp = (inp << 7) | (inp >> 1)
    inp &= 0xff
    return inp

def rotate_l(inp):
    inp = (inp >> 7) | (inp << 1)
    inp &= 0xff
    return inp

def _pad(inp):
    if len(inp) % 4 == 0:
        return inp
    inp += '\x00' * (4 - (len(inp) % 4))
    return inp

def alpha(w):
    w[0], w[1] = w[1], w[0]
    return w

def beta(w):
    w[0][0] ^= w[1][3]
    w[0][1] ^= w[1][2]
    w[0][2] ^= w[1][1]
    w[0][3] ^= w[1][0]
    return w

def gamma(w):
    '''
    w[0][0] = w[0][3]
    w[0][1] = w[1][2]
    w[0][2] = w[1][3]
    w[0][3] = w[1][1]
    w[1][0] = w[0][1]
    w[1][1] = w[1][0]
    w[1][2] = w[0][2]
    w[1][3] = w[0][0]
    '''
    w[0][0], w[0][1], w[0][2], w[0][3], w[1][0], w[1][1], w[1][2], w[1][3] = w[0][3], w[1][2], w[1][3], w[1][1], w[0][1], w[1][0], w[0][2], w[0][0]
    return w

def delta(w):
    w[0][0] = rotate_l(w[0][0])
    w[1][0] = rotate_l(w[1][0])
    w[0][2] = rotate_l(w[0][2])
    w[1][2] = rotate_l(w[1][2])

    w[0][1] = rotate_r(w[0][1])
    w[1][1] = rotate_r(w[1][1])
    w[0][3] = rotate_r(w[0][3])
    w[1][3] = rotate_r(w[1][3])

    return w

#string = raw_input('input string : ').strip()
string = raw_input().strip()
string = _pad(string)
#print ('hashing({})'.format(string))
assert len(string) % 4 == 0

S = [[31, 56, 156, 167],
     [38, 240, 174, 248]]



for i in range(0, len(string), 4):
    for j in range(4):
        S[0][j] ^= ord(string[i+j])
    for _ in range(50):
        S = alpha(S)
        S = beta(S)
        S = gamma(S)
        S = delta(S)

print '0x' + ''.join([hex(i)[2:] for i in S[0]])
S = alpha(S)
S = beta(S)
S = gamma(S)
S = delta(S)
print '0x' + ''.join([hex(i)[2:] for i in S[0]])
S = alpha(S)
S = beta(S)
S = gamma(S)
S = delta(S)
print '0x' + ''.join([hex(i)[2:] for i in S[0]])
S = alpha(S)
S = beta(S)
S = gamma(S)
S = delta(S)




