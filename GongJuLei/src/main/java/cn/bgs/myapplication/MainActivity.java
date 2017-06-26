package cn.bgs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.smartbetter.utilslibrary.ToastUtils;
import net.smartbetter.utilslibrary.net.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkUtils.isWifiConn(MainActivity.this);
        NetworkUtils.isConnected(MainActivity.this);
        ToastUtils.showLong(MainActivity.this,"heheda");
    }
}
