package com.lemonjun.library;

import android.content.Context;
import android.graphics.ColorMatrixColorFilter;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 通用的ViewHolder，提供基本的getView 、setText、setImager
 * <p>
 * Created by Administrator on 2017/9/12.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private View convertView;
    private Context context;
    private int position;

    public ViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        this.context = context;
        this.convertView = itemView;
        this.views = new SparseArray<>();
    }

    public static ViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder holder = new ViewHolder(context, itemView, parent);
        return holder;
    }

    public static ViewHolder get(Context context, ViewGroup parent, View layoutId) {
        ViewHolder holder = new ViewHolder(context, layoutId, parent);
        return holder;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public void updatePosition(int position) {
        this.position = position;
    }

    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        if(!TextUtils.isEmpty(text)){
            text.trim();
        }
        tv.setText(text);
        return this;
    }

    public ViewHolder setTextSize(int viewId, float size) {
        TextView tv = getView(viewId);
        tv.setTextSize(size);
        return this;
    }

    public ViewHolder setText(int viewId, String text, String about) {
        TextView tv = getView(viewId);
        if(TextUtils.isEmpty(text)){
            tv.setText(about);
        }else {
            tv.setText(text);
        }
        return this;
    }

    public ViewHolder setText(int viewId, String text, boolean isHint) {
        TextView tv = getView(viewId);
        if(TextUtils.isEmpty(text)) {
            if (isHint) {
                tv.setVisibility(View.GONE);
            } else {
                tv.setVisibility(View.VISIBLE);
            }
        }
        return this;
    }

    public ViewHolder setHint(int viewId, String text) {
        EditText tv = getView(viewId);
        tv.setHint(text);
        return this;
    }

    public ViewHolder setTextImgTop(int viewId , int res){
        TextView tv = getView(viewId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(null,context.getResources().getDrawable(res),null,null);
//          tv.setCompoundDrawablesRelativeWithIntrinsicBounds(0,res,0,0);

        }
        return this;
    }

    public ViewHolder setTextHint(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setHint(text);
        return this;
    }

    public ViewHolder setHTMLText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(Html.fromHtml(text));
        return this;
    }

    public ViewHolder setText(int viewId, String text, int color) {
        TextView tv = getView(viewId);
        tv.setText(text);
        tv.setTextColor(color);
        return this;
    }

    public ViewHolder setTextColor(int viewId, int color) {
        TextView tv = getView(viewId);
        tv.setTextColor(color);
        return this;
    }

    public ViewHolder setText(int viewId, String text, int color, int flags) {
        TextView tv = getView(viewId);
        tv.setText(text);
        tv.setTextColor(color);
        tv.getPaint().setFlags(flags);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resId, int r, int g, int b, int a) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        setIconColor(view,r,g,b,a);
        return this;
    }

    private void setIconColor(ImageView icon, int r, int g, int b, int a){
        float[] colorMatrix = new float[]{
                0, 0, 0, 0, r,
                0, 0, 0, 0, g,
                0, 0, 0, 0, b,
                0, 0, 0, (float) a / 255, 0
        };
        icon.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    public ViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId,
                                         View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public ImageView getImageView(int viewId){
        ImageView view = getView(viewId);
        return view;
    }

}
