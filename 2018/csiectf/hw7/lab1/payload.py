#!/usr/bin/python

f = open('file', 'rb')
y = f.read().strip()
f.close()
x = raw_input('payload: ').strip()
ans = ''
prev = 0
for i in range(len(x)):
    if i == 0:
        ans += '?' + y + '[]=' + str(ord(x[i]) + prev)
        prev += 256
    else:
        ans += '&' + y + '[]=' + str(ord(x[i]) + prev)
        prev += 256
print (ans)



