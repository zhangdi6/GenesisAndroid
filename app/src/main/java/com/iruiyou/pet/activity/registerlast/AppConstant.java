package com.iruiyou.pet.activity.registerlast;

import static com.iruiyou.http.retrofit_rx.Api.BaseApi.baseUrlNoApi;

/**
 * @author： sgf
 * @date： 2018/9/28
 * @describe：
 */
public class AppConstant {
    /**
     * 注册页面职业图片地址
     */

    //选中
    public static String OCCUPATION_URL_TRUE = baseUrlNoApi + "/img/genesis/profids/profid_";//1.png

    //未选中
    public static String OCCUPATION_URL_FALSE = baseUrlNoApi + "/img/genesis/profids/profid_";//_sel.png

}
