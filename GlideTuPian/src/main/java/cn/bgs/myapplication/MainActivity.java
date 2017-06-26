package cn.bgs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.ManifestParser;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;

public class MainActivity extends AppCompatActivity {
    private String path = "http://img181.poco.cn/mypoco/myphoto/20110315/17/54704062201103151711088752081084673_012.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button mBtn = (Button) findViewById(R.id.mbtn);
        Button mBtn1 = (Button) findViewById(R.id.mbtn1);
        final ImageView img = (ImageView) findViewById(R.id.img);
        final ImageView img1 = (ImageView) findViewById(R.id.img1);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(MainActivity.this).load(path).into(img);
            }
        });
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(MainActivity.this).load(path)
                        .bitmapTransform(new RoundedCornersTransformation(MainActivity.this,30,0, RoundedCornersTransformation.CornerType.ALL))
                        .into(img1);
            }
        });

    }
}
