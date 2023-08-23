package androidx.emoji2.text;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import java.util.List;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class DefaultEmojiCompatConfig$DefaultEmojiCompatConfigHelper {
    public abstract ProviderInfo getProviderInfo(ResolveInfo resolveInfo);

    public abstract Signature[] getSigningSignatures(PackageManager packageManager, String str);

    public abstract List queryIntentContentProviders(PackageManager packageManager, Intent intent);
}
