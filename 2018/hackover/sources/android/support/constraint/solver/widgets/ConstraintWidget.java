package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor.Strength;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import java.util.ArrayList;

public class ConstraintWidget {
    protected static final int ANCHOR_BASELINE = 4;
    protected static final int ANCHOR_BOTTOM = 3;
    protected static final int ANCHOR_LEFT = 0;
    protected static final int ANCHOR_RIGHT = 1;
    protected static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    protected float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    private int mDrawHeight;
    private int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    boolean mIsHeightWrapContent;
    boolean mIsWidthWrapContent;
    ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    protected ConstraintAnchor[] mListAnchors;
    protected DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    protected ConstraintWidget[] mListNextVisibleWidget;
    int mMatchConstraintDefaultHeight;
    int mMatchConstraintDefaultWidth;
    int mMatchConstraintMaxHeight;
    int mMatchConstraintMaxWidth;
    int mMatchConstraintMinHeight;
    int mMatchConstraintMinWidth;
    float mMatchConstraintPercentHeight;
    float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    protected int mMinHeight;
    protected int mMinWidth;
    protected int mOffsetX;
    protected int mOffsetY;
    ConstraintWidget mParent;
    ResolutionDimension mResolutionHeight;
    ResolutionDimension mResolutionWidth;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    int[] mResolvedMatchConstraintDefault;
    ConstraintAnchor mRight;
    boolean mRightHasCentered;
    ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    float[] mWeight;
    int mWidth;
    private int mWrapHeight;
    private int mWrapWidth;
    protected int mX;
    protected int mY;

    public enum ContentAlignment {
        BEGIN,
        MIDDLE,
        END,
        TOP,
        VERTICAL_MIDDLE,
        BOTTOM,
        LEFT,
        RIGHT
    }

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public void connectedTo(ConstraintWidget constraintWidget) {
    }

    public void resolve() {
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int i) {
        this.mMaxDimension[0] = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxDimension[1] = i;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        this.mWeight[0] = -1.0f;
        this.mWeight[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMaxDimension[0] = Integer.MAX_VALUE;
        this.mMaxDimension[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        if (this.mResolutionWidth != null) {
            this.mResolutionWidth.reset();
        }
        if (this.mResolutionHeight != null) {
            this.mResolutionHeight.reset();
        }
    }

    public void resetResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().reset();
        }
    }

