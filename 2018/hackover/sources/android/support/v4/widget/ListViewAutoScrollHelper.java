package android.support.v4.widget;

import android.support.annotation.NonNull;
import android.widget.ListView;

public class ListViewAutoScrollHelper extends AutoScrollHelper {
    private final ListView mTarget;

    public boolean canTargetScrollHorizontally(int i) {
        return false;
    }

    public ListViewAutoScrollHelper(@NonNull ListView listView) {
        super(listView);
        this.mTarget = listView;
    }

    public void scrollTargetBy(int i, int i2) {
        ListViewCompat.scrollListBy(this.mTarget, i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0038 A:{RETURN} */
    public boolean canTargetScrollVertically(int r7) {
        /*
        r6 = this;
        r6 = r6.mTarget;
        r0 = r6.getCount();
        r1 = 0;
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        return r1;
    L_0x000a:
        r2 = r6.getChildCount();
        r3 = r6.getFirstVisiblePosition();
        r4 = r3 + r2;
        r5 = 1;
        if (r7 <= 0) goto L_0x0029;
    L_0x0017:
        if (r4 < r0) goto L_0x0038;
    L_0x0019:
        r2 = r2 - r5;
        r7 = r6.getChildAt(r2);
        r7 = r7.getBottom();
        r6 = r6.getHeight();
        if (r7 > r6) goto L_0x0038;
    L_0x0028:
        return r1;
    L_0x0029:
        if (r7 >= 0) goto L_0x0039;
    L_0x002b:
        if (r3 > 0) goto L_0x0038;
    L_0x002d:
        r6 = r6.getChildAt(r1);
        r6 = r6.getTop();
        if (r6 < 0) goto L_0x0038;
    L_0x0037:
        return r1;
    L_0x0038:
        return r5;
    L_0x0039:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.ListViewAutoScrollHelper.canTargetScrollVertically(int):boolean");
    }
}
