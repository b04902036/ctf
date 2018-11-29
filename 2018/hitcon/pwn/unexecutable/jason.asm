global _start

section .text

_start:
	mov rax, 1;
	mov rdi, 1;
	mov rsi, msg;
	mov rdx, msglen;
	syscall;
	mov rax, 60;
	mov rdi, 1;
	syscall;


section .rodata
msg: db "hello, world!", 10
msglen: equ $ - msg 

