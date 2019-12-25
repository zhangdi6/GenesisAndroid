package com.iruiyou.pet.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.utils.T;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.utils.PageNameConstant;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页-我的钱包-存入资产2
 * 作者：sgf
 *
 */
public class AssetsDepositedActivity extends BaseActivity {
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.edit)
    ImageView edit;
    @BindView(R.id.ll_title_left_view)
    LinearLayout llTitleLeftView;
    @BindView(R.id.titleview)
    RelativeLayout titleview;
    @BindView(R.id.tv_assets_deposited_money)
    TextView tv_assets_deposited_money;
    @BindView(R.id.bt_assets_deposited_amount)
    Button bt_assets_deposited_amount;
    @BindView(R.id.bt_assets_deposited_copy_address)
    Button bt_assets_deposited_copy_address;
    @BindView(R.id.bt_assets_deposited_save_code)
    Button bt_assets_deposited_save_code;
    private static final int SAVE_SUCCESS = 0;//保存图片成功
    private static final int SAVE_FAILURE = 1;//保存图片失败
    private static final int SAVE_BEGIN = 2;//开始保存图片
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAVE_BEGIN:
                    T.showShort("开始保存图片...");
                    break;
                case SAVE_SUCCESS:
                    T.showShort("图片保存成功,请到相册查找");
                    break;
                case SAVE_FAILURE:
                    T.showShort("图片保存失败,请稍后再试...");
                    break;
            }
        }
    };

//    @BindView(R.id.et_wallet_address)
//    EditText et_wallet_address;
//    @BindView(R.id.im_QR_code)
//    ImageView im_QR_code;
//    @BindView(R.id.bt_duplicate_wallet_address)
//    Button bt_duplicate_wallet_address;
    private Context context;

    @Override
    public int getLayout() {
        return R.layout.activity_assets_deposited;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.assets_deposited));
        context = AssetsDepositedActivity.this;
        initDta();
        getRefresh();
    }

    private void initDta() {

        String pbsAmount = getIntent().getStringExtra("pbsAmount");
        double pbsAmount2 = getIntent().getDoubleExtra("pbsAmount2", 0);
        tv_assets_deposited_money.setText(pbsAmount2+"");
//        et_wallet_address.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                ClipboardManager cm =(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
//                cm.setText(et_wallet_address.getText().toString());
//                return false;
//            }
//        });
    }

    /**
     * 刷新
     */
    private void getRefresh() {
    }

    @OnClick({R.id.bt_assets_deposited_save_code, R.id.bt_assets_deposited_copy_address, R.id.bt_assets_deposited_amount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_assets_deposited_save_code://保存二维码图片
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.wallet_address);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.obtainMessage(SAVE_BEGIN).sendToTarget();
                        //保存
                        saveImageToGallery(AssetsDepositedActivity.this,bmp);
                    }
                }).start();
                break;
            case R.id.bt_assets_deposited_copy_address://复制地址
                ClipboardManager cm1 =(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                Objects.requireNonNull(cm1).setText("0x953916e928080a0f3f538a23474db730809dca4f");
                T.showShort(getString(R.string.replication_success));
                break;
            case R.id.bt_assets_deposited_amount://复制金额
                ClipboardManager cm2 =(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                Objects.requireNonNull(cm2).setText(tv_assets_deposited_money.getText().toString());
                T.showShort(getString(R.string.replication_success));
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_AboutUsActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_AboutUsActivity);
        MobclickAgent.onPause(this);
    }
    /**
     * 保存图片
     * @param context
     * @param bmp
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            mHandler.obtainMessage(SAVE_FAILURE).sendToTarget();
            return;
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
        mHandler.obtainMessage(SAVE_SUCCESS).sendToTarget();
    }
}
