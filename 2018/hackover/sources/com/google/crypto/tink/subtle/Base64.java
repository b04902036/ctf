package com.google.crypto.tink.subtle;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public final class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    static abstract class Coder {
        public int op;
        public byte[] output;

        public abstract int maxOutputSize(int i);

        public abstract boolean process(byte[] bArr, int i, int i2, boolean z);

        Coder() {
        }
    }

    static class Decoder extends Coder {
        private static final int[] DECODE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] DECODE_WEBSAFE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int EQUALS = -2;
        private static final int SKIP = -1;
        private final int[] alphabet;
        private int state;
        private int value;

        public Decoder(int i, byte[] bArr) {
            this.output = bArr;
            this.alphabet = (i & 8) == 0 ? DECODE : DECODE_WEBSAFE;
            this.state = 0;
            this.value = 0;
        }

        public int maxOutputSize(int i) {
            return ((i * 3) / 4) + 10;
        }

        /* JADX WARNING: Removed duplicated region for block: B:54:0x00ec  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x00e5  */
        /* JADX WARNING: Missing block: B:20:0x0074, code:
            r0 = r13;
     */
        /* JADX WARNING: Missing block: B:45:0x00d8, code:
            r6 = r13;
     */
        public boolean process(byte[] r12, int r13, int r14, boolean r15) {
            /*
            r11 = this;
            r0 = r11.state;
            r1 = 0;
            r2 = 6;
            if (r0 != r2) goto L_0x0007;
        L_0x0006:
            return r1;
        L_0x0007:
            r14 = r14 + r13;
            r0 = r11.state;
            r3 = r11.value;
            r4 = r11.output;
            r5 = r11.alphabet;
            r6 = r3;
            r3 = r1;
        L_0x0012:
            r7 = 4;
            if (r13 >= r14) goto L_0x00e2;
        L_0x0015:
            if (r0 != 0) goto L_0x005c;
        L_0x0017:
            r8 = r13 + 4;
            if (r8 > r14) goto L_0x0058;
        L_0x001b:
            r6 = r12[r13];
            r6 = r6 & 255;
            r6 = r5[r6];
            r6 = r6 << 18;
            r9 = r13 + 1;
            r9 = r12[r9];
            r9 = r9 & 255;
            r9 = r5[r9];
            r9 = r9 << 12;
            r6 = r6 | r9;
            r9 = r13 + 2;
            r9 = r12[r9];
            r9 = r9 & 255;
            r9 = r5[r9];
            r9 = r9 << r2;
            r6 = r6 | r9;
            r9 = r13 + 3;
            r9 = r12[r9];
            r9 = r9 & 255;
            r9 = r5[r9];
            r6 = r6 | r9;
            if (r6 < 0) goto L_0x0058;
        L_0x0043:
            r13 = r3 + 2;
            r9 = (byte) r6;
            r4[r13] = r9;
            r13 = r3 + 1;
            r9 = r6 >> 8;
            r9 = (byte) r9;
            r4[r13] = r9;
            r13 = r6 >> 16;
            r13 = (byte) r13;
            r4[r3] = r13;
            r3 = r3 + 3;
            r13 = r8;
            goto L_0x0017;
        L_0x0058:
            if (r13 < r14) goto L_0x005c;
        L_0x005a:
            goto L_0x00e2;
        L_0x005c:
            r8 = r13 + 1;
            r13 = r12[r13];
            r13 = r13 & 255;
            r13 = r5[r13];
            r9 = -2;
            r10 = -1;
            switch(r0) {
                case 0: goto L_0x00d4;
                case 1: goto L_0x00c7;
                case 2: goto L_0x00ae;
                case 3: goto L_0x007c;
                case 4: goto L_0x0070;
                case 5: goto L_0x006b;
                default: goto L_0x0069;
            };
        L_0x0069:
            goto L_0x00df;
        L_0x006b:
            if (r13 == r10) goto L_0x00df;
        L_0x006d:
            r11.state = r2;
            return r1;
        L_0x0070:
            if (r13 != r9) goto L_0x0077;
        L_0x0072:
            r13 = r0 + 1;
        L_0x0074:
            r0 = r13;
            goto L_0x00df;
        L_0x0077:
            if (r13 == r10) goto L_0x00df;
        L_0x0079:
            r11.state = r2;
            return r1;
        L_0x007c:
            if (r13 < 0) goto L_0x0097;
        L_0x007e:
            r0 = r6 << 6;
            r13 = r13 | r0;
            r0 = r3 + 2;
            r6 = (byte) r13;
            r4[r0] = r6;
            r0 = r3 + 1;
            r6 = r13 >> 8;
            r6 = (byte) r6;
            r4[r0] = r6;
            r0 = r13 >> 16;
            r0 = (byte) r0;
            r4[r3] = r0;
            r3 = r3 + 3;
            r6 = r13;
            r0 = r1;
            goto L_0x00df;
        L_0x0097:
            if (r13 != r9) goto L_0x00a9;
        L_0x0099:
            r13 = r3 + 1;
            r0 = r6 >> 2;
            r0 = (byte) r0;
            r4[r13] = r0;
            r13 = r6 >> 10;
            r13 = (byte) r13;
            r4[r3] = r13;
            r3 = r3 + 2;
            r13 = 5;
            goto L_0x0074;
        L_0x00a9:
            if (r13 == r10) goto L_0x00df;
        L_0x00ab:
            r11.state = r2;
            return r1;
        L_0x00ae:
            if (r13 < 0) goto L_0x00b6;
        L_0x00b0:
            r6 = r6 << 6;
            r13 = r13 | r6;
            r0 = r0 + 1;
            goto L_0x00d8;
        L_0x00b6:
            if (r13 != r9) goto L_0x00c2;
        L_0x00b8:
            r13 = r3 + 1;
            r0 = r6 >> 4;
            r0 = (byte) r0;
            r4[r3] = r0;
            r3 = r13;
            r0 = r7;
            goto L_0x00df;
        L_0x00c2:
            if (r13 == r10) goto L_0x00df;
        L_0x00c4:
            r11.state = r2;
            return r1;
        L_0x00c7:
            if (r13 < 0) goto L_0x00cf;
        L_0x00c9:
            r6 = r6 << 6;
            r13 = r13 | r6;
            r0 = r0 + 1;
            goto L_0x00d8;
        L_0x00cf:
            if (r13 == r10) goto L_0x00df;
        L_0x00d1:
            r11.state = r2;
            return r1;
        L_0x00d4:
            if (r13 < 0) goto L_0x00da;
        L_0x00d6:
            r0 = r0 + 1;
        L_0x00d8:
            r6 = r13;
            goto L_0x00df;
        L_0x00da:
            if (r13 == r10) goto L_0x00df;
        L_0x00dc:
            r11.state = r2;
            return r1;
        L_0x00df:
            r13 = r8;
            goto L_0x0012;
        L_0x00e2:
            r12 = 1;
            if (r15 != 0) goto L_0x00ec;
        L_0x00e5:
            r11.state = r0;
            r11.value = r6;
            r11.op = r3;
            return r12;
        L_0x00ec:
            switch(r0) {
                case 0: goto L_0x010e;
                case 1: goto L_0x010b;
                case 2: goto L_0x0102;
                case 3: goto L_0x00f3;
                case 4: goto L_0x00f0;
                case 5: goto L_0x010e;
                default: goto L_0x00ef;
            };
        L_0x00ef:
            goto L_0x010e;
        L_0x00f0:
            r11.state = r2;
            return r1;
        L_0x00f3:
            r13 = r3 + 1;
            r14 = r6 >> 10;
            r14 = (byte) r14;
            r4[r3] = r14;
            r3 = r13 + 1;
            r14 = r6 >> 2;
            r14 = (byte) r14;
            r4[r13] = r14;
            goto L_0x010e;
        L_0x0102:
            r13 = r3 + 1;
            r14 = r6 >> 4;
            r14 = (byte) r14;
            r4[r3] = r14;
            r3 = r13;
            goto L_0x010e;
        L_0x010b:
            r11.state = r2;
            return r1;
        L_0x010e:
            r11.state = r0;
            r11.op = r3;
            return r12;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.Base64.Decoder.process(byte[], int, int, boolean):boolean");
        }
    }

    static class Encoder extends Coder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final byte[] ENCODE = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
        private static final byte[] ENCODE_WEBSAFE = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
        public static final int LINE_GROUPS = 19;
        private final byte[] alphabet;
        private int count;
        public final boolean doCr;
        public final boolean doNewline;
        public final boolean doPadding;
        private final byte[] tail;
        int tailLen;

        static {
            Class cls = Base64.class;
        }

        public Encoder(int i, byte[] bArr) {
            this.output = bArr;
            boolean z = true;
            this.doPadding = (i & 1) == 0;
            this.doNewline = (i & 2) == 0;
            if ((i & 4) == 0) {
                z = false;
            }
            this.doCr = z;
            this.alphabet = (i & 8) == 0 ? ENCODE : ENCODE_WEBSAFE;
            this.tail = new byte[2];
            this.tailLen = 0;
            this.count = this.doNewline ? 19 : -1;
        }

        public int maxOutputSize(int i) {
            return ((i * 8) / 5) + 10;
        }

        public boolean process(byte[] r19, int r20, int r21, boolean r22) {
            /*
            r18 = this;
            r0 = r18;
            r3 = r0.alphabet;
            r4 = r0.output;
            r5 = r0.count;
            r6 = r21 + r20;
            r7 = r0.tailLen;
            r8 = -1;
            r9 = 0;
            r10 = 1;
            switch(r7) {
                case 0: goto L_0x004f;
                case 1: goto L_0x0030;
                case 2: goto L_0x0013;
                default: goto L_0x0012;
            };
        L_0x0012:
            goto L_0x004f;
        L_0x0013:
            r7 = r20 + 1;
            if (r7 > r6) goto L_0x004f;
        L_0x0017:
            r11 = r0.tail;
            r11 = r11[r9];
            r11 = r11 & 255;
            r11 = r11 << 16;
            r12 = r0.tail;
            r12 = r12[r10];
            r12 = r12 & 255;
            r12 = r12 << 8;
            r11 = r11 | r12;
            r2 = r19[r20];
            r2 = r2 & 255;
            r2 = r2 | r11;
            r0.tailLen = r9;
            goto L_0x0052;
        L_0x0030:
            r7 = r20 + 2;
            if (r7 > r6) goto L_0x004f;
        L_0x0034:
            r7 = r0.tail;
            r7 = r7[r9];
            r7 = r7 & 255;
            r7 = r7 << 16;
            r11 = r20 + 1;
            r2 = r19[r20];
            r2 = r2 & 255;
            r2 = r2 << 8;
            r2 = r2 | r7;
            r7 = r11 + 1;
            r11 = r19[r11];
            r11 = r11 & 255;
            r2 = r2 | r11;
            r0.tailLen = r9;
            goto L_0x0052;
        L_0x004f:
            r7 = r20;
            r2 = r8;
        L_0x0052:
            r12 = 4;
            r13 = 13;
            r14 = 10;
            r15 = 2;
            if (r2 == r8) goto L_0x0090;
        L_0x005a:
            r8 = r2 >> 18;
            r8 = r8 & 63;
            r8 = r3[r8];
            r4[r9] = r8;
            r8 = r2 >> 12;
            r8 = r8 & 63;
            r8 = r3[r8];
            r4[r10] = r8;
            r8 = r2 >> 6;
            r8 = r8 & 63;
            r8 = r3[r8];
            r4[r15] = r8;
            r2 = r2 & 63;
            r2 = r3[r2];
            r8 = 3;
            r4[r8] = r2;
            r5 = r5 + -1;
            if (r5 != 0) goto L_0x008d;
        L_0x007d:
            r2 = r0.doCr;
            if (r2 == 0) goto L_0x0085;
        L_0x0081:
            r2 = 5;
            r4[r12] = r13;
            goto L_0x0086;
        L_0x0085:
            r2 = r12;
        L_0x0086:
            r5 = r2 + 1;
            r4[r2] = r14;
            r2 = 19;
            goto L_0x0092;
        L_0x008d:
            r2 = r5;
            r5 = r12;
            goto L_0x0092;
        L_0x0090:
            r2 = r5;
            r5 = r9;
        L_0x0092:
            r8 = r7 + 3;
            if (r8 > r6) goto L_0x00eb;
        L_0x0096:
            r11 = r19[r7];
            r11 = r11 & 255;
            r11 = r11 << 16;
            r16 = r7 + 1;
            r10 = r19[r16];
            r10 = r10 & 255;
            r10 = r10 << 8;
            r10 = r10 | r11;
            r7 = r7 + 2;
            r7 = r19[r7];
            r7 = r7 & 255;
            r7 = r7 | r10;
            r10 = r7 >> 18;
            r10 = r10 & 63;
            r10 = r3[r10];
            r4[r5] = r10;
            r10 = r5 + 1;
            r11 = r7 >> 12;
            r11 = r11 & 63;
            r11 = r3[r11];
            r4[r10] = r11;
            r10 = r5 + 2;
            r11 = r7 >> 6;
            r11 = r11 & 63;
            r11 = r3[r11];
            r4[r10] = r11;
            r10 = r5 + 3;
            r7 = r7 & 63;
            r7 = r3[r7];
            r4[r10] = r7;
            r5 = r5 + 4;
            r2 = r2 + -1;
            if (r2 != 0) goto L_0x00e8;
        L_0x00d6:
            r2 = r0.doCr;
            if (r2 == 0) goto L_0x00df;
        L_0x00da:
            r2 = r5 + 1;
            r4[r5] = r13;
            goto L_0x00e0;
        L_0x00df:
            r2 = r5;
        L_0x00e0:
            r5 = r2 + 1;
            r4[r2] = r14;
            r7 = r8;
            r2 = 19;
            goto L_0x00e9;
        L_0x00e8:
            r7 = r8;
        L_0x00e9:
            r10 = 1;
            goto L_0x0092;
        L_0x00eb:
            if (r22 == 0) goto L_0x01c9;
        L_0x00ed:
            r8 = r0.tailLen;
            r8 = r7 - r8;
            r10 = r6 + -1;
            if (r8 != r10) goto L_0x013d;
        L_0x00f5:
            r6 = r0.tailLen;
            if (r6 <= 0) goto L_0x00ff;
        L_0x00f9:
            r1 = r0.tail;
            r1 = r1[r9];
            r9 = 1;
            goto L_0x0101;
        L_0x00ff:
            r1 = r19[r7];
        L_0x0101:
            r1 = r1 & 255;
            r1 = r1 << r12;
            r6 = r0.tailLen;
            r6 = r6 - r9;
            r0.tailLen = r6;
            r6 = r5 + 1;
            r7 = r1 >> 6;
            r7 = r7 & 63;
            r7 = r3[r7];
            r4[r5] = r7;
            r5 = r6 + 1;
            r1 = r1 & 63;
            r1 = r3[r1];
            r4[r6] = r1;
            r1 = r0.doPadding;
            if (r1 == 0) goto L_0x0129;
        L_0x011f:
            r1 = r5 + 1;
            r3 = 61;
            r4[r5] = r3;
            r5 = r1 + 1;
            r4[r1] = r3;
        L_0x0129:
            r1 = r0.doNewline;
            if (r1 == 0) goto L_0x01c7;
        L_0x012d:
            r1 = r0.doCr;
            if (r1 == 0) goto L_0x0136;
        L_0x0131:
            r1 = r5 + 1;
            r4[r5] = r13;
            goto L_0x0137;
        L_0x0136:
            r1 = r5;
        L_0x0137:
            r5 = r1 + 1;
            r4[r1] = r14;
            goto L_0x01c7;
        L_0x013d:
            r8 = r0.tailLen;
            r8 = r7 - r8;
            r6 = r6 - r15;
            if (r8 != r6) goto L_0x01ae;
        L_0x0144:
            r6 = r0.tailLen;
            r8 = 1;
            if (r6 <= r8) goto L_0x014f;
        L_0x0149:
            r6 = r0.tail;
            r6 = r6[r9];
            r9 = 1;
            goto L_0x0158;
        L_0x014f:
            r6 = r7 + 1;
            r7 = r19[r7];
            r17 = r7;
            r7 = r6;
            r6 = r17;
        L_0x0158:
            r6 = r6 & 255;
            r6 = r6 << r14;
            r8 = r0.tailLen;
            if (r8 <= 0) goto L_0x0166;
        L_0x015f:
            r1 = r0.tail;
            r7 = r9 + 1;
            r1 = r1[r9];
            goto L_0x0169;
        L_0x0166:
            r1 = r19[r7];
            r7 = r9;
        L_0x0169:
            r1 = r1 & 255;
            r1 = r1 << r15;
            r1 = r1 | r6;
            r6 = r0.tailLen;
            r6 = r6 - r7;
            r0.tailLen = r6;
            r6 = r5 + 1;
            r7 = r1 >> 12;
            r7 = r7 & 63;
            r7 = r3[r7];
            r4[r5] = r7;
            r5 = r6 + 1;
            r7 = r1 >> 6;
            r7 = r7 & 63;
            r7 = r3[r7];
            r4[r6] = r7;
            r6 = r5 + 1;
            r1 = r1 & 63;
            r1 = r3[r1];
            r4[r5] = r1;
            r1 = r0.doPadding;
            if (r1 == 0) goto L_0x0199;
        L_0x0192:
            r1 = r6 + 1;
            r3 = 61;
            r4[r6] = r3;
            goto L_0x019a;
        L_0x0199:
            r1 = r6;
        L_0x019a:
            r3 = r0.doNewline;
            if (r3 == 0) goto L_0x01ac;
        L_0x019e:
            r3 = r0.doCr;
            if (r3 == 0) goto L_0x01a7;
        L_0x01a2:
            r3 = r1 + 1;
            r4[r1] = r13;
            r1 = r3;
        L_0x01a7:
            r3 = r1 + 1;
            r4[r1] = r14;
            r1 = r3;
        L_0x01ac:
            r5 = r1;
            goto L_0x01c7;
        L_0x01ae:
            r1 = r0.doNewline;
            if (r1 == 0) goto L_0x01c7;
        L_0x01b2:
            if (r5 <= 0) goto L_0x01c7;
        L_0x01b4:
            r1 = 19;
            if (r2 == r1) goto L_0x01c7;
        L_0x01b8:
            r1 = r0.doCr;
            if (r1 == 0) goto L_0x01c1;
        L_0x01bc:
            r1 = r5 + 1;
            r4[r5] = r13;
            goto L_0x01c2;
        L_0x01c1:
            r1 = r5;
        L_0x01c2:
            r3 = r1 + 1;
            r4[r1] = r14;
            r5 = r3;
        L_0x01c7:
            r6 = 1;
            goto L_0x01f7;
        L_0x01c9:
            r3 = r6 + -1;
            if (r7 != r3) goto L_0x01da;
        L_0x01cd:
            r3 = r0.tail;
            r4 = r0.tailLen;
            r6 = r4 + 1;
            r0.tailLen = r6;
            r1 = r19[r7];
            r3[r4] = r1;
            goto L_0x01c7;
        L_0x01da:
            r6 = r6 - r15;
            if (r7 != r6) goto L_0x01c7;
        L_0x01dd:
            r3 = r0.tail;
            r4 = r0.tailLen;
            r6 = r4 + 1;
            r0.tailLen = r6;
            r6 = r19[r7];
            r3[r4] = r6;
            r3 = r0.tail;
            r4 = r0.tailLen;
            r6 = r4 + 1;
            r0.tailLen = r6;
            r6 = 1;
            r7 = r7 + r6;
            r1 = r19[r7];
            r3[r4] = r1;
        L_0x01f7:
            r0.op = r5;
            r0.count = r2;
            return r6;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.Base64.Encoder.process(byte[], int, int, boolean):boolean");
        }
    }

    public static byte[] decode(String str) {
        return decode(str, 2);
    }

    public static byte[] urlSafeDecode(String str) {
        return decode(str, 11);
    }

    public static byte[] decode(String str, int i) {
        return decode(str.getBytes(UTF_8), i);
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, 0, bArr.length, i);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        Decoder decoder = new Decoder(i3, new byte[((i2 * 3) / 4)]);
        if (!decoder.process(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (decoder.op == decoder.output.length) {
            return decoder.output;
        } else {
            Object obj = new byte[decoder.op];
            System.arraycopy(decoder.output, 0, obj, 0, decoder.op);
            return obj;
        }
    }

    public static String encode(byte[] bArr) {
        return encodeToString(bArr, 2);
    }

    public static String urlSafeEncode(byte[] bArr) {
        return encodeToString(bArr, 11);
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(encode(bArr, i), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static String encodeToString(byte[] bArr, int i, int i2, int i3) {
        try {
            return new String(encode(bArr, i, i2, i3), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, bArr.length, i);
    }

    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        Encoder encoder = new Encoder(i3, null);
        i3 = (i2 / 3) * 4;
        if (!encoder.doPadding) {
            switch (i2 % 3) {
                case 1:
                    i3 += 2;
                    break;
                case 2:
                    i3 += 3;
                    break;
            }
        } else if (i2 % 3 > 0) {
            i3 += 4;
        }
        if (encoder.doNewline && i2 > 0) {
            i3 += (((i2 - 1) / 57) + 1) * (encoder.doCr ? 2 : 1);
        }
        encoder.output = new byte[i3];
        encoder.process(bArr, i, i2, true);
        return encoder.output;
    }

    private Base64() {
    }
}
