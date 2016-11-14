package org.zky.overwatch;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.Log;

/**
 * Created by zhan9 on 2016/11/14.
 */


public class GetText {

    private static Resources sResources;
    private static String package_name;

    public static void initialize(Context context) {
        sResources = context.getApplicationContext().getResources();
        package_name = context.getPackageName();
    }

    @NonNull
    public static String getString(@StringRes int id) {
        return sResources.getString(id);
    }

    @NonNull
    public static String getString(@StringRes int id, Object... formatArgs) {
        return sResources.getString(id, formatArgs);
    }

    public static int getName(String s){
        String[] split = s.split("_");
        int res = sResources.getIdentifier(split[0], "string", package_name);
        return res;
    }

    public static int getTypeImageRes(String s){
        String[] split = s.split("_");
        int drawable=R.drawable.ic_tuji;

        if (split.length>0){
            drawable = sResources.getIdentifier("ic_" + split[1], "drawable", package_name);
        }
        return drawable;
    }
}