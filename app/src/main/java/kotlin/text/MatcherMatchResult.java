package kotlin.text;

import java.util.regex.Matcher;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MatcherMatchResult implements MatchResult {
    private final MatcherMatchResult$groups$1 groups;
    private final CharSequence input;
    private final Matcher matcher;

    public MatcherMatchResult(Matcher matcher, CharSequence input) {
        Intrinsics.checkNotNullParameter(input, "input");
        this.matcher = matcher;
        this.input = input;
        this.groups = new MatcherMatchResult$groups$1(this);
    }

    public static final java.util.regex.MatchResult access$getMatchResult(MatcherMatchResult matcherMatchResult) {
        return matcherMatchResult.matcher;
    }

    @Override // kotlin.text.MatchResult
    public final MatcherMatchResult$groups$1 getGroups() {
        return this.groups;
    }

    public final MatchResult next() {
        int i;
        Matcher matcher = this.matcher;
        int end = matcher.end();
        if (matcher.end() == matcher.start()) {
            i = 1;
        } else {
            i = 0;
        }
        int i2 = end + i;
        CharSequence charSequence = this.input;
        if (i2 > charSequence.length()) {
            return null;
        }
        Matcher matcher2 = matcher.pattern().matcher(charSequence);
        Intrinsics.checkNotNullExpressionValue(matcher2, "matcher.pattern().matcher(input)");
        if (!matcher2.find(i2)) {
            return null;
        }
        return new MatcherMatchResult(matcher2, charSequence);
    }
}
