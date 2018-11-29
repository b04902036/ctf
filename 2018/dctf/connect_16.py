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
offset = 16
flag1 = ''.join([chr(flag[i+offset+16]) for i in range(16)])
key = flag[:16]
new_payload = ''.join([chr(flag[i+offset]) for i in range(15)])
last_payload = chr(flag[15+offset])
x = [176, 101, 112, 198, 241, 206, 0, 208, 90, 105, 9, 103, 150, 136, 91]
y = [68, 114, 103, 110, 83, 123, 84, 104, 97, 110, 107, 95, 103, 111, 100]
z = [x[i] ^ ord(new_payload[i]) for i in range(len(x))]
z = ''.join([chr(y[i] ^ z[i]) for i in range(len(z))])
print (z)
exit()



def f1():
	total = []
	counter = 15
	trying = -1
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
#f2(list(reversed([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15])))
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
f3(176, 15 ^ 15)
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
