package com.iruiyou.pet.activity.registerlast;

import android.app.Activity;

import com.iruiyou.pet.App;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.LangChildBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author： sgf
 * @date： 2018/9/28
 * @describe：注册页面控制器
 */
public class RegisterPresenter implements IPresenter {
    public Activity activity;

    @Override
    public void IPresenter(Activity activity) {
        this.activity = activity;
    }


    /**
     * 请求职业信息
     * @param resultDataLinsenter 返回监听
     */
    public void getOccupationsList(ResultDataLinsenter resultDataLinsenter) {
        //获取缓存的数据-职业
        ConfigBean configBean = App.getConfigBean();
        if(configBean!=null)
        {
            List<LangChildBean.DbSelectInputStandardsBean.ProfessionalIdentitiesBean> professionalIdentities = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getProfessionalIdentities();
            professionalIdentities.remove(0);
            ArrayList<OccupationBeen> occupationBeens = new ArrayList<>();

            for (int i = 0; i <professionalIdentities.size() ; i++) {
                occupationBeens.add(new OccupationBeen(professionalIdentities.get(i).getLangValue(), professionalIdentities.get(i).getDbKey()));
            }
            for (int i = 0; i <occupationBeens.size(); i++) {
                occupationBeens.get(i).setPicUrlSelectfalse(AppConstant.OCCUPATION_URL_FALSE+ occupationBeens.get(i).getDbKey()+".png");
                occupationBeens.get(i).setPicUrlSelectTrue(AppConstant.OCCUPATION_URL_FALSE+ occupationBeens.get(i).getDbKey()+"_sel.png");
            }
            resultDataLinsenter.success(occupationBeens);
        }

    }


    /**
     * {
     ║               "langValue": "创业者",
     ║               "dbKey": 1
     ║             },
     ║             {
     ║               "langValue": "投资人",
     ║               "dbKey": 2
     ║             },
     ║             {
     ║               "langValue": "程序工程师",
     ║               "dbKey": 3
     ║             },
     ║             {
     ║               "langValue": "设计师",
     ║               "dbKey": 4
     ║             },
     ║             {
     ║               "langValue": "网红",
     ║               "dbKey": 5
     ║             },
     ║             {
     ║               "langValue": "出版",
     ║               "dbKey": 6
     ║             },
     ║             {
     ║               "langValue": "法律",
     ║               "dbKey": 7
     ║             },
     ║             {
     ║               "langValue": "顾问",
     ║               "dbKey": 8
     ║             },
     ║             {
     ║               "langValue": "管理",
     ║               "dbKey": 9
     ║             },
     ║             {
     ║               "langValue": "金融",
     ║               "dbKey": 10
     ║             },
     ║             {
     ║               "langValue": "媒体与公关",
     ║               "dbKey": 11
     ║             },
     ║             {
     ║               "langValue": "人力资源",
     ║               "dbKey": 12
     ║             },
     ║             {
     ║               "langValue": "销售与市场营销",
     ║               "dbKey": 13
     ║             },
     ║             {
     ║               "langValue": "学生",
     ║               "dbKey": 14
     ║             },
     ║             {
     ║               "langValue": "其他",
     ║               "dbKey": 999
     ║             }
     */

}
