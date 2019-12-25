package com.iruiyou.pet.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.adapter.WithdrawalRecordsAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CrashRecordBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.iruiyou.pet.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现记录界面
 */
public class WithdrawalRecordsActivity extends BaseActivity {


    public static void startAction(Activity activity)
    {
        Intent intent=new Intent(activity, WithdrawalRecordsActivity.class);
        activity.startActivity(intent);
    }

    private WithdrawalRecordsAdapter withdrawalRecordsAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Override
    public int getLayout() {
        return R.layout.activity_withdrawal_records;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.withdrawal_records));
        withdrawalRecordsAdapter=new WithdrawalRecordsAdapter(WithdrawalRecordsActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(withdrawalRecordsAdapter);
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNotEmpty(resulte))
                {
                    CrashRecordBean crashRecordBean=  GsonUtil.GsonToBean(resulte, CrashRecordBean.class);
                    if (crashRecordBean.getStatusCode() == Constant.SUCCESS&&(crashRecordBean.getData()!=null))
                    {
                        withdrawalRecordsAdapter.setNewData(crashRecordBean.getData());
                    }
                }
//                Log.e("test","resulte is "+resulte);
            }

            @Override
            public void onError(ApiException e) {

            }
        }, WithdrawalRecordsActivity.this).getDrawalRecords();
    }

    @OnClick(value = {R.id.ll_title_left_view})
    public void onViewClicked(View view){
        switch (view.getId())
        {
            case R.id.ll_title_left_view:
                if (SoftKeyboardUtils.isSoftShowing(this)) {//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                finish();
                break;
        }
    }

}
