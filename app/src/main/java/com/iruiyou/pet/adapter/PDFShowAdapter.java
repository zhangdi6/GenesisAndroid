package com.iruiyou.pet.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.pet.R;

import java.util.HashMap;

/**
 * 我的页面 适配器
 * 作者：jiaopeirong on 2018/5/9 22:18
 * 邮箱：chinajpr@163.com
 */
public class PDFShowAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private HashMap<Integer, Bitmap> pdfPages;

    public PDFShowAdapter(HashMap<Integer, Bitmap> pdfPages) {
        super(R.layout.item_pdf_show);
        this.pdfPages=pdfPages;
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        ImageView imageView = helper.getView(R.id.image_content);
        imageView.setImageBitmap(pdfPages.get(item));
    }
}
