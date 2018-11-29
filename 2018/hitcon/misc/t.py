#!/usr/bin/python
from pwn import *

x = '''
push ebp
mov ebp, esp
sub esp, 0x1c
mov eax, 0x5547
mov dword ptr [ebp - 4], 0
mov dword ptr [ebp - 8], 0xe4a1
mov dword ptr [ebp - 0xc], 0x2cfe
mov dword ptr [ebp - 0x10], 0xea67
mov dword ptr [ebp - 0x14], 0x2dfb
mov dword ptr [ebp - 0x18], 0x41e4
mov dword ptr [ebp - 0x1c], 0xf986
sub eax, dword ptr [ebp - 8]
imul eax, dword ptr [ebp - 0xc]
and eax, dword ptr [ebp - 0x10]
imul eax, dword ptr [ebp - 0x14]
and eax, dword ptr [ebp - 0x18]
or eax, dword ptr [ebp - 0x1c]
add esp, 0x1c
pop ebp
ret
'''


print (asm(x))
