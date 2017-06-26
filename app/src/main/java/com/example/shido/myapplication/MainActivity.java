package com.example.shido.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{
    private ViewPager mVp;
    private List<ImageView> list;
    private int aa[] = {
        R.mipmap.aa,R.mipmap.bb,R.mipmap.cc,R.mipmap.dd
    };
    private  boolean isFeart =true ;
    private  boolean isTouch =false;
    private  int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initList();
        Thread thread = new Thread();
        thread.start();
    }

    private void initList() {
        list = new ArrayList<ImageView>();
        for (int i=0;i<aa.length;i++){
            ImageView mImg = new ImageView(this);
            mImg.setScaleType(ImageView.ScaleType.FIT_XY);
            mImg.setImageResource(aa[i]);
            list.add(mImg);
        }
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.mVp);
        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
               if (state==ViewPager.SCROLL_STATE_IDLE){
                    isTouch=false;
                }else {
                   isTouch=true;
               }
            }
        });
        mVp.setPageMargin(20);
        mVp.setOffscreenPageLimit(3);
        MyAdapter adapter = new MyAdapter();
        mVp.setAdapter(adapter);
        mVp.setPageTransformer(true, new RotateDownPageTransformer());

    }
    private  class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            try {
                container.addView(list.get(position%aa.length));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list.get(position%aa.length);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            try {
                container.removeView(list.get(position%aa.length));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private  class  MyThread extends  Thread{
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(3000);
                hand.sendEmptyMessage(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                if (isTouch){
                    return;
                }if (!isFeart){
                    return;
                }
                index++;
                mVp.setCurrentItem(index);
            }
        }
    };
}
