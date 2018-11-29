#!/usr/bin/python
from pwn import *
import numpy as np
import string

possible = list(string.ascii_letters + string.digits + string.punctuation)
np.random.shuffle(possible)
possible = ''.join(possible)
print (possible)
raw_input('#')

f = open('real_command', 'r')
x = f.read().split('\n')[:-1]
f.close()
np.set_printoptions(threshold=np.inf)
sign0 = 1
flag = ''
progress = 1
now = [ord('f')]
#now = [ord('f'), ord('l'), ord('a'), ord('g'), ord('{')]
while(1):
	progress += 1
	sign0 = 1
	for choice in possible:
		if(sign0 == 0):
			break
		sign = 1
		place = 1
		command_place = 2
		now_now = 1
		arr = np.zeros((100)).astype(np.int16)
		arr[0] = 1
		arr[1] = ord('f')
		new_now = now + [ord(choice)]
		print (''.join([chr(i) for i in new_now]))
		while(sign):
			command_place += 1
			command = x[command_place]
			cm1 = int(command[4:], 16)
			cm2 = int(command[:4], 16)
			if(cm1 == 6):
				if(arr[0] == 1 and now_now == progress):
					sign = 0
					sign0 = 0
					now.append(ord(choice))
					break
				elif(arr[0] == 0):
					sign = 0
					break
				arr[2] = (new_now[now_now])
				now_now += 1
				#sign = 0
				#break
			elif(cm1 == 5):
				print ('wtf')
				exit()
			elif(cm1 == 1):
				place += 1
			elif(cm1 == 2):
				place -= 1
			elif(cm1 == 3):
				arr[place] = (arr[place] + 1) & 0xff
			elif(cm1 == 4):
				arr[place] = (arr[place] - 1) & 0xff
			elif(cm1 == 7):
				if(arr[place] == 0):
					command_place = cm2
			elif(cm1 == 8):
				if(arr[place] != 0):
					command_place = cm2
			#print ('cmd : ', cm1)
			#print (arr)
			#print (place)
			#print (command_place)
			#raw_input('#')
		del arr
		del new_now
print (arr)
print (place)
print (command_place)

