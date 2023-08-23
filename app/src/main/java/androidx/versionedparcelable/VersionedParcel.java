package androidx.versionedparcelable;

import android.os.Parcelable;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.drawable.IconCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class VersionedParcel {
    final SimpleArrayMap mParcelizerCache;
    final SimpleArrayMap mReadCache;
    final SimpleArrayMap mWriteCache;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VersionedParcel(SimpleArrayMap simpleArrayMap, SimpleArrayMap simpleArrayMap2, SimpleArrayMap simpleArrayMap3) {
        this.mReadCache = simpleArrayMap;
        this.mWriteCache = simpleArrayMap2;
        this.mParcelizerCache = simpleArrayMap3;
    }

    private Class findParcelClass(Class cls) {
        String name = cls.getName();
        SimpleArrayMap simpleArrayMap = this.mParcelizerCache;
        Class cls2 = (Class) simpleArrayMap.get(name);
        if (cls2 == null) {
            Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
            simpleArrayMap.put(cls.getName(), cls3);
            return cls3;
        }
        return cls2;
    }

    private Method getReadMethod(String str) {
        SimpleArrayMap simpleArrayMap = this.mReadCache;
        Method method = (Method) simpleArrayMap.get(str);
        if (method == null) {
            Method declaredMethod = Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class);
            simpleArrayMap.put(str, declaredMethod);
            return declaredMethod;
        }
        return method;
    }

    private Method getWriteMethod(Class cls) {
        String name = cls.getName();
        SimpleArrayMap simpleArrayMap = this.mWriteCache;
        Method method = (Method) simpleArrayMap.get(name);
        if (method == null) {
            Method declaredMethod = findParcelClass(cls).getDeclaredMethod("write", cls, VersionedParcel.class);
            simpleArrayMap.put(cls.getName(), declaredMethod);
            return declaredMethod;
        }
        return method;
    }

    protected abstract void closeField();

    protected abstract VersionedParcel createSubParcel();

    protected abstract boolean readBoolean();

    public final boolean readBoolean(int i, boolean z) {
        return !readField(i) ? z : readBoolean();
    }

    protected abstract byte[] readByteArray();

    public final byte[] readByteArray(byte[] bArr) {
        return !readField(2) ? bArr : readByteArray();
    }

    protected abstract CharSequence readCharSequence();

    public final CharSequence readCharSequence(CharSequence charSequence, int i) {
        return !readField(i) ? charSequence : readCharSequence();
    }

    protected abstract boolean readField(int i);

    protected abstract int readInt();

    public final int readInt(int i, int i2) {
        return !readField(i2) ? i : readInt();
    }

    protected abstract Parcelable readParcelable();

    public final Parcelable readParcelable(Parcelable parcelable, int i) {
        return !readField(i) ? parcelable : readParcelable();
    }

    protected abstract String readString();

    public final String readString(String str, int i) {
        return !readField(i) ? str : readString();
    }

    public final VersionedParcelable readVersionedParcelable(IconCompat iconCompat) {
        return !readField(1) ? iconCompat : readVersionedParcelable();
    }

    protected abstract void setOutputField(int i);

    public final void writeBoolean(int i, boolean z) {
        setOutputField(i);
        writeBoolean(z);
    }

    protected abstract void writeBoolean(boolean z);

    protected abstract void writeByteArray(byte[] bArr);

    public final void writeByteArray$1(byte[] bArr) {
        setOutputField(2);
        writeByteArray(bArr);
    }

    protected abstract void writeCharSequence(CharSequence charSequence);

    public final void writeCharSequence(CharSequence charSequence, int i) {
        setOutputField(i);
        writeCharSequence(charSequence);
    }

    protected abstract void writeInt(int i);

    public final void writeInt(int i, int i2) {
        setOutputField(i2);
        writeInt(i);
    }

    protected abstract void writeParcelable(Parcelable parcelable);

    public final void writeParcelable(Parcelable parcelable, int i) {
        setOutputField(i);
        writeParcelable(parcelable);
    }

    protected abstract void writeString(String str);

    public final void writeString(String str, int i) {
        setOutputField(i);
        writeString(str);
    }

    public final void writeVersionedParcelable(IconCompat iconCompat) {
        setOutputField(1);
        writeVersionedParcelable((VersionedParcelable) iconCompat);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final VersionedParcelable readVersionedParcelable() {
        String readString = readString();
        if (readString == null) {
            return null;
        }
        try {
            return (VersionedParcelable) getReadMethod(readString).invoke(null, createSubParcel());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            Throwable cause = e4.getCause();
            if (!(cause instanceof RuntimeException)) {
                if (cause instanceof Error) {
                    throw ((Error) cause);
                }
                throw new RuntimeException(e4);
            }
            throw ((RuntimeException) cause);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void writeVersionedParcelable(VersionedParcelable versionedParcelable) {
        if (versionedParcelable == null) {
            writeString(null);
            return;
        }
        try {
            writeString(findParcelClass(versionedParcelable.getClass()).getName());
            VersionedParcel createSubParcel = createSubParcel();
            try {
                getWriteMethod(versionedParcelable.getClass()).invoke(null, versionedParcelable, createSubParcel);
                createSubParcel.closeField();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException(e3);
            } catch (InvocationTargetException e4) {
                Throwable cause = e4.getCause();
                if (!(cause instanceof RuntimeException)) {
                    if (cause instanceof Error) {
                        throw ((Error) cause);
                    }
                    throw new RuntimeException(e4);
                }
                throw ((RuntimeException) cause);
            }
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException(versionedParcelable.getClass().getSimpleName().concat(" does not have a Parcelizer"), e5);
        }
    }
}
