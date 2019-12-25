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
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.SelectLanguageAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CheckBean;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.LangChildBean;
import com.iruiyou.pet.utils.CodeUtils;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GendersSelectActivity extends BaseActivity {
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
    private SelectLanguageAdapter selectLanguageAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_set_language;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("性别");
        initDta();
    }

    private void initDta() {
        String title = Objects.requireNonNull(getIntent().getExtras()).getString(Constant.TITLE);
        if(StringUtil.isNotEmpty(title)){
            setTitle(title);
        }
        int genders = Objects.requireNonNull(getIntent().getExtras()).getInt("genders",0);
        mDatas = new ArrayList<>();
        ConfigBean configBean = App.getConfigBean();
        if(configBean!=null&&configBean.getData()!=null)
        {
            List<LangChildBean.DbSelectInputStandardsBean.CrystalRecordsBean> professionalIdentities =
                    configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getGenders();
            for(int i=1;i<professionalIdentities.size();i++)
            {
                LangChildBean.DbSelectInputStandardsBean.CrystalRecordsBean professionalIdentitiesBean= professionalIdentities.get(i);
                CheckBean checkBean=new CheckBean();
                checkBean.setStr(professionalIdentitiesBean.getLangValue());
                checkBean.setIntValue(professionalIdentitiesBean.getDbKey());
                if(genders==checkBean.getIntValue())//默认选中
                {
                    checkBean.setChecked(true);
                }else {
                    checkBean.setChecked(false);
                }
                mDatas.add(checkBean);
            }
            selectLanguageAdapter = new SelectLanguageAdapter(this, mDatas);
            lv_select_language.setAdapter(selectLanguageAdapter);

        }


        lv_select_language.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SharePreferenceUtils.getBaseSharePreference().saveType(2);
                for (CheckBean bean : mDatas) {//全部设为未选中
                    bean.setChecked(false);
                }
                CheckBean bean= mDatas.get(position);//.setChecked(true);//点击的设为选中
                bean.setChecked(true);
                Intent intent2 = new Intent(GendersSelectActivity.this, BasicInfoActivity.class);
                intent2.putExtra(Constant.BASIC, CodeUtils.getInstance().getGenders(bean.getIntValue()));
                intent2.putExtra("dbKey", bean.getIntValue());
                setResult(0, intent2);
                finish();
            }
        });
    }
}
