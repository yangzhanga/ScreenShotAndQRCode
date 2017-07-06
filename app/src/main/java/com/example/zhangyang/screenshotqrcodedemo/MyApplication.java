package com.example.zhangyang.screenshotqrcodedemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangyang on 2017/7/6.
 */

public class MyApplication extends Application {
    private static Context mContext = null;

    @Override
    public void onCreate() {

        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }


}
