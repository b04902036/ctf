#!/usr/bin/python

import qrtools

qr = qrtools.QR()
qr.decode('ans.png')
f = open('record', 'a')
f.write(qr.data + '\n')
f.close()
