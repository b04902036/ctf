package de.ccc.hamburg.onepass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002J\u0011\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lde/ccc/hamburg/onepass/OnePass;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "topSecretPasswordVault", "", "getTopSecretPasswordVault", "()Ljava/lang/String;", "canonicalizeString", "h", "hackover", "arg", "onClickButton", "", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_release"}, k = 1, mv = {1, 1, 10})
/* compiled from: OnePass.kt */
public final class OnePass extends AppCompatActivity {
    public static final Companion Companion = new Companion();
    private HashMap _$_findViewCache;
    @NotNull
    private final String topSecretPasswordVault = "AQDMB8kOisBnDeJOR1ArcxpqZFgx6ToBi9lorpDYVfgbJJEfR+7V49y1uJeWknpXsvZCcW/NdFGZtXHHawkJ1PMhrGD/";

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lde/ccc/hamburg/onepass/OnePass$Companion;", "", "()V", "app_release"}, k = 1, mv = {1, 1, 10})
    /* compiled from: OnePass.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    @NotNull
    public final native String hackover(@NotNull String str);

    @NotNull
    public final String getTopSecretPasswordVault() {
        return this.topSecretPasswordVault;
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_onepass);
    }

    public final void onClickButton(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        EditText editText = (EditText) _$_findCachedViewById(R.id.in_hamburg_sagt_man);
        Intrinsics.checkExpressionValueIsNotNull(editText, "in_hamburg_sagt_man");
        String obj = editText.getText().toString();
        if (obj != null) {
            obj = obj.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(obj, "(this as java.lang.String).toLowerCase()");
            EditText editText2 = (EditText) _$_findCachedViewById(R.id.das_heisst);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "das_heisst");
            String obj2 = editText2.getText().toString();
            if (obj2 != null) {
                obj2 = obj2.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(obj2, "(this as java.lang.String).toLowerCase()");
                EditText editText3 = (EditText) _$_findCachedViewById(R.id.von_hamburg_nach);
                Intrinsics.checkExpressionValueIsNotNull(editText3, "von_hamburg_nach");
                String obj3 = editText3.getText().toString();
                if (obj3 != null) {
                    obj3 = obj3.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(obj3, "(this as java.lang.String).toLowerCase()");
                    obj = canonicalizeString(obj);
                    obj2 = canonicalizeString(obj2);
                    obj3 = canonicalizeString(obj3);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(obj);
                    stringBuilder.append(obj2);
                    stringBuilder.append(obj3);
                    ((TextView) _$_findCachedViewById(R.id.output)).setText(new MilitaryGradeEncryption().decrypt(this.topSecretPasswordVault, hackover(stringBuilder.toString())));
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    private final String canonicalizeString(String str) {
        return StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(str, "ä", "a", false, 4, null), "ö", "o", false, 4, null), "ö", "o", false, 4, null), "ü", "u", false, 4, null), "ß", "ss", false, 4, null), " ", "", false, 4, null), "!", "", false, 4, null), ",", "", false, 4, null), ".", "", false, 4, null);
    }

    static {
        System.loadLibrary("native-lib");
    }
}
