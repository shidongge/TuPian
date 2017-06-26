package cn.bgs.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by shido on 2017/4/13.
 */

public class MyAdapter extends RecyclerView.Adapter {
    private List<Integer> list;
    private Context mCtx;

    public MyAdapter(List<Integer> list,Context mCtx){
        this.list=list;
        this.mCtx=mCtx;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.fujian,viewGroup,false);
        return new MyHorder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ImageView mIv = ((MyHorder)viewHolder).mIv;
        mIv.setImageResource(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHorder extends RecyclerView.ViewHolder{
        ImageView mIv,xihuuan,buxiuan;
        public MyHorder(View itemView) {
            super(itemView);
            mIv= (ImageView) itemView.findViewById(R.id.mIv);
            xihuuan = (ImageView) itemView.findViewById(R.id.xihuan);
            buxiuan = (ImageView) itemView.findViewById(R.id.buxihuan);
        }
    }
}
