package com.iruiyou.pet.utils;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 作者：jiaopeirong on 2018/5/27 11:45
 * 邮箱：chinajpr@163.com
 */
public class PetTypeSearch {

    /**
     * 返回動物類型
     * @param rxAppCompatActivity
     * @param animalKey
     * @param nameKey
     * @param onDataCallBack
     * @return 这里返回值是个反例！！！
     */
    public static List<String> getTypeAndName(RxAppCompatActivity rxAppCompatActivity, final String animalKey, final String nameKey, final OnDataCallBack onDataCallBack) {
        final List<String> list = new ArrayList<>();
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
//                T.showShort(resulte);
                Map<String, String> animalHashMap = new HashMap<>();
                Map<String, Map<String, String>> variety = new HashMap<>();
//                PetTypeBean petTypeBean = GsonUtils.parseJson(resulte, PetTypeBean.class);
                try {
                    JSONObject jsonObject = new JSONObject(resulte);
                    JSONObject data = jsonObject.getJSONObject("data");
                    //取出animal里面的值
                    JSONObject jsonAnimal = data.getJSONObject("animal");
                    Iterator<String> keys = jsonAnimal.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        String value = jsonAnimal.getString(key);
                        animalHashMap.put(key, value);
                        //获取animal所对应的品种
                        JSONObject job = data.getJSONObject(key);
                        Iterator<String> k = job.keys();
                        Map<String, String> map = new HashMap<>();
                        while (k.hasNext()) {
                            String next = k.next();
                            String v = (String) job.get(next);
                            map.put(next, v);
                        }
                        variety.put(key, map);
                    }
                    //取出其他几个的值

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //获取品种的数据

//                List<String> values = new ArrayList<>();
//                final List<String> keys = new ArrayList<>();
//                Iterator<Map.Entry<String, String>> iterator = animalHashMap.entrySet().iterator();
//                while (iterator.hasNext()) {
//                    Map.Entry<String, String> next = iterator.next();
//                    values.add(next.getValue());
//                    keys.add(next.getKey());
//                }

                //种类的hashmap animalHashMap 和 名称的hashmap variety
                String key = "";
                String value = "";
                //查询种类和品种
                Iterator<Map.Entry<String, String>> iterator0 = animalHashMap.entrySet().iterator();
                while (iterator0.hasNext()) {
                    Map.Entry<String, String> next = iterator0.next();
                    if (next.getKey().equals(animalKey)) {
                        key = next.getValue();
                        Map<String, String> map = variety.get(next.getKey());
                        Iterator<Map.Entry<String, String>> iterator1 = map.entrySet().iterator();
                        while (iterator1.hasNext()) {
                            Map.Entry<String, String> next1 = iterator1.next();
                            value = map.get(next1.getKey());
                            if (next1.getKey().equals(nameKey)){
                                list.add(key);
                                list.add(value);
                            }
                        }
                    }
                }
                onDataCallBack.getData(list.get(0),list.get(1));

            }

            @Override
            public void onError(ApiException e) {

            }
        }, rxAppCompatActivity).petType();
        return list;
    }

    public interface OnDataCallBack{
        void getData(String animal , String name);
    }
}