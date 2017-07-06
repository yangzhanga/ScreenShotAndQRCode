package util;

import android.os.AsyncTask;
import android.os.Environment;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daisw
 */
public class QaStorageUtil {

	public static final String FILE_SCHEMA_PREFIX = "file://";

	public static final String FILE_EXTEND_ZIP = ".zip";
	public static final String FILE_EXTEND_TEMP = ".temp";
	public static final String FILE_EXTEND_HTML = ".html";
	public static final String FILE_EXTEND_TXT = ".txt";
	public static final String FILE_EXTEND_DLI = ".dli";

	public static final String FILE_NAME_JN_COVER_BG = "coverbg";
	public static final String FILE_NAME_JN_COVER_TITLE = "covertitle.png";
	public static final String FILE_NAME_JN_MENU_JSON = "menu.json";
	public static final String FILE_NAME_POI_LIST_JSON = "poi_list.json";

	private static final String HOME_DIR = "/qyer/qyerguide/";// 大应用主目录
	private static final String HTML_FILE_DIR = HOME_DIR + "html_file/";// 存放解压后的锦囊
	private static final String ZIP_DIR = HOME_DIR + "zip/";// 锦囊下载目录
	private static final String PICS_DIR = HOME_DIR + "pics/";// 应用所有网络图片保存目录
	private static final String UPDATE_CACHE_DIR = HOME_DIR + "update_cache/";// 锦囊下载记录目录
	private static final String WEBVIEW_DIR = "/qyer/qyerguide/webview_cache/";
	private static final String QYER_PIC_DIR = "/Pictures/QyerPic";// 聊天 图片保存目录
	private static final String QYER_TEMP = HOME_DIR + "tmp";// 临时的目录,存放其他临时东西
	private static final String LATEST_VISIT_DESTINATION = HOME_DIR + "latest_visit_destination/";// 存储c代码产生的文件
	private static final String HYBIRD_DIR = HOME_DIR + "hybird/";


