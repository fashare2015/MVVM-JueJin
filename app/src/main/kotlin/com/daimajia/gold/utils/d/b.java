package com.daimajia.gold.utils.d;

import android.net.Uri;
import android.text.TextUtils;

import com.daimajia.gold.utils.z;

public class b {
    public static String a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(String.format(" <link href=\"%s\" type=\"text/css\" rel=\"stylesheet\" />", new Object[]{str}));
        }
        stringBuilder.append(String.format(" <link href=\"%s\" type=\"text/css\" rel=\"stylesheet\" />", new Object[]{"file:///android_asset/www/header-img.css"}));
        if (TextUtils.isEmpty(stringBuilder.toString())) {
            return "";
        }
        return stringBuilder.toString();
    }

    public static String b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(String.format(" <script src=\"%s\"></script>", new Object[]{str}));
        }
        stringBuilder.append(String.format(" <script src=\"%s\"></script>", new Object[]{"file:///android_asset/www/img_replace.js"}));
        return stringBuilder.toString();
    }

    public static String a(String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(String.format(" <div id = \"gold-web-header-img\" style=\"background-image: linear-gradient(rgba(0, 0, 0, .1), rgba(0, 0, 0, 0)), url(%s)\" class=\"entry-hero\"></div>", new Object[]{str}));
        }
        stringBuilder.append(String.format("<h2 id=\"entry_title\" class=\"entry-title\">%s</h2>", new Object[]{str3}));
        String str5 = "";
        if (!TextUtils.isEmpty(str2)) {
            str5 = Uri.parse(str2).getHost();
        }
        if (c(str2)) {
            str5 = "<p class=\"entry-meta\"><span style=\"margin-right:0.8em; border:solid #909090 1px;border-radius: 2px;padding:.2em .7em 0.1em 0.7em;\">原创</span>%s</p>";
            Object[] objArr = new Object[1];
            if (z.c(str4)) {
                str4 = "";
            }
            objArr[0] = str4;
            str5 = String.format(str5, objArr);
        } else {
            str5 = String.format("<p class=\"entry-meta\"><a href=\"%s\" target=\"_blank\" rel=\"nofollow\" class=\"action entry-original-url\">原文链接  <span style=\"color: #607fa6 \">%s</span></a></p>", new Object[]{str2, str5});
        }
        stringBuilder.append(str5);
        return stringBuilder.toString();
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith("http://juejin.im") || str.startsWith("https://juejin.im") || str.startsWith("http://gold.xitu.io") || str.startsWith("https://gold.xitu.io") || str.startsWith("http://gold-dev.leanapp.cn") || str.startsWith("https://gold-dev.leanapp.cn")) {
            return true;
        }
        return false;
    }
}