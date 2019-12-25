package com.iruiyou.pet.fragment;

import android.os.Bundle;

public class SubconversationlistFragment extends MCConversationListFragment {

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(outState!=null)//避免崩溃的问题
        {
            try
            {
                super.onSaveInstanceState(outState);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
