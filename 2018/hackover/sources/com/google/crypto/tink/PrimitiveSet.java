package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset.Key;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class PrimitiveSet<P> {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private Entry<P> primary;
    private ConcurrentMap<String, List<Entry<P>>> primitives = new ConcurrentHashMap();

    public static final class Entry<P> {
        private final byte[] identifier;
        private final OutputPrefixType outputPrefixType;
        private final P primitive;
        private final KeyStatusType status;

        public Entry(P p, byte[] bArr, KeyStatusType keyStatusType, OutputPrefixType outputPrefixType) {
            this.primitive = p;
            this.identifier = Arrays.copyOf(bArr, bArr.length);
            this.status = keyStatusType;
            this.outputPrefixType = outputPrefixType;
        }

        public P getPrimitive() {
            return this.primitive;
        }

        public KeyStatusType getStatus() {
            return this.status;
        }

        public OutputPrefixType getOutputPrefixType() {
            return this.outputPrefixType;
        }

        public final byte[] getIdentifier() {
            if (this.identifier == null) {
                return null;
            }
            return Arrays.copyOf(this.identifier, this.identifier.length);
        }
    }

    public Entry<P> getPrimary() {
        return this.primary;
    }

    public List<Entry<P>> getRawPrimitives() throws GeneralSecurityException {
        return getPrimitive(CryptoFormat.RAW_PREFIX);
    }

    public List<Entry<P>> getPrimitive(byte[] bArr) throws GeneralSecurityException {
        List<Entry<P>> list = (List) this.primitives.get(new String(bArr, UTF_8));
        return list != null ? list : Collections.emptyList();
    }

    public Collection<List<Entry<P>>> getAll() throws GeneralSecurityException {
        return this.primitives.values();
    }

    protected static <P> PrimitiveSet<P> newPrimitiveSet() {
        return new PrimitiveSet();
    }

    protected List<Entry<P>> getPrimitive(Key key) throws GeneralSecurityException {
        return getPrimitive(CryptoFormat.getOutputPrefix(key));
    }

    protected void setPrimary(Entry<P> entry) {
        this.primary = entry;
    }

    protected Entry<P> addPrimitive(P p, Key key) throws GeneralSecurityException {
        Entry<P> entry = new Entry(p, CryptoFormat.getOutputPrefix(key), key.getStatus(), key.getOutputPrefixType());
        List arrayList = new ArrayList();
        arrayList.add(entry);
        String str = new String(entry.getIdentifier(), UTF_8);
        arrayList = (List) this.primitives.put(str, Collections.unmodifiableList(arrayList));
        if (arrayList != null) {
            List arrayList2 = new ArrayList();
            arrayList2.addAll(arrayList);
            arrayList2.add(entry);
            this.primitives.put(str, Collections.unmodifiableList(arrayList2));
        }
        return entry;
    }
}
