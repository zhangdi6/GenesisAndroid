package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.AppManager;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置页
 * 作者：jiaopeirong on 2018/5/24 21:16
 * 邮箱：chinajpr@163.com
 */
public class SetActivity extends BaseActivity {
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
    @BindView(R.id.updatePwd)
    TextView updatePwd;
    @BindView(R.id.versionName)
    TextView versionName;
    @BindView(R.id.logout)
    TextView logout;
    @BindView(R.id.language)
    TextView language;
    @BindView(R.id.langTv)
    TextView langTv;
//    @BindView(R.id.LlAbout)
//    LinearLayout LlAbout;

    @Override
    public int getLayout() {
        return R.layout.activity_set;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.set));
        versionName.setText(getVersionName(this));
        int readLanguageType = SharePreferenceUtils.getBaseSharePreference().readLanguageType();
        if (readLanguageType == 1) {
            langTv.setText(getResources().getString(R.string.Chinese));
        } else {
            langTv.setText(getResources().getString(R.string.English));
        }
    }


    public String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    @OnClick({R.id.updatePwd, R.id.logout, R.id.languageLl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.updatePwd:
//                startActivity(ModifyPwdActivity.class);
                startActivity(SetPwdActivity.class);
                break;
//            case R.id.LlAbout:
//                startActivity(AboutUsActivity.class);
//                break;
            case R.id.logout:
                ACache aCache = ACache.get(SetActivity.this);
                aCache.remove(TagConstants.loginfig);
                SharePreferenceUtils.getBaseSharePreference().saveUserId("");
                SharePreferenceUtils.getBaseSharePreference().savePassword("");
                SharePreferenceUtils.getBaseSharePreference().saveShowEdit(-1);
                AppManager.getAppManager().finishOtherActivity(MainActivity.class);
                SharePreferenceUtils.getBaseSharePreference().saveState(false);
                // App.isLogin=false;
                startActivity(LoginOrRegisterActivity.class);
                finish();
                break;
            case R.id.languageLl:
                startActivity(SetLanguageActivity.class);
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                final int[] mWhich = {0};
//                final String[] mWhichStr = {""};
//                builder.setTitle(getResources().getString(R.string.language));
//                String[] strings = {getResources().getString(R.string.Chinese), getResources().getString(R.string.English)};
//                int checkedItem = 0;
//                if (TagConstants.ZH.equals(SharePreferenceUtils.getBaseSharePreference().readLanguage())){
//                    checkedItem = 0;
//                } else {
//                    checkedItem = 1;
//                }
//                builder.setSingleChoiceItems(strings, checkedItem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mWhich[0] = which;
//                        mWhichStr[0] = strings[which];
//                    }
//                });
//                builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String s = "";
//                        //中文
//                        if (mWhich[0] == 0) {
//                            s = mWhichStr[0];
//                            SharePreferenceUtils.getBaseSharePreference().saveLanguage(TagConstants.ZH);
//                        } else {
//                            s = mWhichStr[0];
//                            SharePreferenceUtils.getBaseSharePreference().saveLanguage(TagConstants.EN);
//                        }
//                        if (!langTv.getText().toString().equals(s)) {
//                            langTv.setText(s);
//                            //重启MainActivity
//                            Intent intent = new Intent(SetActivity.this, MainActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                        }
//                        langTv.setText(s);
//
//                        dialog.dismiss();
//                    }
//                });
//                builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//                AlertDialog dialog = builder.create();
//                dialog.show();

                break;
        }
    }
}
