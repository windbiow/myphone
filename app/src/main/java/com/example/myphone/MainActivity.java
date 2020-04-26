package com.example.myphone;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.example.myphone.adapter.BannerPagerAdapter;
import com.example.myphone.adapter.HomePageContentAdapter;
import com.example.myphone.fragment.HomeFragment;
import com.example.myphone.mqtt.*;
import com.example.myphone.pager.MyBannerPager;
import com.example.myphone.util.SizeUtils;

public class MainActivity extends AppCompatActivity  {

    public static final int PAY_FLAG = 1;
    public static final int OPEN_FLAG = 2;
    public static Handler handler;

    private RecyclerView content;
    private HomePageContentAdapter contentAdapter;
    private FragmentManager fm;
    private HomeFragment homeFragment;
    private MyBannerPager bannerPager;
    private BannerPagerAdapter bannerPagerAdapter;
    private LinearLayout banner_point_container;
    private LinearLayout buy_view;
    private TextView textView;
    private AppCompatActivity SuperMainActivity=this;

    Integer[] banner={R.mipmap.banner1,R.mipmap.banner2,R.mipmap.banner3};
    private Intent mqttService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          //去掉顶部标题
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);


        bannerPager = findViewById(R.id.banner_pager);
        banner_point_container = findViewById(R.id.banner_point_container);
        initBanner();
        initFragments();
        initListener();
        initMQTT();
    }

    private void initMQTT() {
        mqttService = new Intent(MainActivity.this,AndoidMqttService.class);
        startService(mqttService);
        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==PAY_FLAG){
                    TextView text_tips=findViewById(R.id.text_tips);
                    text_tips.setText("付款完成,即将打开柜门");
                }
                if(msg.what==OPEN_FLAG){
                    buy_view = findViewById(R.id.buy_view);
                    buy_view.setVisibility(View.GONE);
                }
            }

        };
    }



    /**
     * 初始化监听
     */
    private void initListener() {
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
        homeFragment = new HomeFragment(MainActivity.this);
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_page_container,homeFragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
