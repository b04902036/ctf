#!/usr/bin/python
from pwn import *
import time
from struct import *

# find out all numbers y i.e. 0 <= y ^ x_i < 16 for some x_i in x
def guess(x):
	ret = []
	for i in range(256):
		y = (np.array([i ^ j for j in x]) < 16).all()
		if(y):
			ret.append(y)
	return ret

host = 'aes-128-tsb.hackable.software'
port = 1337
#r = remote(host, port)
counter = 15
match = []
trying = -1
flag = [170, 235, 242, 177, 164, 189, 197, 16, 240, 8, 6, 50, 253, 224, 93, 163, 154, 120, 72, 199, 204, 208, 11, 220, 94, 119, 14, 87, 136, 130, 91, 75, 165, 134, 246, 22, 233, 223, 20, 158, 227, 134, 34, 93, 113, 220, 191, 21, 60, 35, 43, 249, 137, 45, 175, 145, 4, 65, 97, 107, 34, 226, 216, 132, 87, 175, 1, 188, 12, 101, 197, 35, 241, 201, 163, 25, 98, 102, 45, 236, 177, 20, 179, 187, 111, 167, 41, 155, 127, 121, 199, 21, 96, 119, 46, 174]
offset = 0
for offset in range(0, 64, 16):
	partial_flag = ''.join([chr(flag[i+offset+16]) for i in range(16)])
	key = flag[:16]
	new_payload = ''.join([chr(flag[i+offset]) for i in range(16)])
	last_payload = chr(flag[15+offset])
	x = [68, 114, 103, 110, 83, 123, 84, 104, 97, 110, 107, 95, 103, 111, 100]
	x = [x[i] ^ ord(new_payload[i]) for i in range(len(x))]
	def f1():
		total = []
		counter = 15
		trying = -1
		while(counter):
			trying += 1
			print (trying)
			# first let a == ''
			r.send('\x00'*4)

			# now try all possible padding length (a.k.a. the last
			# byte of decrypted msg of b) to find out possible value 			# of last byte of decrypted msg of b
			r.send('\x30\x00\x00\x00')
			r.send(new_payload + chr(trying) + partial_flag[:15] + chr(ord(partial_flag[-1]) ^ trying ^ ord(last_payload)) + new_payload + chr(trying))
			size = unpack('<I', r.recv(4))[0]
			x = r.recv(size)
			if('Looks' in x):
				# which means padding size <= 15
				total.append(trying)
				counter -= 1
		return (total)
	ret1 = f1()	
	def f2(pos):
		for trying in pos:
			for length in range(256):
				print (length)
				# let a become chr(length)
				r.send('\x01\x00\x00\x00')
				r.send(chr(length))
				# try to find out what is last byte of
				# decrypted msg
				r.send('\x30\x00\x00\x00')
				r.send(new_payload + chr(trying) + partial_flag[:15] + chr(ord(partial_flag[-1]) ^ trying ^ ord(last_payload)) + new_payload + chr(trying))
				size = unpack('<I', r.recv(4))[0]
				x = r.recv(size)
				if('Looks' not in x):
					# which means after unpadding the msg
					# become one byte and its value is
					# exactly chr(length)
					return (length, trying)
	ret2_1, ret2_2 = f2(guess(ret1))

	# gradually bruteforce each byte of decrypted msg
	def f3(start, end):
		payload = chr(start)
		for testing in [14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1]:
			trying = end ^ testing
			for length in range(256):
				print (length)
				# let a become payload + chr(length)
				r.send(chr(16 - testing) + '\x00'*3)
				r.send(payload + chr(length))
				
				# fix (16 - testing)th byte of decrypted msg and
				# bruteforce it
				r.send('\x30\x00\x00\x00')
				sending = new_payload + chr(trying) + partial_flag[:15] + chr(ord(partial_flag[-1]) ^ trying ^ ord(last_payload))  + new_payload + chr(trying)
				r.send(sending)
				size = unpack('<I', r.recv(4))[0]
				x = r.recv(size)
				if('Looks' not in x):
					# the (16 - testing)th byte of decrypted
					# msg is exactly chr(length)
					payload += chr(length)
					print ('now payload : ', [ord(i) for i in payload])
					print ('payload length : ', len(payload))
					break
		# this is the final decrypted msg
		print ([ord(i) for i in payload])
	# since ret2_2 can make the padding length become 15, the value of last
	# byte of decrypted msg is exacly ret2_2 ^ 15
	f3(ret2_1, ret2_2 ^ 15)

