#!/usr/bin/python

x = '7d5c46060d0a392d17131317011a0709382b07014357392d171c262c1a362c42591c2b390f36261838221c060d0a'
x = x.decode('hex')
ans = ['}']
for i in range(1, len(x)):
    try:
        ans.append(chr(ord(x[i]) ^ ord(ans[-1])))
    except:
        pass
ans = ''.join(list(reversed(ans)))
print ans
# flag{Yay_if_th1s_is_yer_f1rst_gnisrever_flag!}

