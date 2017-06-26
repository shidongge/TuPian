package cn.bgs.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by shido on 2017/4/10.
 */

public class PhotoListAdaper extends BaseAdapter {
    private List<PhotoInfo> list;
    private LayoutInflater inflater;
    private int mScreenWith;
    public PhotoListAdaper(Activity activity,List<PhotoInfo> list){
        this.list=list;
        inflater = LayoutInflater.from(activity);
        mScreenWith = DeviceUtils.getScreenPix(activity).widthPixels;
    }
    @Override
    public int getCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_gf_default_photo)
                .showImageOnFail(R.mipmap.ic_gf_default_photo)
                .showImageOnLoading(R.mipmap.ic_gf_default_photo)
                .build();
        ImageView iv_photo= (ImageView) inflater.inflate(R.layout.adapter_photo_list_item,null);
        setheight(iv_photo);
        PhotoInfo info=list.get(position);
        ImageLoader.getInstance().displayImage("file:/"+info.getPhotoPath(),iv_photo,options);

        return iv_photo;
    }
    public void setheight(View contentView){
        int height  = mScreenWith/3-8;
        contentView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height));

    }
}
