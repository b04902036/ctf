#!/usr/bin/python
from pwn import *

name = 'instructions.txt'
#name = 'jason1'

f = open(name, 'r')
y = f.read()
x = y.strip().split('.')[:-1]
ans = []
cnt = 0
for i in x:
    ans.append(len(i))
ans = set(sorted(ans))
#print (ans)
real_ans = ''
i = -1
for _ in range(0, max(ans), 8):
    i += 1
    tmp = ''
    for j in range(1, 9):
        if (i * 8) + j in ans:
            tmp += '1'
        else:
            tmp += '0'
    tmp = ''.join(list(reversed(tmp)))
    #print ('num : ', i * 8)
    #print ('tmp : ', tmp)
    real_ans += chr(int(tmp, 2))
    #print ('real_ans : ', real_ans)
    #raw_input('#')
print ''.join(list(reversed(real_ans)))

