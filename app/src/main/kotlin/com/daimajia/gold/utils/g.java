package com.daimajia.gold.utils;

import android.text.TextUtils;

import com.fashare.mvvm_juejin.JueJinApp;

import java.io.File;

public class g {

    public static String b() {
        String b = b("entry-content.css");
        if (TextUtils.isEmpty(b) || new File(b).length() == 0) {
            return "file:///android_asset/www/entry-content.css";
        }
        return "file://" + b;
    }

    public static String c() {
        String b = b("entry-content.js");
        if (TextUtils.isEmpty(b) || new File(b).length() == 0) {
            return "file:///android_asset/www/entry-content.js";
        }
        return "file://" + b;
    }

    public static String b(String str) {
        File file = new File(JueJinApp.instance.getFilesDir(), str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Throwable e) {
//                a.a(e);
                return "";
            }
        }
        return file.getAbsolutePath();
    }
}