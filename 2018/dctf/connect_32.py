#!/usr/bin/python
from pwn import *
import time
from struct import *
host = 'localhost'
host = 'aes-128-tsb.hackable.software'
port = 1337
# 110 + 'x'*14 + 210
r = remote(host, port)
counter = 15
match = []
trying = -1
#while(counter):
payload = chr(110)
flag = [170, 235, 242, 177, 164, 189, 197, 16, 240, 8, 6, 50, 253, 224, 93, 163, 154, 120, 72, 199, 204, 208, 11, 220, 94, 119, 14, 87, 136, 130, 91, 75, 165, 134, 246, 22, 233, 223, 20, 158, 227, 134, 34, 93, 113, 220, 191, 21, 60, 35, 43, 249, 137, 45, 175, 145, 4, 65, 97, 107, 34, 226, 216, 132, 87, 175, 1, 188, 12, 101, 197, 35, 241, 201, 163, 25, 98, 102, 45, 236, 177, 20, 179, 187, 111, 167, 41, 155, 127, 121, 199, 21, 96, 119, 46, 174]
offset = 32
flag1 = ''.join([chr(flag[i+offset+16]) for i in range(16)])
new_payload = ''.join([chr(flag[i+offset]) for i in range(15)])
last_payload = chr(flag[15+offset])
x = [ord(i) for i in 'no_one_deployed']
y = [191, 129, 192, 10, 216, 213, 37, 165, 246, 132, 33, 86, 125, 218, 175] 
z = [ord(new_payload[i]) ^ y[i] for i in range(len(y))]
z = ''.join([chr(z[i] ^ x[i]) for i in range(len(z))])
print (z + chr(57 ^ 15 ^ ord('_')))
exit()

def f1():
	total = []
	counter = 15
	trying = 47
	while(counter):
		trying += 1
		print (trying)
		r.send('\x00'*4)
		r.send('\x30\x00\x00\x00')
		r.send(new_payload + chr(trying) + flag1[:15] + chr(ord(flag1[-1]) ^ trying ^ ord(last_payload)) + new_payload + chr(trying))
		size = unpack('<I', r.recv(4))[0]
		x = r.recv(size)
		#print (x)
		if('Looks' in x):
			total.append(trying)
			counter -= 1
	print (total)
	exit()
#f1()

def f2(pos):
	for trying in pos:
		for length in range(256):
			print (length)
			r.send('\x01\x00\x00\x00')
			r.send(chr(length))
			r.send('\x30\x00\x00\x00')
			r.send(new_payload + chr(trying) + flag1[:15] + chr(ord(flag1[-1]) ^ trying ^ ord(last_payload)) + new_payload + chr(trying))
			size = unpack('<I', r.recv(4))[0]
			x = r.recv(size)
			if('Looks' not in x):
				print (trying)
				exit()
#f2((([48, 49, 50, 51, 52, 53, 55, 56, 57, 58, 59, 60, 61, 62, 63])))
def f3(start, end):
	payload = chr(start)
	for testing in [14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1]:
		trying = end ^ testing
		for length in range(256):
			print (length)
			r.send(chr(16 - testing) + '\x00'*3)
			r.send(payload + chr(length))
	
			r.send('\x30\x00\x00\x00')
			#trying += 1
			sending = new_payload + chr(trying) + flag1[:15] + chr(ord(flag1[-1]) ^ trying ^ ord(last_payload))  + new_payload + chr(trying)
			r.send(sending)
			size = unpack('<I', r.recv(4))[0]
			x = r.recv(size)
			#print (x)
			if('Looks' not in x):
				print (trying, length)
				payload += chr(length)
				print ([ord(i) for i in payload])
				print ('payload length : ', len(payload))
				break
	print ([ord(i) for i in payload])
	exit()
f3(191, 57 ^ 15)
'''
now = [110, 140, 199, 209, 101, 206, 168, 95, 28, 89, 213, 190, 165, 56, 68]
target = 'gimme_flag'
target = [ord(i) for i in target]
new_payload = [chr(target[i] ^ now[i]) for i in range(len(target))]
mid_payload = [chr(ord('a') ^ ord(i)) for i in new_payload]
new_payload = ''.join(new_payload).ljust(16, chr(210 ^ 6))
mid_payload = ''.join(mid_payload).ljust(16, chr(ord('a') ^ 210 ^ 6))
r.send('\x0a\x00\x00\x00')
r.send('gimme_flag')
r.send('\x30\x00\x00\x00')
r.send(new_payload + mid_payload + new_payload)
size = unpack('<I', r.recv(4))[0]
x = r.recv(size)
ret = list([ord(i) for i in x])
print (ret)
new_iv = ret[:16]
ret = ret[16:]
r.interactive()
'''
