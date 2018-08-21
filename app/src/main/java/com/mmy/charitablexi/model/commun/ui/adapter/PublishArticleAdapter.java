package com.mmy.charitablexi.model.commun.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mmy.charitablexi.R;
import com.mmy.frame.base.adapter.BaseRecyclerViewAdapter;
import com.mmy.frame.data.bean.PhotoBean;
import com.mmy.frame.utils.Config;

import java.util.LinkedList;


/**
 * @创建者 lucas
 * @创建时间 2017/9/11 0011 9:21
 * @描述 TODO
 */

public class PublishArticleAdapter extends BaseRecyclerViewAdapter {

    private LinkedList<PhotoBean> mData;
    private View                  deleteView;
    static final int ADD = 0xa0;
    static final int DEF = 0xa1;

    public PublishArticleAdapter(Context context) {
        super(context);
        notifyDataSetChanged();
    }

    @Override
    public BaseRecyclerViewAdapter.BaseRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_refund_info, viewGroup,false);
        return new BaseRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int i) {
        //影藏最后一个item的删除键
        deleteView = holder.getRootView().findViewById(R.id.delete);
        if (getItemViewType(i) == ADD) {
            deleteView.setVisibility(View.GONE);
            holder.clearTVBG(R.id.icon);
            return;
        }

        if(mData.get(i).getPath().startsWith("/Uploads")){
            Glide.with(mContext).load(Config.HOST +mData.get(i).getPath())
                    .error(R.mipmap.ic_def)
                    .placeholder(R.mipmap.ic_def)
                    .into((ImageView) holder.findView(R.id.icon));
        }
        else{
            holder.setBitmap2IV(R.id.icon, BitmapFactory.decodeFile(mData.get(i).getPath()));
        }
        deleteView.setVisibility(View.VISIBLE);
        deleteView.setOnClickListener(v -> {
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

    public LinkedList<PhotoBean> getData(){
        return mData;
    }

    public void setData(LinkedList<PhotoBean> data) {
        if (mData == null)
            mData = data;
        else
            mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 1 : mData.size() + 1;
    }

}
