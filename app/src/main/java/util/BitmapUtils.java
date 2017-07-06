package util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class BitmapUtils {

	private static final String TAG = BitmapUtils.class.getSimpleName();

	public static byte[] bitmapToByteArray(Bitmap image, int quality) {

		if (null == image)
			return null;

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			image.compress(Bitmap.CompressFormat.JPEG, quality, baos);
			return baos.toByteArray();
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return null;
	}

	public static Bitmap byteArrayToBitmap(byte[] data) {

		if (null == data)
			return null;

		try {
			return BitmapFactory.decodeByteArray(data, 0, data.length);
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return null;
	}

	/**
	 * compute Sample Size
	 * 
	 * @param options
	 * @param minSideLength
	 * @param maxNumOfPixels
	 * @return
	 */
	private static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	/**
	 * compute Initial Sample Size
	 * 
	 * @param options
	 * @param minSideLength
	 * @param maxNumOfPixels
	 * @return
	 */
	private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.    
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	public static int[] getMaxNumOfPixels(byte[] binary, int maxSize) {

		return getMaxNumOfPixels(byteArrayToBitmap(binary), maxSize);
	}

	public static int[] getMaxNumOfPixels(Bitmap bitmap, int maxSize) {

		if (bitmap == null)
			return new int[] { 0, 0 };

		return resize(bitmap.getWidth(), bitmap.getHeight(), maxSize);
	}

	public static int[] getMaxNumOfPixels(String srcPath, int maxSize) {

		if (TextUtils.isEmpty(srcPath))
			return new int[] { 0, 0 };

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(srcPath, options);

		return resize(options.outWidth, options.outHeight, maxSize);
	}

	private static int[] resize(int w, int h, int maxSize) {

			Log.e(TAG, "getMaxNumOfPixels original ## w: " + w + " # h: " + h);

		if (w > h) {// 横图
			h = (int) (h / ((float) w / maxSize));
			w = maxSize;
		} else if (w < h) {// 竖图
			w = (int) (w / ((float) h / maxSize));
			h = maxSize;
		} else if (w == h) {// 方图
			w = maxSize;
			h = maxSize;
		}


			Log.e(TAG, "getMaxNumOfPixels changed ## w: " + w + " # h: " + h);

		return new int[] { w, h };
	}

	/**
	 * 用固定的压缩率压缩图片
	 * 
	 * @param srcPath
	 * @param inSampleSize
	 * @return
	 */
	public static Bitmap compress(String srcPath, int inSampleSize) {

		if (TextUtils.isEmpty(srcPath))
			return null;

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(srcPath, options);


			Log.e(TAG, "compress original ## width: " + options.outWidth + " # height: " + options.outHeight);

		options.inJustDecodeBounds = false;
		options.inSampleSize = inSampleSize;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, options);


			Log.e(TAG, "compress changed ## width: " + bitmap.getWidth() + " # height: " + bitmap.getHeight());

		return bitmap;
	}

	/**
	 * 压缩图片质量
	 * 
	 * @param srcPath
	 * 				图片的路径
	 * @param maxSize
	 * 				长轴的长度，单位px
	 * @return
	 */
	public static Bitmap getImage(String srcPath, int maxSize) {

		if (TextUtils.isEmpty(srcPath))
			return null;

		try {

			BitmapFactory.Options newOpts = new BitmapFactory.Options();
			newOpts.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(srcPath, newOpts);
			int w = newOpts.outWidth;
			int h = newOpts.outHeight;

				Log.e(TAG, "getImage original ## w: " + w + " # h: " + h + " # maxSize: " + maxSize);

			if (w <= maxSize && h <= maxSize) {
				newOpts.inJustDecodeBounds = false;
				return BitmapFactory.decodeFile(srcPath, newOpts);
			}

			int minSideLength = -1;
			if (w > h && w > maxSize) {// 横图
				w = maxSize;
				h = h * w / maxSize;
				minSideLength = h;
			} else if (w < h && h > maxSize) {// 竖图
				h = maxSize;
				w = w * h / maxSize;
				minSideLength = w;
			} else if (w == h && w > maxSize) {// 方图
				w = maxSize;
				h = maxSize;
				minSideLength = w;
			}
			int rate = computeSampleSize(newOpts, minSideLength, w * h);


				Log.e(TAG, "getImage compress ## rate: " + rate);

			newOpts.inSampleSize = rate;
			newOpts.inJustDecodeBounds = false;

			Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);


				Log.e(TAG, "getImage changed ## width: " + bitmap.getWidth() + " # height: " + bitmap.getHeight());

			return bitmap;

		} catch (Throwable t) {
			t.printStackTrace();
		}

		return null;
	}

	/**
	 * 压缩图片尺寸，保持图片的宽高比。
	 * 
	 * @param bitmap
	 * 				源
	 * @param maxSize
	 * 				长轴的长度，单位px
	 * @param srcPath
	 * 				图片的路径，用来检验图片是否被旋转
	 * @return
	 */
	public static Bitmap getImage(Bitmap bitmap, int maxSize, String srcPath) {

		if (null == bitmap)
			return bitmap;

		try {
			
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();


				Log.e(TAG, "getImage original ## width: " + width + " # height: " + height);

			if (width <= maxSize && height <= maxSize)
				return bitmap;

			Matrix matrix = new Matrix();
			if (width > height) {
				float rate = (float) maxSize / width;
				matrix.postScale(rate, rate);
				matrix.postRotate(readPictureDegree(srcPath));
			} else {
				float rate = (float) maxSize / height;
				matrix.postScale(rate, rate);
				matrix.postRotate(readPictureDegree(srcPath));
			}

			Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
			
			bitmap.recycle();
			bitmap = null;
			

				Log.e(TAG, "getImage changed ## width: " + newBitmap.getWidth() + " # height: " + newBitmap.getHeight());
			
			return newBitmap;
			
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return null;
	}

	public synchronized static String addBitmapToCache(byte[] data) {

		if (null == data)
			return null;

		try {
			File targetFileDirectory = QaStorageUtil.getPicsDir();
			File targetFile = new File(targetFileDirectory.getAbsolutePath(), String.valueOf(System.currentTimeMillis()));
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile));
			bos.write(data);
			bos.flush();
			bos.close();
			return targetFile.getPath();
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 读取图片属性：旋转的角度
	 * @param srcPath 图片绝对路径
	 * @return degree 旋转的角度
	 */
    public static int readPictureDegree(String srcPath) {
    	
        int degree  = 0;
        
        try {
        	
        	ExifInterface exifInterface = new ExifInterface(srcPath);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
        
        return degree;
    }
    
    public static Bitmap rotateImageView(int degree , Bitmap bitmap) {
    	
    	if(degree == 0 || bitmap == null)
    		return bitmap;
    	
    	try {
    		Matrix matrix = new Matrix();
    	    matrix.postRotate(degree);
    	    Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    	    
    	    bitmap.recycle();
    	    bitmap = null;
    	    
    	    return newBitmap;
		} catch (Throwable t) {
			return null;
		}
    }

	public static Bitmap convertViewToBitmap(View view) {

		view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
				View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());  //根据字符串的长度显示view的宽度
		view.buildDrawingCache();
		return view.getDrawingCache();
	}
}
