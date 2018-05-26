# Enjoy
 - problem : in aes-cbc, iv equal to key
 - require : an oracle that given ciphertext, it will return plaintext
 - solve : send (C1, '\x00' * 16, C1), each one is 16 bytes, to the oracle, and get (P1, P2, P3) back, then the key is equal to xor(P1, P3)
