package com.test.viaplay.biocram.viaplaytest.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import java.util.List;
import java.util.Map;

/**
 * Created by biocram on 02/02/16.
 */
public abstract class CommonUtil {

    public static boolean isEmpty(List<? extends Object> list) {
        return list == null || list.size() == 0;
    }

    public static boolean isEmpty(Map<? extends Object, ? extends Object> map) {
        return map == null || map.size() == 0;
    }

    public static boolean checkInternetConnection(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
