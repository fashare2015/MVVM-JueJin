package com.daimajia.gold.utils;

import android.content.Context;
import android.os.Environment;
//import com.daimajia.gold.utils.helpers.a;
import java.io.File;

public class r {
    public static boolean a() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String b() {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        try {
            externalStoragePublicDirectory.mkdirs();
        } catch (Throwable e) {
//            a.a(e);
        }
        return externalStoragePublicDirectory.getAbsolutePath();
    }

    public static String a(Context context) {
        return a(context, "cache");
    }

    private static String a(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (context == null) {
            return null;
        }
        File externalCacheDir;
        if (!a()) {
            if (str.equals("cache")) {
                stringBuilder.append(context.getCacheDir()).append(File.separator);
            } else {
                stringBuilder.append(context.getFilesDir()).append(File.separator);
            }
        }
        if (str.equals("cache")) {
            externalCacheDir = context.getExternalCacheDir();
        } else {
            externalCacheDir = context.getExternalFilesDir(null);
        }
        if (externalCacheDir != null) {
            stringBuilder.append(externalCacheDir.getAbsolutePath()).append(File.separator);
        } else {
            stringBuilder.append(Environment.getExternalStorageDirectory().getPath()).append("/Android/data/").append(context.getPackageName()).append("/").append(str).append("/").append(File.separator).toString();
        }
        return stringBuilder.toString();
    }
}