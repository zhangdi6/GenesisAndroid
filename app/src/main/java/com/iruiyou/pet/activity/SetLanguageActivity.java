package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.SelectLanguageAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CheckBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置语言
 * 作者：sgf
 *
 */
public class SetLanguageActivity extends BaseActivity {
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
    @BindView(R.id.lv_select_language)
    ListView lv_select_language;
    private List<CheckBean> mDatas;
    private String[] languageName;
    private SelectLanguageAdapter selectLanguageAdapter;
    private int num = 0;

    @Override
    public int getLayout() {
        return R.layout.activity_set_language;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.select_language));
        initDta();
    }

    private void initDta() {
        mDatas = new ArrayList<>();
        int position = SharePreferenceUtils.getBaseSharePreference().readLanguageType();
        languageName = new String[]{"English","中文"};
        for (int i = 0; i < languageName.length; i++) {
            if (i == position) {//默认选中第一个
                mDatas.add(new CheckBean(languageName[i], true));
                continue;
            }
            mDatas.add(new CheckBean(languageName[i], false));
        }
        selectLanguageAdapter = new SelectLanguageAdapter(this, mDatas);
        lv_select_language.setAdapter(selectLanguageAdapter);

        lv_select_language.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private String s;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (CheckBean bean : mDatas) {//全部设为未选中
                    bean.setChecked(false);
                }
                mDatas.get(position).setChecked(true);//点击的设为选中
                num = position;
                SharePreferenceUtils.getBaseSharePreference().saveLanguageType(num);//设置是点击的条目
                //英文
                if (num == 0) {
                    s = languageName[0];
                    SharePreferenceUtils.getBaseSharePreference().saveLanguage(TagConstants.EN);
                } else {//中文
                    s = languageName[1];
                    SharePreferenceUtils.getBaseSharePreference().saveLanguage(TagConstants.ZH);
                }
                //重启MainActivity
                Intent intent = new Intent(SetLanguageActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }
}
