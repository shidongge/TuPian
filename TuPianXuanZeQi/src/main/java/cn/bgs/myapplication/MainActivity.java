package cn.bgs.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bgs.myapplication.listener.GlidePauseOnScrollListener;
import cn.bgs.myapplication.loader.GlideImageLoader;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.PauseOnScrollListener;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.HorizontalListView;

public class MainActivity extends AppCompatActivity {
    private Button mBtn;
    private HorizontalListView lv;
    private List<PhotoInfo> list;
    private PhotoListAdaper adapter;
    private boolean mutiSelect=true;//图片多选单选开关，默认为多选
    private ThemeConfig themeConfig= ThemeConfig.DEFAULT;//主题
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;
    private   cn.finalteam.galleryfinal.ImageLoader imageLoader=new GlideImageLoader();//选择器：可以根据自己图片异步加载框架自助选择
    private PauseOnScrollListener pauseOnScrollListener=new GlidePauseOnScrollListener(false,true);//选择器滑动监听和选择器配套
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        initView();
    }

    private void initList() {
        list = new ArrayList<>();
    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.mBtn);
        lv = (HorizontalListView) findViewById(R.id.grild_photo);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
    }

    private void show() {
        //配置基本信息
        FunctionConfig.Builder functionConfigbuiler = new FunctionConfig.Builder();
        //设置最大相片选择张数
        functionConfigbuiler.setMutiSelectMaxSize(9);
        functionConfigbuiler.setSelected(list);
        final FunctionConfig functionConfig = functionConfigbuiler.build();
        CoreConfig coreconfig = new CoreConfig.Builder(MainActivity.this,imageLoader,themeConfig)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(pauseOnScrollListener)
                .build();
        //初始化
        GalleryFinal.init(coreconfig);
        //配置底部弹出sheet信息
        ActionSheet.createBuilder(MainActivity.this,getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("打开相册","拍照","裁剪")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        String path = "/sdcard/pk1-2.jpg";

                        switch (index){
                            //调用相册
                            case 0:
                                if (mutiSelect){
                                    GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY,functionConfig,callback);
                                }else {
                                    GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY,functionConfig,callback);
                                }
                                break;
                            case 1:
                                //调用相机
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, callback);
                                break;
                            case 2:
                                //剪裁
                                if (new File(path).exists()) {
                                    GalleryFinal.openCrop(REQUEST_CODE_CROP, functionConfig, path, callback);
                                } else {
                                    Toast.makeText(MainActivity.this, "图片不存在", Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }
                    }
                }).show();
        //选择适配器
        initImageLoader(this);
        x.Ext.init(getApplication());
    }

    private GalleryFinal.OnHanlderResultCallback callback= new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            //判断回调带回的值，然后初始化适配器，设置适配器，刷新适配器
            if(resultList!=null){
                list.addAll(resultList);
                Toast.makeText(MainActivity.this,"list的大小："+list.size(), Toast.LENGTH_SHORT).show();
                if(adapter==null){
                    adapter=new PhotoListAdaper(MainActivity.this,list);
                    lv.setAdapter(adapter);
                }else{
                    adapter.notifyDataSetChanged();
                }
            }
            /**
             * 可以再次获取图片的地址：遍历整list，通过photoInfo对象获取即可
             * */
            Toast.makeText(MainActivity.this,list.get(0).getPhotoPath(),Toast.LENGTH_SHORT).show();
            Log.e("图片位置",list.get(0).getPhotoPath());
        }
        //错误信息处理
        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };


    /**
     * 配置选择器相关信息
     * */
    private void initImageLoader(Context context){
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itenId = item.getItemId();
        if (itenId==R.id.action_clean_cache){
            GalleryFinal.cleanCacheFile();
        }

        return super.onOptionsItemSelected(item);
    }
}
