package com.iruiyou.pet.activity.registered;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.SpacesItemDecoration;
import com.iruiyou.pet.base.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述:注册最后界面
 * 创建日期:2018/8/27 on 16:21
 * 作者:sgf
 */
public class RegisterLastActivity extends BaseActivity {
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.etRegisterName)
    TextInputEditText etRegisterName;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.tvRegisterCompleteLast)
    TextView tvRegisterCompleteLast;
    @BindView(R.id.llHead)
    LinearLayout llHead;
    @BindView(R.id.imHead)
    ImageView imHead;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<Integer> mDatas;
    private GalleryAdapter mAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_registered_last;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        titleNameText.setText(getResources().getString(R.string.register22));
        initDatas();
        initData();
    }

    private void initData() {

        etRegisterName.addTextChangedListener(codeWatcher);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //第一个参数横向间距，第二个参数纵向间距，根据需求具体来看
        int spanCount = 3; // 3 columns
        int spacing = 10; // 50px
        boolean includeEdge = false;
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recyclerView.addItemDecoration(new SpacesItemDecoration(5));
        //设置适配器
        mAdapter = new GalleryAdapter(this, mDatas);
        recyclerView.setAdapter(mAdapter);
    }

    private void initDatas()
    {
        mDatas = new ArrayList<>(Arrays.asList(R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher));
    }

    public class GalleryAdapter extends
            RecyclerView.Adapter<GalleryAdapter.ViewHolder>
    {
        private LayoutInflater mInflater;
        private List<Integer> mDatas;

        public GalleryAdapter(Context context, List<Integer> datats)
        {
            mInflater = LayoutInflater.from(context);
            mDatas = datats;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            public ViewHolder(View arg0)
            {
                super(arg0);
            }

            ImageView imHeadItem;
            TextView tvNameItem;
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        /**
         * 创建ViewHolder
         */

        @Override
        public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i)
        {
            View view = mInflater.inflate(R.layout.item_recycler_register,
                    viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);

            viewHolder.imHeadItem = view
                    .findViewById(R.id.imHeadItem);
            viewHolder.tvNameItem = view
                    .findViewById(R.id.tvNameItem);
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder( final ViewHolder viewHolder, final int i)
        {
            viewHolder.imHeadItem.setImageResource(mDatas.get(i));
        }

    }
    /**
     * 手机号码监听
     */
    private TextWatcher codeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() > 0 ) {
                setLoginBackground(true, R.drawable.bt_psw_details_shape2);
            } else {
                setLoginBackground(false, R.drawable.bt_psw_details_shape);
            }
        }
    };

    /**
     * 设置登录按钮背景颜色
     * @param type 是否可点击
     * @param background  背景颜色
     */
    private void setLoginBackground(boolean type, int background) {
        tvRegisterCompleteLast.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        tvRegisterCompleteLast.setBackgroundDrawable(drawable);
    }

    @OnClick({R.id.ll_title_left_view, R.id.tvRegisterCompleteLast})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                finish();
                break;
            case R.id.tvRegisterCompleteLast:
                //网络请求
                break;
        }
    }

}
