stwu r1, -0x20(r1)
stw r31, 0x1c(r1)
mr r31, r1
li r3, 0
stw r3, 0x18(r31)
li r3, 0x319f
stw r3, 0x14(r31)
li r3, 0x692c
stw r3, 0x10(r31)
li r3, 0x28cd
stw r3, 0xc(r31)
lwz r3, 0x14(r31)
lwz r4, 0x10(r31)
or r3, r3, r4
ori r3, r3, 0x65ee
lwz r4, 0xc(r31)
xor r3, r3, r4
lwz r31, 0x1c(r1)
addi r1, r1, 0x20
blr