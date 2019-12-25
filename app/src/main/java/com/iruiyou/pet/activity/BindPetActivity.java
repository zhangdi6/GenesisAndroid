package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CodeBean;
import com.iruiyou.pet.bean.HeadEventPost;
import com.ta.utdid2.android.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述:绑定云宠
 * 创建日期:2018/6/1 on 10:59
 * 作者:JiaoPeiRong
 */
public class BindPetActivity extends BaseActivity {
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
    @BindView(R.id.acount)
    EditText acount;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.sure)
    Button sure;

    @Override
    public int getLayout() {
        return R.layout.activity_bing_pet;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        titleNameText.setText("绑定云宠");
    }

    @OnClick({R.id.ll_title_left_view, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                finish();
                break;
            case R.id.sure:
                if (StringUtils.isEmpty(acount.getText().toString())) {
                    T.showShort("邮箱不能为空");
                    return;
                }
                if (StringUtils.isEmpty(pwd.getText().toString())) {
                    T.showShort("密码不能为空");
                    return;
                }
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
                        T.showShort(codeBean.getMessage());
                        if (codeBean.getStatusCode() == 0){
                            EventBusUtils.getInstance().postEvent(new HeadEventPost());
                            finish();
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).bindCloudPets(acount.getText().toString(), pwd.getText().toString());
                break;
        }
    }
}
