package com.iruiyou.common.http;


import com.iruiyou.common.utils.EncodeUtils;

import java.util.HashMap;

/**
 * 类描述:base64加密的HashMap
 * 作者：Created by JiaoPeiRong on 2017/4/27 22:41
 * 邮箱：chinajpr@163.com
 */

public class Base64Map extends HashMap<Object , Object> {
    public Base64Map(){

    }

    @Override
    public Object put(Object key, Object value) {
        byte[] bytes = EncodeUtils.base64Encode(value.toString());
        return super.put(key, bytes);
    }
}