	public static File getExStorageDir() {

		return Environment.getExternalStorageDirectory();
	}

//	/**
//	 * 获取大应用sd卡主目录
//	 * @return
//	 */
//	public static File getHomeDir() {
//
//		File homeDir = new File(getExStorageDir(), HOME_DIR);
//		if (!homeDir.exists())
//			homeDir.mkdirs();
//
//		return homeDir;
//	}
//
//	public static File getHybirdDir(){
//
//		File dir = new File(getExStorageDir(), HYBIRD_DIR);
//		if(!dir.exists()){
//			dir.mkdirs();
//		}
//		return dir;
//	}
//
//	/**
//	 * 获取c代码产生的存储pid的文件
//	 * @return
//	 */
//	public static File getLatestVisitDir(){
//
//		File dir = new File(QaApplication.getContext().getFilesDir(), LATEST_VISIT_DESTINATION);
//		if(!dir.exists()){
//			dir.mkdirs();
//		}
//		return dir;
//	}
//
//
	/**
	 * 获取大app缓存图片目录
	 * @return
	 */
	public static File getPicsDir() {

		File picsDir = new File(getExStorageDir(), PICS_DIR);
		if (!picsDir.exists()) {
			picsDir.mkdirs();
		}

		return picsDir;
	}
//
//	/**
//	 * 获取锦囊下载记录的目录，目录下都是*.dli文件
//	 * @return
//	 */
//	public static File getGuideJnDownloadCacheDir() {
//
//		File updateCacheDir = new File(getExStorageDir(), UPDATE_CACHE_DIR);
//		if (!updateCacheDir.exists()) {
//			updateCacheDir.mkdirs();
//		}
//
//		return updateCacheDir;
//	}
//
//	/**
//	 * 根据锦囊英文名称获取锦囊下载记录下的指定*.dli文件
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static File getGuideJnDownloadCacheFile(String jnNameEn) {
//
//		return new File(getGuideJnDownloadCacheDir(), jnNameEn + FILE_EXTEND_DLI);
//	}
//
//	/**
//	 * 根据锦囊英文名称删除锦囊下载记录 *.dli文件
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static boolean deleteGuideJnDownloadCacheFile(String jnNameEn) {
//
//		return IOUtil.deleteFile(getGuideJnDownloadCacheFile(jnNameEn));
//	}
//
//	/**
//	 * 获取锦囊下载的zip包的目录
//	 * @return
//	 */
//	public static File getGuideJnZipDir() {
//
//		File zipCacheDir = new File(getExStorageDir(), ZIP_DIR);
//		if (!zipCacheDir.exists())
//			zipCacheDir.mkdirs();
//
//		return zipCacheDir;
//	}
//
//	/**
//	 * 获取锦囊下载的zip包目录下的指定zip文件
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static File getGuideJnZipFile(String jnNameEn) {
//
//		return new File(getGuideJnZipDir(), jnNameEn + FILE_EXTEND_ZIP);
//	}
//
//	/**
//	 * 获取锦囊下载的zip包目录下的指定zip文件的路径
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static String getGuideJnZipPath(String jnNameEn) {
//
//		return getGuideJnZipFile(jnNameEn).toString();
//	}
//
//	/**
//	 * 获取锦囊下载的zip包目录下的指定zip 临时文件
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static File getGuideJnTempFile(String jnNameEn) {
//
//		return new File(getGuideJnZipDir(), jnNameEn + FILE_EXTEND_TEMP);
//	}
//
//	/**
//	 * 根据锦囊英文名称删除锦囊下载的zip包目录下的锦囊zip文件
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static boolean deleteGuideJnZipFile(String jnNameEn) {
//
//		return IOUtil.deleteFile(getGuideJnZipFile(jnNameEn));
//	}
//
//	/**
//	 * 获取解压后存放锦囊的目录
//	 * @return
//	 */
//	public static File getGuideJnHtmlFileDir() {
//
//		File htmlFileDir = new File(getExStorageDir(), HTML_FILE_DIR);
//		if (!htmlFileDir.exists())
//			htmlFileDir.mkdirs();
//
//		return htmlFileDir;
//	}
//
//	/**
//	 * 根据锦囊英文名获取锦囊解压目录下的 该锦囊的解压文件目录
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static File getGuideJnHtmlFileDir(String jnNameEn) {
//
//		return new File(getGuideJnHtmlFileDir(), jnNameEn);
//	}
//
//	/**
//	 * 根据锦囊英文名获取锦囊解压目录下的 该锦囊的解压文件目录路径
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static String getGuideJnHtmlFileDirPath(String jnNameEn) {
//
//		return getGuideJnHtmlFileDir(jnNameEn).getAbsolutePath();
//	}
//
//	/**
//	 * 根据锦囊英文名称获取解压后锦囊目录下的锦囊封面文件
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static File getGuideJnHtmlFileCoverFile(String jnNameEn) {
//
//		final File file = getGuideJnHtmlFileDir(jnNameEn);
//		File[] filelist = file.listFiles(new FilenameFilter() {
//			@Override
//			public boolean accept(File dir, String filename) {
//				if (filename.startsWith(FILE_NAME_JN_COVER_BG))
//					return true;
//				else
//					return false;
//			}
//		});
//		if (filelist.length > 0 ){
//			LogMgr.i("path:"+filelist[0]);
//			return filelist[0];
//		}else {
//			return null;
//		}
//	}
//
//	/**
//	 * 根据锦囊英文名称获取解压后锦囊目录下的锦囊标题封面文件
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static File getJnHtmlFileCoverTitleFile(String jnNameEn) {
//
//		return new File(getGuideJnHtmlFileDir(jnNameEn), FILE_NAME_JN_COVER_TITLE);
//	}
//
//	/**
//	 * 根据锦囊英文名获取该锦囊对应的解压目录的文件协议字符串
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static String getGuideJnHtmlDirFileUrl(String jnNameEn) {
//
//		return FILE_SCHEMA_PREFIX + getGuideJnHtmlFileDir(jnNameEn) + File.separator;
//	}
//
//	/**
//	 * 根据锦囊英文名删除解压目录
//	 * @param jnNameEn
//	 */
//	public static void deleteGuideJnHtmlFileDir(String jnNameEn) {
//
//		if (jnNameEn == null || jnNameEn.length() == 0)
//			return;
//
//		try {
//
//			IOUtil.deleteDir(getGuideJnHtmlFileDir(jnNameEn), false);
//
//		} catch (Exception e) {
//
//		}
//	}
//
//	public static String getHtmlFileDirPoiJsonFile(String jnNameEn) {
//
//		return getGuideJnHtmlFileDirPath(jnNameEn) + File.separator + FILE_NAME_POI_LIST_JSON;
//	}
//
//	public static void asyncCreateNomediaFileInHomeDir() {
//
//        try{
//
//            new AsyncTask<Void, Void, Void>() {
//
//                @Override
//                protected Void doInBackground(Void... params) {
//
//                    try {
//
//                        File noMediaFile = new File(getHomeDir(), ".nomedia");
//                        if (!noMediaFile.exists())
//                            noMediaFile.createNewFile();
//
//                    } catch (IOException e) {
//
//                        if (LogMgr.isDebug())
//                            e.printStackTrace();
//                    }
//                    return null;
//                }
//            }.execute();
//
//        }catch(Throwable t){
//
//            if(LogMgr.isDebug())
//                t.printStackTrace();
//        }
//
//
//	}
//
//	/**
//	 * 解析锦囊解压包中的书签数据
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static List<JnBookCatalog> parseGuideJnHtmlDirBookCatalog(String jnNameEn) {
//
//		ArrayList<JnBookCatalog> list = new ArrayList<JnBookCatalog>();
//		try {
//
//			JSONObject jsonObject = new JSONObject(getGuideJnJsonText(getGuideJnHtmlFileDir(jnNameEn) + File.separator
//					+ FILE_NAME_JN_MENU_JSON));
//			JSONArray jsonArray = jsonObject.getJSONArray("JsonRoot");
//			for (int i = 0; i < jsonArray.length(); i++) {
//
//				JSONObject job = jsonArray.getJSONObject(i);
//				JnBookCatalog bc = new JnBookCatalog();
//				bc.setTitle(job.getString("title"));
//				bc.setFilePath(job.getString("file"));
//				list.add(bc);
//			}
//		} catch (Exception e) {
//
//			if (LogMgr.isDebug())
//				LogMgr.e("~~parseBookCatalog ## " + e.getMessage());
//
//			e.printStackTrace();
//		}
//
//		return list;
//	}
//
//	/**
//	 * 解析锦囊解压包中的poi数据
//	 * @param jnNameEn
//	 * @return
//	 */
//	public static ArrayList<PoiDetail> paraseGuideJnHtmlDirPois(String jnNameEn) {
//
//		ArrayList<PoiDetail> list = new ArrayList<PoiDetail>();
//		try {
//
//			JSONObject jsonObject = new JSONObject(getGuideJnJsonText(getHtmlFileDirPoiJsonFile(jnNameEn)));
//			JSONArray jsonArray = jsonObject.getJSONArray("JsonRoot");
//			for (int i = 0; i < jsonArray.length(); i++) {
//
//				final JSONObject obj = jsonArray.getJSONObject(i);
//				final PoiDetail poi = new PoiDetail();
//				poi.setCateid(MapUtil.ICON_RECOMMEND);//锦囊的poi category id 设置为 地图的MapUtil.ICON_RECOMMEND
//				poi.setCate_name(obj.getString("category_name"));
//				poi.setChinesename(obj.getString("cn_name"));
//				poi.setEnglishname(obj.getString("en_name"));
//				poi.setId(obj.getInt("id"));
//				poi.setLat(obj.getString("lat"));
//				poi.setLng(obj.getString("lng"));
//				list.add(poi);
//			}
//
//		} catch (Exception e) {
//
//			list.clear();
//			if (LogMgr.isDebug())
//				e.printStackTrace();
//		}
//
//		return list;
//	}
//
//	private static String getGuideJnJsonText(String path) throws Exception {
//
//		StringBuilder sb = new StringBuilder();
//		BufferedReader reader = null;
//		try {
//
//			File f = new File(path);
//			String buffer = null;
//			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8"));
//			while ((buffer = reader.readLine()) != null) {
//				sb.append(buffer);
//			}
//
//		} catch (Exception e) {
//
//			sb.setLength(0);
//		} finally {
//
//			IOUtil.closeReader(reader);
//		}
//
//		// String jsonStr = null;
//		// StringBuilder sb = new StringBuilder();
//		// File file = new File(path);
//		// FileInputStream fis = new FileInputStream(file);
//		// InputStreamReader isr = new InputStreamReader(fis, "utf-8");
//		// BufferedReader reader = new BufferedReader(isr);
//		// while ((jsonStr = reader.readLine()) != null) {
//		// sb.append(jsonStr);
//		// }
//		// reader.close();
//		// isr.close();
//		// fis.close();
//		return "{\"JsonRoot\":" + sb.toString() + "}";
//	}
//
//	public static String getWebViewCachePath() {
//		final String path = new StringBuilder(Environment.getExternalStorageDirectory().toString()).append(WEBVIEW_DIR)
//				.toString();
//		final File dirFile = new File(path);
//		if (!dirFile.exists()) {
//			dirFile.mkdirs();
//		}
//		return path;
//	}
//
//	public static File getQyerPicturesDir() {
//
//		File qyerPicturesDir = new File(getExStorageDir(), QYER_PIC_DIR);
//		if (!qyerPicturesDir.exists()) {
//			qyerPicturesDir.mkdirs();
//		}
//
//		return qyerPicturesDir;
//	}
//
//	public static File getQyerTempDir() {
//
//		File qyerTemp = new File(getExStorageDir(), QYER_TEMP);
//		if (!qyerTemp.exists()) {
//			qyerTemp.mkdirs();
//		}
//
//		return qyerTemp;
//	}
//
//	/**
//	 * IM模块，缓存聊天图片信息，add by Daisw。
//	 * @param data
//	 * @return file path
//	 */
//	public static String addBitmapToCache(byte[] data) {
//
//		if (data == null || data.length == 0)
//			return null;
//
//		try {
//			File targetFileDirectory = QaStorageUtil.getPicsDir();
//			File targetFile = new File(targetFileDirectory.getAbsolutePath(), String.valueOf(System.currentTimeMillis()));
//			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile));
//			bos.write(data);
//			bos.flush();
//			bos.close();
//			return targetFile.getPath();
//		} catch (Throwable t) {
//			t.printStackTrace();
//		}
//
//		return null;
//	}
//
//	/**
//	 * <sdcard>/qyer/qyerguide/pics  //图片  {@link #getPicsDir()}
//	 * <sdcard>/qyer/qyerguide/webview_cache  //webview缓存  {@link #getWebViewCachePath()} ()}
//	 * <sdcard>/Android/data/<packageName>/cache  // sdcard提供的app缓存 QaApplication.getContext().getExternalCacheDir()
//	 * /data/data/package_name/files			  // data/data下的文件
//	 * /data/data/package_name/cache			  // data/data下的缓存
//	 * @return
//	 */
//	public static String getCacheSize() {
//
//		// 计算缓存大小
//		long fileSize = 0;
//		String cacheSize = "0KB";
//
//		File picDir = getPicsDir();
//		File webviewCache = new File(getWebViewCachePath());
//		File externalCacheDir = QaApplication.getContext().getExternalCacheDir();
//		File filesDir = QaApplication.getContext().getFilesDir();
//		File cacheDir = QaApplication.getContext().getCacheDir();
//
//		fileSize += getDirSize(picDir);
//		fileSize += getDirSize(webviewCache);
//		fileSize += getDirSize(externalCacheDir);
//		fileSize += getDirSize(filesDir);
//		fileSize += getDirSize(cacheDir);
//
//		if (fileSize > 0)
//			cacheSize = formatFileSize(fileSize);
//
//		LogMgr.i("Qyer Lastminute cacheSize=== " + cacheSize);
//		return cacheSize;
//	}
//
//	/**
//	 * 清除app缓存
//	 */
//	public static void clearAppCache() {
//
//		// 清除webview缓存
//		File webviewCache = new File(getWebViewCachePath());
//
//		// 先删除WebViewCache目录下的文件
//		if (webviewCache.exists() && webviewCache.isDirectory()) {
//			for (File item : webviewCache.listFiles())
//				item.delete();
//			webviewCache.delete();
//		}
//
//		QaApplication.getContext().deleteDatabase("webview.db");
//		QaApplication.getContext().deleteDatabase("webview.db-shm");
//		QaApplication.getContext().deleteDatabase("webview.db-wal");
//		QaApplication.getContext().deleteDatabase("webviewCache.db");
//		QaApplication.getContext().deleteDatabase("webviewCache.db-shm");
//		QaApplication.getContext().deleteDatabase("webviewCache.db-wal");
//		QaApplication.getContext().deleteDatabase("webviewCookiesChromiumPrivate.db");
//		QaApplication.getContext().deleteDatabase("webviewCookiesChrominum.db");
//		// 清除数据缓存
//		clearCacheFolder(QaApplication.getContext().getFilesDir(), System.currentTimeMillis());
//		clearCacheFolder(QaApplication.getContext().getCacheDir(), System.currentTimeMillis());
//		clearCacheFolder(QaApplication.getContext().getExternalCacheDir(), System.currentTimeMillis());
//
//		// 清除 pic
//		clearCacheFolder(getPicsDir(), System.currentTimeMillis());
//
//		// 清除 webview缓存目录
//		clearCacheFolder(webviewCache, System.currentTimeMillis());
//
//		// 清除 chrom内核 webview缓存目录
//		File appWebviewDir = new File(QaApplication.getContext().getFilesDir().getParent(), "app_webview");
//		File chromWebViewCacheDir = new File(appWebviewDir, "cache");
//		clearCacheFolder(chromWebViewCacheDir, System.currentTimeMillis());
//	}
//
//	/**
//	 * 清除缓存目录
//	 *
//	 * @param dir
//	 *            目录
//	 * @param
//	 *
//	 * @return
//	 */
//	public static int clearCacheFolder(File dir, long curTime) {
//
//		int deletedFiles = 0;
//		if (dir != null && dir.isDirectory()) {
//			try {
//				for (File child : dir.listFiles()) {
//
//					if (child.isDirectory()) {
//						deletedFiles += clearCacheFolder(child, curTime);
//					}
//					if (child.lastModified() < curTime) {
//						if (child.delete()) {
//							deletedFiles++;
//						}
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return deletedFiles;
//	}
//
//
//
//	/**
//	 * 获取目录文件大小
//	 *
//	 * @param dir
//	 * @return
//	 */
//	public static long getDirSize(File dir) {
//
//		if (dir == null) {
//			return 0;
//		}
//		if (!dir.isDirectory()) {
//			return 0;
//		}
//		long dirSize = 0;
//		File[] files = dir.listFiles();
//		for (File file : files) {
//			if (file.isFile()) {
//				dirSize += file.length();
//			} else if (file.isDirectory()) {
//				dirSize += file.length();
//				dirSize += getDirSize(file); // 递归调用继续统计
//			}
//		}
//		return dirSize;
//	}
//
//	/**
//	 * 转换文件大小
//	 * @param fileS
//	 * @return B/KB/MB/GB
//	 */
//	public static String formatFileSize(long fileS) {
//
//		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
//		String fileSizeString = "";
//		if (fileS < 1024) {
//			fileSizeString = df.format((double) fileS) + "B";
//		} else if (fileS < 1048576) {
//			fileSizeString = df.format((double) fileS / 1024) + "KB";
//		} else if (fileS < 1073741824) {
//			fileSizeString = df.format((double) fileS / 1048576) + "MB";
//		} else {
//			fileSizeString = df.format((double) fileS / 1073741824) + "G";
//		}
//		return fileSizeString;
//	}


}
