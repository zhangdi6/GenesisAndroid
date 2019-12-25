package com.iruiyou.pet.utils;

import java.util.Random;

public class DataUtil {

    /**
     * 生成随机字符串
     * @param length
     * @return
     */
    public  String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
