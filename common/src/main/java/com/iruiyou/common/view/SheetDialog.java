package com.iruiyou.common.view;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.R;

import java.util.List;
import java.util.Objects;

/**
 * 类描述：
 * 作者：JiaoPeiRong on 2017/4/28 10:26
 */
public class SheetDialog {
    private Display display;
    private Context context;
    private RecyclerView recyclerView;
    private List<String> mList;
    private Dialog dialog;
    private OnItemListener onItemListener;
    //当前选中的item
    private int positon;

    public SheetDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = Objects.requireNonNull(windowManager).getDefaultDisplay();
    }

    public SheetDialog build() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sheet, null, false);
        recyclerView = view.findViewById(R.id.recycView);
        LinearLayout layout = view.findViewById(R.id.Ll);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        layout.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.6), ViewGroup.LayoutParams.WRAP_CONTENT));
//        layout.setLayoutParams(new FrameLayout.LayoutParams((int) (display
//                .getWidth() * 0.6), (int) (display.getHeight() * 0.2)));
        return this;
    }

    public SheetDialog setList(List<String> list, int position) {
        this.mList = list;
        this.positon = position;
        initAdapter();
        return this;
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        SheetDialogAdapter adapter = new SheetDialogAdapter(R.layout.adapter_sheet_dialog, mList);
        recyclerView.setAdapter(adapter);

    }

    public void show() {
        dialog.show();
    }

    public SheetDialog setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
        return this;
    }

    class SheetDialogAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public SheetDialogAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, String item) {
            final CheckBox cb = helper.getView(R.id.cb);
            helper.setText(R.id.name , item);
            if (helper.getPosition() == positon) {
                cb.setSelected(true);
            } else {
                cb.setSelected(false);
            }
            final LinearLayout linearLayout = helper.getView(R.id.item);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemListener.selectItem(helper.getPosition());
                    //checkbox
                    positon = helper.getPosition();
                    notifyDataSetChanged();
                    //关闭
                    linearLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 500);
                }
            });
        }
    }

    public interface OnItemListener {
        void selectItem(int position);
    }
}
