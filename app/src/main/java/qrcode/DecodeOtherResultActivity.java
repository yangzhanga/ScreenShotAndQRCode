//package qrcode;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//import com.qyer.android.jinnang.R;
//import com.qyer.android.lib.activity.QyerActivity;
//
///**
// * Created by xiaobai on 16/4/21.
// */
//public class DecodeOtherResultActivity extends QyerActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_qrcode_no_result);
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void initTitleView() {
//
//        addTitleLeftImageView(R.drawable.ic_back_white, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        addTitleMiddleTextView("扫描结果");
//
//    }
//
//    @Override
//    protected void initContentView() {
//
//    }
//
//    public static void startActivity(Activity activity) {
//
//        Intent intent = new Intent(activity, DecodeOtherResultActivity.class);
//        activity.startActivity(intent);
//    }
//}
