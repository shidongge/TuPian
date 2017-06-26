package com.example.swipelayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shido on 2017/2/16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    List<String> list;
    Context mCtc;

    public MyAdapter(List<String> list , Context mCtc){
        this.list=list;
        this.mCtc=mCtc;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_my, viewGroup, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder itemViewHolder, int position) {
        //Here you can fill your row view
        itemViewHolder.text.setText(list.get(position));
    }
    @Override
    public int getItemCount() {
        if (list==null){
            return  0;
        }
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private android.widget.TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
