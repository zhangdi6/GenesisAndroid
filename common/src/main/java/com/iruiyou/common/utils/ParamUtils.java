package com.iruiyou.common.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 类描述:
 * 作者：Created by JiaoPeiRong on 2017/5/7 13:14
 * 邮箱：chinajpr@163.com
 */

public class ParamUtils {
    /**
     * 统一参数封装
     * @param paramMap 自己的参数(没有传null)
     * @param isAddCommon 是否使用公共参数
     * @return
     */
    public static String enCode(Map<String, Object> paramMap , boolean isAddCommon) {
        JSONObject jsonObject = new JSONObject();
        //封装公共参数
        if (isAddCommon){
            try {
                jsonObject.put("userid", SharePreferenceUtils.getBaseSharePreference().readUserId());
                jsonObject.put("sessionid", SharePreferenceUtils.getBaseSharePreference().readSessionId());
                jsonObject.put("client", SystemUtil.getIMEI() + ";" +
                        SystemUtil.getSystemVersion() + ";" + SystemUtil.getAppVersion());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //封装map参数
        if (paramMap != null) {
            Set set = paramMap.entrySet();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) i.next();
                try {
                    jsonObject.put(entry.getKey(), paramMap.get(entry.getKey()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        String s = EncodeUtils.base64Encode2String(jsonObject.toString().getBytes());
        return s;
    }

}
