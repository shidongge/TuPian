package cn.bgs.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.LottieComposition;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by shido on 2017/5/9.
 */

public class BActivity extends Activity {
    private LottieAnimationView mLottie;
    private String url = "http://192.168.4.188/MS/app/file/LottieLogo1.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_b);
        initView();

    }

    private void initView() {
        mLottie = (LottieAnimationView) findViewById(R.id.mLottie);
        OkUtils.UpLoadWZ(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("aaaaaaa",""+e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()){

                }
                try {
                    JSONObject json = new JSONObject(response.body().string());
                    LottieComposition.fromJson(getResources(), json, new LottieComposition.OnCompositionLoadedListener() {
                        @Override
                        public void onCompositionLoaded(LottieComposition composition) {
                            setComposition(composition);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void setComposition(LottieComposition composition){
        mLottie.setProgress(0);
        mLottie.loop(true);
        mLottie.playAnimation();
        mLottie.setComposition(composition);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLottie.cancelAnimation();
    }
}
