package util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import com.example.zhangyang.screenshotqrcodedemo.DealDetailPicShareDialog;
import com.example.zhangyang.screenshotqrcodedemo.MyApplication;
import com.example.zhangyang.screenshotqrcodedemo.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhangyang on 2017/7/5.
 * <p>
 * 折扣详情页 截屏+底部拼接二维码+弹层+分享
 */

public class ShareDealDetailScreenQRCodeUtils {
    private String imglocalName;
    private Activity mActivity;

    public void showShare(Activity activity, String imgPath, String qrUrl) {
        mActivity = activity;

        Flowable.just(getBitmap(imgPath, qrUrl))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        if(mActivity != null && !mActivity.isFinishing()){

                            DealDetailPicShareDialog dialog = new DealDetailPicShareDialog(mActivity, bitmap, imglocalName);
                            dialog.show();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private Bitmap getBitmap(String imgPath, String qrUrl) {
        //获取相册里的截屏图片，根据路径找到图片
        Bitmap bmp = BitmapUtils.getImage(imgPath, DeviceUtil.getScreenHeight());
        return getQrCodeImage(bmp, qrUrl);
    }

    /**
     * 将Viwe转为bitmap  将两个bitmap拼接
     *
     * @param bmp1
     * @param qrUrl
     * @return
     */
    private Bitmap getQrCodeImage(Bitmap bmp1, String qrUrl) {
        Bitmap QR_bmp = getQrCodefromURL(qrUrl);
        View contentView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.view_qrcodelayout, null);
//        FrescoImageView qrimg = (FrescoImageView) contentView.findViewById(R.id.qrimg);
//        qrimg.setImageBitmap(QR_bmp);

        contentView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        contentView.layout(0, 0, bmp1.getWidth(), contentView.getMeasuredHeight());
        contentView.setDrawingCacheEnabled(true);
        contentView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(contentView.getDrawingCache());
        Bitmap newBitmap = addBitmap(bmp1, bitmap);
        return newBitmap;
    }

    /**
     * 根据url生成二维码
     *
     * @param qrUrl
     * @return
     */
    private Bitmap getQrCodefromURL(String qrUrl) {
        Bitmap Bmp_qrcode = null;
        // FIXME ====================== 注释掉的是Zxing二维码生成 ，由于引用资源问题，暂时注释掉 ==========================
//        if (qrUrl != null) {
//            try {
//                Bmp_qrcode = CodeCreator.createQRCode(qrUrl);
//            } catch (WriterException e) {
//                e.printStackTrace();
//            }
//        }
        return Bmp_qrcode;
    }

    /**
     * 获取屏幕截屏
     *
     * @param mActivity
     * @return
     */
    private Bitmap getCurrentScreen(Activity mActivity) {
        //1.构建Bitmap
        WindowManager windowManager = mActivity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        //2.获取屏幕
        View decorview = mActivity.getWindow().getDecorView();
        decorview.setDrawingCacheEnabled(true);
        decorview.destroyDrawingCache();
        bmp = decorview.getDrawingCache();

        return bmp;
    }

    /**
     * 将两个bitmap拼接起来
     *
     * @return
     */
    private Bitmap addBitmap(Bitmap topBitmap, Bitmap bottomBitmap) {
        if (topBitmap == null || topBitmap.isRecycled()
                || bottomBitmap == null || bottomBitmap.isRecycled()) {
            return null;
        }
        int width = 0;
        width = Math.max(topBitmap.getWidth(), bottomBitmap.getWidth());
        Bitmap tempBitmapT = topBitmap;
        Bitmap tempBitmapB = bottomBitmap;



        int height = tempBitmapT.getHeight() + tempBitmapB.getHeight();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Rect topRect = new Rect(0, 0, tempBitmapT.getWidth(), tempBitmapT.getHeight());
        Rect bottomRect = new Rect(0, 0, tempBitmapB.getWidth(), tempBitmapB.getHeight());

        Rect bottomRectT = new Rect(0, tempBitmapT.getHeight(), width, height);

        canvas.drawBitmap(tempBitmapT, topRect, topRect, null);
        canvas.drawBitmap(tempBitmapB, bottomRect, bottomRectT, null);

        saveLocal(bitmap);
        return bitmap;
    }

    /**
     * 保持本地
     *
     * @param result
     */
    private void saveLocal(Bitmap result) {
        String SavePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Qyer/";

        File path = new File(SavePath + "/Screen");
        if (!path.exists()) {
            path.mkdirs();
        }
        //文件
        String picName = System.currentTimeMillis() + ".jpg";
        File file = new File(SavePath + "/Screen/", picName);
        imglocalName = file.getAbsolutePath();
        try {
            FileOutputStream fos = null;
            fos = new FileOutputStream(file);
            if (null != fos) {
                result.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                fos.flush();
                fos.close();
                Toast.makeText(mActivity, "该图片保存到" + SavePath, Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(mActivity.getContentResolver(), file.getAbsolutePath(), picName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        mActivity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + "/sdcard/namecard/")));
    }
}
