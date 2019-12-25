package com.iruiyou.pet.activity;

import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述:
 * 创建日期:2018/6/7 on 16:01
 * 作者:JiaoPeiRong
 */
public class ImgActivity extends BaseActivity {

    @BindView(R.id.photoview)
    PhotoView photoview;

    @Override
    public int getLayout() {
        return R.layout.activity_photoview;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra(TagConstants.IMG);
        GlideUtils.displayBitmap(this,url,photoview);
    }

}
