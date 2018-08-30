package com.yc.homelibrary.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yc.YcRecyclerViewBaseAdapter.base.YcBaseViewHolder;
import com.yc.YcRecyclerViewBaseAdapter.interfaces.OnItemClickListener;
import com.yc.commonlibrary.base.BaseFragment;
import com.yc.commonlibrary.utils.loopviewpager.LoopViewPager;
import com.yc.commonlibrary.utils.loopviewpager.RoundImageView;
import com.yc.homelibrary.R;
import com.yc.homelibrary.R2;
import com.yc.homelibrary.adapter.NewsAdapter;
import com.yc.homelibrary.bean.NewsBean;
import com.yc.homelibrary.presenter.HomePresenter;
import com.yc.homelibrary.view.IHomeView;
import com.yc.yclibrary.YcGlideUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.yc.YcRecyclerViewBaseAdapter.adapter.YcBaseAdapter.STATUS_END_GONE;

/**

 */

public class HomeFragment extends BaseFragment<IHomeView, HomePresenter<IHomeView>> implements IHomeView {
    private static HomeFragment sHomeFragment;
    @BindView(R2.id.RecyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private NewsAdapter adapter;

    private LoopViewPager looviewpager;

    List<String> mStringList;
    int[] resIds = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};

    public static HomeFragment newInstance(String param1) {
        sHomeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        sHomeFragment.setArguments(args);
        return sHomeFragment;
    }

    public HomeFragment() {

    }


    @Override
    protected HomePresenter<IHomeView> createPresenter() {
        return new HomePresenter<>();
    }

    @Override
    protected int initLayoutInflater() {
        return R.layout.home_fragment;
    }

    @Override
    public void initView(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new NewsAdapter(getActivity());
        //设置 空布局
        adapter.setEmptyView();
        mRecyclerView.setAdapter(adapter);
        initBanner();
    }

    private void initBanner() {
        mStringList = new ArrayList<>();
        mStringList.add("https://img02.sogoucdn.com/app/a/100520024/f0ed23c30a055e0ccfe96d76c468d123");
        mStringList.add("https://img02.sogoucdn.com/app/a/100520024/b46e7b35b621406e90dc716c04cd1554");
        mStringList.add("https://img04.sogoucdn.com/app/a/100520024/8fdad6484b71a132a7c2e6313e348c8c");
        View view = getActivity().getLayoutInflater().inflate(R.layout.loop_view_pager_header, (ViewGroup) mRecyclerView.getParent(), false);
        //将头布局文件添加到适配器中
        adapter.addHeaderView(view);
        looviewpager = (LoopViewPager) view.findViewById(R.id.looviewpager);
        looviewpager.setAdapter(new MyAdapter());
        looviewpager.setOffscreenPageLimit(3);
        looviewpager.setPageTransformer(true, new ViewPager.PageTransformer() {
            float scale = 0.9f;

            @Override
            public void transformPage(View page, float position) {
                if (position >= 0 && position <= 1) {
                    page.setScaleY(scale + (1 - scale) * (1 - position));
                } else if (position > -1 && position < 0) {
                    page.setScaleY(1 + (1 - scale) * position);
                } else {
                    page.setScaleY(scale);
                }
            }
        });
        looviewpager.autoLoop(true);
    }

    @Override
    public void initData() {
        basePresenter.getHomeData();

        adapter.setOnItemClickListener(new OnItemClickListener<NewsBean.ResultBean.DataBean>() {
            @Override
            public void onItemClick(YcBaseViewHolder viewHolder, NewsBean.ResultBean.DataBean data, int position) {
                //通过路由跳转到webview界面
                ARouter.getInstance().build("/common/X5BrowserActivity")
                        .withString("url", data.getUrl())
                        .navigation(getActivity());
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading(boolean b) {

    }

    @Override
    public void onError(Object result) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showhome(List apiResult) {
        if (apiResult != null) {
            adapter.setNewData(apiResult);
            adapter.loadMoreEnd(STATUS_END_GONE);
        }
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
//            return mStringList.size();
            return resIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(getActivity(), R.layout.item_viewpager, null);
            RoundImageView itemImage = (RoundImageView) view.findViewById(R.id.item_image);
//            YcGlideUtils.loadImageView(getActivity(), mStringList.get(position), itemImage);
            itemImage.setImageResource(resIds[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
