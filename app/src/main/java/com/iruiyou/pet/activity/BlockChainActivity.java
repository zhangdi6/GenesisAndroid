package com.iruiyou.pet.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CommonBean;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.LangChildBean;
import com.iruiyou.pet.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述:区块链信息
 * 创建日期:2018/8/14 on 17:10
 * 作者:JiaoPeiRong
 */
public class BlockChainActivity extends BaseActivity {
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.edit)
    ImageView edit;
    @BindView(R.id.ll_title_left_view)
    LinearLayout llTitleLeftView;
    @BindView(R.id.titleview)
    RelativeLayout titleview;
    @BindView(R.id.blockIdenTv)
    TextView blockIdenTv;
    @BindView(R.id.blockIdenLl)
    LinearLayout blockIdenLl;
    @BindView(R.id.blockTime)
    TextView blockTime;
    @BindView(R.id.blockTimeLl)
    LinearLayout blockTimeLl;
    @BindView(R.id.blockExpTv)
    TextView blockExpTv;
    @BindView(R.id.blockExpLl)
    LinearLayout blockExpLl;
    @BindView(R.id.blockTimeLeft)
    TextView blockTimeLeft;
    @BindView(R.id.blockExpLeft)
    TextView blockExpLeft;
    @BindView(R.id.saveLl)
    LinearLayout saveLl;
    private String blockIdeStr;
    private int dialogWhich;
    private int dbKey;

    @Override
    public int getLayout() {
        return R.layout.actiivty_blockchain;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.blockChainInfo));
    }

    @OnClick({R.id.blockIdenLl, R.id.blockTimeLl, R.id.blockExpLl, R.id.saveLl})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this, IdentityInfoActivity.class);
        intent.putExtra(Constant.IDENTITY, Constant.BLOCK);
        switch (view.getId()) {
            case R.id.blockIdenLl:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getResources().getString(R.string.choiceBlockChainIdentity));
                ConfigBean configBean = App.getConfigBean();
                if(configBean!=null)
                {
                    List<LangChildBean.DbSelectInputStandardsBean.BlockchainIdentityLocationsBean> blockchainIdentityLocations =
                            configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getBlockchainIdentityLocations();
                    String strings[] = new String[blockchainIdentityLocations.size()];
                    for (int i = 0; i < blockchainIdentityLocations.size(); i++) {
                        strings[i] = blockchainIdentityLocations.get(i).getLangValue();
                    }
                    blockIdeStr = strings[0];
                    builder.setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            blockIdeStr = strings[which];
                            dialogWhich = which;
                        }
                    });

                }

                //设置反面按钮
                builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        blockIdenTv.setText(blockIdeStr);
                        dbKey = dialogWhich;
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                break;
            case R.id.blockTimeLl:
                intent.putExtra(Constant.TITLE, blockTimeLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, blockTime.getText().toString());
                startActivityForResult(intent, Constant.BLOCKDURATION);
                break;
            case R.id.blockExpLl:
                intent.putExtra(Constant.TITLE, blockExpLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, blockExpTv.getText().toString());
                startActivityForResult(intent, Constant.BLOCKEXPE);
                break;
            case R.id.saveLl:
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
                        if (commonBean.getStatusCode() == Constant.SUCCESS) {
                            finish();
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).addBlockChain(String.valueOf(dbKey), blockTime.getText().toString(), blockExpTv.getText().toString(), "区块链");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String stringExtra = data.getStringExtra(Constant.BLOCK);
        switch (requestCode) {
            case Constant.BLOCKDURATION:
                blockTime.setText(stringExtra);
                break;
            case Constant.BLOCKEXPE:
                blockExpTv.setText(stringExtra);
                break;
        }
    }

}
