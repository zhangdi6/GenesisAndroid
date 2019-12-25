package com.iruiyou.pet.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.FriendApplicationListActivity;
import com.iruiyou.pet.activity.UserDetailsActivity;
import com.iruiyou.pet.bean.AdoptBean;
import com.iruiyou.pet.bean.BaseBean;
import com.iruiyou.pet.bean.BasicInfoBean;
import com.iruiyou.pet.bean.GetAppListBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.StringUtil;

import java.util.List;

public class FriendApplicationListAdapter extends BaseQuickAdapter<GetAppListBean.ApplyBean, BaseViewHolder> {
    private boolean isMine;
    private Context context;
    private int infoCount;
    private boolean isNewVersion;
    public FriendApplicationListAdapter(boolean isMine, Context context) {
        super(R.layout.adapter_friend_application);
        this.isMine=isMine;
        this.context=context;
    }

    public void setNewVersion(boolean newVersion) {
        isNewVersion = newVersion;
    }

    public void setNewdataAndCount(int infoCount, List<GetAppListBean.ApplyBean> data) {
        this.infoCount = infoCount;
        if (data != null) {
            setNewData(data);
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, GetAppListBean.ApplyBean item) {
        final int userId;final String realName;
        BasicInfoBean basicInfo=null;
        Button btn_action=helper.getView(R.id.btn_action);
        LinearLayout linear_new_action = helper.getView(R.id.linear_new_action);
        TextView text_application=helper.getView(R.id.text_application);
        TextView text_self_desc=helper.getView(R.id.text_self_desc);

        ImageView imageAgree = helper.getView(R.id.image_agree);
        ImageView imageRefuse = helper.getView(R.id.image_refuse);
        ImageView imageView=helper.getView(R.id.headIv);
        imageView.setImageResource(R.drawable.head_home);
        ImageView iconComplete=helper.getView(R.id.image_complete);
        LinearLayout linearCount=helper.getView(R.id.linear_count);
        LinearLayout view_bottom=helper.getView(R.id.view_bottom);

        if(item.getUserIdA()== Integer.valueOf(SharePreferenceUtils.getBaseSharePreference().readUserId()))
        {
            basicInfo=item.getBasicInfoB();
        }
        else if(item.getUserIdB()== Integer.valueOf(SharePreferenceUtils.getBaseSharePreference().readUserId()))
        {
            basicInfo=item.getBasicInfoA();
        }

        if(basicInfo!=null)
        {
            userId=basicInfo.getUserId();
            realName=basicInfo.getRealName();
            helper.setText(R.id.name,basicInfo.getRealName());
            helper.setText(R.id.text_position,basicInfo.getPosition());
            helper.setText(R.id.text_commpany_name,basicInfo.getCompany());
            if(StringUtil.isNoBlankAndNoNull(basicInfo.getHeadImg()))
            {
                String url= BaseApi.baseUrlNoApi+basicInfo.getHeadImg();
                url=url.replace("//img","/img");
                GlideUtils.displayRound(context,url,imageView);
            }
            if(item.getStatusA2B()==3&&(item.getStatusB2A()==3))//已经是好友
            {
                text_application.setVisibility(View.VISIBLE);
                btn_action.setVisibility(View.GONE);
                linear_new_action.setVisibility(View.GONE);
            } else if(item.getStatusA2B()==0&&(item.getStatusB2A()==0)){
                text_application.setVisibility(View.VISIBLE);
                text_application.setText("已忽略");
                btn_action.setVisibility(View.GONE);
                linear_new_action.setVisibility(View.GONE);
            }else
            {
                text_application.setVisibility(View.GONE);
                btn_action.setVisibility(View.VISIBLE);
                if(isMine) //我申请的
                {
                    btn_action.setText(context.getResources().getText(R.string.back_request));

                } else if(isNewVersion){

                    btn_action.setVisibility(View.GONE);
                    linear_new_action.setVisibility(View.VISIBLE);
                    view_bottom.setVisibility(View.VISIBLE);
                    text_self_desc.setText(basicInfo.getSelfDesc());

                }
                else //申请加我的
                {
                    btn_action.setText(context.getResources().getString(R.string.get_pbs,((int)basicInfo.getFriendPrice())));
                }
            }

            if(!isNewVersion){
                linearCount.setVisibility(View.VISIBLE);
            }else{
                linearCount.setVisibility(View.GONE);
            }

            if(infoCount!=0)
            {
                GetAppListBean.Statistics statisticsInfo= item.getStatistics();
                if(statisticsInfo!=null)
                {
                    double fenshu=((double)(statisticsInfo.getBasicCount()+statisticsInfo.getBlockchainCount()+statisticsInfo.getWorkCount()+statisticsInfo.getEducationCount()))/infoCount;
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
            }
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserDetailsActivity.startAction((Activity) context,userId,realName);
                }
            });
            btn_action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isMine) {
                        cancelApply(userId,helper.getAdapterPosition());
                    } else {
                        getAdopt(userId,item);
                    }
                    int i = SharePreferenceUtils.getBaseSharePreference().readApplicationCount();
                    SharePreferenceUtils.getBaseSharePreference().saveMeg(i);

                }
            });
            imageAgree.setOnClickListener(view -> {
                getAdopt(userId,item);
            });
            imageRefuse.setOnClickListener(view -> {
                ignore(userId,item);
            });
        }

    }



    /**
     * 通过申请请求
     */
    private void getAdopt(int userid, GetAppListBean.ApplyBean item) {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                AdoptBean adoptBean = GsonUtils.parseJson(resulte, AdoptBean.class);
                if (adoptBean.getStatusCode() == Constant.SUCCESS) {
                    int applicationCount = SharePreferenceUtils.getBaseSharePreference().readApplicationCount();
                    applicationCount --;
                    SharePreferenceUtils.getBaseSharePreference().saveApplicationCount(applicationCount);
                    item.setStatusA2B(3);
                    item.setStatusB2A(3);
                    notifyDataSetChanged();

                }else if(!TextUtils.isEmpty(adoptBean.getMessage()))
                {
                    T.showShort(adoptBean.getMessage());
                }
            }

            @Override
            public void onError(ApiException e) {
            }
        }, (FriendApplicationListActivity)context).adopt(userid);
    }

    private void ignore(int userid, GetAppListBean.ApplyBean item){
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                AdoptBean adoptBean = GsonUtils.parseJson(resulte, AdoptBean.class);
                if (adoptBean.getStatusCode() == Constant.SUCCESS) {
                    int applicationCount = SharePreferenceUtils.getBaseSharePreference().readApplicationCount();
                    applicationCount --;
                    SharePreferenceUtils.getBaseSharePreference().saveApplicationCount(applicationCount);
                    item.setStatusA2B(0);
                    item.setStatusB2A(0);
                    notifyDataSetChanged();
                }else if(!TextUtils.isEmpty(adoptBean.getMessage()))
                {
                    T.showShort(adoptBean.getMessage());
                }
            }

            @Override
            public void onError(ApiException e) {
            }
        }, (FriendApplicationListActivity)context).ignore(userid);
    }



    /**
     *
     */
    private void cancelApply(int targetUserId,int position) {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(!TextUtils.isEmpty(resulte))
                {
                    BaseBean baseBean= GsonUtils.parseJson(resulte, BaseBean.class);
                    if (baseBean.getStatusCode() == Constant.SUCCESS) {
                        remove(position);
                        T.showShort("撤回成功，PBS已返还!");
                    }
                    else if(!TextUtils.isEmpty(baseBean.getMessage()))
                    {
                        T.showShort(baseBean.getMessage());
                    }
                }
            }

            @Override
            public void onError(ApiException e) {

            }
        }, (FriendApplicationListActivity)context).cancelApply(targetUserId);
    }


}
