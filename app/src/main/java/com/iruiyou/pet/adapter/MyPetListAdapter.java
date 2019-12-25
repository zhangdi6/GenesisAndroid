package com.iruiyou.pet.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.PetDetailActivity;
import com.iruiyou.pet.bean.PetList;

import java.util.Iterator;
import java.util.Map;

/**
 * 我的宠物列表适配器
 * 作者：jiaopeirong on 2018/5/27 08:41
 * 邮箱：chinajpr@163.com
 */
public class MyPetListAdapter extends BaseQuickAdapter<PetList.DataBean, BaseViewHolder> {
    private Map<String, String> animalHashMap;
    private Map<String, Map<String, String>> variety;

    public MyPetListAdapter() {
        super(R.layout.adapter_my_petlist);
    }

    public void setType(Map<String, String> animalHashMap, Map<String, Map<String, String>> variety) {
        this.animalHashMap = animalHashMap;
        this.variety = variety;
    }

    @Override
    protected void convert(BaseViewHolder helper, final PetList.DataBean item) {
        ImageView iv = helper.getView(R.id.iv);
        GlideUtils.displayRound(mContext, BaseApi.baseUrl + item.getHeadImg(), iv);
        helper.setText(R.id.petNick, item.getPetNick());
        String key = "";
        String value = "";
        //查询种类和品种
        Iterator<Map.Entry<String, String>> iterator = animalHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            if (next.getKey().equals(item.getAnimal())) {
                key = next.getValue();
                Map<String, String> map = variety.get(next.getKey());
                Iterator<Map.Entry<String, String>> iterator1 = map.entrySet().iterator();
                while (iterator1.hasNext()) {
                    Map.Entry<String, String> next1 = iterator1.next();
                    value = map.get(next1.getKey());
                    if (next1.getKey().equals(item.getVariety()))
                        helper.setText(R.id.type, "种类：" + key + "  " + "品种：" + value);
                    item.setAnimalName(key);
                    item.setVarietyName(value);
                }
            }
        }

        helper.getView(R.id.ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, PetDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(TagConstants.petDetail, item);
                bundle.putInt(TagConstants.petDetail1, 1);
                i.putExtras(bundle);
                mContext.startActivity(i);
            }
        });
    }
}
