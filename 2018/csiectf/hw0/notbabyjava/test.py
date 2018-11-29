# 0x83d 0x9c


f = open('Main2.class', 'rb')
x = f.read()
f.close()
f = open('Main.class', 'wb')
f.write(x[:0x9c])
f.write('Lxxx;')
f.write(x[0x83d:])
f.close()
