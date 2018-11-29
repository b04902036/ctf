#!/usr/bin/python
from pwn import *
import sys
from base64 import b64decode as d
from base64 import b64encode as e
'''
context.arch = 'mips'

f = open('mips', 'rb')
x1 = '.set noat\n' + f.read()
f.close()
f = open('mips_ans', 'rb')
x2 = f.read()
f.close()

x1 = asm(x1)
print (len(x1))
ans = ''
for i in range(0, len(x1), 4):
    ans += ''.join(list(reversed(x1[i:i+4])))
x2 = d(x2)
print (len(x2))
ans_idx = 0
x2_idx = 0

print (str(ans))
print (str(x2))
print (e(ans))
print (e(x2))
print disasm(ans)
print disasm(x2)
'''
def parse(string_in):
    now = string_in.split('\n')
    ans = ''
    f = open('now', 'w')
    f.write(string_in)
    f.close()
    for line in now:
        line = line[24:].split('  ')
        print (line)
        if len(line) < 2:
            ans += line[-1].strip()
        else:
            last = line[-1].strip().split(';')[-1].split(',')
            for i in range(len(last)):
                all_digits = list(reversed([(m.start(0), m.end(0), m.group(0)) for m in re.finditer('\d+', last[i])]))
                for digits_start, digits_end, digits in all_digits:
                    if last[i][digits_start-1] == 'r':
                        continue
                    print (last[i], digits_start, digits_end, digits)
                    last[i] = last[i][:digits_start] + last[i][digits_start:digits_end].replace(digits, hex(int(digits, 10))) + last[i][digits_end:]
                    print(last[i])

            ans += line[-2].strip() + ' ' + ', '.join(last) + '\n'

    return ans





context.arch = sys.argv[1]
str1='c3R3dSByMSwgLTB4MzAocjEpCnN0dyByMzEsIDB4MmMocjEpCm1yIHIzMSwgcjEKbGkgcjMsIDAKc3R3IHIzLCAweDI4KHIzMSkKbGkgcjMsIDB4NDM4ZgpzdHcgcjMsIDB4MjQocjMxKQpsaXMgcjMsIDAKb3JpIHI0LCByMywgMHhlNDIzCnN0dyByNCwgMHgyMChyMzEpCmxpIHI0LCAweDIwZWEKc3R3IHI0LCAweDFjKHIzMSkKb3JpIHI0LCByMywgMHhhOGE1CnN0dyByNCwgMHgxOChyMzEpCm9yaSByMywgcjMsIDB4YTY4NApzdHcgcjMsIDB4MTQocjMxKQpsd3ogcjMsIDB4MjQocjMxKQphZGRpIHIzLCByMywgLTB4NThjNgphZGRpcyByMywgcjMsIDB4MQpsd3ogcjQsIDB4MjAocjMxKQpvciByMywgcjMsIHI0Cmx3eiByNCwgMHgxYyhyMzEpCnN1YmYgcjMsIHI0LCByMwpsd3ogcjQsIDB4MTgocjMxKQphbmQgcjMsIHIzLCByNApsd3ogcjQsIDB4MTQocjMxKQphZGQgcjMsIHIzLCByNApsd3ogcjMxLCAweDJjKHIxKQphZGRpIHIxLCByMSwgMHgzMApibHI='
str2='c3R3dSByMSwgLTB4MzAocjEpCnN0dyByMzEsIDB4MmMocjEpCm1yIHIzMSwgcjEKbGkgcjMsIDAKc3R3IHIzLCAweDI4KHIzMSkKbGkgcjMsIDB4NDM4ZgpzdHcgcjMsIDB4MjQocjMxKQpsaXMgcjMsIDAKb3JpIHI0LCByMywgMHhlNDIzCnN0dyByNCwgMHgyMChyMzEpCmxpIHI0LCAweDIwZWEKc3R3IHI0LCAweDFjKHIzMSkKb3JpIHI0LCByMywgMHhhOGE1CnN0dyByNCwgMHgxOChyMzEpCm9yaSByMywgcjMsIDB4YTY4NApzdHcgcjMsIDB4MTQocjMxKQpsd3ogcjMsIDB4MjQocjMxKQphZGRpIHIzLCByMywgLTB4NThjNgphZGRpcyByMywgcjMsIDEKbHd6IHI0LCAweDIwKHIzMSkKb3IgcjMsIHIzLCByNApsd3ogcjQsIDB4MWMocjMxKQpzdWJmIHIzLCByNCwgcjMKbHd6IHI0LCAweDE4KHIzMSkKYW5kIHIzLCByMywgcjQKbHd6IHI0LCAweDE0KHIzMSkKYWRkIHIzLCByMywgcjQKbHd6IHIzMSwgMHgyYyhyMSkKYWRkaSByMSwgcjEsIDB4MzAKYmxy'
def dec2hex(i):
    import re
    prog = re.compile('\d+')
    return prog.match(i)


str1 = (d(str1))
print ()
str2 = (d(str2))
print (str1)

print ()
print (str2)
str1 = str1.split('\n')
f = open('now', 'r')
x = f.read()
f.close()
parse(x)
