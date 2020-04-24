package com.example.myphone.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myphone.MainActivity;
import com.example.myphone.R;
import com.example.myphone.adapter.HomePageContentAdapter;
import com.example.myphone.callback.IHomeCallback;
import com.example.myphone.model.domain.Commodity;
import com.example.myphone.presenter.impl.HomePresenterImpl;

import java.util.List;

public class HomeFragment extends Fragment implements IHomeCallback {

    private HomePresenterImpl homePresenter;
    private HomePageContentAdapter contentAdapter;
    private MainActivity mContext;

    public HomeFragment(MainActivity mContext){
        this.mContext=mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_frame, container, false);
        initPresenter();
        loadData();
        initView(rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        release();
    }

    private void initView(View rootView) {
        RecyclerView content = rootView.findViewById(R.id.commodity_content);
        content.setLayoutManager(new GridLayoutManager(getContext(),4));
//        设置单元间隔
        content.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 5;
                outRect.bottom = 5;
                outRect.left = 5;
                outRect.right = 5;
            }
        });
        contentAdapter = new HomePageContentAdapter(mContext);
        content.setAdapter(contentAdapter);
    }

    private void loadData() {
        homePresenter.getCommodities();
    }

    private void initPresenter() {
        homePresenter = new HomePresenterImpl();
        homePresenter.registerCallback(this);
    }

    private void release() {
        if(homePresenter!=null){
            homePresenter.unregisterCallback();
        }
    }

    @Override
    public void onCategoriesLoaded(List<Commodity.DataBean> commodities) {
        contentAdapter.setCommodities(commodities);
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void onEmpty() {

    }
}
