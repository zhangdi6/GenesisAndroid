package com.iruiyou.pet.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.ReleaseActivity;
import com.iruiyou.pet.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MCClassFragment2 extends BaseFragment {

    private static MCClassFragment2 MCClassFragment = null;

    private TextView mTab1;
    private TextView mTab2;
    private TextView mTab3;
    private FrameLayout mFragLayout;

    private FragmentManager manager;
    private ImageView mImg;

    public MCClassFragment2() {
        // Required empty public constructor
    }

    public static MCClassFragment2 getInstance() {
        return new MCClassFragment2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mcclass_fragment2, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        mTab1 = view.findViewById(R.id.tab1);
        mTab2 = view.findViewById(R.id.tab2);
        mTab3 = view.findViewById(R.id.tab3);
        mFragLayout = view.findViewById(R.id.frag);
        mImg = view.findViewById(R.id.add);
        manager = getChildFragmentManager();
        manager.beginTransaction().add(R.id.frag,new HotListFragment(),null).commit();
    }

    private void initData() {

        //切换fragment的方法，现在需求不明确，所以一直设置为1，随后根据需求设置1，2，3

        mTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(1);
            }
        });
       /* mTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(2);
            }
        });
        mTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(3);
            }
        });*/
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if (SharePreferenceUtils.getBaseSharePreference().readVipLevel() < 1) {
                    T.showShort("请开通会员后再发布微博！");
                } else {*/
                    Intent intent = new Intent(getActivity(), ReleaseActivity.class);
                    startActivity(intent);
              //  }
            }
        });

    }

    //切换fragment的方法，现在需求不明确，所以一直设置为1，随后根据需求设置1，2，3
    private void changeFragment(int i) {
        switch (i) {
            case 1:
                mTab1.setTextColor(Color.parseColor("#333333"));
                mTab2.setTextColor(Color.parseColor("#999999"));
                mTab3.setTextColor(Color.parseColor("#999999"));
                mTab1.setCompoundDrawablesWithIntrinsicBounds(null,null,null
                ,getResources().getDrawable(R.drawable.shape_line));
                mTab2.setCompoundDrawablesWithIntrinsicBounds(null,null,null
                ,null);
                mTab3.setCompoundDrawablesWithIntrinsicBounds(null,null,null
                ,null);
                manager.beginTransaction().replace(R.id.frag,new HotListFragment()).commit();
                break;
            case 2:
                mTab1.setTextColor(Color.parseColor("#999999"));
                mTab2.setTextColor(Color.parseColor("#333333"));
                mTab3.setTextColor(Color.parseColor("#999999"));
                mTab2.setCompoundDrawablesWithIntrinsicBounds(null,null,null
                        ,getResources().getDrawable(R.drawable.shape_line));
                mTab1.setCompoundDrawablesWithIntrinsicBounds(null,null,null
                        ,null);
                mTab3.setCompoundDrawablesWithIntrinsicBounds(null,null,null
                        ,null);
                manager.beginTransaction().replace(R.id.frag,new LoveListFragment()).commit();
                break;
            case 3:
                mTab1.setTextColor(Color.parseColor("#999999"));
                mTab2.setTextColor(Color.parseColor("#999999"));
                mTab3.setTextColor(Color.parseColor("#333333"));
                mTab3.setCompoundDrawablesWithIntrinsicBounds(null,null,null
                        ,getResources().getDrawable(R.drawable.shape_line));
                mTab2.setCompoundDrawablesWithIntrinsicBounds(null,null,null
                        ,null);
                mTab1.setCompoundDrawablesWithIntrinsicBounds(null,null,null
                        ,null);
                manager.beginTransaction().replace(R.id.frag,new TopicListFragment()).commit();
                break;

            default:
                break;
        }
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        initData();
    }
}
