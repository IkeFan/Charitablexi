package com.mmy.charitablexi.model.project.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmy.charitablexi.R;
import com.mmy.charitablexi.base.BaseRecyclerViewAdapter;
import com.mmy.frame.data.bean.PhotoBean;

import java.util.ArrayList;


/**
 * @创建者 lucas
 * @创建时间 2017/9/11 0011 9:21
 * @描述 TODO
 */

public class PublishPicAdapter extends BaseRecyclerViewAdapter {

    private ArrayList<PhotoBean> mData= new ArrayList<PhotoBean>();
    private View                  deleteView;
    public static final int ADD = 0xa0;
    static final int DEF = 0xa1;

    public PublishPicAdapter(Context context) {
        super(context);
        notifyDataSetChanged();
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_refund_info, viewGroup,false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerViewHolder holder, int i) {
        //影藏最后一个item的删除键
        deleteView = holder.getRootView().findViewById(R.id.delete);
        if (getItemViewType(i) == ADD) {
            deleteView.setVisibility(View.GONE);
            holder.clearTVBG(R.id.icon);
            return;
        }
        holder.setBitmap2IV(R.id.icon, BitmapFactory.decodeFile(mData.get(i).getPath()));
        deleteView.setVisibility(View.VISIBLE);
        deleteView.setOnClickListener(view -> {
            int position = holder.getAdapterPosition();
            mData.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemViewType(int position) {
        if ((getItemCount() == 1) && mData == null) {
            return ADD;
        }
        if (mData.size() == position) {
            return ADD;
        } else {
            return DEF;
        }
    }

    public void addData(ArrayList<PhotoBean> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 1 : mData.size() + 1;
    }

}
