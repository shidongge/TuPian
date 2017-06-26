package cn.bgs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

public class MainActivity extends AppCompatActivity {
    private List<Integer> list = new ArrayList<>();

    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        initView();
    }


    private void initView() {
        final RecyclerView mRv = (RecyclerView) findViewById(R.id.mRv);
        mRv.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyAdapter(list,MainActivity.this);
        mRv.setAdapter(adapter);
        CardItemTouchHelperCallback callback = new CardItemTouchHelperCallback(mRv.getAdapter(),list);
        callback.setOnSwipedListener(new OnSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                MyAdapter.MyHorder horder = (MyAdapter.MyHorder) viewHolder;
                horder.itemView.setAlpha(1-Math.abs(ratio)*0.4f);
                if (direction== CardConfig.SWIPING_LEFT){
                    horder.buxiuan.setAlpha(Math.abs(ratio));
                }else if (direction == CardConfig.SWIPING_RIGHT){
                    horder.xihuuan.setAlpha(Math.abs(ratio));
                }else {
                    horder.buxiuan.setAlpha(0f);
                    horder.xihuuan.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                MyAdapter.MyHorder horder = (MyAdapter.MyHorder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                horder.xihuuan.setAlpha(0f);
                horder.buxiuan.setAlpha(0f);
                Toast.makeText(MainActivity.this,direction==CardConfig.SWIPED_LEFT ?"喜欢":"不喜欢",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipedClear() {
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"让我们重新再来",Toast.LENGTH_SHORT).show();
                mRv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initList();
                        mRv.getAdapter().notifyDataSetChanged();
                    }
                }, 1000);
            }
        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(mRv, touchHelper);
        mRv.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(mRv);
    }

    private void initList() {

        list.add(R.mipmap.img_avatar_01);
        list.add(R.mipmap.img_avatar_02);
        list.add(R.mipmap.img_avatar_03);
        list.add(R.mipmap.img_avatar_04);
        list.add(R.mipmap.img_avatar_05);
        list.add(R.mipmap.img_avatar_06);
        list.add(R.mipmap.img_avatar_07);

    }
}