    public void updateResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().update();
        }
    }

    public void analyze(int i) {
        Optimizer.analyze(i, this);
    }

    public boolean isFullyResolved() {
        if (this.mLeft.getResolutionNode().state == 1 && this.mRight.getResolutionNode().state == 1 && this.mTop.getResolutionNode().state == 1 && this.mBottom.getResolutionNode().state == 1) {
            return true;
        }
        return false;
    }

    public ResolutionDimension getResolutionWidth() {
        if (this.mResolutionWidth == null) {
            this.mResolutionWidth = new ResolutionDimension();
        }
        return this.mResolutionWidth;
    }

    public ResolutionDimension getResolutionHeight() {
        if (this.mResolutionHeight == null) {
            this.mResolutionHeight = new ResolutionDimension();
        }
        return this.mResolutionHeight;
    }

    public ConstraintWidget() {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.mLeft = new ConstraintAnchor(this, Type.LEFT);
        this.mTop = new ConstraintAnchor(this, Type.TOP);
        this.mRight = new ConstraintAnchor(this, Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mListNextVisibleWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    public ConstraintWidget(int i, int i2, int i3, int i4) {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.mLeft = new ConstraintAnchor(this, Type.LEFT);
        this.mTop = new ConstraintAnchor(this, Type.TOP);
        this.mRight = new ConstraintAnchor(this, Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mListNextVisibleWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.mX = i;
        this.mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
        addAnchors();
        forceUpdateDrawPosition();
    }

    public ConstraintWidget(int i, int i2) {
        this(0, 0, i, i2);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isRootContainer() {
        return (this instanceof ConstraintWidgetContainer) && (this.mParent == null || !(this.mParent instanceof ConstraintWidgetContainer));
    }

    public boolean isInsideConstraintLayout() {
        this = getParent();
        if (this == null) {
            return false;
        }
        while (this != null) {
            if (this instanceof ConstraintWidgetContainer) {
                return true;
            }
            this = this.getParent();
        }
        return false;
    }

    public boolean hasAncestor(ConstraintWidget constraintWidget) {
        this = getParent();
        if (this == constraintWidget) {
            return true;
        }
        if (this == constraintWidget.getParent()) {
            return false;
        }
        while (this != null) {
            if (this == constraintWidget || this == constraintWidget.getParent()) {
                return true;
            }
            this = this.getParent();
        }
        return false;
    }

    public WidgetContainer getRootWidgetContainer() {
        while (this.getParent() != null) {
            this = this.getParent();
        }
        return this instanceof WidgetContainer ? (WidgetContainer) this : null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i) {
        immediateConnect(Type.CENTER, constraintWidget, Type.CENTER, i, 0);
        this.mCircleConstraintAngle = f;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(".left");
        createObjectVariable.setName(stringBuilder.toString());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(".top");
        createObjectVariable2.setName(stringBuilder2.toString());
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(".right");
        createObjectVariable3.setName(stringBuilder2.toString());
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(".bottom");
        createObjectVariable4.setName(stringBuilder2.toString());
        if (this.mBaselineDistance > 0) {
            SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(this.mBaseline);
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str);
            stringBuilder3.append(".baseline");
            createObjectVariable5.setName(stringBuilder3.toString());
        }
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        StringBuilder stringBuilder;
        String stringBuilder2;
        StringBuilder stringBuilder3 = new StringBuilder();
        if (this.mType != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("type: ");
            stringBuilder.append(this.mType);
            stringBuilder.append(" ");
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder2 = "";
        }
        stringBuilder3.append(stringBuilder2);
        if (this.mDebugName != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("id: ");
            stringBuilder.append(this.mDebugName);
            stringBuilder.append(" ");
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder2 = "";
        }
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append("(");
        stringBuilder3.append(this.mX);
        stringBuilder3.append(", ");
        stringBuilder3.append(this.mY);
        stringBuilder3.append(") - (");
        stringBuilder3.append(this.mWidth);
        stringBuilder3.append(" x ");
        stringBuilder3.append(this.mHeight);
        stringBuilder3.append(") wrap: (");
        stringBuilder3.append(this.mWrapWidth);
        stringBuilder3.append(" x ");
        stringBuilder3.append(this.mWrapHeight);
        stringBuilder3.append(")");
        return stringBuilder3.toString();
    }

    int getInternalDrawX() {
        return this.mDrawX;
    }

    int getInternalDrawY() {
        return this.mDrawY;
    }

    public int getInternalDrawRight() {
        return this.mDrawX + this.mDrawWidth;
    }

    public int getInternalDrawBottom() {
        return this.mDrawY + this.mDrawHeight;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int i = this.mWidth;
        if (this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i;
        }
        if (this.mMatchConstraintDefaultWidth == 1) {
            i = Math.max(this.mMatchConstraintMinWidth, i);
        } else if (this.mMatchConstraintMinWidth > 0) {
            i = this.mMatchConstraintMinWidth;
            this.mWidth = i;
        } else {
            i = 0;
        }
        return (this.mMatchConstraintMaxWidth <= 0 || this.mMatchConstraintMaxWidth >= i) ? i : this.mMatchConstraintMaxWidth;
    }

    public int getOptimizerWrapHeight() {
        int i = this.mHeight;
        if (this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i;
        }
        if (this.mMatchConstraintDefaultHeight == 1) {
            i = Math.max(this.mMatchConstraintMinHeight, i);
        } else if (this.mMatchConstraintMinHeight > 0) {
            i = this.mMatchConstraintMinHeight;
            this.mHeight = i;
        } else {
            i = 0;
        }
        return (this.mMatchConstraintMaxHeight <= 0 || this.mMatchConstraintMaxHeight >= i) ? i : this.mMatchConstraintMaxHeight;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    public int getDrawWidth() {
        return this.mDrawWidth;
    }

    public int getDrawHeight() {
        return this.mDrawHeight;
    }

    public int getDrawBottom() {
        return getDrawY() + this.mDrawHeight;
    }

    public int getDrawRight() {
        return getDrawX() + this.mDrawWidth;
    }

    protected int getRootX() {
        return this.mX + this.mOffsetX;
    }

    protected int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void setGoneMargin(Type type, int i) {
        switch (type) {
            case LEFT:
                this.mLeft.mGoneMargin = i;
                return;
            case TOP:
                this.mTop.mGoneMargin = i;
                return;
            case RIGHT:
                this.mRight.mGoneMargin = i;
                return;
            case BOTTOM:
                this.mBottom.mGoneMargin = i;
                return;
            default:
                return;
        }
    }

    public void updateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        int i4 = this.mY + this.mHeight;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = i4 - i2;
    }

    public void forceUpdateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        int i4 = this.mY + this.mHeight;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = i4 - i2;
    }

    public void setDrawOrigin(int i, int i2) {
        this.mDrawX = i - this.mOffsetX;
        this.mDrawY = i2 - this.mOffsetY;
        this.mX = this.mDrawX;
        this.mY = this.mDrawY;
    }

    public void setDrawX(int i) {
        this.mDrawX = i - this.mOffsetX;
        this.mX = this.mDrawX;
    }

    public void setDrawY(int i) {
        this.mDrawY = i - this.mOffsetY;
        this.mY = this.mDrawY;
    }

    public void setDrawWidth(int i) {
        this.mDrawWidth = i;
    }

    public void setDrawHeight(int i) {
        this.mDrawHeight = i;
    }

    public void setWidth(int i) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        this.mMatchConstraintMaxWidth = i3;
        this.mMatchConstraintPercentWidth = f;
        if (f < 1.0f && this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        this.mMatchConstraintMaxHeight = i3;
        this.mMatchConstraintPercentHeight = f;
        if (f < 1.0f && this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    public void setDimensionRatio(String str) {
        if (str == null || str.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        float parseFloat;
        int i = -1;
        int length = str.length();
        int indexOf = str.indexOf(44);
        int i2 = 0;
        if (indexOf > 0 && indexOf < length - 1) {
            String substring = str.substring(0, indexOf);
            if (substring.equalsIgnoreCase("W")) {
                i = 0;
            } else if (substring.equalsIgnoreCase("H")) {
                i = 1;
            }
            i2 = indexOf + 1;
        }
        indexOf = str.indexOf(58);
        if (indexOf < 0 || indexOf >= length - 1) {
            str = str.substring(i2);
            if (str.length() > 0) {
                parseFloat = Float.parseFloat(str);
            }
        } else {
            String substring2 = str.substring(i2, indexOf);
            str = str.substring(indexOf + 1);
            if (substring2.length() > 0 && str.length() > 0) {
                try {
                    float parseFloat2 = Float.parseFloat(substring2);
                    parseFloat = Float.parseFloat(str);
                    if (parseFloat2 > 0.0f && parseFloat > 0.0f) {
                        parseFloat = i == 1 ? Math.abs(parseFloat / parseFloat2) : Math.abs(parseFloat2 / parseFloat);
                    }
                } catch (NumberFormatException unused) {
                    parseFloat = 0.0f;
                }
            }
        }
        if (parseFloat > 0.0f) {
            this.mDimensionRatio = parseFloat;
            this.mDimensionRatioSide = i;
        }
    }

    public void setDimensionRatio(float f, int i) {
        this.mDimensionRatio = f;
        this.mDimensionRatioSide = i;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setWrapWidth(int i) {
        this.mWrapWidth = i;
    }

    public void setWrapHeight(int i) {
        this.mWrapHeight = i;
    }

    public void setDimension(int i, int i2) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
        this.mHeight = i2;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        i3 -= i;
        i4 -= i2;
        this.mX = i;
        this.mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i3 < this.mWidth) {
            i3 = this.mWidth;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i4 < this.mHeight) {
            i4 = this.mHeight;
        }
        this.mWidth = i3;
        this.mHeight = i4;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        this.mWidth = i2 - i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.mY = i;
        this.mHeight = i2 - i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i) {
        if (i >= 0) {
            this.mContainerItemSkip = i;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(Type type, ConstraintWidget constraintWidget, Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, Strength.STRONG, 0, true);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2) {
        connect(constraintAnchor, constraintAnchor2, i, Strength.STRONG, i2);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        connect(constraintAnchor, constraintAnchor2, i, Strength.STRONG, 0);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, Strength strength, int i2) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i, strength, i2);
        }
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2, int i) {
        connect(type, constraintWidget, type2, i, Strength.STRONG);
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2) {
        connect(type, constraintWidget, type2, 0, Strength.STRONG);
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2, int i, Strength strength) {
        connect(type, constraintWidget, type2, i, strength, 0);
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2, int i, Strength strength, int i2) {
        Type type3 = type;
        ConstraintWidget constraintWidget2 = constraintWidget;
        Type type4 = type2;
        int i3 = i2;
        int i4 = 0;
        ConstraintAnchor anchor;
        ConstraintAnchor anchor2;
        ConstraintAnchor anchor3;
        if (type3 == Type.CENTER) {
            ConstraintWidget constraintWidget3;
            Strength strength2;
            int i5;
            Type type5;
            if (type4 == Type.CENTER) {
                int i6;
                anchor = getAnchor(Type.LEFT);
                anchor2 = getAnchor(Type.RIGHT);
                ConstraintAnchor anchor4 = getAnchor(Type.TOP);
                ConstraintAnchor anchor5 = getAnchor(Type.BOTTOM);
                int i7 = 1;
                if ((anchor == null || !anchor.isConnected()) && (anchor2 == null || !anchor2.isConnected())) {
                    constraintWidget3 = constraintWidget;
                    strength2 = strength;
                    i5 = i2;
                    connect(Type.LEFT, constraintWidget3, Type.LEFT, 0, strength2, i5);
                    connect(Type.RIGHT, constraintWidget3, Type.RIGHT, 0, strength2, i5);
                    i6 = 1;
                } else {
                    i6 = 0;
                }
                if ((anchor4 == null || !anchor4.isConnected()) && (anchor5 == null || !anchor5.isConnected())) {
                    constraintWidget3 = constraintWidget;
                    strength2 = strength;
                    i5 = i2;
                    connect(Type.TOP, constraintWidget3, Type.TOP, 0, strength2, i5);
                    connect(Type.BOTTOM, constraintWidget3, Type.BOTTOM, 0, strength2, i5);
                } else {
                    i7 = 0;
                }
                if (i6 != 0 && i7 != 0) {
                    getAnchor(Type.CENTER).connect(constraintWidget2.getAnchor(Type.CENTER), 0, i3);
                } else if (i6 != 0) {
                    getAnchor(Type.CENTER_X).connect(constraintWidget2.getAnchor(Type.CENTER_X), 0, i3);
                } else if (i7 != 0) {
                    getAnchor(Type.CENTER_Y).connect(constraintWidget2.getAnchor(Type.CENTER_Y), 0, i3);
                }
            } else if (type4 == Type.LEFT || type4 == Type.RIGHT) {
                constraintWidget3 = constraintWidget;
                type5 = type2;
                strength2 = strength;
                i5 = i2;
                connect(Type.LEFT, constraintWidget3, type5, 0, strength2, i5);
                connect(Type.RIGHT, constraintWidget3, type5, 0, strength2, i5);
                getAnchor(Type.CENTER).connect(constraintWidget.getAnchor(type2), 0, i3);
            } else if (type4 == Type.TOP || type4 == Type.BOTTOM) {
                constraintWidget3 = constraintWidget;
                type5 = type2;
                strength2 = strength;
                i5 = i2;
                connect(Type.TOP, constraintWidget3, type5, 0, strength2, i5);
                connect(Type.BOTTOM, constraintWidget3, type5, 0, strength2, i5);
                getAnchor(Type.CENTER).connect(constraintWidget.getAnchor(type2), 0, i3);
            }
        } else if (type3 == Type.CENTER_X && (type4 == Type.LEFT || type4 == Type.RIGHT)) {
            anchor = getAnchor(Type.LEFT);
            anchor2 = constraintWidget.getAnchor(type2);
            anchor3 = getAnchor(Type.RIGHT);
            anchor.connect(anchor2, 0, i3);
            anchor3.connect(anchor2, 0, i3);
            getAnchor(Type.CENTER_X).connect(anchor2, 0, i3);
        } else if (type3 == Type.CENTER_Y && (type4 == Type.TOP || type4 == Type.BOTTOM)) {
            anchor = constraintWidget.getAnchor(type2);
            getAnchor(Type.TOP).connect(anchor, 0, i3);
            getAnchor(Type.BOTTOM).connect(anchor, 0, i3);
            getAnchor(Type.CENTER_Y).connect(anchor, 0, i3);
        } else if (type3 == Type.CENTER_X && type4 == Type.CENTER_X) {
            getAnchor(Type.LEFT).connect(constraintWidget2.getAnchor(Type.LEFT), 0, i3);
            getAnchor(Type.RIGHT).connect(constraintWidget2.getAnchor(Type.RIGHT), 0, i3);
            getAnchor(Type.CENTER_X).connect(constraintWidget.getAnchor(type2), 0, i3);
        } else if (type3 == Type.CENTER_Y && type4 == Type.CENTER_Y) {
            getAnchor(Type.TOP).connect(constraintWidget2.getAnchor(Type.TOP), 0, i3);
            getAnchor(Type.BOTTOM).connect(constraintWidget2.getAnchor(Type.BOTTOM), 0, i3);
            getAnchor(Type.CENTER_Y).connect(constraintWidget.getAnchor(type2), 0, i3);
        } else {
            anchor2 = getAnchor(type);
            anchor3 = constraintWidget.getAnchor(type2);
            if (anchor2.isValidConnection(anchor3)) {
                ConstraintAnchor anchor6;
                if (type3 == Type.BASELINE) {
                    anchor = getAnchor(Type.TOP);
                    anchor6 = getAnchor(Type.BOTTOM);
                    if (anchor != null) {
                        anchor.reset();
                    }
                    if (anchor6 != null) {
                        anchor6.reset();
                    }
                } else {
                    if (type3 == Type.TOP || type3 == Type.BOTTOM) {
                        anchor6 = getAnchor(Type.BASELINE);
                        if (anchor6 != null) {
                            anchor6.reset();
                        }
                        anchor6 = getAnchor(Type.CENTER);
                        if (anchor6.getTarget() != anchor3) {
                            anchor6.reset();
                        }
                        anchor = getAnchor(type).getOpposite();
                        anchor6 = getAnchor(Type.CENTER_Y);
                        if (anchor6.isConnected()) {
                            anchor.reset();
                            anchor6.reset();
                        }
                    } else if (type3 == Type.LEFT || type3 == Type.RIGHT) {
                        anchor6 = getAnchor(Type.CENTER);
                        if (anchor6.getTarget() != anchor3) {
                            anchor6.reset();
                        }
                        anchor = getAnchor(type).getOpposite();
                        anchor6 = getAnchor(Type.CENTER_X);
                        if (anchor6.isConnected()) {
                            anchor.reset();
                            anchor6.reset();
                        }
                    }
                    i4 = i;
                }
                anchor2.connect(anchor3, i4, strength, i3);
                anchor3.getOwner().connectedTo(anchor2.getOwner());
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
        if (!(this instanceof ConstraintWidgetContainer)) {
            if (getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (getWidth() == getWrapWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                } else if (getWidth() > getMinWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
            }
            if (getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (getHeight() == getWrapHeight()) {
                    setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                } else if (getHeight() > getMinHeight()) {
                    setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
            }
        }
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() == null || !(getParent() instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            ConstraintAnchor anchor = getAnchor(Type.LEFT);
            ConstraintAnchor anchor2 = getAnchor(Type.RIGHT);
            ConstraintAnchor anchor3 = getAnchor(Type.TOP);
            ConstraintAnchor anchor4 = getAnchor(Type.BOTTOM);
            ConstraintAnchor anchor5 = getAnchor(Type.CENTER);
            ConstraintAnchor anchor6 = getAnchor(Type.CENTER_X);
            ConstraintAnchor anchor7 = getAnchor(Type.CENTER_Y);
            if (constraintAnchor == anchor5) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor.reset();
                    anchor2.reset();
                }
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor6) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                    anchor.reset();
                    anchor2.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor7) {
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor || constraintAnchor == anchor2) {
                if (anchor.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor5.reset();
                }
            } else if ((constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                anchor5.reset();
            }
            constraintAnchor.reset();
        }
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i = 0; i < size; i++) {
                ((ConstraintAnchor) this.mAnchors.get(i)).reset();
            }
        }
    }

    public void resetAnchors(int i) {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintAnchor constraintAnchor = (ConstraintAnchor) this.mAnchors.get(i2);
                if (i == constraintAnchor.getConnectionCreator()) {
                    if (constraintAnchor.isVerticalAnchor()) {
                        setVerticalBiasPercent(DEFAULT_BIAS);
                    } else {
                        setHorizontalBiasPercent(DEFAULT_BIAS);
                    }
                    constraintAnchor.reset();
                }
            }
        }
    }

    public void disconnectWidget(ConstraintWidget constraintWidget) {
        ArrayList anchors = getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) anchors.get(i);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget) {
                constraintAnchor.reset();
            }
        }
    }

    public void disconnectUnlockedWidget(ConstraintWidget constraintWidget) {
        ArrayList anchors = getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) anchors.get(i);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget && constraintAnchor.getConnectionCreator() == 2) {
                constraintAnchor.reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(Type type) {
        switch (type) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER:
                return this.mCenter;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case NONE:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public boolean isInHorizontalChain() {
        return (this.mLeft.mTarget != null && this.mLeft.mTarget.mTarget == this.mLeft) || (this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight);
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintWidget constraintWidget3;
            ConstraintAnchor anchor = constraintWidget.getAnchor(Type.LEFT);
            if (anchor == null) {
                anchor = null;
            } else {
                anchor = anchor.getTarget();
            }
            if (anchor == null) {
                constraintWidget3 = null;
            } else {
                constraintWidget3 = anchor.getOwner();
            }
            if (constraintWidget3 == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor constraintAnchor;
            if (constraintWidget3 == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = constraintWidget3.getAnchor(Type.RIGHT).getTarget();
            }
            if (constraintAnchor == null || constraintAnchor.getOwner() == constraintWidget) {
                constraintWidget = constraintWidget3;
            } else {
                constraintWidget2 = constraintWidget;
            }
        }
        return constraintWidget2;
    }

    public boolean isInVerticalChain() {
        return (this.mTop.mTarget != null && this.mTop.mTarget.mTarget == this.mTop) || (this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom);
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintWidget constraintWidget3;
            ConstraintAnchor anchor = constraintWidget.getAnchor(Type.TOP);
            if (anchor == null) {
                anchor = null;
            } else {
                anchor = anchor.getTarget();
            }
            if (anchor == null) {
                constraintWidget3 = null;
            } else {
                constraintWidget3 = anchor.getOwner();
            }
            if (constraintWidget3 == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor constraintAnchor;
            if (constraintWidget3 == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = constraintWidget3.getAnchor(Type.BOTTOM).getTarget();
            }
            if (constraintAnchor == null || constraintAnchor.getOwner() == constraintWidget) {
                constraintWidget = constraintWidget3;
            } else {
                constraintWidget2 = constraintWidget;
            }
        }
        return constraintWidget2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:183:0x0352  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x0347  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x0362  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x0358  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x03c2  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x0399  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x03cc  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02cc  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02de  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02dd A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x023d  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x022f  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0249  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02cc  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02dd A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02de  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x022f  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x023d  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0249  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02cc  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02de  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02dd A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x023d  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x022f  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0249  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02cc  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02dd A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02de  */
    /* JADX WARNING: Missing block: B:131:0x0236, code:
            if (r15.mResolvedDimensionRatioSide == -1) goto L_0x023a;
     */
    public void addToSolver(android.support.constraint.solver.LinearSystem r39) {
        /*
        r38 = this;
        r15 = r38;
        r14 = r39;
        r0 = r15.mLeft;
        r21 = r14.createObjectVariable(r0);
        r0 = r15.mRight;
        r10 = r14.createObjectVariable(r0);
        r0 = r15.mTop;
        r6 = r14.createObjectVariable(r0);
        r0 = r15.mBottom;
        r4 = r14.createObjectVariable(r0);
        r0 = r15.mBaseline;
        r3 = r14.createObjectVariable(r0);
        r0 = r15.mParent;
        r1 = 8;
        r2 = 1;
        r13 = 0;
        if (r0 == 0) goto L_0x0124;
    L_0x002a:
        r0 = r15.mParent;
        if (r0 == 0) goto L_0x003a;
    L_0x002e:
        r0 = r15.mParent;
        r0 = r0.mListDimensionBehaviors;
        r0 = r0[r13];
        r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r0 != r5) goto L_0x003a;
    L_0x0038:
        r0 = r2;
        goto L_0x003b;
    L_0x003a:
        r0 = r13;
    L_0x003b:
        r5 = r15.mParent;
        if (r5 == 0) goto L_0x004b;
    L_0x003f:
        r5 = r15.mParent;
        r5 = r5.mListDimensionBehaviors;
        r5 = r5[r2];
        r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r5 != r7) goto L_0x004b;
    L_0x0049:
        r5 = r2;
        goto L_0x004c;
    L_0x004b:
        r5 = r13;
    L_0x004c:
        r7 = r15.mLeft;
        r7 = r7.mTarget;
        if (r7 == 0) goto L_0x0073;
    L_0x0052:
        r7 = r15.mLeft;
        r7 = r7.mTarget;
        r7 = r7.mTarget;
        r8 = r15.mLeft;
        if (r7 == r8) goto L_0x0073;
    L_0x005c:
        r7 = r15.mRight;
        r7 = r7.mTarget;
        if (r7 == 0) goto L_0x0073;
    L_0x0062:
        r7 = r15.mRight;
        r7 = r7.mTarget;
        r7 = r7.mTarget;
        r8 = r15.mRight;
        if (r7 != r8) goto L_0x0073;
    L_0x006c:
        r7 = r15.mParent;
        r7 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r7;
        r7.addChain(r15, r13);
    L_0x0073:
        r7 = r15.mLeft;
        r7 = r7.mTarget;
        if (r7 == 0) goto L_0x0083;
    L_0x0079:
        r7 = r15.mLeft;
        r7 = r7.mTarget;
        r7 = r7.mTarget;
        r8 = r15.mLeft;
        if (r7 == r8) goto L_0x0093;
    L_0x0083:
        r7 = r15.mRight;
        r7 = r7.mTarget;
        if (r7 == 0) goto L_0x0095;
    L_0x0089:
        r7 = r15.mRight;
        r7 = r7.mTarget;
        r7 = r7.mTarget;
        r8 = r15.mRight;
        if (r7 != r8) goto L_0x0095;
    L_0x0093:
        r7 = r2;
        goto L_0x0096;
    L_0x0095:
        r7 = r13;
    L_0x0096:
        r8 = r15.mTop;
        r8 = r8.mTarget;
        if (r8 == 0) goto L_0x00bd;
    L_0x009c:
        r8 = r15.mTop;
        r8 = r8.mTarget;
        r8 = r8.mTarget;
        r9 = r15.mTop;
        if (r8 == r9) goto L_0x00bd;
    L_0x00a6:
        r8 = r15.mBottom;
        r8 = r8.mTarget;
        if (r8 == 0) goto L_0x00bd;
    L_0x00ac:
        r8 = r15.mBottom;
        r8 = r8.mTarget;
        r8 = r8.mTarget;
        r9 = r15.mBottom;
        if (r8 != r9) goto L_0x00bd;
    L_0x00b6:
        r8 = r15.mParent;
        r8 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r8;
        r8.addChain(r15, r2);
    L_0x00bd:
        r8 = r15.mTop;
        r8 = r8.mTarget;
        if (r8 == 0) goto L_0x00cd;
    L_0x00c3:
        r8 = r15.mTop;
        r8 = r8.mTarget;
        r8 = r8.mTarget;
        r9 = r15.mTop;
        if (r8 == r9) goto L_0x00dd;
    L_0x00cd:
        r8 = r15.mBottom;
        r8 = r8.mTarget;
        if (r8 == 0) goto L_0x00df;
    L_0x00d3:
        r8 = r15.mBottom;
        r8 = r8.mTarget;
        r8 = r8.mTarget;
        r9 = r15.mBottom;
        if (r8 != r9) goto L_0x00df;
    L_0x00dd:
        r8 = r2;
        goto L_0x00e0;
    L_0x00df:
        r8 = r13;
    L_0x00e0:
        if (r0 == 0) goto L_0x00fd;
    L_0x00e2:
        r9 = r15.mVisibility;
        if (r9 == r1) goto L_0x00fd;
    L_0x00e6:
        r9 = r15.mLeft;
        r9 = r9.mTarget;
        if (r9 != 0) goto L_0x00fd;
    L_0x00ec:
        r9 = r15.mRight;
        r9 = r9.mTarget;
        if (r9 != 0) goto L_0x00fd;
    L_0x00f2:
        r9 = r15.mParent;
        r9 = r9.mRight;
        r9 = r14.createObjectVariable(r9);
        r14.addGreaterThan(r9, r10, r13, r2);
    L_0x00fd:
        if (r5 == 0) goto L_0x011e;
    L_0x00ff:
        r9 = r15.mVisibility;
        if (r9 == r1) goto L_0x011e;
    L_0x0103:
        r9 = r15.mTop;
        r9 = r9.mTarget;
        if (r9 != 0) goto L_0x011e;
    L_0x0109:
        r9 = r15.mBottom;
        r9 = r9.mTarget;
        if (r9 != 0) goto L_0x011e;
    L_0x010f:
        r9 = r15.mBaseline;
        if (r9 != 0) goto L_0x011e;
    L_0x0113:
        r9 = r15.mParent;
        r9 = r9.mBottom;
        r9 = r14.createObjectVariable(r9);
        r14.addGreaterThan(r9, r4, r13, r2);
    L_0x011e:
        r12 = r5;
        r16 = r7;
        r22 = r8;
        goto L_0x012a;
    L_0x0124:
        r0 = r13;
        r12 = r0;
        r16 = r12;
        r22 = r16;
    L_0x012a:
        r5 = r15.mWidth;
        r7 = r15.mMinWidth;
        if (r5 >= r7) goto L_0x0132;
    L_0x0130:
        r5 = r15.mMinWidth;
    L_0x0132:
        r7 = r15.mHeight;
        r8 = r15.mMinHeight;
        if (r7 >= r8) goto L_0x013a;
    L_0x0138:
        r7 = r15.mMinHeight;
    L_0x013a:
        r8 = r15.mListDimensionBehaviors;
        r8 = r8[r13];
        r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r8 == r9) goto L_0x0144;
    L_0x0142:
        r8 = r2;
        goto L_0x0145;
    L_0x0144:
        r8 = r13;
    L_0x0145:
        r9 = r15.mListDimensionBehaviors;
        r9 = r9[r2];
        r11 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r9 == r11) goto L_0x014f;
    L_0x014d:
        r9 = r2;
        goto L_0x0150;
    L_0x014f:
        r9 = r13;
    L_0x0150:
        r11 = r15.mDimensionRatioSide;
        r15.mResolvedDimensionRatioSide = r11;
        r11 = r15.mDimensionRatio;
        r15.mResolvedDimensionRatio = r11;
        r11 = r15.mMatchConstraintDefaultWidth;
        r2 = r15.mMatchConstraintDefaultHeight;
        r13 = r15.mDimensionRatio;
        r17 = 0;
        r13 = (r13 > r17 ? 1 : (r13 == r17 ? 0 : -1));
        r17 = 4;
        if (r13 <= 0) goto L_0x0217;
    L_0x0166:
        r13 = r15.mVisibility;
        r1 = 8;
        if (r13 == r1) goto L_0x0217;
    L_0x016c:
        r1 = r15.mListDimensionBehaviors;
        r13 = 0;
        r1 = r1[r13];
        r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        r27 = r3;
        r3 = 3;
        if (r1 != r13) goto L_0x017b;
    L_0x0178:
        if (r11 != 0) goto L_0x017b;
    L_0x017a:
        r11 = r3;
    L_0x017b:
        r1 = r15.mListDimensionBehaviors;
        r13 = 1;
        r1 = r1[r13];
        r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r1 != r13) goto L_0x0187;
    L_0x0184:
        if (r2 != 0) goto L_0x0187;
    L_0x0186:
        r2 = r3;
    L_0x0187:
        r1 = r15.mListDimensionBehaviors;
        r13 = 0;
        r1 = r1[r13];
        r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r1 != r13) goto L_0x01a2;
    L_0x0190:
        r1 = r15.mListDimensionBehaviors;
        r13 = 1;
        r1 = r1[r13];
        r13 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r1 != r13) goto L_0x01a2;
    L_0x0199:
        if (r11 != r3) goto L_0x01a2;
    L_0x019b:
        if (r2 != r3) goto L_0x01a2;
    L_0x019d:
        r15.setupDimensionRatio(r0, r12, r8, r9);
        goto L_0x020c;
    L_0x01a2:
        r1 = r15.mListDimensionBehaviors;
        r8 = 0;
        r1 = r1[r8];
        r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r1 != r9) goto L_0x01d3;
    L_0x01ab:
        if (r11 != r3) goto L_0x01d3;
    L_0x01ad:
        r15.mResolvedDimensionRatioSide = r8;
        r1 = r15.mResolvedDimensionRatio;
        r3 = r15.mHeight;
        r3 = (float) r3;
        r1 = r1 * r3;
        r1 = (int) r1;
        r3 = r15.mListDimensionBehaviors;
        r8 = 1;
        r3 = r3[r8];
        r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r3 == r5) goto L_0x01c8;
    L_0x01bf:
        r29 = r1;
        r25 = r2;
        r30 = r7;
        r20 = r17;
        goto L_0x0221;
    L_0x01c8:
        r29 = r1;
        r25 = r2;
        r30 = r7;
        r28 = r8;
        r20 = r11;
        goto L_0x0223;
    L_0x01d3:
        r8 = 1;
        r1 = r15.mListDimensionBehaviors;
        r1 = r1[r8];
        r9 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r1 != r9) goto L_0x020c;
    L_0x01dc:
        if (r2 != r3) goto L_0x020c;
    L_0x01de:
        r15.mResolvedDimensionRatioSide = r8;
        r1 = r15.mDimensionRatioSide;
        r3 = -1;
        if (r1 != r3) goto L_0x01ec;
    L_0x01e5:
        r1 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3 = r15.mResolvedDimensionRatio;
        r1 = r1 / r3;
        r15.mResolvedDimensionRatio = r1;
    L_0x01ec:
        r1 = r15.mResolvedDimensionRatio;
        r3 = r15.mWidth;
        r3 = (float) r3;
        r1 = r1 * r3;
        r1 = (int) r1;
        r3 = r15.mListDimensionBehaviors;
        r7 = 0;
        r3 = r3[r7];
        r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r3 == r7) goto L_0x0205;
    L_0x01fc:
        r30 = r1;
        r29 = r5;
        r20 = r11;
        r25 = r17;
        goto L_0x0221;
    L_0x0205:
        r30 = r1;
        r25 = r2;
        r29 = r5;
        goto L_0x0212;
    L_0x020c:
        r25 = r2;
        r29 = r5;
        r30 = r7;
    L_0x0212:
        r20 = r11;
        r28 = 1;
        goto L_0x0223;
    L_0x0217:
        r27 = r3;
        r25 = r2;
        r29 = r5;
        r30 = r7;
        r20 = r11;
    L_0x0221:
        r28 = 0;
    L_0x0223:
        r1 = r15.mResolvedMatchConstraintDefault;
        r2 = 0;
        r1[r2] = r20;
        r1 = r15.mResolvedMatchConstraintDefault;
        r2 = 1;
        r1[r2] = r25;
        if (r28 == 0) goto L_0x023d;
    L_0x022f:
        r1 = r15.mResolvedDimensionRatioSide;
        if (r1 == 0) goto L_0x0239;
    L_0x0233:
        r1 = r15.mResolvedDimensionRatioSide;
        r2 = -1;
        if (r1 != r2) goto L_0x023e;
    L_0x0238:
        goto L_0x023a;
    L_0x0239:
        r2 = -1;
    L_0x023a:
        r26 = 1;
        goto L_0x0240;
    L_0x023d:
        r2 = -1;
    L_0x023e:
        r26 = 0;
    L_0x0240:
        r1 = r15.mListDimensionBehaviors;
        r3 = 0;
        r1 = r1[r3];
        r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r1 != r3) goto L_0x0250;
    L_0x0249:
        r1 = r15 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer;
        if (r1 == 0) goto L_0x0250;
    L_0x024d:
        r31 = 1;
        goto L_0x0252;
    L_0x0250:
        r31 = 0;
    L_0x0252:
        r1 = r15.mCenter;
        r1 = r1.isConnected();
        r3 = 1;
        r23 = r1 ^ 1;
        r1 = r15.mHorizontalResolution;
        r13 = 2;
        r32 = 0;
        if (r1 == r13) goto L_0x02cc;
    L_0x0262:
        r1 = r15.mParent;
        if (r1 == 0) goto L_0x0271;
    L_0x0266:
        r1 = r15.mParent;
        r1 = r1.mRight;
        r1 = r14.createObjectVariable(r1);
        r33 = r1;
        goto L_0x0273;
    L_0x0271:
        r33 = r32;
    L_0x0273:
        r1 = r15.mParent;
        if (r1 == 0) goto L_0x0282;
    L_0x0277:
        r1 = r15.mParent;
        r1 = r1.mLeft;
        r1 = r14.createObjectVariable(r1);
        r34 = r1;
        goto L_0x0284;
    L_0x0282:
        r34 = r32;
    L_0x0284:
        r1 = r15.mListDimensionBehaviors;
        r17 = 0;
        r5 = r1[r17];
        r7 = r15.mLeft;
        r8 = r15.mRight;
        r9 = r15.mX;
        r11 = r15.mMinWidth;
        r1 = r15.mMaxDimension;
        r1 = r1[r17];
        r24 = r12;
        r12 = r1;
        r1 = r15.mHorizontalBiasPercent;
        r13 = r1;
        r1 = r15.mMatchConstraintMinWidth;
        r17 = r1;
        r1 = r15.mMatchConstraintMaxWidth;
        r18 = r1;
        r1 = r15.mMatchConstraintPercentWidth;
        r19 = r1;
        r35 = r0;
        r0 = r38;
        r1 = r39;
        r2 = r35;
        r36 = r27;
        r3 = r34;
        r27 = r4;
        r4 = r33;
        r37 = r6;
        r6 = r31;
        r31 = r10;
        r10 = r29;
        r14 = r26;
        r15 = r16;
        r16 = r20;
        r20 = r23;
        r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
        goto L_0x02d6;
    L_0x02cc:
        r37 = r6;
        r31 = r10;
        r24 = r12;
        r36 = r27;
        r27 = r4;
    L_0x02d6:
        r15 = r38;
        r0 = r15.mVerticalResolution;
        r1 = 2;
        if (r0 != r1) goto L_0x02de;
    L_0x02dd:
        return;
    L_0x02de:
        r0 = r15.mListDimensionBehaviors;
        r14 = 1;
        r0 = r0[r14];
        r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r0 != r1) goto L_0x02ed;
    L_0x02e7:
        r0 = r15 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer;
        if (r0 == 0) goto L_0x02ed;
    L_0x02eb:
        r6 = r14;
        goto L_0x02ee;
    L_0x02ed:
        r6 = 0;
    L_0x02ee:
        if (r28 == 0) goto L_0x02fc;
    L_0x02f0:
        r0 = r15.mResolvedDimensionRatioSide;
        if (r0 == r14) goto L_0x02f9;
    L_0x02f4:
        r0 = r15.mResolvedDimensionRatioSide;
        r1 = -1;
        if (r0 != r1) goto L_0x02fc;
    L_0x02f9:
        r16 = r14;
        goto L_0x02fe;
    L_0x02fc:
        r16 = 0;
    L_0x02fe:
        r0 = r15.mBaselineDistance;
        if (r0 <= 0) goto L_0x033d;
    L_0x0302:
        r0 = r15.mBaseline;
        r0 = r0.getResolutionNode();
        r0 = r0.state;
        if (r0 != r14) goto L_0x031a;
    L_0x030c:
        r0 = r15.mBaseline;
        r0 = r0.getResolutionNode();
        r10 = r39;
        r0.addResolvedValue(r10);
        r4 = r37;
        goto L_0x0341;
    L_0x031a:
        r10 = r39;
        r0 = r38.getBaselineDistance();
        r1 = 6;
        r2 = r36;
        r4 = r37;
        r10.addEquality(r2, r4, r0, r1);
        r0 = r15.mBaseline;
        r0 = r0.mTarget;
        if (r0 == 0) goto L_0x0341;
    L_0x032e:
        r0 = r15.mBaseline;
        r0 = r0.mTarget;
        r0 = r10.createObjectVariable(r0);
        r3 = 0;
        r10.addEquality(r2, r0, r3, r1);
        r20 = r3;
        goto L_0x0343;
    L_0x033d:
        r4 = r37;
        r10 = r39;
    L_0x0341:
        r20 = r23;
    L_0x0343:
        r0 = r15.mParent;
        if (r0 == 0) goto L_0x0352;
    L_0x0347:
        r0 = r15.mParent;
        r0 = r0.mBottom;
        r0 = r10.createObjectVariable(r0);
        r23 = r0;
        goto L_0x0354;
    L_0x0352:
        r23 = r32;
    L_0x0354:
        r0 = r15.mParent;
        if (r0 == 0) goto L_0x0362;
    L_0x0358:
        r0 = r15.mParent;
        r0 = r0.mTop;
        r0 = r10.createObjectVariable(r0);
        r3 = r0;
        goto L_0x0364;
    L_0x0362:
        r3 = r32;
    L_0x0364:
        r0 = r15.mListDimensionBehaviors;
        r5 = r0[r14];
        r7 = r15.mTop;
        r8 = r15.mBottom;
        r9 = r15.mY;
        r11 = r15.mMinHeight;
        r0 = r15.mMaxDimension;
        r12 = r0[r14];
        r13 = r15.mVerticalBiasPercent;
        r0 = r15.mMatchConstraintMinHeight;
        r17 = r0;
        r0 = r15.mMatchConstraintMaxHeight;
        r18 = r0;
        r0 = r15.mMatchConstraintPercentHeight;
        r19 = r0;
        r0 = r38;
        r1 = r39;
        r2 = r24;
        r24 = r4;
        r4 = r23;
        r10 = r30;
        r14 = r16;
        r15 = r22;
        r16 = r25;
        r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
        if (r28 == 0) goto L_0x03c2;
    L_0x0399:
        r6 = 6;
        r7 = r38;
        r0 = r7.mResolvedDimensionRatioSide;
        r1 = 1;
        if (r0 != r1) goto L_0x03b2;
    L_0x03a1:
        r5 = r7.mResolvedDimensionRatio;
        r6 = 6;
        r0 = r39;
        r1 = r27;
        r2 = r24;
        r3 = r31;
        r4 = r21;
        r0.addRatio(r1, r2, r3, r4, r5, r6);
        goto L_0x03c4;
    L_0x03b2:
        r5 = r7.mResolvedDimensionRatio;
        r0 = r39;
        r1 = r31;
        r2 = r21;
        r3 = r27;
        r4 = r24;
        r0.addRatio(r1, r2, r3, r4, r5, r6);
        goto L_0x03c4;
    L_0x03c2:
        r7 = r38;
    L_0x03c4:
        r0 = r7.mCenter;
        r0 = r0.isConnected();
        if (r0 == 0) goto L_0x03ec;
    L_0x03cc:
        r0 = r7.mCenter;
        r0 = r0.getTarget();
        r0 = r0.getOwner();
        r1 = r7.mCircleConstraintAngle;
        r2 = 1119092736; // 0x42b40000 float:90.0 double:5.529052754E-315;
        r1 = r1 + r2;
        r1 = (double) r1;
        r1 = java.lang.Math.toRadians(r1);
        r1 = (float) r1;
        r2 = r7.mCenter;
        r2 = r2.getMargin();
        r3 = r39;
        r3.addCenterPoint(r7, r0, r1, r2);
    L_0x03ec:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.addToSolver(android.support.constraint.solver.LinearSystem):void");
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && !(this.mLeft.isConnected() && this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && !(this.mTop.isConnected() && this.mBottom.isConnected() && this.mLeft.isConnected() && this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z && !z2) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z && z2) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (this.mMatchConstraintMinWidth > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1 && z && z2) {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02da  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x02f3  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x02e0  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x02fd  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x02f8  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0300  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0300  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00dc  */
    private void applyConstraints(android.support.constraint.solver.LinearSystem r31, boolean r32, android.support.constraint.solver.SolverVariable r33, android.support.constraint.solver.SolverVariable r34, android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour r35, boolean r36, android.support.constraint.solver.widgets.ConstraintAnchor r37, android.support.constraint.solver.widgets.ConstraintAnchor r38, int r39, int r40, int r41, int r42, float r43, boolean r44, boolean r45, int r46, int r47, int r48, float r49, boolean r50) {
        /*
        r30 = this;
        r0 = r30;
        r9 = r31;
        r10 = r33;
        r11 = r34;
        r1 = r41;
        r2 = r42;
        r12 = r37;
        r8 = r9.createObjectVariable(r12);
        r7 = r38;
        r6 = r9.createObjectVariable(r7);
        r13 = r37.getTarget();
        r14 = r9.createObjectVariable(r13);
        r13 = r38.getTarget();
        r13 = r9.createObjectVariable(r13);
        r7 = r9.graphOptimizer;
        r15 = 1;
        if (r7 == 0) goto L_0x0066;
    L_0x002e:
        r7 = r37.getResolutionNode();
        r7 = r7.state;
        r12 = 1;
        if (r7 != r12) goto L_0x0066;
    L_0x0037:
        r7 = r38.getResolutionNode();
        r7 = r7.state;
        if (r7 != r12) goto L_0x0066;
    L_0x003f:
        r0 = android.support.constraint.solver.LinearSystem.getMetrics();
        if (r0 == 0) goto L_0x004e;
    L_0x0045:
        r0 = android.support.constraint.solver.LinearSystem.getMetrics();
        r1 = r0.resolvedWidgets;
        r1 = r1 + r15;
        r0.resolvedWidgets = r1;
    L_0x004e:
        r0 = r37.getResolutionNode();
        r0.addResolvedValue(r9);
        r0 = r38.getResolutionNode();
        r0.addResolvedValue(r9);
        if (r45 != 0) goto L_0x0065;
    L_0x005e:
        if (r32 == 0) goto L_0x0065;
    L_0x0060:
        r0 = 0;
        r1 = 6;
        r9.addGreaterThan(r11, r6, r0, r1);
    L_0x0065:
        return;
    L_0x0066:
        r7 = android.support.constraint.solver.LinearSystem.getMetrics();
        if (r7 == 0) goto L_0x0078;
    L_0x006c:
        r7 = android.support.constraint.solver.LinearSystem.getMetrics();
        r19 = r13;
        r12 = r7.nonresolvedWidgets;
        r12 = r12 + r15;
        r7.nonresolvedWidgets = r12;
        goto L_0x007a;
    L_0x0078:
        r19 = r13;
    L_0x007a:
        r7 = r37.isConnected();
        r12 = r38.isConnected();
        r13 = r0.mCenter;
        r20 = r13.isConnected();
        if (r7 == 0) goto L_0x008c;
    L_0x008a:
        r13 = 1;
        goto L_0x008d;
    L_0x008c:
        r13 = 0;
    L_0x008d:
        if (r12 == 0) goto L_0x0091;
    L_0x008f:
        r13 = r13 + 1;
    L_0x0091:
        if (r20 == 0) goto L_0x0095;
    L_0x0093:
        r13 = r13 + 1;
    L_0x0095:
        if (r44 == 0) goto L_0x0099;
    L_0x0097:
        r10 = 3;
        goto L_0x009b;
    L_0x0099:
        r10 = r46;
    L_0x009b:
        r15 = android.support.constraint.solver.widgets.ConstraintWidget.AnonymousClass1.$SwitchMap$android$support$constraint$solver$widgets$ConstraintWidget$DimensionBehaviour;
        r16 = r35.ordinal();
        r15 = r15[r16];
        r11 = 4;
        switch(r15) {
            case 1: goto L_0x00a7;
            case 2: goto L_0x00a7;
            case 3: goto L_0x00a7;
            case 4: goto L_0x00a9;
            default: goto L_0x00a7;
        };
    L_0x00a7:
        r15 = 0;
        goto L_0x00ad;
    L_0x00a9:
        if (r10 != r11) goto L_0x00ac;
    L_0x00ab:
        goto L_0x00a7;
    L_0x00ac:
        r15 = 1;
    L_0x00ad:
        r11 = r0.mVisibility;
        r21 = r13;
        r13 = 8;
        if (r11 != r13) goto L_0x00b8;
    L_0x00b5:
        r11 = 0;
        r15 = 0;
        goto L_0x00ba;
    L_0x00b8:
        r11 = r40;
    L_0x00ba:
        if (r50 == 0) goto L_0x00d7;
    L_0x00bc:
        if (r7 != 0) goto L_0x00c8;
    L_0x00be:
        if (r12 != 0) goto L_0x00c8;
    L_0x00c0:
        if (r20 != 0) goto L_0x00c8;
    L_0x00c2:
        r13 = r39;
        r9.addEquality(r8, r13);
        goto L_0x00d7;
    L_0x00c8:
        if (r7 == 0) goto L_0x00d7;
    L_0x00ca:
        if (r12 != 0) goto L_0x00d7;
    L_0x00cc:
        r13 = r37.getMargin();
        r22 = r12;
        r12 = 6;
        r9.addEquality(r8, r14, r13, r12);
        goto L_0x00da;
    L_0x00d7:
        r22 = r12;
        r12 = 6;
    L_0x00da:
        if (r15 != 0) goto L_0x010a;
    L_0x00dc:
        if (r36 == 0) goto L_0x00f4;
    L_0x00de:
        r12 = 3;
        r13 = 0;
        r9.addEquality(r6, r8, r13, r12);
        if (r1 <= 0) goto L_0x00ea;
    L_0x00e5:
        r13 = 6;
        r9.addGreaterThan(r6, r8, r1, r13);
        goto L_0x00eb;
    L_0x00ea:
        r13 = 6;
    L_0x00eb:
        r11 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r2 >= r11) goto L_0x00f9;
    L_0x00f0:
        r9.addLowerThan(r6, r8, r2, r13);
        goto L_0x00f9;
    L_0x00f4:
        r13 = r12;
        r12 = 3;
        r9.addEquality(r6, r8, r11, r13);
    L_0x00f9:
        r12 = r47;
        r2 = r48;
        r26 = r10;
        r27 = r14;
        r28 = r19;
        r0 = r21;
        r10 = 2;
        r19 = 4;
        goto L_0x01fd;
    L_0x010a:
        r12 = 3;
        r2 = -2;
        r13 = r47;
        if (r13 != r2) goto L_0x0114;
    L_0x0110:
        r13 = r48;
        r12 = r11;
        goto L_0x0117;
    L_0x0114:
        r12 = r13;
        r13 = r48;
    L_0x0117:
        if (r13 != r2) goto L_0x011b;
    L_0x0119:
        r2 = r11;
        goto L_0x011c;
    L_0x011b:
        r2 = r13;
    L_0x011c:
        if (r12 <= 0) goto L_0x012e;
    L_0x011e:
        if (r32 == 0) goto L_0x0125;
    L_0x0120:
        r13 = 6;
        r9.addGreaterThan(r6, r8, r12, r13);
        goto L_0x0129;
    L_0x0125:
        r13 = 6;
        r9.addGreaterThan(r6, r8, r12, r13);
    L_0x0129:
        r11 = java.lang.Math.max(r11, r12);
        goto L_0x012f;
    L_0x012e:
        r13 = 6;
    L_0x012f:
        if (r2 <= 0) goto L_0x0140;
    L_0x0131:
        if (r32 == 0) goto L_0x0139;
    L_0x0133:
        r13 = 1;
        r9.addLowerThan(r6, r8, r2, r13);
        r13 = 6;
        goto L_0x013c;
    L_0x0139:
        r9.addLowerThan(r6, r8, r2, r13);
    L_0x013c:
        r11 = java.lang.Math.min(r11, r2);
    L_0x0140:
        r13 = 1;
        if (r10 != r13) goto L_0x016c;
    L_0x0143:
        if (r32 == 0) goto L_0x0154;
    L_0x0145:
        r13 = 6;
        r9.addEquality(r6, r8, r11, r13);
    L_0x0149:
        r26 = r10;
        r27 = r14;
        r28 = r19;
        r0 = r21;
        r10 = 2;
        goto L_0x01e6;
    L_0x0154:
        if (r45 == 0) goto L_0x0167;
    L_0x0156:
        r13 = 4;
        r9.addEquality(r6, r8, r11, r13);
        r26 = r10;
        r27 = r14;
        r28 = r19;
        r0 = r21;
        r10 = 2;
        r19 = r13;
        goto L_0x01e8;
    L_0x0167:
        r13 = 1;
        r9.addEquality(r6, r8, r11, r13);
        goto L_0x0149;
    L_0x016c:
        r13 = 2;
        if (r10 != r13) goto L_0x01dd;
    L_0x016f:
        r13 = r37.getType();
        r23 = r14;
        r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        if (r13 == r14) goto L_0x01a1;
    L_0x0179:
        r13 = r37.getType();
        r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        if (r13 != r14) goto L_0x0182;
    L_0x0181:
        goto L_0x01a1;
    L_0x0182:
        r13 = r0.mParent;
        r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT;
        r13 = r13.getAnchor(r14);
        r13 = r9.createObjectVariable(r13);
        r14 = r0.mParent;
        r24 = r13;
        r13 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT;
        r13 = r14.getAnchor(r13);
        r13 = r9.createObjectVariable(r13);
        r16 = r13;
        r17 = r24;
        goto L_0x01bf;
    L_0x01a1:
        r13 = r0.mParent;
        r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        r13 = r13.getAnchor(r14);
        r13 = r9.createObjectVariable(r13);
        r14 = r0.mParent;
        r25 = r13;
        r13 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        r13 = r14.getAnchor(r13);
        r13 = r9.createObjectVariable(r13);
        r16 = r13;
        r17 = r25;
    L_0x01bf:
        r13 = r31.createRow();
        r26 = r10;
        r14 = r19;
        r0 = r21;
        r10 = 2;
        r19 = 4;
        r28 = r14;
        r27 = r23;
        r14 = r6;
        r15 = r8;
        r18 = r49;
        r13 = r13.createRowDimensionRatio(r14, r15, r16, r17, r18);
        r9.addConstraint(r13);
        r15 = 0;
        goto L_0x01e8;
    L_0x01dd:
        r26 = r10;
        r10 = r13;
        r27 = r14;
        r28 = r19;
        r0 = r21;
    L_0x01e6:
        r19 = 4;
    L_0x01e8:
        if (r15 == 0) goto L_0x01fd;
    L_0x01ea:
        if (r0 == r10) goto L_0x01fd;
    L_0x01ec:
        if (r44 != 0) goto L_0x01fd;
    L_0x01ee:
        r11 = java.lang.Math.max(r12, r11);
        if (r2 <= 0) goto L_0x01f8;
    L_0x01f4:
        r11 = java.lang.Math.min(r2, r11);
    L_0x01f8:
        r13 = 6;
        r9.addEquality(r6, r8, r11, r13);
        r15 = 0;
    L_0x01fd:
        if (r50 == 0) goto L_0x0306;
    L_0x01ff:
        if (r45 == 0) goto L_0x0203;
    L_0x0201:
        goto L_0x0306;
    L_0x0203:
        r0 = 5;
        if (r7 != 0) goto L_0x021a;
    L_0x0206:
        if (r22 != 0) goto L_0x021a;
    L_0x0208:
        if (r20 != 0) goto L_0x021a;
    L_0x020a:
        if (r32 == 0) goto L_0x0213;
    L_0x020c:
        r4 = 0;
        r11 = r34;
        r9.addGreaterThan(r11, r6, r4, r0);
        goto L_0x0228;
    L_0x0213:
        r11 = r34;
        r2 = r6;
        r0 = 0;
    L_0x0217:
        r1 = 6;
        goto L_0x02fe;
    L_0x021a:
        r5 = r19;
        r4 = 0;
        r11 = r34;
        if (r7 == 0) goto L_0x022b;
    L_0x0221:
        if (r22 != 0) goto L_0x022b;
    L_0x0223:
        if (r32 == 0) goto L_0x0228;
    L_0x0225:
        r9.addGreaterThan(r11, r6, r4, r0);
    L_0x0228:
        r0 = r4;
        r2 = r6;
        goto L_0x0217;
    L_0x022b:
        if (r7 != 0) goto L_0x0242;
    L_0x022d:
        if (r22 == 0) goto L_0x0242;
    L_0x022f:
        r1 = r38.getMargin();
        r1 = -r1;
        r10 = r28;
        r2 = 6;
        r9.addEquality(r6, r10, r1, r2);
        if (r32 == 0) goto L_0x0228;
    L_0x023c:
        r13 = r33;
        r9.addGreaterThan(r8, r13, r4, r0);
        goto L_0x0228;
    L_0x0242:
        r10 = r28;
        r13 = r33;
        r14 = 3;
        if (r7 == 0) goto L_0x0228;
    L_0x0249:
        if (r22 == 0) goto L_0x0228;
    L_0x024b:
        if (r15 == 0) goto L_0x02a9;
    L_0x024d:
        if (r32 == 0) goto L_0x0255;
    L_0x024f:
        if (r1 != 0) goto L_0x0255;
    L_0x0251:
        r1 = 6;
        r9.addGreaterThan(r6, r8, r4, r1);
    L_0x0255:
        if (r26 != 0) goto L_0x027c;
    L_0x0257:
        if (r2 > 0) goto L_0x025f;
    L_0x0259:
        if (r12 <= 0) goto L_0x025c;
    L_0x025b:
        goto L_0x025f;
    L_0x025c:
        r1 = 0;
        r5 = 6;
        goto L_0x0260;
    L_0x025f:
        r1 = 1;
    L_0x0260:
        r3 = r37.getMargin();
        r7 = r27;
        r9.addEquality(r8, r7, r3, r5);
        r3 = r38.getMargin();
        r3 = -r3;
        r9.addEquality(r6, r10, r3, r5);
        if (r2 > 0) goto L_0x0278;
    L_0x0273:
        if (r12 <= 0) goto L_0x0276;
    L_0x0275:
        goto L_0x0278;
    L_0x0276:
        r12 = 0;
        goto L_0x0279;
    L_0x0278:
        r12 = 1;
    L_0x0279:
        r14 = r0;
        r15 = r1;
        goto L_0x02bf;
    L_0x027c:
        r1 = r26;
        r7 = r27;
        r12 = 1;
        if (r1 != r12) goto L_0x0286;
    L_0x0283:
        r15 = r12;
        r14 = 6;
        goto L_0x02bf;
    L_0x0286:
        if (r1 != r14) goto L_0x02a6;
    L_0x0288:
        if (r44 != 0) goto L_0x0294;
    L_0x028a:
        r1 = r30;
        r1 = r1.mResolvedDimensionRatioSide;
        r3 = -1;
        if (r1 == r3) goto L_0x0294;
    L_0x0291:
        if (r2 > 0) goto L_0x0294;
    L_0x0293:
        r5 = 6;
    L_0x0294:
        r1 = r37.getMargin();
        r9.addEquality(r8, r7, r1, r5);
        r1 = r38.getMargin();
        r1 = -r1;
        r9.addEquality(r6, r10, r1, r5);
        r14 = r0;
        r15 = r12;
        goto L_0x02bf;
    L_0x02a6:
        r14 = r0;
        r12 = 0;
        goto L_0x02be;
    L_0x02a9:
        r7 = r27;
        r12 = 1;
        if (r32 == 0) goto L_0x02bd;
    L_0x02ae:
        r1 = r37.getMargin();
        r9.addGreaterThan(r8, r7, r1, r0);
        r1 = r38.getMargin();
        r1 = -r1;
        r9.addLowerThan(r6, r10, r1, r0);
    L_0x02bd:
        r14 = r0;
    L_0x02be:
        r15 = 0;
    L_0x02bf:
        if (r12 == 0) goto L_0x02da;
    L_0x02c1:
        r3 = r37.getMargin();
        r12 = r38.getMargin();
        r0 = r31;
        r1 = r8;
        r2 = r7;
        r4 = r43;
        r5 = r10;
        r29 = r6;
        r11 = r7;
        r7 = r12;
        r12 = r8;
        r8 = r14;
        r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8);
        goto L_0x02de;
    L_0x02da:
        r29 = r6;
        r11 = r7;
        r12 = r8;
    L_0x02de:
        if (r15 == 0) goto L_0x02f3;
    L_0x02e0:
        r0 = r37.getMargin();
        r1 = 6;
        r9.addGreaterThan(r12, r11, r0, r1);
        r0 = r38.getMargin();
        r0 = -r0;
        r2 = r29;
        r9.addLowerThan(r2, r10, r0, r1);
        goto L_0x02f6;
    L_0x02f3:
        r2 = r29;
        r1 = 6;
    L_0x02f6:
        if (r32 == 0) goto L_0x02fd;
    L_0x02f8:
        r0 = 0;
        r9.addGreaterThan(r12, r13, r0, r1);
        goto L_0x02fe;
    L_0x02fd:
        r0 = 0;
    L_0x02fe:
        if (r32 == 0) goto L_0x0305;
    L_0x0300:
        r3 = r34;
        r9.addGreaterThan(r3, r2, r0, r1);
    L_0x0305:
        return;
    L_0x0306:
        r4 = r0;
        r2 = r6;
        r12 = r8;
        r0 = 0;
        r1 = 6;
        r3 = r34;
        r13 = r33;
        if (r4 >= r10) goto L_0x0319;
    L_0x0311:
        if (r32 == 0) goto L_0x0319;
    L_0x0313:
        r9.addGreaterThan(r12, r13, r0, r1);
        r9.addGreaterThan(r3, r2, r0, r1);
    L_0x0319:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.applyConstraints(android.support.constraint.solver.LinearSystem, boolean, android.support.constraint.solver.SolverVariable, android.support.constraint.solver.SolverVariable, android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, android.support.constraint.solver.widgets.ConstraintAnchor, android.support.constraint.solver.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, int, int, int, float, boolean):void");
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        int i = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue4 = 0;
            objectVariableValue = objectVariableValue4;
            objectVariableValue2 = objectVariableValue;
            objectVariableValue3 = objectVariableValue2;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }
}
