package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.DMOptionListBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 职位详情界面
 */
public class PositionDetailActivity extends BaseActivity {

    @BindView(R.id.text_position_title)
    TextView text_position_title;

    @BindView(R.id.text_position_price)
    TextView text_position_price;

    @BindView(R.id.text_area)
    TextView text_area;

    @BindView(R.id.text_position_class)
    TextView text_position_class;

    @BindView(R.id.text_ren)
    TextView text_ren;

    @BindView(R.id.text_position_detail)
    TextView text_position_detail;

    @BindView(R.id.text_sex)
    TextView text_sex;

    @BindView(R.id.text_age)
    TextView text_age;

    @BindView(R.id.text_school)
    TextView text_school;

    @BindView(R.id.text_address)
    TextView text_address;

    private DMOptionListBean.DataBean dataBean;

    @Override
    public int getLayout() {
        return R.layout.activity_position_detail;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        dataBean = (DMOptionListBean.DataBean) getIntent().getSerializableExtra("dataBean");
        if(dataBean!=null){
            text_position_title.setText(dataBean.getTitle());
            text_position_price.setText(dataBean.getSalary());
            text_area.setText(dataBean.getCity_name());
            text_position_class.setText(dataBean.getJob_type_title());
            text_ren.setText(dataBean.getHire_number()+"人");
            text_position_detail.setText(dataBean.getDescription());
            text_sex.setText(dataBean.getSex_demand());
            text_age.setText(dataBean.getAge_demand());
            text_school.setText(dataBean.getEducation_demand());
            if(dataBean.getAddress()!=null&&(dataBean.getAddress().size()>0)){
                text_address.setText(dataBean.getAddress().get(0));
            }
        }
    }
}
