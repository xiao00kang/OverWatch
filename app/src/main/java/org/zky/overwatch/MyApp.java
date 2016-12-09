package org.zky.overwatch;

import android.app.Application;

import org.zky.overwatch.utils.GetRes;

/**
 *
 * Created by zhan9 on 2016/11/14.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GetRes.initialize(this);
    }
}
