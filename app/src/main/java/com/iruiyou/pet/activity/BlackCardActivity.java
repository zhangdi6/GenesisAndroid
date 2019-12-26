package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlackCardActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;

    @Override
    public int getLayout() {
        return R.layout.activity_black_card;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
