package com.example.myphone.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 自定义ViewPager
 * 1.设置自动切换page
 * 2.设置page触摸事件
 * 3.自定义page点击接口
 */
public class MyBannerPager extends ViewPager {

    private long delaytime = 4000;

    private boolean isClick = false ;
    private float downX;
    private float downY;
    private long downTime;
    private OnPageItemClickListener onPageItemClickListener = null;


    public MyBannerPager(@NonNull Context context) {
        this(context,null);
    }

    public MyBannerPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        downY = event.getY();
                        downTime = System.currentTimeMillis();
                        Log.d("","手指触碰屏幕");
                        stopLooper();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        float dx = Math.abs(event.getX() - downX);
                        float dy = Math.abs(event.getY() - downY);
                        long dTime = System.currentTimeMillis() - downTime;
                        isClick = (dx <= 5 && dy <= 5 && dTime<1000);
                        if(isClick&&onPageItemClickListener!=null){
                            onPageItemClickListener.OnItemClick(getCurrentItem());
                        }
                        Log.d("","是否点击事件?--->"+isClick);
                            startLooper();
                            break;
                }
                return false;
            }
        });
    }

    public void setPageItemClickListener(OnPageItemClickListener listener){
        this.onPageItemClickListener=listener;
    }

    public interface OnPageItemClickListener{
        void OnItemClick(int position);
    }

    //view被窗口调用时调用
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startLooper();
    }

    //view被窗口放开时调用
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLooper();
    }

    //自动切换轮播图线程
    private Runnable looper = new Runnable() {
        @Override
        public void run() {
            int currentItem = getCurrentItem();
            Log.d("looper","currentItem----->"+currentItem);
            currentItem++;
            setCurrentItem(currentItem);
            postDelayed(this,delaytime);
        }
    };

    private void startLooper() {
        removeCallbacks(looper);
        postDelayed(looper,delaytime);
    }


    private void stopLooper() {
        removeCallbacks(looper);
    }
}
