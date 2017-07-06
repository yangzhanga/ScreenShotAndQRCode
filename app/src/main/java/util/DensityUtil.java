package util;


import android.app.Application;

import com.example.zhangyang.screenshotqrcodedemo.MyApplication;

public class DensityUtil {

	public static int dip2px(float dpValue) {
		
		final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(float pxValue) {
		
		final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
