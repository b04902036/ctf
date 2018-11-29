#!/usr/bin/python
from pwn import *
from base64 import b64encode as encode
from base64 import b64decode as decode

host = '13.231.83.89'
port = 30262


def parse(string_in):
    now = string_in.split('\n')
    ans = ''
    for line in now:
        line = line[24:]
        if len(line) < 8:
            ans += line.strip()
        else:
            last = line[8:].strip().split(';')[-1].split(',')
            for i in range(len(last)):
                all_digits = list(reversed([(m.start(0), m.end(0), m.group(0)) for m in re.finditer('\d+', last[i])]))
                for digits_start, digits_end, digits in all_digits:
                    if last[i][digits_start-1] == 'r':
                        continue
                    if int(digits) < 9:
                        continue
                    last[i] = last[i][:digits_start] + last[i][digits_start:digits_end].replace(digits, hex(int(digits, 10))) + last[i][digits_end:]

            ans += line[:8].strip() + ' ' + ', '.join(last) + '\n'

    return ans

mips_banner = '.----------------.  .----------------.  .----------------.  .----------------.'
i386_banner = '___   _______   _____   ___'
x86_64_banner = "________________"
arm_banner = "|____| |____||____| |___||_____||_____|"
aarcx64_banner = "/__(   )__"
ppc32_banner = "\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84  \xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84"

r = remote(host, port)
r.recvuntil('game')
r.sendline()
r.recvuntil('continue...)')
r.sendline()
r.recvuntil('continue...)')
r.sendline()
r.recvuntil(':')
r.sendline('A')
r.recvuntil('continue...)')
r.sendline()
r.recvuntil(':')
r.sendline('B')
r.recvuntil('continue...)')
r.sendline()
r.recvuntil(':')
r.sendline()
for i in range(3):
    r.recvuntil('./#&&&&&&&%/.\n')

# down room
for i in range(5):
    r.recvuntil('w/a/s/d:')
    r.sendline('s\n')
inst = r.recvuntil('challenge...')
r.sendline()
print (inst)
if(mips_banner in inst):
    now = 'mips'
    context.arch = 'mips'
    odd = r.recvuntil(':')
    z = r.recvuntil('Answer')
    code = z[:-7].strip()
    ans = (asm('.set noat\n' + code))
    assert (len(ans) % 4 == 0)
    sending = ''
    for i in range(0, len(ans), 4):
        sending += ''.join(list(reversed(ans[i:i+4])))
    r.sendline(sending)
    r.recvuntil(':')
    r.recvuntil(':')
    code2 = r.recvuntil('Answer')[:-7].strip()
    sending = encode(parse(disasm(decode(code2))))
    r.sendline(sending)
elif(i386_banner in inst):
    context.arch = 'i386'
    now = 'i386'
    odd = r.recvuntil(':')
    z = r.recvuntil('Answer')
    code = z[:-7].strip()
    sending = encode(asm(code))
    r.sendline(sending)
    r.recvuntil(':')
    r.recvuntil(':')
    code2 = r.recvuntil('Answer')[:-7].strip()
    sending = encode(parse(disasm(decode(code2))))
    r.sendline(sending)
elif(x86_64_banner in inst):
    now = 'x86_64'
    context.arch = 'amd64'
    odd = r.recvuntil(':')
    code = r.recvuntil('Answer')[:-7].strip()
    sending = encode(asm(code))
    r.sendline(sending)
    r.recvuntil(':')
    r.recvuntil(':')
    code2 = r.recvuntil('Answer')[:-7].strip()
    sending = encode(parse(disasm(decode(code2))))
    r.sendline(sending)
elif(arm_banner in inst):
    now = 'arm'
    context.arch = 'arm'
    odd = r.recvuntil(':')
    z = r.recvuntil('Answer')
    code = z[:-7].strip()
    sending = encode(asm(code))
    r.sendline(sending)
    r.recvuntil(':')
    r.recvuntil(':')
    code2 = r.recvuntil('Answer')[:-7].strip()
    sending = encode(parse(disasm(decode(code2))))
    r.sendline(sending)
elif(aarcx64_banner in inst):
    now = 'aarcx64'
    context.arch = 'aarch64'
    odd = r.recvuntil(':')
    code = r.recvuntil('Answer')[:-7].strip()
    sending = encode(asm(code))
    r.sendline(sending)
    r.recvuntil(':')
    r.recvuntil(':')
    code2 = r.recvuntil('Answer')[:-7].strip()
    sending = encode(parse(disasm(decode(code2))))
    r.sendline(sending)
else:
    now = "ppc32"
    context.arch='ppc32'
    odd = r.recvuntil(':')
    z = r.recvuntil('Answer')
    code = z[:-7].strip()
    f = open('ppc32.asm', 'w')
    f.write(code)
    f.close()
    os.system('~/ctf/2018/hitcon/misc/power-pc-eabi-assembling/Linux/assemble.sh')
    f = open('./asm', 'rb')
    ans = f.read()
    f.close()
    sending = encode(ans)
    r.sendline(sending)
    r.recvuntil(':')
    r.recvuntil(':')
    code2 = r.recvuntil('Answer')[:-7].strip()
    sending = encode(parse(disasm(decode(code2))))
    r.sendline(sending)




r.interactive()
print (now)
print ('odd : ', odd)
print (sending)
print ('code2 : ', code2)
print (code)
print (z)

