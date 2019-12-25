package com.iruiyou.pet.activity.registerlast;

import android.app.Activity;

import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.LangChildBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author： sgf
 * @date： 2018/9/28
 * @describe：联系人搜索控制器
 */
public class SearchPresenter implements IPresenter {
    public Activity activity;

    private boolean isSquare;

    private boolean isHidden = false;

    @Override
    public void IPresenter(Activity activity) {
        this.activity = activity;
    }

    public void setSquare(boolean square) {
        isSquare = square;
    }

    public void setHidden(boolean Hidden) {
        isHidden = Hidden;
    }

    public boolean isSquare() {
        return isSquare;
    }

    /**
     * 请求职业信息
     *
     * @param resultDataLinsenter 返回监听
     */
    public void getOccupationsList(ResultDataLinsenter resultDataLinsenter) {
        //获取缓存的数据-职业
        ConfigBean configBean = App.getConfigBean();
        if (configBean != null) {
            List<LangChildBean.DbSelectInputStandardsBean.ProfessionalIdentitiesBean> professionalIdentities =
                    configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getProfessionalIdentities();
            ArrayList<OccupationBeen> occupationBeens = new ArrayList<>();

            for (int i = 0; i < professionalIdentities.size(); i++) {
                if (i == 0) {//把后台默认的数据替换成需要的
                    if (isHidden) {
                        continue;
                    }
                    String str = "";
                    if (isSquare) {
                        str = activity.getString(R.string.square);
                    } else {
                        str = activity.getString(R.string.all);
                    }
                    occupationBeens.add(new OccupationBeen(str, professionalIdentities.get(i).getDbKey()));
                } else {
                    occupationBeens.add(new OccupationBeen(professionalIdentities.get(i).getLangValue(), professionalIdentities.get(i).getDbKey()));
                }
            }


//        occupationBeens.add(new OccupationBeen("创业者", 1));
//        occupationBeens.add(new OccupationBeen("投资人", 2));
//        occupationBeens.add(new OccupationBeen("程序工程师", 3));
//        occupationBeens.add(new OccupationBeen("设计师", 4));
//        occupationBeens.add(new OccupationBeen("网红", 5));
//        occupationBeens.add(new OccupationBeen("出版", 6));
//        occupationBeens.add(new OccupationBeen("法律", 7));
//        occupationBeens.add(new OccupationBeen("顾问", 8));
//        occupationBeens.add(new OccupationBeen("管理", 9));
//        occupationBeens.add(new OccupationBeen("金融", 10));
//        occupationBeens.add(new OccupationBeen("媒体与公关", 11));
//        occupationBeens.add(new OccupationBeen("人力资源", 12));
//        occupationBeens.add(new OccupationBeen("销售与市场营销", 13));
//        occupationBeens.add(new OccupationBeen("学生", 14));
//        occupationBeens.add(new OccupationBeen("其他", 999));

            for (int i = 0; i < occupationBeens.size(); i++) {
                if (i == 0&&(!isHidden)) {//添加自己需要的图标
                    occupationBeens.get(i).setPicUrlSelectfalse(R.drawable.all0);
                    occupationBeens.get(i).setPicUrlSelectTrue(R.drawable.all1);
                } else {
                    occupationBeens.get(i).setPicUrlSelectfalse(AppConstant.OCCUPATION_URL_FALSE + occupationBeens.get(i).getDbKey() + ".png");
                    occupationBeens.get(i).setPicUrlSelectTrue(AppConstant.OCCUPATION_URL_FALSE + occupationBeens.get(i).getDbKey() + "_sel.png");
                }
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
