package util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


import com.example.zhangyang.screenshotqrcodedemo.MyApplication;

import java.io.File;
import java.lang.reflect.Field;

public class DeviceUtil {

//	public final static String NETWORK_STATE_NOTHING = "network_state_nothing";
//	public final static String NETWORK_STATE_MOBILE = "network_state_mobile";
//	public final static String NETWORK_STATE_WIFI = "network_state_wifi";
//	public final static String NETWORK_STATE_OTHER = "network_state_other";
//
//	public static File getSdcardDir() {
//
//		File dir = Environment.getExternalStorageDirectory();
//		if(!dir.exists()){
//			dir.mkdirs();
//		}
//		return dir;
//	}
//
//	public static File getCameraDir(){
//
//		File cameraDir = new File(getSdcardDir(), "/"+Environment.DIRECTORY_DCIM+"/Camera");
//		if(!cameraDir.exists()){
//
//			cameraDir.mkdirs();
//		}
//
//		return cameraDir;
//	}
//
//	public static boolean sdcardIsMounted() {
//
//		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
//	}
//
//	public static boolean sdcardIsEnable() {
//
//		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
//				&& !Environment.getExternalStorageState().equals(Environment.MEDIA_SHARED);
//	}
//
//	public static long getRuntimeMaxMemory() {
//
//		return Runtime.getRuntime().maxMemory();
//	}
//
//	public static String getIMEI() {
//
//		String imei = "";
//		try{
//
//			Context ctx = ExApplication.getContext();
//			TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
//			if (telephonyManager != null) {
//
//				imei = telephonyManager.getDeviceId();
//				if (TextUtils.isEmpty(imei))
//					imei = Secure.getString(ctx.getContentResolver(), Secure.ANDROID_ID);
//
//				if(imei == null)
//					imei = "";
//			}
//
//		}catch(Exception e){
//
//			if(LogMgr.isDebug())
//				e.printStackTrace();
//		}
//
//		return imei;
//	}
//
//	public static boolean hasApp(String packageName) {
//
//		if(TextUtils.isEmpty(packageName))
//			return false;
//
//		PackageInfo packageInfo = null;
//		try {
//
//			packageInfo = ExApplication.getContext().getPackageManager().getPackageInfo(packageName, 0);
//
//		} catch (NameNotFoundException e) {
//
//			if(LogMgr.isDebug())
//				e.printStackTrace();
//		}catch(Exception e){
//
//            if(LogMgr.isDebug())
//                e.printStackTrace();
//        }
//
//		return packageInfo == null ? false : true;
//	}
//
//	public static boolean hasGoogleMapApp() {
//
//		return hasApp("com.google.android.apps.maps");
//	}
//
//	public static boolean isNetworkEnable() {
//
//		ConnectivityManager conManager = (ConnectivityManager) ExApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
//		return networkInfo != null && networkInfo.isAvailable();
//	}
//
//	public static boolean isNetworkDisable() {
//
//		ConnectivityManager conManager = (ConnectivityManager) ExApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
//		return networkInfo == null || !networkInfo.isAvailable();
//	}
//
//	public static String getNetworkState() {
//
//		ConnectivityManager cm = (ConnectivityManager) ExApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo info = cm.getActiveNetworkInfo();
//		if (info == null || !info.isAvailable()) {
//			return NETWORK_STATE_NOTHING;
//		} else {
//
//			if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
//				return NETWORK_STATE_MOBILE;
//			} else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
//				return NETWORK_STATE_WIFI;
//			} else {
//				return NETWORK_STATE_OTHER;
//			}
//		}
//	}
//
//	public static boolean isWifiNetWork() {
//
//		return NETWORK_STATE_WIFI.equals(DeviceUtil.getNetworkState());
//	}
//
//	public static boolean isMobileNetWork() {
//
//		return NETWORK_STATE_MOBILE.equals(DeviceUtil.getNetworkState());
//	}
//
//	public static int getScreenWidth() {
//
//		return ExApplication.getContext().getResources().getDisplayMetrics().widthPixels;
//	}
//
	public static int getScreenHeight() {

		return MyApplication.getContext().getResources().getDisplayMetrics().heightPixels;
	}
//
//	/**
//	 * add by Daisw
//	 * @return 状态栏的高度
//	 */
//	public static int getStatusBarHeight() {
//
//		int height = 0;
//
//		try {
//			Class<?> c = Class.forName("com.android.internal.R$dimen");
//			Object obj = c.newInstance();
//			Field field = c.getField("status_bar_height");
//			int id = Integer.parseInt(field.get(obj).toString());
//			height = ExApplication.getContext().getResources().getDimensionPixelSize(id);
//		} catch (Exception e) {
//		}
//
//		return height;
//	}
//
//	public static boolean hasSinaWeiboClient() {
//		try {
//
//			PackageInfo packageInfo = ExApplication.getContext().getPackageManager().getPackageInfo("com.sina.weibo", 0);
//			if (packageInfo == null)
//				return false;
//
//			int highBit = packageInfo.versionName.charAt(0);
//			return highBit > 50 ? true : false;// 50 = 2
//
//		} catch (NameNotFoundException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	/**
//	 * 判断是否有电话功能
//	 * @return
//	 * true:有电话功能
//	 * false:没有电话功能
//	 */
//	public static boolean hasPhone() {
//
//		TelephonyManager telephony = (TelephonyManager) ExApplication.getContext().
//														getSystemService(Context.TELEPHONY_SERVICE);
//		return telephony.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE ? false : true;
//	}
}
