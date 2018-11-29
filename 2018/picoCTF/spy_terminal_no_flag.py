#!/usr/bin/python2 -u
from Crypto.Cipher import AES
import string
from pwn import *
poss = string.ascii_letters + string.digits + string.punctuation
agent_code = """flag"""

def pad(message):
    if len(message) % 16 != 0:
        message = message + '0'*(16 - len(message)%16 )
    return message

def encrypt(key, plain):
    cipher = AES.new( key.decode('hex'), AES.MODE_ECB )
    return cipher.encrypt(plain).encode('hex')


host = '2018shell2.picoctf.com'
port = 34490
#host = 'localhost'
#port = 4000

sitrep = raw_input("Please enter your situation report: ")
message = """Agent,
Greetings. My situation report is as follows:
{0}
My agent identifying code is: {1}.
Down with the Soviets,
006
""".format( sitrep, agent_code )
message2 = """Agent,
Greetings. My situation report is as follows:
{0}
My agent identifying code is: {1}.
Down with the Soviets,
006
"""
m = message
total_ans = ''
now_ans = ''
payload_offset = ((m.find('follows:\n') + len('follows:\n')))
flag_offset = (m.find('code is: ') + len('code is: '))
print ('1: ',payload_offset)
print ('2: ',flag_offset)
for j in range(100):
    total_ans += now_ans
    m = message2.format(sitrep, total_ans)
    if(j == 0):
        now_ans = 'picoC'
        start = len('picoC')
    else:
        now_ans = ''
        start = 0
    for length in range(start, 16):
        payload = 'a'*11 + m[flag_offset+(16*j)-15+length:][:15][:15-length] + now_ans
        for i in poss:
            print (now_ans + i)
            while(1):
                try:
                    r = remote(host, port)
                    break
                except:
                    pass
            r.recvuntil('report: ')
            trying = payload + i
            trying += 'a'*((16 - ((31 + (16*j) - 15 + length) % 16)) % 16)
            r.sendline(trying)
            ans = r.recvuntil('\n').strip()
            print (ans)
            print (message2.format(trying, agent_code))
            want1 = ans[2*16*4:2*16*5]
            want2 = ans[2*16*(5+int(math.ceil((31+(16*j)-15+length)/16.0))):2*16*(6+int(math.ceil((31+(16*j)-15+length)/16.0)))]
            r.close()
            if(want1 == want2):
                now_ans += i
                if(i == '}'):
                    print (total_ans)
                    exit()
                break


message = pad(message)
#print encrypt( 'a'*16, message )
