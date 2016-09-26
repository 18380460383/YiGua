package com.qhzlwh.yigua.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qhzlwh.yigua.bean.SenicCommentBean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/9/8
 * 功能描述：
 */
public class MyCommentAdapter extends BaseAdapter {
    private List<SenicCommentBean.DataEntity>mList;
    private Context mContext;

    public MyCommentAdapter(List<SenicCommentBean.DataEntity> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
    class CommentViewHolderSenic{

    }
    class CommtentViewHolderLine{

    }
}
