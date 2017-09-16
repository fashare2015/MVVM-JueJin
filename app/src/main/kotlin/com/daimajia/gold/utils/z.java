package com.daimajia.gold.utils;

import android.text.TextUtils;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class z {
    public static void a(TextView textView, String str) {
        if (textView != null && !TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        Matcher matcher = Pattern.compile("^[a-zA-Z0-9_一-龥]+$").matcher(str);
        if (str.length() > 20 || !matcher.find()) {
            return false;
        }
        return true;
    }

    public static boolean b(String str) {
        if (str == null) {
            return false;
        }
        return Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?").matcher(str).find();
    }

    public static boolean c(String str) {
        return str == null || str.length() == 0;
    }

    public static String a(TextView textView) {
        if (textView != null) {
            return textView.getText().toString();
        }
        return "";
    }
}