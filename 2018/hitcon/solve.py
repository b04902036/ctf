#!/usr/bin/env python
from pwn import *
from keystone import *
from capstone import *
from unicorn import *
from unicorn.x86_const import *
from unicorn.arm_const import *
from unicorn.arm64_const import *
from unicorn.mips_const import *
from time import sleep
from base64 import b64encode, b64decode

def f1(x):
    if x < 10:
        return str(x)
    return hex(x)

table = {
    b' .----------------.  .----------------.  .----------------.  .----------------. \n': 'mips',
    b'                     .----.                                                      _____      \n': 'amd64',
    b' \xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84  \xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84  \xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84  \xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84  \xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84\xe2\x96\x84 \n': 'powerpc',
    b' ___   _______   _____   ___     \n': 'i386',
    b'      _       _______     ____    ____  \n': 'arm',
    b'   ____       ____     ______       ____   __    __     ______   _   _     \n': 'aarch64'
}

UCS = {
    'i386':    (UC_ARCH_X86, UC_MODE_32),
    'amd64':   (UC_ARCH_X86, UC_MODE_64),
    'arm':     (UC_ARCH_ARM, UC_MODE_ARM),
    'aarch64': (UC_ARCH_ARM64, UC_MODE_ARM),
    'mips':    (UC_ARCH_MIPS, UC_MODE_MIPS32 + UC_MODE_BIG_ENDIAN),
}

CPS = {
    'i386':     (CS_ARCH_X86, CS_MODE_32),
    'amd64':    (CS_ARCH_X86, CS_MODE_64),
    'arm':      (CS_ARCH_ARM, CS_MODE_ARM),
    'aarch64':  (CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN),
    'mips':     (CS_ARCH_MIPS, CS_MODE_MIPS64 + CS_MODE_BIG_ENDIAN),
    'powerpc':    (CS_ARCH_PPC, CS_MODE_32 + CS_MODE_BIG_ENDIAN)
}

def init(r):
    r.sendlineafter('Press the Enter key to start the game', '')
    r.sendlineafter('(Press the Enter key to continue...)', '')
    r.sendlineafter('(Press the Enter key to continue...)', '')
    r.sendlineafter('Choice: ', 'A')
    r.sendlineafter('(Press the Enter key to continue...)', '')
    r.sendlineafter('Choice: ', 'B')
    r.sendlineafter('(Press the Enter key to continue...)', '')
    r.sendlineafter('Answer: ', '')

def walk(r, steps):
    for step in steps:
        r.sendlineafter('w/a/s/d:', step)

def getArch(r):
    r.recvline()
    head = r.recvline()
    try:
        return table[head]
    except KeyError:
        print(head)
        r.interactive()
        exit()

def challenge1(r, arch):
    r.sendlineafter('Press the Enter key to start the challenge...', '')
    r.recvline()
    assembly = r.recvuntil('Answer:')
    assembly = assembly[:assembly.rfind(b'\n')]

    if arch == 'mips':
        ks = Ks(KS_ARCH_MIPS, KS_MODE_MIPS64 + KS_MODE_BIG_ENDIAN)
        machine, _ = ks.asm(assembly)
        machine = bytes(machine)[:-4]
    else:
        machine = asm(assembly.decode(), arch = arch)

    r.sendline(b64encode(machine))

def challenge2(r, arch):
    r.recvline()
    machine = b64decode(r.recvline().strip())

    cs = Cs(*CPS[arch])
    assembly = ''
    for i in cs.disasm(machine, 0x1000):
        assembly += f"{i.mnemonic} {i.op_str}\n"
    assembly = assembly.strip()

    #assembly = disasm(machine, arch = arch)
    #assembly = parse(assembly, arch)

    assembly = b64encode(assembly.encode())
    r.sendlineafter('Answer:', assembly)

    #r.recvuntil('The answer should be: ')
    #answer = b64decode(r.recvline().strip())
    #print(answer)

def challenge3(r, arch):
    r.recvuntil('What is ')
    shellcode = r.recvuntil(' ?\nAnswer:').strip(b' ?\nAnswer:')
    print(shellcode)

    if arch == 'powerpc':
        print('powerpc not implemented')
        exit()

    ADDRESS = 0x2000
    STACK   = 0x0000
    mu = Uc(*UCS[arch])
    mu.mem_map(ADDRESS, 0x1000)
    mu.mem_map(STACK, 0x1000)

    if arch == 'i386':
        shellcode = shellcode[:-1]
        mu.mem_write(ADDRESS, shellcode)
        mu.reg_write(UC_X86_REG_ESP, STACK + 0x500)
        mu.emu_start(ADDRESS, ADDRESS + len(shellcode))
        eax = mu.reg_read(UC_X86_REG_EAX)
        r.sendline(hex(eax))
    elif arch == 'amd64':
        shellcode = shellcode[:-1]
        mu.mem_write(ADDRESS, shellcode)
        mu.reg_write(UC_X86_REG_RSP, STACK + 0x500)
        mu.emu_start(ADDRESS, ADDRESS + len(shellcode))
        rax = mu.reg_read(UC_X86_REG_RAX)
        r.sendline(hex(rax))
    elif arch == 'arm':
        shellcode = shellcode[:-4]
        mu.mem_write(ADDRESS, shellcode)
        mu.reg_write(UC_ARM_REG_SP, STACK + 0x500)
        mu.emu_start(ADDRESS, ADDRESS + len(shellcode))
        r0 = mu.reg_read(UC_ARM_REG_R0)
        r.sendline(hex(r0))
    elif arch == 'mips':
        shellcode = shellcode[:-4]
        mu.mem_write(ADDRESS, shellcode)
        mu.reg_write(UC_MIPS_REG_SP, STACK + 0x500)
        mu.emu_start(ADDRESS, ADDRESS + len(shellcode))
        v0 = mu.reg_read(UC_MIPS_REG_V0)
        r.sendline(hex(v0))
    elif arch == 'aarch64':
        shellcode = shellcode[:-4]
        mu.mem_write(ADDRESS, shellcode)
        mu.reg_write(UC_ARM64_REG_SP, STACK + 0x500)
        mu.emu_start(ADDRESS, ADDRESS + len(shellcode))
        x0 = mu.reg_read(UC_ARM64_REG_X0)
        r.sendline(hex(x0))

def main():
    r = remote('13.231.83.89', 30262)

    init(r)

    routes = ['w' * 5,
              'a' * 15 + 'w',
              'd' * 5 + 's' * 4 + 'a',
              's' * 4 + 'a' * 5 + 's',
              'd' * 14 + 's']
    for route in routes:
        walk(r, route)
        arch = getArch(r)
        print('arch =', arch)

        challenge1(r, arch)
        challenge2(r, arch)
        challenge3(r, arch)

    r.interactive()

main()
