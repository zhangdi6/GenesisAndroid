package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HehuorenAgreementActivity extends BaseActivity {
    @BindView(R.id.text_content)
    TextView textContent;
    @Override
    public int getLayout() {
        return R.layout.activity_member_agreement;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("众筹合伙人协议");
        textContent.setText(getResources().getText(R.string.hehuoren_agreement));
        textContent.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @OnClick({R.id.ll_title_left_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                finish();
                break;
        }
    }
}
