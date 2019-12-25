package com.iruiyou.pet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.DoumiOptionBean;

import java.util.List;

public class PositionSpinnerAdapter extends ArrayAdapter<DoumiOptionBean.DataValue> {

    private int resourceId;
    private LayoutInflater inflater ;
    public PositionSpinnerAdapter(@NonNull Context context, List<DoumiOptionBean.DataValue> dataSource) {
        super(context, R.layout.spinner_checked_text,dataSource);
        resourceId = R.layout.item_spinner;
        inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(resourceId,null);
        }
        DoumiOptionBean.DataValue dataValue = getItem(position);
        ((TextView)convertView.findViewById(R.id.text_value)).setText(dataValue.getText());
        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DoumiOptionBean.DataValue dataValue = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_checked_text,null);
        }
        ((TextView)convertView).setText(dataValue.getText());
        return convertView;
    }
}
