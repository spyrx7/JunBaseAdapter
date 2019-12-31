package com.lemonjun.library;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * 单类型通用的Adapter
 * Created by junjianliu
 * time: 2017/6/13.
 */

public abstract class BaseCommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    public static final int EMPTY_VIEW = 0x00000555;

    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected List<ViewHolder> viewHolders;

    private RecyclerView mRecyclerView;

    //empty
    private FrameLayout mEmptyLayout;

    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }
    /**
     * same as recyclerView.setAdapter(), and save the instance of recyclerView
     */
    public void bindToRecyclerView(RecyclerView recyclerView) {
        if (getRecyclerView() != null) {
            throw new RuntimeException("Don't bind twice");
        }
        setRecyclerView(recyclerView);
        getRecyclerView().setAdapter(this);
    }



    private void checkNotNull() {
        if (getRecyclerView() == null) {
            throw new RuntimeException("please bind recyclerView first!");
        }
    }

    public void add(T t) {
        this.mDatas.add(t);
    }

    public void add(T t,int i) {
        this.mDatas.add(i,t);
    }

    public void put(int i,T t) {
        this.mDatas.set(i,t);
    }

    public void set(int i,T t) {
        this.mDatas.set(i,t);
    }

    public void addDatas(List<T> t){
        this.mDatas = t;
        notifyDataSetChanged();
    }

    public void clear() {
        if(mDatas != null) {
            this.mDatas.clear();
            notifyDataSetChanged();
        }
    }

    public void add(List<T> t){
        int count  = t.size();
        for(int i = 0; i < count; i++){
            mDatas.add(t.get(i));
        }
    }

    public void del(int pos){
        if(mDatas != null){
            mDatas.remove(pos);
        }
    }

    public T get(int pos){
        return mDatas.get(pos);
    }

    public List<T> getData(){
        return mDatas;
    }

    public void remove(int pos) {
        this.mDatas.remove(pos);
    }

    public BaseCommonAdapter(Context mContext, int mLayoutId, List<T> mDatas) {
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(mContext);
        this.viewHolders = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if(getEmptyViewCount() == 1){
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        if(viewType == EMPTY_VIEW){
            viewHolder = ViewHolder.get(mContext,parent,mEmptyLayout);
        }else{
            viewHolder = ViewHolder.get(mContext, parent, mLayoutId);
            viewHolders.add(viewHolder);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updatePosition(position);
        if(getEmptyViewCount() == 0) {
            convert(holder, mDatas.get(position), position);
        }
    }

    public abstract void convert(ViewHolder holder, T t, int position) ;

    @Override
    public int getItemCount() {
        if(getEmptyViewCount() == 1){
            return 1;
        }
        return mDatas == null ? 0 : mDatas.size() ;
    }

    public int getEmptyViewCount(){
        if(mEmptyLayout == null){
            return 0;
        }
        if(mDatas.size() != 0){
            return 0;
        }
        return 1;
    }

    public void setEmptyLayout(int layoutResId, ViewGroup viewGroup){
        View view = LayoutInflater.from(mContext).inflate(layoutResId,viewGroup,false);
        setEmptyLayout(view);
    }

    public void setmEmptyLayout(int layoutResId){
        setEmptyLayout(layoutResId, null);
    }

    public void setEmptyLayout(View emptyView){
        if(mEmptyLayout == null){
            mEmptyLayout = new FrameLayout(mContext);
            final FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT
                    , FrameLayout.LayoutParams.MATCH_PARENT);
            final ViewGroup.LayoutParams lp = emptyView.getLayoutParams();
            if(lp != null){
                layoutParams.width = lp.width;
                layoutParams.height = lp.height;
            }

            mEmptyLayout.setLayoutParams(layoutParams);
        }
        mEmptyLayout.removeAllViews();
        mEmptyLayout.addView(emptyView);

    }


}
