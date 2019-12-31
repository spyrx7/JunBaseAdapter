package com.lemonjun.junbaseadapter.adapter;

import android.content.Context;

import com.lemonjun.junbaseadapter.R;
import com.lemonjun.library.BaseCommonAdapter;
import com.lemonjun.library.ViewHolder;

import java.util.List;

public class DemoAdapter extends BaseCommonAdapter<String> {

    public DemoAdapter(Context mContext, int mLayoutId, List<String> mDatas) {
        super(mContext, mLayoutId, mDatas);
    }

    @Override
    public void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.name,s);
    }
}
