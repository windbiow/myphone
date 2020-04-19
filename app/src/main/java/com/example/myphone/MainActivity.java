package com.example.myphone;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.myphone.adapter.BannerPagerAdapter;
import com.example.myphone.adapter.HomePageContentAdapter;
import com.example.myphone.callback.IHomeCallback;
import com.example.myphone.fragment.HomeFragment;
import com.example.myphone.model.domain.Commodity;
import com.example.myphone.pager.MyBannerPager;
import com.example.myphone.util.SizeUtils;

import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity{

    private RecyclerView content;
    private HomePageContentAdapter contentAdapter;
    private FragmentManager fm;
    private HomeFragment homeFragment;
    private MyBannerPager bannerPager;
    private BannerPagerAdapter bannerPagerAdapter;
    private LinearLayout banner_point_container;
    private AppCompatActivity SuperMainActivity=this;

    Integer[] banner={R.mipmap.banner1,R.mipmap.banner2,R.mipmap.banner3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          //去掉顶部标题
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        bannerPager = findViewById(R.id.banner_pager);
        banner_point_container = findViewById(R.id.banner_point_container);
        initBanner();
        initFragments();
        initListener();
    }

    private void initListener() {
        bannerPager.setPageItemClickListener(new MyBannerPager.OnPageItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                //TODO: 点击事件
            }
        });
        bannerPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int targetPosition = position % banner.length;
                updateLooperIndicator(targetPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    /**
     * 初始化轮播图
     */
    private void initBanner() {
        bannerPagerAdapter = new BannerPagerAdapter();
        bannerPagerAdapter.getBanner(banner);

        bannerPager.setAdapter(bannerPagerAdapter);

        //设置轮播图起点
        int targetPosition = (Integer.MAX_VALUE / 2) - ((Integer.MAX_VALUE / 2) % banner.length);
        bannerPager.setCurrentItem(targetPosition);

        for(int i = 0;i<banner.length;i++){
            View point = new View(this);
            int size = SizeUtils.dip2px(this,8);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size,size);
            layoutParams.leftMargin =  SizeUtils.dip2px(this,5);
            layoutParams.rightMargin =  SizeUtils.dip2px(this,5);
            point.setLayoutParams(layoutParams);
            if(0 == i){
                point.setBackgroundResource(R.drawable.shape_indicator_point);
            }else{
                point.setBackgroundResource(R.drawable.shape_indicator_point_normal);
            }

            banner_point_container.addView(point);
        }
    }

    /**
     * 切换指示器样式
     * @param targetPosition
     */
    private void updateLooperIndicator(int targetPosition) {
        for(int i = 0 ; i < banner_point_container.getChildCount();i++){
            View childAt = banner_point_container.getChildAt(i);
            if(targetPosition == i){
                childAt.setBackgroundResource(R.drawable.shape_indicator_point);
            }else{
                childAt.setBackgroundResource(R.drawable.shape_indicator_point_normal);
            }
        }
    }

    /**
     * 初始化首页商品信息fragment
     */
    private void initFragments() {
        homeFragment = new HomeFragment();
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_page_container,homeFragment);
        transaction.commit();
    }

}
