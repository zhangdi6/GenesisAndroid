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
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 职业身份
 * 作者：sgf
 *
 */
public class PositionActivity extends BaseActivity {
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
    private int num = 0;
    private String workFlag;

    @Override
    public int getLayout() {
        return R.layout.activity_set_language;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.position));
        initDta();
    }

    private void initDta() {
        workFlag = getIntent().getStringExtra(Constant.WORKFLAG);
        String title = Objects.requireNonNull(getIntent().getExtras()).getString(Constant.TITLE);
        if(StringUtil.isNotEmpty(title)){
            setTitle(title);
        }
        int professionalIdentity = Objects.requireNonNull(getIntent().getExtras()).getInt("ProfessionalIdentity");
        mDatas = new ArrayList<>();
        ConfigBean configBean = App.getConfigBean();
        if(configBean!=null&&configBean.getData()!=null)
        {
            List<LangChildBean.DbSelectInputStandardsBean.ProfessionalIdentitiesBean> professionalIdentities =
                    configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getProfessionalIdentities();
//            int position = SharePreferenceUtils.getBaseSharePreference().readPositionType();
            for(int i=1;i<professionalIdentities.size();i++)
            {
                LangChildBean.DbSelectInputStandardsBean.ProfessionalIdentitiesBean professionalIdentitiesBean= professionalIdentities.get(i);
                if(professionalIdentitiesBean.getSecondary()==null){
                    CheckBean checkBean=new CheckBean();
                    checkBean.setStr(professionalIdentitiesBean.getLangValue());
                    checkBean.setIntValue(professionalIdentitiesBean.getDbKey());
                    if(professionalIdentity==checkBean.getIntValue())//默认选中
                    {
                        checkBean.setChecked(true);
                    }else {
                        checkBean.setChecked(false);
                    }
                    mDatas.add(checkBean);
                }else if(professionalIdentitiesBean.getSecondary().size()>0){
                    for(int n=0;n<professionalIdentitiesBean.getSecondary().size();n++){
                        LangChildBean.DbSelectInputStandardsBean.ProfessionalIdentitiesBean professionalIdentitiesBean1 = professionalIdentitiesBean.getSecondary().get(n);
                        CheckBean checkBean=new CheckBean();
                        checkBean.setStr(professionalIdentitiesBean1.getLangValue());
                        checkBean.setIntValue(professionalIdentitiesBean1.getDbKey());
                        if(professionalIdentity==checkBean.getIntValue())//默认选中
                        {
                            checkBean.setChecked(true);
                        }else {
                            checkBean.setChecked(false);
                        }
                        mDatas.add(checkBean);
                    }
                }
            }
            selectLanguageAdapter = new SelectLanguageAdapter(this, mDatas);
            lv_select_language.setAdapter(selectLanguageAdapter);

        }
        int readType = SharePreferenceUtils.getBaseSharePreference().readType();
        if(readType == 1){
            SharePreferenceUtils.getBaseSharePreference().savePositionType(professionalIdentity);
            SharePreferenceUtils.getBaseSharePreference().saveType(0);
        }else if(readType == 2){
            SharePreferenceUtils.getBaseSharePreference().savePositionType(professionalIdentity);
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
                num = position;
                SharePreferenceUtils.getBaseSharePreference().savePositionType(num+1);//设置是点击的条目
                SharePreferenceUtils.getBaseSharePreference().saveType2(num+1);

                Intent intent2 = new Intent(PositionActivity.this, BasicInfoActivity.class);
//                if(workFlag.equals(Constant.DESCRIBE)){
                    intent2.putExtra(Constant.BASIC, CodeUtils.getInstance().getProfessional(bean.getIntValue()));
                intent2.putExtra("dbKey", bean.getIntValue());
//                }
                setResult(0, intent2);
                finish();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_PositionActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_PositionActivity);
        MobclickAgent.onPause(this);
    }
}
