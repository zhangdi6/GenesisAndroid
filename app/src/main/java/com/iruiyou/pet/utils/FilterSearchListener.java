package com.iruiyou.pet.utils;

import com.iruiyou.pet.bean.SearchBean;

import java.util.List;

/**
 * 接口类，抽象方法用来获取过滤后的数据
 * @author shenggaofei
 *
 */
public interface FilterSearchListener {
    void getFilterData(List<SearchBean> list);// 获取过滤后的数据
}