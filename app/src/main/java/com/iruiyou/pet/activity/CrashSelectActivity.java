package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.view.View;

import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现选择界面
 */
public class CrashSelectActivity extends BaseActivity {

    private String pbsAmount;
    private String hatchAmount;
    private String usdtPbs;
    private String rebateCrystal;
    private String usdCNY;
    @Override
    public int getLayout() {
        return R.layout.activity_crash_select;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.withdrawal_of_assets));
        pbsAmount = getIntent().getStringExtra("pbsAmount");
        hatchAmount = getIntent().getStringExtra("fixedAssetsAmount");
        usdtPbs = getIntent().getStringExtra("usdtPbs");
        rebateCrystal = getIntent().getStringExtra("rebateCrystal");
        usdCNY = getIntent().getStringExtra("UsdCNY");
    }

    @OnClick(value = {R.id.ll_title_left_view, R.id.relay_zhifubao, R.id.relay_biquan, R.id.relay_pbs})
    public void viewOnClick(View view)
    {
        int id=view.getId();
        switch (id)
        {
            case R.id.ll_title_left_view:
                finish();
                break;
            case R.id.relay_zhifubao:
                StartActivityManager.startWithdrawalOfAssetsActivity(CrashSelectActivity.this,pbsAmount,
                        hatchAmount,usdtPbs,rebateCrystal,usdCNY, BqexTransactionActivity.ACCOUNT_TYPE_ALIPAY);
                break;
            case R.id.relay_pbs:
                StartActivityManager.startWithdrawalOfAssetsActivity(CrashSelectActivity.this,pbsAmount,
                        hatchAmount,usdtPbs,rebateCrystal,usdCNY, BqexTransactionActivity.ACCOUNT_TYPE_BIQUAN);
                break;
            case R.id.relay_biquan:
                StartActivityManager.startWithdrawalOfAssetsActivity(CrashSelectActivity.this,pbsAmount,
                        hatchAmount,usdtPbs,rebateCrystal,usdCNY, BqexTransactionActivity.ACCOUNT_TYPE_BANK);
                break;
        }
    }

}
