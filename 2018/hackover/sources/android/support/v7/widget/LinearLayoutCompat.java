package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    public static class LayoutParams extends MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinearLayoutCompat_Layout);
            this.weight = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2);
            this.gravity = -1;
            this.weight = f;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
            this.weight = layoutParams.weight;
            this.gravity = layoutParams.gravity;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    int measureNullChild(int i) {
        return 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.LinearLayoutCompat, i, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        i2 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i2 >= 0) {
            setGravity(i2);
        }
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(obtainStyledAttributes.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        obtainStyledAttributes.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            boolean z = false;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    protected void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        int i = 0;
        while (i < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
            i++;
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                virtualChildCount = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                virtualChildCount = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, virtualChildCount);
        }
    }

    void drawDividersHorizontal(Canvas canvas) {
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int i = 0;
        while (i < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i))) {
                int right;
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (isLayoutRtl) {
                    right = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    right = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, right);
            }
            i++;
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (isLayoutRtl) {
                    virtualChildCount = (virtualChildAt2.getLeft() - layoutParams2.leftMargin) - this.mDividerWidth;
                } else {
                    virtualChildCount = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                }
            } else if (isLayoutRtl) {
                virtualChildCount = getPaddingLeft();
            } else {
                virtualChildCount = (getWidth() - getPaddingRight()) - this.mDividerWidth;
            }
            drawVerticalDivider(canvas, virtualChildCount);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public int getBaseline() {
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        if (getChildCount() > this.mBaselineAlignedChildIndex) {
            View childAt = getChildAt(this.mBaselineAlignedChildIndex);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i = this.mBaselineChildTop;
                if (this.mOrientation == 1) {
                    int i2 = this.mGravity & 112;
                    if (i2 != 48) {
                        if (i2 == 16) {
                            i += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                        } else if (i2 == 80) {
                            i = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                        }
                    }
                }
                return (i + ((LayoutParams) childAt.getLayoutParams()).topMargin) + baseline;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        }
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("base aligned child index out of range (0, ");
            stringBuilder.append(getChildCount());
            stringBuilder.append(")");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        this.mBaselineAlignedChildIndex = i;
    }

    View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    protected void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    @RestrictTo({Scope.LIBRARY})
    protected boolean hasDividerBeforeChildAt(int i) {
        boolean z = false;
        if (i == 0) {
            if ((this.mShowDividers & 1) != 0) {
                z = true;
            }
            return z;
        } else if (i == getChildCount()) {
            if ((this.mShowDividers & 4) != 0) {
                z = true;
            }
            return z;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            for (i--; i >= 0; i--) {
                if (getChildAt(i).getVisibility() != 8) {
                    z = true;
                    break;
                }
            }
            return z;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:141:0x0337  */
    void measureVertical(int r41, int r42) {
        /*
        r40 = this;
        r7 = r40;
        r8 = r41;
        r9 = r42;
        r10 = 0;
        r7.mTotalLength = r10;
        r11 = r40.getVirtualChildCount();
        r12 = android.view.View.MeasureSpec.getMode(r41);
        r13 = android.view.View.MeasureSpec.getMode(r42);
        r14 = r7.mBaselineAlignedChildIndex;
        r15 = r7.mUseLargestChild;
        r16 = 0;
        r17 = 1;
        r1 = r10;
        r2 = r1;
        r3 = r2;
        r4 = r3;
        r5 = r4;
        r6 = r5;
        r18 = r6;
        r20 = r18;
        r0 = r16;
        r19 = r17;
    L_0x002b:
        r10 = 8;
        r22 = r4;
        if (r6 >= r11) goto L_0x01a0;
    L_0x0031:
        r4 = r7.getVirtualChildAt(r6);
        if (r4 != 0) goto L_0x0048;
    L_0x0037:
        r4 = r7.mTotalLength;
        r10 = r7.measureNullChild(r6);
        r4 = r4 + r10;
        r7.mTotalLength = r4;
        r32 = r11;
        r31 = r13;
        r4 = r22;
        goto L_0x0194;
    L_0x0048:
        r24 = r1;
        r1 = r4.getVisibility();
        if (r1 != r10) goto L_0x005f;
    L_0x0050:
        r1 = r7.getChildrenSkipCount(r4, r6);
        r6 = r6 + r1;
        r32 = r11;
        r31 = r13;
        r4 = r22;
        r1 = r24;
        goto L_0x0194;
    L_0x005f:
        r1 = r7.hasDividerBeforeChildAt(r6);
        if (r1 == 0) goto L_0x006c;
    L_0x0065:
        r1 = r7.mTotalLength;
        r10 = r7.mDividerHeight;
        r1 = r1 + r10;
        r7.mTotalLength = r1;
    L_0x006c:
        r1 = r4.getLayoutParams();
        r10 = r1;
        r10 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r10;
        r1 = r10.weight;
        r25 = r0 + r1;
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r13 != r1) goto L_0x00a8;
    L_0x007b:
        r0 = r10.height;
        if (r0 != 0) goto L_0x00a8;
    L_0x007f:
        r0 = r10.weight;
        r0 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1));
        if (r0 <= 0) goto L_0x00a8;
    L_0x0085:
        r0 = r7.mTotalLength;
        r1 = r10.topMargin;
        r1 = r1 + r0;
        r26 = r2;
        r2 = r10.bottomMargin;
        r1 = r1 + r2;
        r0 = java.lang.Math.max(r0, r1);
        r7.mTotalLength = r0;
        r0 = r3;
        r3 = r4;
        r33 = r5;
        r32 = r11;
        r31 = r13;
        r18 = r17;
        r13 = r22;
        r8 = r24;
        r29 = r26;
        r11 = r6;
        goto L_0x0118;
    L_0x00a8:
        r26 = r2;
        r0 = r10.height;
        if (r0 != 0) goto L_0x00b9;
    L_0x00ae:
        r0 = r10.weight;
        r0 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1));
        if (r0 <= 0) goto L_0x00b9;
    L_0x00b4:
        r0 = -2;
        r10.height = r0;
        r2 = 0;
        goto L_0x00bb;
    L_0x00b9:
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
    L_0x00bb:
        r23 = 0;
        r0 = (r25 > r16 ? 1 : (r25 == r16 ? 0 : -1));
        if (r0 != 0) goto L_0x00c6;
    L_0x00c1:
        r0 = r7.mTotalLength;
        r27 = r0;
        goto L_0x00c8;
    L_0x00c6:
        r27 = 0;
    L_0x00c8:
        r0 = r40;
        r8 = r24;
        r24 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r1 = r4;
        r28 = r2;
        r29 = r26;
        r2 = r6;
        r9 = r3;
        r3 = r41;
        r30 = r4;
        r32 = r11;
        r31 = r13;
        r13 = r22;
        r11 = r24;
        r4 = r23;
        r33 = r5;
        r5 = r42;
        r11 = r6;
        r6 = r27;
        r0.measureChildBeforeLayout(r1, r2, r3, r4, r5, r6);
        r0 = r28;
        r1 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r0 == r1) goto L_0x00f5;
    L_0x00f3:
        r10.height = r0;
    L_0x00f5:
        r0 = r30.getMeasuredHeight();
        r1 = r7.mTotalLength;
        r2 = r1 + r0;
        r3 = r10.topMargin;
        r2 = r2 + r3;
        r3 = r10.bottomMargin;
        r2 = r2 + r3;
        r3 = r30;
        r4 = r7.getNextLocationOffset(r3);
        r2 = r2 + r4;
        r1 = java.lang.Math.max(r1, r2);
        r7.mTotalLength = r1;
        if (r15 == 0) goto L_0x0117;
    L_0x0112:
        r0 = java.lang.Math.max(r0, r9);
        goto L_0x0118;
    L_0x0117:
        r0 = r9;
    L_0x0118:
        if (r14 < 0) goto L_0x0122;
    L_0x011a:
        r6 = r11 + 1;
        if (r14 != r6) goto L_0x0122;
    L_0x011e:
        r1 = r7.mTotalLength;
        r7.mBaselineChildTop = r1;
    L_0x0122:
        if (r11 >= r14) goto L_0x0133;
    L_0x0124:
        r1 = r10.weight;
        r1 = (r1 > r16 ? 1 : (r1 == r16 ? 0 : -1));
        if (r1 > 0) goto L_0x012b;
    L_0x012a:
        goto L_0x0133;
    L_0x012b:
        r0 = new java.lang.RuntimeException;
        r1 = "A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.";
        r0.<init>(r1);
        throw r0;
    L_0x0133:
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 == r1) goto L_0x0141;
    L_0x0137:
        r1 = r10.width;
        r2 = -1;
        if (r1 != r2) goto L_0x0141;
    L_0x013c:
        r1 = r17;
        r20 = r1;
        goto L_0x0142;
    L_0x0141:
        r1 = 0;
    L_0x0142:
        r2 = r10.leftMargin;
        r4 = r10.rightMargin;
        r2 = r2 + r4;
        r4 = r3.getMeasuredWidth();
        r4 = r4 + r2;
        r5 = r29;
        r5 = java.lang.Math.max(r5, r4);
        r6 = r3.getMeasuredState();
        r6 = android.view.View.combineMeasuredStates(r8, r6);
        if (r19 == 0) goto L_0x0164;
    L_0x015c:
        r8 = r10.width;
        r9 = -1;
        if (r8 != r9) goto L_0x0164;
    L_0x0161:
        r8 = r17;
        goto L_0x0165;
    L_0x0164:
        r8 = 0;
    L_0x0165:
        r9 = r10.weight;
        r9 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1));
        if (r9 <= 0) goto L_0x0177;
    L_0x016b:
        if (r1 == 0) goto L_0x016e;
    L_0x016d:
        goto L_0x016f;
    L_0x016e:
        r2 = r4;
    L_0x016f:
        r4 = java.lang.Math.max(r13, r2);
        r13 = r4;
        r1 = r33;
        goto L_0x0182;
    L_0x0177:
        if (r1 == 0) goto L_0x017c;
    L_0x0179:
        r1 = r33;
        goto L_0x017e;
    L_0x017c:
        r2 = r4;
        goto L_0x0179;
    L_0x017e:
        r1 = java.lang.Math.max(r1, r2);
    L_0x0182:
        r2 = r7.getChildrenSkipCount(r3, r11);
        r2 = r2 + r11;
        r3 = r0;
        r19 = r8;
        r4 = r13;
        r0 = r25;
        r39 = r5;
        r5 = r1;
        r1 = r6;
        r6 = r2;
        r2 = r39;
    L_0x0194:
        r6 = r6 + 1;
        r13 = r31;
        r11 = r32;
        r8 = r41;
        r9 = r42;
        goto L_0x002b;
    L_0x01a0:
        r8 = r1;
        r9 = r3;
        r1 = r5;
        r32 = r11;
        r31 = r13;
        r13 = r22;
        r5 = r2;
        r2 = r7.mTotalLength;
        if (r2 <= 0) goto L_0x01be;
    L_0x01ae:
        r2 = r32;
        r3 = r7.hasDividerBeforeChildAt(r2);
        if (r3 == 0) goto L_0x01c0;
    L_0x01b6:
        r3 = r7.mTotalLength;
        r4 = r7.mDividerHeight;
        r3 = r3 + r4;
        r7.mTotalLength = r3;
        goto L_0x01c0;
    L_0x01be:
        r2 = r32;
    L_0x01c0:
        if (r15 == 0) goto L_0x020f;
    L_0x01c2:
        r3 = r31;
        r4 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r3 == r4) goto L_0x01ca;
    L_0x01c8:
        if (r3 != 0) goto L_0x0211;
    L_0x01ca:
        r4 = 0;
        r7.mTotalLength = r4;
        r4 = 0;
    L_0x01ce:
        if (r4 >= r2) goto L_0x0211;
    L_0x01d0:
        r6 = r7.getVirtualChildAt(r4);
        if (r6 != 0) goto L_0x01e0;
    L_0x01d6:
        r6 = r7.mTotalLength;
        r11 = r7.measureNullChild(r4);
        r6 = r6 + r11;
        r7.mTotalLength = r6;
        goto L_0x020a;
    L_0x01e0:
        r11 = r6.getVisibility();
        if (r11 != r10) goto L_0x01ec;
    L_0x01e6:
        r6 = r7.getChildrenSkipCount(r6, r4);
        r4 = r4 + r6;
        goto L_0x020a;
    L_0x01ec:
        r11 = r6.getLayoutParams();
        r11 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r11;
        r14 = r7.mTotalLength;
        r21 = r14 + r9;
        r10 = r11.topMargin;
        r21 = r21 + r10;
        r10 = r11.bottomMargin;
        r21 = r21 + r10;
        r6 = r7.getNextLocationOffset(r6);
        r6 = r21 + r6;
        r6 = java.lang.Math.max(r14, r6);
        r7.mTotalLength = r6;
    L_0x020a:
        r4 = r4 + 1;
        r10 = 8;
        goto L_0x01ce;
    L_0x020f:
        r3 = r31;
    L_0x0211:
        r4 = r7.mTotalLength;
        r6 = r40.getPaddingTop();
        r10 = r40.getPaddingBottom();
        r6 = r6 + r10;
        r4 = r4 + r6;
        r7.mTotalLength = r4;
        r4 = r7.mTotalLength;
        r6 = r40.getSuggestedMinimumHeight();
        r4 = java.lang.Math.max(r4, r6);
        r10 = r9;
        r6 = r42;
        r9 = 0;
        r4 = android.view.View.resolveSizeAndState(r4, r6, r9);
        r9 = 16777215; // 0xffffff float:2.3509886E-38 double:8.2890456E-317;
        r9 = r9 & r4;
        r11 = r7.mTotalLength;
        r9 = r9 - r11;
        if (r18 != 0) goto L_0x0282;
    L_0x023a:
        if (r9 == 0) goto L_0x0241;
    L_0x023c:
        r11 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1));
        if (r11 <= 0) goto L_0x0241;
    L_0x0240:
        goto L_0x0282;
    L_0x0241:
        r0 = java.lang.Math.max(r1, r13);
        if (r15 == 0) goto L_0x027d;
    L_0x0247:
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r3 == r1) goto L_0x027d;
    L_0x024b:
        r1 = 0;
    L_0x024c:
        if (r1 >= r2) goto L_0x027d;
    L_0x024e:
        r3 = r7.getVirtualChildAt(r1);
        if (r3 == 0) goto L_0x027a;
    L_0x0254:
        r9 = r3.getVisibility();
        r11 = 8;
        if (r9 != r11) goto L_0x025d;
    L_0x025c:
        goto L_0x027a;
    L_0x025d:
        r9 = r3.getLayoutParams();
        r9 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r9;
        r9 = r9.weight;
        r9 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1));
        if (r9 <= 0) goto L_0x027a;
    L_0x0269:
        r9 = r3.getMeasuredWidth();
        r11 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r11);
        r13 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r11);
        r3.measure(r9, r13);
    L_0x027a:
        r1 = r1 + 1;
        goto L_0x024c;
    L_0x027d:
        r1 = r8;
        r11 = r41;
        goto L_0x0381;
    L_0x0282:
        r10 = r7.mWeightSum;
        r10 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1));
        if (r10 <= 0) goto L_0x028a;
    L_0x0288:
        r0 = r7.mWeightSum;
    L_0x028a:
        r10 = 0;
        r7.mTotalLength = r10;
        r11 = r0;
        r0 = r10;
        r39 = r8;
        r8 = r1;
        r1 = r39;
    L_0x0294:
        if (r0 >= r2) goto L_0x0370;
    L_0x0296:
        r13 = r7.getVirtualChildAt(r0);
        r14 = r13.getVisibility();
        r15 = 8;
        if (r14 != r15) goto L_0x02a9;
    L_0x02a2:
        r38 = r3;
        r10 = r11;
        r11 = r41;
        goto L_0x0368;
    L_0x02a9:
        r14 = r13.getLayoutParams();
        r14 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r14;
        r10 = r14.weight;
        r18 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1));
        if (r18 <= 0) goto L_0x030d;
    L_0x02b5:
        r15 = (float) r9;
        r15 = r15 * r10;
        r15 = r15 / r11;
        r15 = (int) r15;
        r11 = r11 - r10;
        r9 = r9 - r15;
        r10 = r40.getPaddingLeft();
        r18 = r40.getPaddingRight();
        r10 = r10 + r18;
        r34 = r9;
        r9 = r14.leftMargin;
        r10 = r10 + r9;
        r9 = r14.rightMargin;
        r10 = r10 + r9;
        r9 = r14.width;
        r35 = r11;
        r11 = r41;
        r9 = getChildMeasureSpec(r11, r10, r9);
        r10 = r14.height;
        if (r10 != 0) goto L_0x02ec;
    L_0x02db:
        r10 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r3 == r10) goto L_0x02e0;
    L_0x02df:
        goto L_0x02ee;
    L_0x02e0:
        if (r15 <= 0) goto L_0x02e3;
    L_0x02e2:
        goto L_0x02e4;
    L_0x02e3:
        r15 = 0;
    L_0x02e4:
        r15 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r10);
        r13.measure(r9, r15);
        goto L_0x02fe;
    L_0x02ec:
        r10 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
    L_0x02ee:
        r18 = r13.getMeasuredHeight();
        r15 = r18 + r15;
        if (r15 >= 0) goto L_0x02f7;
    L_0x02f6:
        r15 = 0;
    L_0x02f7:
        r15 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r10);
        r13.measure(r9, r15);
    L_0x02fe:
        r9 = r13.getMeasuredState();
        r9 = r9 & -256;
        r1 = android.view.View.combineMeasuredStates(r1, r9);
        r9 = r34;
        r10 = r35;
        goto L_0x0310;
    L_0x030d:
        r10 = r11;
        r11 = r41;
    L_0x0310:
        r15 = r14.leftMargin;
        r36 = r1;
        r1 = r14.rightMargin;
        r15 = r15 + r1;
        r1 = r13.getMeasuredWidth();
        r1 = r1 + r15;
        r5 = java.lang.Math.max(r5, r1);
        r37 = r1;
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 == r1) goto L_0x0330;
    L_0x0326:
        r1 = r14.width;
        r38 = r3;
        r3 = -1;
        if (r1 != r3) goto L_0x0333;
    L_0x032d:
        r1 = r17;
        goto L_0x0334;
    L_0x0330:
        r38 = r3;
        r3 = -1;
    L_0x0333:
        r1 = 0;
    L_0x0334:
        if (r1 == 0) goto L_0x0337;
    L_0x0336:
        goto L_0x0339;
    L_0x0337:
        r15 = r37;
    L_0x0339:
        r1 = java.lang.Math.max(r8, r15);
        if (r19 == 0) goto L_0x0346;
    L_0x033f:
        r8 = r14.width;
        if (r8 != r3) goto L_0x0346;
    L_0x0343:
        r8 = r17;
        goto L_0x0347;
    L_0x0346:
        r8 = 0;
    L_0x0347:
        r15 = r7.mTotalLength;
        r18 = r13.getMeasuredHeight();
        r18 = r15 + r18;
        r3 = r14.topMargin;
        r18 = r18 + r3;
        r3 = r14.bottomMargin;
        r18 = r18 + r3;
        r3 = r7.getNextLocationOffset(r13);
        r3 = r18 + r3;
        r3 = java.lang.Math.max(r15, r3);
        r7.mTotalLength = r3;
        r19 = r8;
        r8 = r1;
        r1 = r36;
    L_0x0368:
        r0 = r0 + 1;
        r11 = r10;
        r3 = r38;
        r10 = 0;
        goto L_0x0294;
    L_0x0370:
        r11 = r41;
        r0 = r7.mTotalLength;
        r3 = r40.getPaddingTop();
        r9 = r40.getPaddingBottom();
        r3 = r3 + r9;
        r0 = r0 + r3;
        r7.mTotalLength = r0;
        r0 = r8;
    L_0x0381:
        if (r19 != 0) goto L_0x0388;
    L_0x0383:
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 == r3) goto L_0x0388;
    L_0x0387:
        goto L_0x0389;
    L_0x0388:
        r0 = r5;
    L_0x0389:
        r3 = r40.getPaddingLeft();
        r5 = r40.getPaddingRight();
        r3 = r3 + r5;
        r0 = r0 + r3;
        r3 = r40.getSuggestedMinimumWidth();
        r0 = java.lang.Math.max(r0, r3);
        r0 = android.view.View.resolveSizeAndState(r0, r11, r1);
        r7.setMeasuredDimension(r0, r4);
        if (r20 == 0) goto L_0x03a7;
    L_0x03a4:
        r7.forceUniformWidth(r2, r6);
    L_0x03a7:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.measureVertical(int, int):void");
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x018b  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01dc  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0451  */
    void measureHorizontal(int r45, int r46) {
        /*
        r44 = this;
        r7 = r44;
        r8 = r45;
        r9 = r46;
        r10 = 0;
        r7.mTotalLength = r10;
        r11 = r44.getVirtualChildCount();
        r12 = android.view.View.MeasureSpec.getMode(r45);
        r13 = android.view.View.MeasureSpec.getMode(r46);
        r0 = r7.mMaxAscent;
        r14 = 4;
        if (r0 == 0) goto L_0x001e;
    L_0x001a:
        r0 = r7.mMaxDescent;
        if (r0 != 0) goto L_0x0026;
    L_0x001e:
        r0 = new int[r14];
        r7.mMaxAscent = r0;
        r0 = new int[r14];
        r7.mMaxDescent = r0;
    L_0x0026:
        r15 = r7.mMaxAscent;
        r6 = r7.mMaxDescent;
        r16 = 3;
        r5 = -1;
        r15[r16] = r5;
        r17 = 2;
        r15[r17] = r5;
        r18 = 1;
        r15[r18] = r5;
        r15[r10] = r5;
        r6[r16] = r5;
        r6[r17] = r5;
        r6[r18] = r5;
        r6[r10] = r5;
        r4 = r7.mBaselineAligned;
        r3 = r7.mUseLargestChild;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 != r2) goto L_0x004c;
    L_0x0049:
        r19 = r18;
        goto L_0x004e;
    L_0x004c:
        r19 = r10;
    L_0x004e:
        r20 = 0;
        r1 = r10;
        r14 = r1;
        r21 = r14;
        r22 = r21;
        r23 = r22;
        r24 = r23;
        r26 = r24;
        r28 = r26;
        r27 = r18;
        r0 = r20;
    L_0x0062:
        r29 = r6;
        r5 = 8;
        if (r1 >= r11) goto L_0x0208;
    L_0x0068:
        r6 = r7.getVirtualChildAt(r1);
        if (r6 != 0) goto L_0x007d;
    L_0x006e:
        r5 = r7.mTotalLength;
        r6 = r7.measureNullChild(r1);
        r5 = r5 + r6;
        r7.mTotalLength = r5;
    L_0x0077:
        r32 = r3;
        r36 = r4;
        goto L_0x01f8;
    L_0x007d:
        r10 = r6.getVisibility();
        if (r10 != r5) goto L_0x0089;
    L_0x0083:
        r5 = r7.getChildrenSkipCount(r6, r1);
        r1 = r1 + r5;
        goto L_0x0077;
    L_0x0089:
        r5 = r7.hasDividerBeforeChildAt(r1);
        if (r5 == 0) goto L_0x0096;
    L_0x008f:
        r5 = r7.mTotalLength;
        r10 = r7.mDividerWidth;
        r5 = r5 + r10;
        r7.mTotalLength = r5;
    L_0x0096:
        r5 = r6.getLayoutParams();
        r10 = r5;
        r10 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r10;
        r5 = r10.weight;
        r31 = r0 + r5;
        if (r12 != r2) goto L_0x00ec;
    L_0x00a3:
        r0 = r10.width;
        if (r0 != 0) goto L_0x00ec;
    L_0x00a7:
        r0 = r10.weight;
        r0 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1));
        if (r0 <= 0) goto L_0x00ec;
    L_0x00ad:
        if (r19 == 0) goto L_0x00ba;
    L_0x00af:
        r0 = r7.mTotalLength;
        r5 = r10.leftMargin;
        r2 = r10.rightMargin;
        r5 = r5 + r2;
        r0 = r0 + r5;
        r7.mTotalLength = r0;
        goto L_0x00c8;
    L_0x00ba:
        r0 = r7.mTotalLength;
        r2 = r10.leftMargin;
        r2 = r2 + r0;
        r5 = r10.rightMargin;
        r2 = r2 + r5;
        r0 = java.lang.Math.max(r0, r2);
        r7.mTotalLength = r0;
    L_0x00c8:
        if (r4 == 0) goto L_0x00dd;
    L_0x00ca:
        r0 = 0;
        r2 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r0);
        r6.measure(r2, r2);
        r34 = r1;
        r32 = r3;
        r36 = r4;
        r3 = r6;
        r30 = -2;
        goto L_0x0166;
    L_0x00dd:
        r34 = r1;
        r32 = r3;
        r36 = r4;
        r3 = r6;
        r22 = r18;
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r30 = -2;
        goto L_0x0168;
    L_0x00ec:
        r0 = r10.width;
        if (r0 != 0) goto L_0x00fb;
    L_0x00f0:
        r0 = r10.weight;
        r0 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1));
        if (r0 <= 0) goto L_0x00fb;
    L_0x00f6:
        r5 = -2;
        r10.width = r5;
        r2 = 0;
        goto L_0x00fe;
    L_0x00fb:
        r5 = -2;
        r2 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
    L_0x00fe:
        r0 = (r31 > r20 ? 1 : (r31 == r20 ? 0 : -1));
        if (r0 != 0) goto L_0x0107;
    L_0x0102:
        r0 = r7.mTotalLength;
        r30 = r0;
        goto L_0x0109;
    L_0x0107:
        r30 = 0;
    L_0x0109:
        r33 = 0;
        r0 = r44;
        r34 = r1;
        r1 = r6;
        r35 = r2;
        r2 = r34;
        r32 = r3;
        r3 = r45;
        r36 = r4;
        r4 = r30;
        r30 = r5;
        r9 = -1;
        r5 = r46;
        r37 = r6;
        r9 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r6 = r33;
        r0.measureChildBeforeLayout(r1, r2, r3, r4, r5, r6);
        r0 = r35;
        if (r0 == r9) goto L_0x0130;
    L_0x012e:
        r10.width = r0;
    L_0x0130:
        r0 = r37.getMeasuredWidth();
        if (r19 == 0) goto L_0x0149;
    L_0x0136:
        r1 = r7.mTotalLength;
        r2 = r10.leftMargin;
        r2 = r2 + r0;
        r3 = r10.rightMargin;
        r2 = r2 + r3;
        r3 = r37;
        r4 = r7.getNextLocationOffset(r3);
        r2 = r2 + r4;
        r1 = r1 + r2;
        r7.mTotalLength = r1;
        goto L_0x0160;
    L_0x0149:
        r3 = r37;
        r1 = r7.mTotalLength;
        r2 = r1 + r0;
        r4 = r10.leftMargin;
        r2 = r2 + r4;
        r4 = r10.rightMargin;
        r2 = r2 + r4;
        r4 = r7.getNextLocationOffset(r3);
        r2 = r2 + r4;
        r1 = java.lang.Math.max(r1, r2);
        r7.mTotalLength = r1;
    L_0x0160:
        if (r32 == 0) goto L_0x0166;
    L_0x0162:
        r14 = java.lang.Math.max(r0, r14);
    L_0x0166:
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
    L_0x0168:
        if (r13 == r1) goto L_0x0174;
    L_0x016a:
        r0 = r10.height;
        r2 = -1;
        if (r0 != r2) goto L_0x0174;
    L_0x016f:
        r0 = r18;
        r28 = r0;
        goto L_0x0175;
    L_0x0174:
        r0 = 0;
    L_0x0175:
        r2 = r10.topMargin;
        r4 = r10.bottomMargin;
        r2 = r2 + r4;
        r4 = r3.getMeasuredHeight();
        r4 = r4 + r2;
        r5 = r3.getMeasuredState();
        r6 = r26;
        r5 = android.view.View.combineMeasuredStates(r6, r5);
        if (r36 == 0) goto L_0x01b7;
    L_0x018b:
        r6 = r3.getBaseline();
        r9 = -1;
        if (r6 == r9) goto L_0x01b7;
    L_0x0192:
        r9 = r10.gravity;
        if (r9 >= 0) goto L_0x0199;
    L_0x0196:
        r9 = r7.mGravity;
        goto L_0x019b;
    L_0x0199:
        r9 = r10.gravity;
    L_0x019b:
        r9 = r9 & 112;
        r25 = 4;
        r9 = r9 >> 4;
        r9 = r9 & -2;
        r9 = r9 >> 1;
        r1 = r15[r9];
        r1 = java.lang.Math.max(r1, r6);
        r15[r9] = r1;
        r1 = r29[r9];
        r6 = r4 - r6;
        r1 = java.lang.Math.max(r1, r6);
        r29[r9] = r1;
    L_0x01b7:
        r1 = r21;
        r1 = java.lang.Math.max(r1, r4);
        if (r27 == 0) goto L_0x01c7;
    L_0x01bf:
        r6 = r10.height;
        r9 = -1;
        if (r6 != r9) goto L_0x01c7;
    L_0x01c4:
        r6 = r18;
        goto L_0x01c8;
    L_0x01c7:
        r6 = 0;
    L_0x01c8:
        r9 = r10.weight;
        r9 = (r9 > r20 ? 1 : (r9 == r20 ? 0 : -1));
        if (r9 <= 0) goto L_0x01dc;
    L_0x01ce:
        if (r0 == 0) goto L_0x01d3;
    L_0x01d0:
        r10 = r24;
        goto L_0x01d5;
    L_0x01d3:
        r2 = r4;
        goto L_0x01d0;
    L_0x01d5:
        r24 = java.lang.Math.max(r10, r2);
    L_0x01d9:
        r10 = r34;
        goto L_0x01ea;
    L_0x01dc:
        r10 = r24;
        if (r0 == 0) goto L_0x01e1;
    L_0x01e0:
        r4 = r2;
    L_0x01e1:
        r2 = r23;
        r23 = java.lang.Math.max(r2, r4);
        r24 = r10;
        goto L_0x01d9;
    L_0x01ea:
        r0 = r7.getChildrenSkipCount(r3, r10);
        r0 = r0 + r10;
        r21 = r1;
        r26 = r5;
        r27 = r6;
        r1 = r0;
        r0 = r31;
    L_0x01f8:
        r1 = r1 + 1;
        r6 = r29;
        r3 = r32;
        r4 = r36;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r5 = -1;
        r9 = r46;
        r10 = 0;
        goto L_0x0062;
    L_0x0208:
        r32 = r3;
        r36 = r4;
        r1 = r21;
        r2 = r23;
        r10 = r24;
        r6 = r26;
        r9 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r30 = -2;
        r3 = r7.mTotalLength;
        if (r3 <= 0) goto L_0x0229;
    L_0x021c:
        r3 = r7.hasDividerBeforeChildAt(r11);
        if (r3 == 0) goto L_0x0229;
    L_0x0222:
        r3 = r7.mTotalLength;
        r4 = r7.mDividerWidth;
        r3 = r3 + r4;
        r7.mTotalLength = r3;
    L_0x0229:
        r3 = r15[r18];
        r4 = -1;
        if (r3 != r4) goto L_0x023f;
    L_0x022e:
        r3 = 0;
        r5 = r15[r3];
        if (r5 != r4) goto L_0x023f;
    L_0x0233:
        r3 = r15[r17];
        if (r3 != r4) goto L_0x023f;
    L_0x0237:
        r3 = r15[r16];
        if (r3 == r4) goto L_0x023c;
    L_0x023b:
        goto L_0x023f;
    L_0x023c:
        r38 = r6;
        goto L_0x0272;
    L_0x023f:
        r3 = r15[r16];
        r4 = 0;
        r5 = r15[r4];
        r9 = r15[r18];
        r4 = r15[r17];
        r4 = java.lang.Math.max(r9, r4);
        r4 = java.lang.Math.max(r5, r4);
        r3 = java.lang.Math.max(r3, r4);
        r4 = r29[r16];
        r5 = 0;
        r9 = r29[r5];
        r5 = r29[r18];
        r38 = r6;
        r6 = r29[r17];
        r5 = java.lang.Math.max(r5, r6);
        r5 = java.lang.Math.max(r9, r5);
        r4 = java.lang.Math.max(r4, r5);
        r3 = r3 + r4;
        r21 = java.lang.Math.max(r1, r3);
        r1 = r21;
    L_0x0272:
        if (r32 == 0) goto L_0x02d5;
    L_0x0274:
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r12 == r3) goto L_0x027a;
    L_0x0278:
        if (r12 != 0) goto L_0x02d5;
    L_0x027a:
        r3 = 0;
        r7.mTotalLength = r3;
        r3 = 0;
    L_0x027e:
        if (r3 >= r11) goto L_0x02d5;
    L_0x0280:
        r4 = r7.getVirtualChildAt(r3);
        if (r4 != 0) goto L_0x0290;
    L_0x0286:
        r4 = r7.mTotalLength;
        r5 = r7.measureNullChild(r3);
        r4 = r4 + r5;
        r7.mTotalLength = r4;
        goto L_0x029d;
    L_0x0290:
        r5 = r4.getVisibility();
        r6 = 8;
        if (r5 != r6) goto L_0x02a0;
    L_0x0298:
        r4 = r7.getChildrenSkipCount(r4, r3);
        r3 = r3 + r4;
    L_0x029d:
        r39 = r1;
        goto L_0x02d0;
    L_0x02a0:
        r5 = r4.getLayoutParams();
        r5 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r5;
        if (r19 == 0) goto L_0x02b9;
    L_0x02a8:
        r6 = r7.mTotalLength;
        r9 = r5.leftMargin;
        r9 = r9 + r14;
        r5 = r5.rightMargin;
        r9 = r9 + r5;
        r4 = r7.getNextLocationOffset(r4);
        r9 = r9 + r4;
        r6 = r6 + r9;
        r7.mTotalLength = r6;
        goto L_0x029d;
    L_0x02b9:
        r6 = r7.mTotalLength;
        r9 = r6 + r14;
        r39 = r1;
        r1 = r5.leftMargin;
        r9 = r9 + r1;
        r1 = r5.rightMargin;
        r9 = r9 + r1;
        r1 = r7.getNextLocationOffset(r4);
        r9 = r9 + r1;
        r1 = java.lang.Math.max(r6, r9);
        r7.mTotalLength = r1;
    L_0x02d0:
        r3 = r3 + 1;
        r1 = r39;
        goto L_0x027e;
    L_0x02d5:
        r39 = r1;
        r1 = r7.mTotalLength;
        r3 = r44.getPaddingLeft();
        r4 = r44.getPaddingRight();
        r3 = r3 + r4;
        r1 = r1 + r3;
        r7.mTotalLength = r1;
        r1 = r7.mTotalLength;
        r3 = r44.getSuggestedMinimumWidth();
        r1 = java.lang.Math.max(r1, r3);
        r3 = 0;
        r1 = android.view.View.resolveSizeAndState(r1, r8, r3);
        r3 = 16777215; // 0xffffff float:2.3509886E-38 double:8.2890456E-317;
        r3 = r3 & r1;
        r4 = r7.mTotalLength;
        r3 = r3 - r4;
        if (r22 != 0) goto L_0x0346;
    L_0x02fd:
        if (r3 == 0) goto L_0x0304;
    L_0x02ff:
        r5 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1));
        if (r5 <= 0) goto L_0x0304;
    L_0x0303:
        goto L_0x0346;
    L_0x0304:
        r0 = java.lang.Math.max(r2, r10);
        if (r32 == 0) goto L_0x0340;
    L_0x030a:
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 == r2) goto L_0x0340;
    L_0x030e:
        r2 = 0;
    L_0x030f:
        if (r2 >= r11) goto L_0x0340;
    L_0x0311:
        r3 = r7.getVirtualChildAt(r2);
        if (r3 == 0) goto L_0x033d;
    L_0x0317:
        r5 = r3.getVisibility();
        r6 = 8;
        if (r5 != r6) goto L_0x0320;
    L_0x031f:
        goto L_0x033d;
    L_0x0320:
        r5 = r3.getLayoutParams();
        r5 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r5;
        r5 = r5.weight;
        r5 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1));
        if (r5 <= 0) goto L_0x033d;
    L_0x032c:
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r6 = android.view.View.MeasureSpec.makeMeasureSpec(r14, r5);
        r9 = r3.getMeasuredHeight();
        r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r5);
        r3.measure(r6, r9);
    L_0x033d:
        r2 = r2 + 1;
        goto L_0x030f;
    L_0x0340:
        r42 = r11;
        r3 = r46;
        goto L_0x04ef;
    L_0x0346:
        r5 = r7.mWeightSum;
        r5 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1));
        if (r5 <= 0) goto L_0x034e;
    L_0x034c:
        r0 = r7.mWeightSum;
    L_0x034e:
        r5 = -1;
        r15[r16] = r5;
        r15[r17] = r5;
        r15[r18] = r5;
        r6 = 0;
        r15[r6] = r5;
        r29[r16] = r5;
        r29[r17] = r5;
        r29[r18] = r5;
        r29[r6] = r5;
        r7.mTotalLength = r6;
        r10 = r2;
        r6 = r5;
        r9 = r38;
        r2 = r0;
        r0 = 0;
    L_0x0368:
        if (r0 >= r11) goto L_0x0495;
    L_0x036a:
        r14 = r7.getVirtualChildAt(r0);
        if (r14 == 0) goto L_0x0484;
    L_0x0370:
        r5 = r14.getVisibility();
        r4 = 8;
        if (r5 != r4) goto L_0x037a;
    L_0x0378:
        goto L_0x0484;
    L_0x037a:
        r5 = r14.getLayoutParams();
        r5 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r5;
        r4 = r5.weight;
        r21 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1));
        if (r21 <= 0) goto L_0x03e2;
    L_0x0386:
        r8 = (float) r3;
        r8 = r8 * r4;
        r8 = r8 / r2;
        r8 = (int) r8;
        r2 = r2 - r4;
        r3 = r3 - r8;
        r4 = r44.getPaddingTop();
        r21 = r44.getPaddingBottom();
        r4 = r4 + r21;
        r40 = r2;
        r2 = r5.topMargin;
        r4 = r4 + r2;
        r2 = r5.bottomMargin;
        r4 = r4 + r2;
        r2 = r5.height;
        r41 = r3;
        r42 = r11;
        r3 = r46;
        r11 = -1;
        r2 = getChildMeasureSpec(r3, r4, r2);
        r4 = r5.width;
        if (r4 != 0) goto L_0x03c0;
    L_0x03af:
        r4 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r12 == r4) goto L_0x03b4;
    L_0x03b3:
        goto L_0x03c2;
    L_0x03b4:
        if (r8 <= 0) goto L_0x03b7;
    L_0x03b6:
        goto L_0x03b8;
    L_0x03b7:
        r8 = 0;
    L_0x03b8:
        r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r4);
        r14.measure(r8, r2);
        goto L_0x03d2;
    L_0x03c0:
        r4 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
    L_0x03c2:
        r21 = r14.getMeasuredWidth();
        r8 = r21 + r8;
        if (r8 >= 0) goto L_0x03cb;
    L_0x03ca:
        r8 = 0;
    L_0x03cb:
        r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r4);
        r14.measure(r8, r2);
    L_0x03d2:
        r2 = r14.getMeasuredState();
        r4 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;
        r2 = r2 & r4;
        r9 = android.view.View.combineMeasuredStates(r9, r2);
        r2 = r40;
        r4 = r41;
        goto L_0x03e8;
    L_0x03e2:
        r4 = r3;
        r42 = r11;
        r3 = r46;
        r11 = -1;
    L_0x03e8:
        if (r19 == 0) goto L_0x0407;
    L_0x03ea:
        r8 = r7.mTotalLength;
        r21 = r14.getMeasuredWidth();
        r11 = r5.leftMargin;
        r21 = r21 + r11;
        r11 = r5.rightMargin;
        r21 = r21 + r11;
        r11 = r7.getNextLocationOffset(r14);
        r21 = r21 + r11;
        r8 = r8 + r21;
        r7.mTotalLength = r8;
        r43 = r2;
    L_0x0404:
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        goto L_0x0422;
    L_0x0407:
        r8 = r7.mTotalLength;
        r11 = r14.getMeasuredWidth();
        r11 = r11 + r8;
        r43 = r2;
        r2 = r5.leftMargin;
        r11 = r11 + r2;
        r2 = r5.rightMargin;
        r11 = r11 + r2;
        r2 = r7.getNextLocationOffset(r14);
        r11 = r11 + r2;
        r2 = java.lang.Math.max(r8, r11);
        r7.mTotalLength = r2;
        goto L_0x0404;
    L_0x0422:
        if (r13 == r2) goto L_0x042c;
    L_0x0424:
        r2 = r5.height;
        r8 = -1;
        if (r2 != r8) goto L_0x042c;
    L_0x0429:
        r2 = r18;
        goto L_0x042d;
    L_0x042c:
        r2 = 0;
    L_0x042d:
        r8 = r5.topMargin;
        r11 = r5.bottomMargin;
        r8 = r8 + r11;
        r11 = r14.getMeasuredHeight();
        r11 = r11 + r8;
        r6 = java.lang.Math.max(r6, r11);
        if (r2 == 0) goto L_0x043e;
    L_0x043d:
        goto L_0x043f;
    L_0x043e:
        r8 = r11;
    L_0x043f:
        r2 = java.lang.Math.max(r10, r8);
        if (r27 == 0) goto L_0x044d;
    L_0x0445:
        r8 = r5.height;
        r10 = -1;
        if (r8 != r10) goto L_0x044e;
    L_0x044a:
        r8 = r18;
        goto L_0x044f;
    L_0x044d:
        r10 = -1;
    L_0x044e:
        r8 = 0;
    L_0x044f:
        if (r36 == 0) goto L_0x047c;
    L_0x0451:
        r14 = r14.getBaseline();
        if (r14 == r10) goto L_0x047c;
    L_0x0457:
        r10 = r5.gravity;
        if (r10 >= 0) goto L_0x045e;
    L_0x045b:
        r5 = r7.mGravity;
        goto L_0x0460;
    L_0x045e:
        r5 = r5.gravity;
    L_0x0460:
        r5 = r5 & 112;
        r21 = 4;
        r5 = r5 >> 4;
        r5 = r5 & -2;
        r5 = r5 >> 1;
        r10 = r15[r5];
        r10 = java.lang.Math.max(r10, r14);
        r15[r5] = r10;
        r10 = r29[r5];
        r11 = r11 - r14;
        r10 = java.lang.Math.max(r10, r11);
        r29[r5] = r10;
        goto L_0x047e;
    L_0x047c:
        r21 = 4;
    L_0x047e:
        r10 = r2;
        r27 = r8;
        r2 = r43;
        goto L_0x048b;
    L_0x0484:
        r4 = r3;
        r42 = r11;
        r3 = r46;
        r21 = 4;
    L_0x048b:
        r0 = r0 + 1;
        r3 = r4;
        r11 = r42;
        r5 = -1;
        r8 = r45;
        goto L_0x0368;
    L_0x0495:
        r42 = r11;
        r3 = r46;
        r0 = r7.mTotalLength;
        r2 = r44.getPaddingLeft();
        r4 = r44.getPaddingRight();
        r2 = r2 + r4;
        r0 = r0 + r2;
        r7.mTotalLength = r0;
        r0 = r15[r18];
        r2 = -1;
        if (r0 != r2) goto L_0x04bc;
    L_0x04ac:
        r0 = 0;
        r4 = r15[r0];
        if (r4 != r2) goto L_0x04bc;
    L_0x04b1:
        r0 = r15[r17];
        if (r0 != r2) goto L_0x04bc;
    L_0x04b5:
        r0 = r15[r16];
        if (r0 == r2) goto L_0x04ba;
    L_0x04b9:
        goto L_0x04bc;
    L_0x04ba:
        r0 = r6;
        goto L_0x04ea;
    L_0x04bc:
        r0 = r15[r16];
        r2 = 0;
        r4 = r15[r2];
        r5 = r15[r18];
        r8 = r15[r17];
        r5 = java.lang.Math.max(r5, r8);
        r4 = java.lang.Math.max(r4, r5);
        r0 = java.lang.Math.max(r0, r4);
        r4 = r29[r16];
        r2 = r29[r2];
        r5 = r29[r18];
        r8 = r29[r17];
        r5 = java.lang.Math.max(r5, r8);
        r2 = java.lang.Math.max(r2, r5);
        r2 = java.lang.Math.max(r4, r2);
        r0 = r0 + r2;
        r0 = java.lang.Math.max(r6, r0);
    L_0x04ea:
        r39 = r0;
        r38 = r9;
        r0 = r10;
    L_0x04ef:
        if (r27 != 0) goto L_0x04f7;
    L_0x04f1:
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r13 == r2) goto L_0x04f7;
    L_0x04f5:
        r39 = r0;
    L_0x04f7:
        r0 = r44.getPaddingTop();
        r2 = r44.getPaddingBottom();
        r0 = r0 + r2;
        r0 = r39 + r0;
        r2 = r44.getSuggestedMinimumHeight();
        r0 = java.lang.Math.max(r0, r2);
        r2 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;
        r2 = r38 & r2;
        r1 = r1 | r2;
        r2 = r38 << 16;
        r0 = android.view.View.resolveSizeAndState(r0, r3, r2);
        r7.setMeasuredDimension(r1, r0);
        if (r28 == 0) goto L_0x0521;
    L_0x051a:
        r1 = r42;
        r0 = r45;
        r7.forceUniformHeight(r1, r0);
    L_0x0521:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.measureHorizontal(int, int):void");
    }

    private void forceUniformHeight(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, makeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    void layoutVertical(int i, int i2, int i3, int i4) {
        int paddingTop;
        int paddingLeft = getPaddingLeft();
        int i5 = i3 - i;
        int paddingRight = i5 - getPaddingRight();
        int paddingRight2 = (i5 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        i5 = this.mGravity & 112;
        int i6 = this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i5 == 16) {
            paddingTop = (((i4 - i2) - this.mTotalLength) / 2) + getPaddingTop();
        } else if (i5 != 80) {
            paddingTop = getPaddingTop();
        } else {
            paddingTop = ((getPaddingTop() + i4) - i2) - this.mTotalLength;
        }
        int i7 = 0;
        while (i7 < virtualChildCount) {
            int i8;
            View virtualChildAt = getVirtualChildAt(i7);
            if (virtualChildAt == null) {
                paddingTop += measureNullChild(i7);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                i8 = layoutParams.gravity;
                if (i8 < 0) {
                    i8 = i6;
                }
                i8 = GravityCompat.getAbsoluteGravity(i8, ViewCompat.getLayoutDirection(this)) & 7;
                if (i8 == 1) {
                    i8 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                } else if (i8 != 5) {
                    i8 = layoutParams.leftMargin + paddingLeft;
                } else {
                    i8 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                }
                i5 = i8;
                if (hasDividerBeforeChildAt(i7)) {
                    paddingTop += this.mDividerHeight;
                }
                int i9 = paddingTop + layoutParams.topMargin;
                LayoutParams layoutParams2 = layoutParams;
                setChildFrame(virtualChildAt, i5, i9 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                i7 += getChildrenSkipCount(virtualChildAt, i7);
                paddingTop = i9 + ((measuredHeight + layoutParams2.bottomMargin) + getNextLocationOffset(virtualChildAt));
                i8 = 1;
                i7 += i8;
            }
            i8 = 1;
            i7 += i8;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x010a  */
    void layoutHorizontal(int r28, int r29, int r30, int r31) {
        /*
        r27 = this;
        r6 = r27;
        r2 = android.support.v7.widget.ViewUtils.isLayoutRtl(r27);
        r7 = r27.getPaddingTop();
        r3 = r31 - r29;
        r4 = r27.getPaddingBottom();
        r8 = r3 - r4;
        r3 = r3 - r7;
        r4 = r27.getPaddingBottom();
        r9 = r3 - r4;
        r10 = r27.getVirtualChildCount();
        r3 = r6.mGravity;
        r4 = 8388615; // 0x800007 float:1.1754953E-38 double:4.1445265E-317;
        r3 = r3 & r4;
        r4 = r6.mGravity;
        r11 = r4 & 112;
        r12 = r6.mBaselineAligned;
        r13 = r6.mMaxAscent;
        r14 = r6.mMaxDescent;
        r4 = android.support.v4.view.ViewCompat.getLayoutDirection(r27);
        r3 = android.support.v4.view.GravityCompat.getAbsoluteGravity(r3, r4);
        r15 = 2;
        r5 = 1;
        if (r3 == r5) goto L_0x004e;
    L_0x0039:
        r4 = 5;
        if (r3 == r4) goto L_0x0041;
    L_0x003c:
        r0 = r27.getPaddingLeft();
        goto L_0x0059;
    L_0x0041:
        r3 = r27.getPaddingLeft();
        r3 = r3 + r30;
        r3 = r3 - r28;
        r0 = r6.mTotalLength;
        r0 = r3 - r0;
        goto L_0x0059;
    L_0x004e:
        r3 = r27.getPaddingLeft();
        r0 = r30 - r28;
        r1 = r6.mTotalLength;
        r0 = r0 - r1;
        r0 = r0 / r15;
        r0 = r0 + r3;
    L_0x0059:
        r1 = 0;
        if (r2 == 0) goto L_0x0063;
    L_0x005c:
        r2 = r10 + -1;
        r16 = r2;
        r17 = -1;
        goto L_0x0067;
    L_0x0063:
        r16 = r1;
        r17 = r5;
    L_0x0067:
        r3 = r1;
    L_0x0068:
        if (r3 >= r10) goto L_0x0154;
    L_0x006a:
        r1 = r17 * r3;
        r2 = r16 + r1;
        r1 = r6.getVirtualChildAt(r2);
        if (r1 != 0) goto L_0x0085;
    L_0x0074:
        r1 = r6.measureNullChild(r2);
        r0 = r0 + r1;
        r18 = r5;
        r26 = r7;
        r23 = r10;
        r24 = r11;
    L_0x0081:
        r20 = -1;
        goto L_0x0147;
    L_0x0085:
        r5 = r1.getVisibility();
        r15 = 8;
        if (r5 == r15) goto L_0x013b;
    L_0x008d:
        r15 = r1.getMeasuredWidth();
        r5 = r1.getMeasuredHeight();
        r20 = r1.getLayoutParams();
        r4 = r20;
        r4 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r4;
        if (r12 == 0) goto L_0x00ad;
    L_0x009f:
        r22 = r3;
        r3 = r4.height;
        r23 = r10;
        r10 = -1;
        if (r3 == r10) goto L_0x00b1;
    L_0x00a8:
        r3 = r1.getBaseline();
        goto L_0x00b2;
    L_0x00ad:
        r22 = r3;
        r23 = r10;
    L_0x00b1:
        r3 = -1;
    L_0x00b2:
        r10 = r4.gravity;
        if (r10 >= 0) goto L_0x00b7;
    L_0x00b6:
        r10 = r11;
    L_0x00b7:
        r10 = r10 & 112;
        r24 = r11;
        r11 = 16;
        if (r10 == r11) goto L_0x00f6;
    L_0x00bf:
        r11 = 48;
        if (r10 == r11) goto L_0x00e3;
    L_0x00c3:
        r11 = 80;
        if (r10 == r11) goto L_0x00cc;
    L_0x00c7:
        r3 = r7;
        r11 = -1;
    L_0x00c9:
        r18 = 1;
        goto L_0x0104;
    L_0x00cc:
        r10 = r8 - r5;
        r11 = r4.bottomMargin;
        r10 = r10 - r11;
        r11 = -1;
        if (r3 == r11) goto L_0x00e1;
    L_0x00d4:
        r20 = r1.getMeasuredHeight();
        r20 = r20 - r3;
        r3 = 2;
        r21 = r14[r3];
        r21 = r21 - r20;
        r10 = r10 - r21;
    L_0x00e1:
        r3 = r10;
        goto L_0x00c9;
    L_0x00e3:
        r11 = -1;
        r10 = r4.topMargin;
        r10 = r10 + r7;
        if (r3 == r11) goto L_0x00f2;
    L_0x00e9:
        r18 = 1;
        r20 = r13[r18];
        r20 = r20 - r3;
        r10 = r10 + r20;
        goto L_0x00f4;
    L_0x00f2:
        r18 = 1;
    L_0x00f4:
        r3 = r10;
        goto L_0x0104;
    L_0x00f6:
        r11 = -1;
        r18 = 1;
        r3 = r9 - r5;
        r10 = 2;
        r3 = r3 / r10;
        r3 = r3 + r7;
        r10 = r4.topMargin;
        r3 = r3 + r10;
        r10 = r4.bottomMargin;
        r3 = r3 - r10;
    L_0x0104:
        r10 = r6.hasDividerBeforeChildAt(r2);
        if (r10 == 0) goto L_0x010d;
    L_0x010a:
        r10 = r6.mDividerWidth;
        r0 = r0 + r10;
    L_0x010d:
        r10 = r4.leftMargin;
        r10 = r10 + r0;
        r0 = r6.getLocationOffset(r1);
        r19 = r10 + r0;
        r0 = r27;
        r25 = r1;
        r11 = r2;
        r2 = r19;
        r19 = r22;
        r26 = r7;
        r20 = -1;
        r7 = r4;
        r4 = r15;
        r0.setChildFrame(r1, r2, r3, r4, r5);
        r0 = r7.rightMargin;
        r15 = r15 + r0;
        r0 = r25;
        r1 = r6.getNextLocationOffset(r0);
        r15 = r15 + r1;
        r10 = r10 + r15;
        r0 = r6.getChildrenSkipCount(r0, r11);
        r3 = r19 + r0;
        r0 = r10;
        goto L_0x0147;
    L_0x013b:
        r19 = r3;
        r26 = r7;
        r23 = r10;
        r24 = r11;
        r18 = 1;
        goto L_0x0081;
    L_0x0147:
        r3 = r3 + 1;
        r5 = r18;
        r10 = r23;
        r11 = r24;
        r7 = r26;
        r15 = 2;
        goto L_0x0068;
    L_0x0154:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.layoutHorizontal(int, int, int, int):void");
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK & i) == 0) {
                i |= GravityCompat.START;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i) {
        i &= GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if ((GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK & this.mGravity) != i) {
            this.mGravity = i | (this.mGravity & -8388616);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        i &= 112;
        if ((this.mGravity & 112) != i) {
            this.mGravity = i | (this.mGravity & -113);
            requestLayout();
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -2);
        }
        return this.mOrientation == 1 ? new LayoutParams(-1, -2) : null;
    }

    protected LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }
}
