package com.iruiyou.pet.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新版消息界面
 */
public class NewMessageFragment extends BaseFragment {

    @BindView(R.id.text_chat)
    TextView text_chat;

    @BindView(R.id.mine_interaction)
    TextView mine_interaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_new, null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {

    }


    @OnClick(value = {R.id.text_chat, R.id.mine_interaction})
    public void viewOnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.text_chat:
                text_chat.setAlpha(1);
                mine_interaction.setAlpha(0.5f);
                break;
            case R.id.mine_interaction:
                text_chat.setAlpha(0.5f);
                mine_interaction.setAlpha(1);
                break;
        }
    }

}
