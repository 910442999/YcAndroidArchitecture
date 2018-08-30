package com.yc.homelibrary.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.yc.YcRecyclerViewBaseAdapter.adapter.YcCommonBaseAdapter;
import com.yc.YcRecyclerViewBaseAdapter.base.YcBaseViewHolder;
import com.yc.commonlibrary.base.BaseApplication;
import com.yc.homelibrary.R;
import com.yc.homelibrary.bean.NewsBean;
import com.yc.yclibrary.YcGlideUtils;

/**
 * Created by yc on 2018/8/21.
 */

public class NewsAdapter extends YcCommonBaseAdapter<NewsBean.ResultBean.DataBean> {

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(YcBaseViewHolder holder, NewsBean.ResultBean.DataBean data, int position, int viewType) {
        holder.setText(R.id.tv_title, data.getTitle());
        ImageView view = holder.getView(R.id.iv_icon);
        YcGlideUtils.loadImageView(BaseApplication.getAppContext(), data.getThumbnail_pic_s(), view);
        holder.setText(R.id.tv_time, data.getDate());
        holder.setText(R.id.tv_frome, "来源 :" + data.getAuthor_name());
        //        holder.addOnClickListener(R.id.item_title);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.news_item_layout;
    }

}
