package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.common.view.RecyclerViewLine;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.MyPetListAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.PetList;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的宠物列表
 * 作者：jiaopeirong on 2018/5/27 00:57
 * 邮箱：chinajpr@163.com
 */
public class MyPetListActivity extends BaseActivity {
    @BindView(R.id.ll_title_left_view)
    LinearLayout llTitleLeftView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title)
    TextView title;
    private ACache aCache;
    private PetList petList;
    private MyPetListAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_pet_list;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        EventBusUtils.getInstance().register(this);
        title.setText("宠物列表");
        aCache = ACache.get(this);
        initView();
        getType();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getType();
    }

    /**
     * 获取宠物类型
     */
    public void getType() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
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
                adapter.setNewData(petList.getData());
                adapter.setType(animalHashMap, variety);
            }

            @Override
            public void onError(ApiException e) {

            }
        }, this).petType();
    }

    //接收事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(PetRefreshEvent petRefreshEvent) {
        petList = (PetList) aCache.getAsObject(TagConstants.petList);
        adapter.setNewData(petList.getData());
    }

    /**
     * 初始化
     */
    private void initView() {
        petList = (PetList) aCache.getAsObject(TagConstants.petList);
        adapter = new MyPetListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecyclerViewLine(this, RecyclerViewLine.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.ll_title_left_view, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                finish();
                break;
            case R.id.add:
                startActivity(AddPetActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtils.getInstance().unregister(this);
    }

}
