package com.example.zhangyang.screenshotqrcodedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import dialog.QaBaseDialog;
import util.DensityUtil;


/**
 * Created by zhangyang on 2017/7/3.
 *
 */

public class DealDetailPicShareDialog extends QaBaseDialog implements View.OnClickListener {
    private Activity mActivity;
    private Bitmap mBitmap;
    private String mImgLocalName;

    public DealDetailPicShareDialog(Activity activity, Bitmap bitmap, String imglocalName) {
        super(activity, R.style.ex_dialog_push_down);
        mActivity = activity;
        mBitmap = bitmap;
        mImgLocalName = imglocalName;
        initDialog();
    }

    /**
     * 初始化dialog的属性
     */
    private void initDialog() {

        setCanceledOnTouchOutside(true);
        getWindow().setLayout(DensityUtil.dip2px(287), ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.CENTER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(getLayoutInflater().inflate(R.layout.dialog_share_pic_dealdetail, null), new ViewGroup.LayoutParams(DensityUtil.dip2px(287), ViewGroup.LayoutParams.WRAP_CONTENT));
        initContentView();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tvcancel://取消
//                dismiss();
//                break;
//            case R.id.llWXSessionDiv://微信好友
//                sendToWX(v.getId());
//                break;
//            case R.id.llWXTimelineDiv://朋友圈
//                sendToWX(v.getId());
//                break;
//            case R.id.llqqDiv://QQ
//                sendToQQ(mActivity);
//                break;
//            case R.id.llWeiboDiv://微博
//                ShareWeiboUtil.shareSina(mActivity,"", mImgLocalName, new SinaShareCallBack());
//                break;
        }
    }

//    private void sendToQQ(Activity act) {
//        Bundle bundle = new Bundle();
//
//        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
//        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, mImgLocalName);
//        Tencent tencent = Tencent.createInstance("100737787", act);
//        tencent.shareToQQ(act, bundle, new QQListener());
//    }
//
//    private void sendToWX(int id) {
//        if (checkWeixinApp()) {
//            if (id == R.id.llWXSessionDiv) {
//                ShareWeixinUtil.getInstance(mActivity).sendWenxinImage2Session(mBitmap);
//            } else if (id == R.id.llWXTimelineDiv) {
//                ShareWeixinUtil.getInstance(mActivity).sendWenxinImage2TimeLine(mBitmap);
//            }
//        }
//        dismiss();
//    }

    @Override
    protected void initContentView() {
//        FrescoImageView fivpic = (FrescoImageView) findViewById(R.id.fivpic);
//        TextView tvcancel = (TextView) findViewById(R.id.tvcancel);
//        tvcancel.setOnClickListener(this);
//
//        View weixin = findViewById(R.id.llWXSessionDiv);
//        weixin.setOnClickListener(this);
//
//        View wxTimeLine = findViewById(R.id.llWXTimelineDiv);
//        wxTimeLine.setOnClickListener(this);
//
//        View qq = findViewById(R.id.llqqDiv);
//        qq.setOnClickListener(this);
//
//        View weibo = findViewById(R.id.llWeiboDiv);
//        weibo.setOnClickListener(this);
//
//
//        if (mBitmap != null) {
//            fivpic.setImageBitmap(mBitmap);
//        }
    }

//    /**
//     * 检查是否安装了微信
//     *
//     * @return
//     */
//    private static boolean checkWeixinApp() {
//        if (!DeviceUtil.hasApp(SNS_WX_PACKAGE_NAME)) {
//            ToastUtil.showToast(ResLoader.getStringById(R.string.toast_share_weixin));
//            return false;
//        }
//        return true;
//    }

//    private static class QQListener implements IUiListener {
//        @Override
//        public void onComplete(Object o) {
//        }
//
//        @Override
//        public void onError(UiError uiError) {
//            if (LogMgr.isDebug())
//                LogMgr.i("onError:", "code:" + uiError.errorCode + ", msg:"
//                        + uiError.errorMessage + ", detail:" + uiError.errorDetail);
//        }
//
//        @Override
//        public void onCancel() {
//        }
//    }
//    /**
//     * 新浪微博的分享回调
//     *
//     * @author liulongzhenhai
//     */
//    private static class SinaShareCallBack implements ShareWeiboUtil.SinaShareListener {
//        @Override
//        public void onSuccess() {
//            ToastUtil.showToast(R.string.toast_share_succeed);
//        }
//
//        @Override
//        public void onFailed(int errorCode) {
//            switch (errorCode) {
//                case ERROR_INTERNET_DISABLE:
//                    ToastUtil.showToast(R.string.toast_common_no_network);
//                    break;
//                case ERROR_INTERNET_TIMEOUT:
//                    ToastUtil.showToast(R.string.toast_exception_net_timeout);
//                    break;
//                default:
//                    ToastUtil.showToast(R.string.toast_share_failed);
//                    break;
//            }
//        }
//    }

}
