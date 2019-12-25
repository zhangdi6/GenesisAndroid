package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.utils.GlobalLog;
import com.iruiyou.pet.utils.PageNameConstant;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的-关于我们
 * 作者：sgf
 *
 */
public class AboutUsActivity extends BaseActivity {
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
    @BindView(R.id.tvVersion)
    TextView tvVersion;
    @BindView(R.id.llPraise)
    LinearLayout llPraise;
    @BindView(R.id.llOfficial)
    LinearLayout llOfficial;

    @BindView(R.id.linear_youzan_item)
    LinearLayout youzanItem;
    @BindView(R.id.linear_youzan_list)
    LinearLayout youzanList;
    @BindView(R.id.edit_good_price)
    EditText editGoodPrice;
    @BindView(R.id.edit_good_number)
    EditText editGoodNumber;
    @BindView(R.id.edit_good_page_number)
    EditText getEditGoodPageNumber;

    @BindView(R.id.linear_fanxain_show)
    LinearLayout linear_fanxain_show;

    @BindView(R.id.text_zongdingcun)
    TextView text_zongdingcun;

    @BindView(R.id.text_zongfanxian)
    TextView text_zongfanxian;

    @BindView(R.id.text_yifanxian)
    TextView text_yifanxian;

    @BindView(R.id.text_fanxain_shengyu)
    TextView text_fanxain_shengyu;

    @BindView(R.id.text_shouyi)
    TextView text_shouyi;

    @BindView(R.id.text_shouyi_zongshu)
    TextView text_shouyi_zongshu;

    @Override
    public int getLayout() {
        return R.layout.activity_about_us;
    }

//    private final String value="13718790245,15639741210,13732281677,18106732789";

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.aboutUs));
        initDta();
    }

    private void initDta() {
        tvVersion.setText(getString(R.string.currentVersion)+"\t"+getVersionName(this));
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

    @OnClick({R.id.llOfficial, R.id.llPraise, R.id.text_buy_test, R.id.linear_youzan_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llOfficial:
                StartActivityManager.startWebViewNewActivity(this,getString(R.string.official_website),"https://www.maibase.com");
                break;
            case R.id.llPraise:
                StartActivityManager.startWebViewNewActivity(this,getString(R.string.praise),"https://www.maibase.com");
                break;
            case R.id.text_buy_test:
//                new UserTask(new HttpOnNextListener() {
//                    @Override
//                    public void onNext(String resulte, String method) {
//                        GlobalLog.e("test","getGoodById resulte is :"+resulte);
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//
//                    }
//                },AboutUsActivity.this).froceAction();

//                String [] phones = value.split(",");
//                for(String phone:phones){
//                    if(StringUtil.isNotEmpty(phone)&&phone.length()==11){
//                        new UserTask(new HttpOnNextListener() {
//                            @Override
//                            public void onNext(String resulte, String method) {
//                                GlobalLog.e("test","getGoodById resulte is :"+resulte);
//                            }
//
//                            @Override
//                            public void onError(ApiException e) {
//
//                            }
//                        },AboutUsActivity.this).unfreezePBS(phone);
//                    }
//                }

//                int number=Integer.valueOf(editGoodNumber.getText().toString().trim());

                break;
            case R.id.linear_youzan_list:
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        GlobalLog.e("test","resulte resulte is :"+resulte);
                    }

                    @Override
                    public void onError(ApiException e) {

                    }
                }, AboutUsActivity.this).scheduleEquityPartnersDeposit();

//                String pageNumber=getEditGoodPageNumber.getText().toString().trim();
//                new UserTask(new HttpOnNextListener() {
//                    @Override
//                    public void onNext(String resulte, String method) {
//                        GlobalLog.e("test","getYZGoodsList resulte is :"+resulte);
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//
//                    }
//                },AboutUsActivity.this).getYZGoodsListV2(1);

//                String phone=getEditGoodPageNumber.getText().toString().trim();
//                if(StringUtil.isNotEmpty(phone)){
//                    new UserTask(new HttpOnNextListener() {
//                        @Override
//                        public void onNext(String resulte, String method) {
//                            DeleteBean deleteBean = GsonUtils.parseJson(resulte, DeleteBean.class);
//                            T.showShort(deleteBean.getMessage());
//                        }
//
//                        @Override
//                        public void onError(ApiException e) {
//                        }
//                    }, AboutUsActivity.this).deleteFriend(Integer.valueOf(phone).intValue());
//                }

//                String phone=getEditGoodPageNumber.getText().toString().trim();
//                new UserTask(new HttpOnNextListener() {
//                    @Override
//                    public void onNext(String resulte, String method) {
//                        if(StringUtil.isNotEmpty(resulte)){
//                            try {
//                                JSONObject jsonObject = new JSONObject(resulte);
//                                if(Constant.SUCCESS == jsonObject.optInt("statusCode")){
//                                    JSONObject data = jsonObject.getJSONObject("data");
//                                    text_zongdingcun.setText("总定存数: "+data.optString("totalSaveNumber"));
//                                    text_zongfanxian.setText("总返现数: "+data.optString("totalReturnNumber"));
//                                    text_yifanxian.setText("已返现数: "+data.optString("totalActuallyReturnNumber"));
//                                    text_fanxain_shengyu.setText("剩余可返现: "+data.optString("totalRemainderReturnNumber"));
//                                    text_shouyi.setText("账户收益余额: "+data.optString("rebateCrystal"));
//                                    text_shouyi_zongshu.setText("可用收益总数: "+data.optString("totalRebateCrystal"));
//                                    T.showShort("操作成功");
//                                }else if(StringUtil.isNotEmpty(jsonObject.optString("message"))){
//                                    T.showShort(jsonObject.optString("message"));
//                                }else{
//                                    T.showShort("操作失败");
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        Log.e("test","");
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//                        T.showShort("操作失败");
//                    }
//                },AboutUsActivity.this).assetsImputation(phone);

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
}
