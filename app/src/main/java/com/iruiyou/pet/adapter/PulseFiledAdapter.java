package com.iruiyou.pet.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.LookForSbBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.Objects;

public class PulseFiledAdapter extends BaseQuickAdapter<LookForSbBean.ItemData,BaseViewHolder> {
    private Context context;
    private int infoCount;
    private PulseFiledAdapter pulseFiledAdapter;
    public PulseFiledAdapter( Context context) {
        super(R.layout.item_pulse_filed);
        this.context=context;
        pulseFiledAdapter=this;
    }

    public int getInfoCount() {
        return infoCount;
    }

    public void setInfoCount(int infoCount) {
        this.infoCount = infoCount;
    }

    @Override
    protected void convert(BaseViewHolder helper, LookForSbBean.ItemData item) {
        LookForSbBean.DataBean.BasicsInfo basicsInfo=item.getBasicsInfo();
        LookForSbBean.DataBean.StatisticsInfo statisticsInfo=item.getStatisticsInfo();
        helper.setText(R.id.text_name,basicsInfo.getRealName());
        helper.setText(R.id.text_commpany_name,basicsInfo.getCompany());
        helper.setText(R.id.text_zhiwei_name,basicsInfo.getPosition());
        RoundedImageView imageView= helper.getView(R.id.image_head);
        ImageView iconComplete=helper.getView(R.id.image_complete);
        LinearLayout linearCount=helper.getView(R.id.linear_count);
        if(!TextUtils.isEmpty(basicsInfo.getHeadImg()))
        {
            String url= BaseApi.baseUrlNoApi+basicsInfo.getHeadImg();
            url=url.replace("//img","/img");
            GlideUtils.displayRound(context,url,imageView);
        }
        else
        {
            imageView.setImageResource(R.drawable.head_home);
        }
        if(infoCount!=0)
        {
            double fenshu=((double)(statisticsInfo.getBasicCount()+statisticsInfo.getPhotoCount()+statisticsInfo.getWorkCount()+statisticsInfo.getEducationCount()))/infoCount;
            if(fenshu>=1)
            {
                iconComplete.setImageResource(R.drawable.icon_comlete);
                linearCount.setBackgroundResource(R.drawable.bg_pulse_complete);
            }
            else
            {
                iconComplete.setImageResource(R.drawable.icon_un_comlete);
                linearCount.setBackgroundResource(R.drawable.bg_pulse_un_complete);
            }
            String value=((int)(fenshu*100))+"%";
            helper.setText(R.id.text_complete_count,value);
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getOnItemChildClickListener()).onItemChildClick(pulseFiledAdapter,view,helper.getAdapterPosition());
            }
        });
    }
}
