package com.iruiyou.pet.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.PetList;

/**
 * 作者：jiaopeirong on 2018/5/22 23:07
 * 邮箱：chinajpr@163.com
 */
public class MineHeadAdapter extends BaseQuickAdapter<PetList.DataBean, BaseViewHolder> {
    public MineHeadAdapter() {
        super(R.layout.view_head);
    }

    @Override
    protected void convert(BaseViewHolder helper, PetList.DataBean item) {
        ImageView iv = helper.getView(R.id.iv);
        String replace = item.getHeadImg().replace("\\", "");
        String url = BaseApi.baseUrl + replace;
        GlideUtils.displayRound(App.getInstance(), url, iv);
    }
}
