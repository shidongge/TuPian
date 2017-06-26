package com.example.swipelayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.swipelayout.R.id.swipeToLoadLayout;


public class MainActivity extends AppCompatActivity {
    private SwipeToLoadLayout sWipe;
    private RecyclerView mRv;
    private List list;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initList();
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i=0 ;i<20;i++){
            list.add("我是"+(i+1)+"号");
            adapter = new MyAdapter(list,MainActivity.this);
            mRv.setAdapter(adapter);
            sWipe.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    for (int i = 0; i < 20; i++) {
                        list.add(0,"刷新获得的数据");
                    }
                    adapter.notifyDataSetChanged();
                    //设置下拉刷新结束
                    sWipe.setRefreshing(false);
                }
            });
        }


        //为swipeToLoadLayout设置上拉加载更多监听者
        sWipe.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                for (int i = 0; i < 20; i++) {
                    list.add(0,"加载更多获得的数据");
                }
                adapter.notifyDataSetChanged();
                //设置上拉加载更多结束
                sWipe.setLoadingMore(false);
            }
        });

    }

    private void initView() {
        this.sWipe = (SwipeToLoadLayout) findViewById(swipeToLoadLayout);
        this.mRv = (RecyclerView) findViewById(R.id.swipe_target);
        mRv.setLayoutManager(new LinearLayoutManager(this));

    }
}
