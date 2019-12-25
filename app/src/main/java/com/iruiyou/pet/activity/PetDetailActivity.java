package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CodeBean;
import com.iruiyou.pet.bean.PetList;
import com.iruiyou.pet.utils.PetTypeSearch;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 宠物详情
 * 作者：jiaopeirong on 2018/5/27 00:12
 * 邮箱：chinajpr@163.com
 */
public class PetDetailActivity extends BaseActivity {
    //    @BindView(R.id.edit)
//    ImageView edit;
//    @BindView(R.id.titleview)
//    RelativeLayout titleview;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.upImg)
    RelativeLayout upImg;
    @BindView(R.id.petNameEt)
    TextView petNameEt;
    @BindView(R.id.redioMan)
    RadioButton redioMan;
    @BindView(R.id.radioWoman)
    RadioButton radioWoman;
    @BindView(R.id.group)
    RadioGroup group;
    @BindView(R.id.petTypeTv)
    TextView petTypeTv;
    @BindView(R.id.petTypeLl)
    LinearLayout petTypeLl;
    @BindView(R.id.petNameTv)
    TextView petNameTv;
    @BindView(R.id.petNameLl)
    LinearLayout petNameLl;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.del)
    TextView del;
    @BindView(R.id.title)
    TextView title;
    private String id;
    private PetList.DataBean bean;

    @Override
    public int getLayout() {
        return R.layout.activity_pet_detail;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("宠物");
        Bundle bundle = getIntent().getExtras();
        bean = (PetList.DataBean) Objects.requireNonNull(bundle).getSerializable(TagConstants.petDetail);
        int anInt = bundle.getInt(TagConstants.petDetail1);
        if (anInt == 1) {
            cardView.setVisibility(View.VISIBLE);
        } else {
            cardView.setVisibility(View.GONE);
        }
        id = bean.get_id();
        List<String> typeAndName = PetTypeSearch.getTypeAndName(this, bean.getAnimal(), bean.getVariety(), new PetTypeSearch.OnDataCallBack() {

            @Override
            public void getData(String animal, String name) {
                petTypeTv.setText(animal);
                petNameTv.setText(name);
            }
        });
        petNameEt.setText(bean.getPetNick());
        GlideUtils.displayRound(this, BaseApi.baseUrl + bean.getHeadImg(), head);
        int sex = bean.getSex();
        if (sex == 1) {
            redioMan.setChecked(true);
            radioWoman.setChecked(false);
        } else if (sex == 0) {
            redioMan.setChecked(false);
            radioWoman.setChecked(true);

        }

    }

    @OnClick({R.id.ll_title_left_view, R.id.cardView, R.id.head})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ll_title_left_view:
                finish();
                break;
            case R.id.cardView:
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
                        T.showShort(codeBean.getMessage());
                        cardView.setClickable(false);
                        EventBusUtils.getInstance().postEvent(codeBean);
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).deletePet(id);

                break;
            case R.id.head:
                Intent i = new Intent(this , ImgActivity.class);
                i.putExtra(TagConstants.IMG , BaseApi.baseUrl + bean.getHeadImg());
                startActivity(i);
                break;
        }
    }

    public static List<String> getTypeAndName(RxAppCompatActivity rxAppCompatActivity, final String animalKey, String nameKey) {
        final List<String> list = new ArrayList<>();
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                T.showShort(resulte);
//                Map<String, String> animalHashMap = new HashMap<>();
//                Map<String, Map<String, String>> variety = new HashMap<>();
////                PetTypeBean petTypeBean = GsonUtils.parseJson(resulte, PetTypeBean.class);
//                try {
//                    JSONObject jsonObject = new JSONObject(resulte);
//                    JSONObject data = jsonObject.getJSONObject("data");
//                    //取出animal里面的值
//                    JSONObject jsonAnimal = data.getJSONObject("animal");
//                    Iterator<String> keys = jsonAnimal.keys();
//                    while (keys.hasNext()) {
//                        String key = keys.next();
//                        String value = jsonAnimal.getString(key);
//                        animalHashMap.put(key, value);
//                        //获取animal所对应的品种
//                        JSONObject job = data.getJSONObject(key);
//                        Iterator<String> k = job.keys();
//                        Map<String, String> map = new HashMap<>();
//                        while (k.hasNext()) {
//                            String next = k.next();
//                            String v = (String) job.get(next);
//                            map.put(next, v);
//                        }
//                        variety.put(key, map);
//                    }
//                    //取出其他几个的值
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                //获取品种的数据
//
//                List<String> values = new ArrayList<>();
//                final List<String> keys = new ArrayList<>();
//                Iterator<Map.Entry<String, String>> iterator = animalHashMap.entrySet().iterator();
//                while (iterator.hasNext()) {
//                    Map.Entry<String, String> next = iterator.next();
//                    values.add(next.getValue());
//                    keys.add(next.getKey());
//                }
//
//                //种类的hashmap animalHashMap 和 名称的hashmap variety
//                String key = "";
//                String value = "";
//                //查询种类和品种
//                Iterator<Map.Entry<String, String>> iterator0 = animalHashMap.entrySet().iterator();
//                while (iterator0.hasNext()) {
//                    Map.Entry<String, String> next = iterator0.next();
//                    if (next.getKey().equals(animalKey)) {
//                        key = next.getValue();
//                        Map<String, String> map = variety.get(next.getKey());
//                        Iterator<Map.Entry<String, String>> iterator1 = map.entrySet().iterator();
//                        while (iterator1.hasNext()) {
//                            Map.Entry<String, String> next1 = iterator1.next();
//                            value = map.get(next1.getKey());
//                            list.add(key);
//                            list.add(value);
//                        }
//                    }
//                }
            }

            @Override
            public void onError(ApiException e) {

            }
        }, rxAppCompatActivity).petType();
        return list;
    }

}
