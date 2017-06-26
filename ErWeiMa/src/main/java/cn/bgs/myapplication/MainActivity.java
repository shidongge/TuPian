package cn.bgs.myapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import com.baoyz.actionsheet.ActionSheet;
import com.google.zxing.WriterException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtn1,mBtn2,mBtn3;
    private ImageView mImg;
    private Bitmap bitmap;
    private static int width, height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mBtn1 = (Button) findViewById(R.id.mBtn1);
        mBtn2 = (Button) findViewById(R.id.mBtn2);
        mBtn3 = (Button) findViewById(R.id.mBtn3);
        mImg = (ImageView) findViewById(R.id.mImg);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.mBtn1:
                calculateView();
                break;
            case R.id.mBtn2:
                calculateView2();
                break;
            case R.id.mBtn3:
                calculateView3();
                break;
        }
    }
    /* *
            * 计算控件的宽高
            */
    private void calculateView() {
        final ViewTreeObserver vto = mImg.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (vto.isAlive()) {
                    vto.removeOnPreDrawListener(this);
                }
                height = mImg.getMeasuredHeight();
                width = mImg.getMeasuredWidth();
 //               Bitmap    logo = MakeQRCodeUtil.gainBitmap(MainActivity.this, R.mipmap.logo1);
//                Bitmap   background = MakeQRCodeUtil.gainBitmap(MainActivity.this, R.mipmap.bg1);
//                Bitmap markBMP = MakeQRCodeUtil.gainBitmap(MainActivity.this, R.mipmap.water);
                try {
                    //获得二维码图片
                    bitmap = MakeQRCodeUtil.makeQRImage(
                            "http://www.baidu.com",
                            width, height);
                    //给二维码加背景
                   // bitmap = MakeQRCodeUtil.addBackground(bitmap, background);
                    //加水印
                    //bitmap = MakeQRCodeUtil.composeWatermark(bitmap, markBMP);
                    //设置二维码图片
                    mImg.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

    }
    /* *
            * 计算控件的宽高
            */
    private void calculateView2() {
        final ViewTreeObserver vto = mImg.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (vto.isAlive()) {
                    vto.removeOnPreDrawListener(this);
                }
                height = mImg.getMeasuredHeight();
                width = mImg.getMeasuredWidth();
                Bitmap    logo = MakeQRCodeUtil.gainBitmap(MainActivity.this, R.mipmap.logo1);
//                Bitmap   background = MakeQRCodeUtil.gainBitmap(MainActivity.this, R.mipmap.bg1);
//                Bitmap markBMP = MakeQRCodeUtil.gainBitmap(MainActivity.this, R.mipmap.water);
                try {
                    //获得二维码图片
                    bitmap = MakeQRCodeUtil.makeQRImage(logo,
                            "http://www.baidu.com",
                            width, height);
                    //给二维码加背景
                    // bitmap = MakeQRCodeUtil.addBackground(bitmap, background);
                    //加水印
                    //bitmap = MakeQRCodeUtil.composeWatermark(bitmap, markBMP);
                    //设置二维码图片
                    mImg.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

    }
    /* *
           * 计算控件的宽高
           */
    private void calculateView3() {
        final ViewTreeObserver vto = mImg.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (vto.isAlive()) {
                    vto.removeOnPreDrawListener(this);
                }
                height = mImg.getMeasuredHeight();
                width = mImg.getMeasuredWidth();
                Bitmap    logo = MakeQRCodeUtil.gainBitmap(MainActivity.this, R.mipmap.logo1);
                Bitmap   background = MakeQRCodeUtil.gainBitmap(MainActivity.this, R.mipmap.bg1);
                Bitmap markBMP = MakeQRCodeUtil.gainBitmap(MainActivity.this, R.mipmap.water);
                try {
                    //获得二维码图片
                    bitmap = MakeQRCodeUtil.makeQRImage(logo,
                            "http://www.baidu.com",
                            width*3/5, height*3/5);
                    //给二维码加背景
                     bitmap = MakeQRCodeUtil.addBackground(bitmap, background);
                    //加水印
                    bitmap = MakeQRCodeUtil.composeWatermark(bitmap, markBMP);
                    //设置二维码图片
                    mImg.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

    }

}
