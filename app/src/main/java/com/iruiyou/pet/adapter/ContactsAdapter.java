package com.iruiyou.pet.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.CheckRegisterBean;
import com.iruiyou.pet.fragment.Fragment2;

import java.util.List;
import java.util.Map;

/**
 * 我的页面 适配器
 * 作者：jiaopeirong on 2018/5/9 22:18
 * 邮箱：chinajpr@163.com
 */
public class ContactsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Map<String, Boolean> map;
    private List<CheckRegisterBean.DataBean> isAcconntList;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnTextViewClickListener onTextViewClickListener;

    public ContactsAdapter() {
        super(R.layout.adapter_contacts);
    }

    public void setNewDatas(List<String> list, Map<String, Boolean> map, List<CheckRegisterBean.DataBean> isAcconntList) {
        setNewData(list);
        this.map = map;
        this.isAcconntList = isAcconntList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        onItemLongClickListener=listener;
    }
    public void setOnTextViewClickListener(OnTextViewClickListener listener){
        onTextViewClickListener=listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        String[] split = item.split("\\" + Fragment2.Person);
        String[] split1 = item.split("\\" + Fragment2.SIGN);//街区*后的通讯录姓名
        try {
//            helper.setText(R.id.name, split[0])
//                    .setText(R.id.details, split[1]);

            TextView tv0 = helper.getView(R.id.invitation0);
            TextView tv1 = helper.getView(R.id.invitation1);
            ImageView iv = helper.getView(R.id.headIv);
            if (isAcconntList != null && helper.getAdapterPosition() < isAcconntList.size()) {//已经注册的用户
                GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + isAcconntList.get(helper.getAdapterPosition()).getHeadImg(), iv);
                String company = isAcconntList.get(helper.getAdapterPosition()).getCompany();
                String positionAcconnt = isAcconntList.get(helper.getAdapterPosition()).getPosition();
                if(TextUtils.isEmpty(company)&&!TextUtils.isEmpty(positionAcconnt)){
                    helper.setText(R.id.details,positionAcconnt);
                }else if(TextUtils.isEmpty(positionAcconnt)&&!TextUtils.isEmpty(company)){
                    helper.setText(R.id.details,company);
                }else if(!TextUtils.isEmpty(company)&&!TextUtils.isEmpty(positionAcconnt)){
                    helper.setText(R.id.details,isAcconntList.get(helper.getAdapterPosition()).getCompany()+"\t"+isAcconntList.get(helper.getAdapterPosition()).getPosition());
                }else {
                    helper.setText(R.id.details,"");
                }
//                helper.setText(R.id.details,isAcconntList.get(helper.getAdapterPosition()).getCompany()+"\t"+isAcconntList.get(helper.getAdapterPosition()).getPosition());
                helper.setText(R.id.name,split[0]+"("+split1[1]+")");//设置已注册的用户姓名和通讯录中的姓名
                tv0.setVisibility(View.GONE);
                tv1.setVisibility(View.VISIBLE);
                if (isAcconntList.get(helper.getAdapterPosition()).getInvitedCode().equals(SharePreferenceUtils.getBaseSharePreference().readInviteCode())) {
                    tv1.setText(mContext.getResources().getString(R.string.InvitedByMe));
                } else {//已注册
                    tv1.setVisibility(View.GONE);
                    tv1.setText(mContext.getResources().getString(R.string.Registered));
                }
            } else {//未注册的用户
                helper.setText(R.id.name, split[0])
                        .setText(R.id.details, split[1]);
                iv.setImageResource(R.drawable.head_home);
                if (map.get(split[1])) {
                    tv0.setVisibility(View.GONE);
                    tv1.setVisibility(View.VISIBLE);
                    tv1.setText(mContext.getResources().getString(R.string.AlreadyInvited));
//                    tv0.setClickable(true);
                } else {//未邀请
                    tv0.setVisibility(View.VISIBLE);
                    tv1.setVisibility(View.GONE);
                    tv0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            onItemClickListener.onClick(helper.getAdapterPosition());
                            onTextViewClickListener.onTextViewClick(helper.getAdapterPosition());
                        }
                    });
                }
            }

        } catch (Exception e) {

        }



        if (onItemClickListener!=null) {
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //判断只有注册的账号才可以点击
                    if(isAcconntList.size()-1>=helper.getAdapterPosition()){
                        onItemClickListener.onClick(helper.getAdapterPosition());
                    }
                }
            });
        }

            if (onItemLongClickListener!=null){
                helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        onItemLongClickListener.onItemLongClick(helper.getAdapterPosition());
                        return false;
                    }
                });
            }


    }
//    @Override
//    public void onBindViewHolder(final BaseViewHolder holder, int position) {//新加
//            if (onItemClickListener!=null){
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        onItemClickListener.onClick(holder.getAdapterPosition());
//                    }
//                });
//
//            if (onItemLongClickListener!=null){
//                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View view) {
//                        onItemLongClickListener.onItemLongClick(holder.getAdapterPosition());
//                        return false;
//                    }
//                });
//            }
//        }
//    }


    public interface OnItemClickListener {
        void onClick(int position);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }
    public interface OnTextViewClickListener{
        void onTextViewClick(int position);
    }
}