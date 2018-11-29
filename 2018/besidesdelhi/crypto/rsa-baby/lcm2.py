from gmpy2 import lcm
e1 = 114194
e2 = 130478
e3 = 122694
e4 = 79874
ret = (lcm(e1, lcm(e2, lcm(e3, e4))))
e11 = ret // e1
e21 = ret // e2
e31 = ret // e3
e41 = ret // e4
print (e11)
print (e21)
print (e31)
print (e41)
