package cn.bgs.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by shido on 2017/4/3.
 */

public class Login extends Activity implements View.OnClickListener {
    private EditText zhanghao,mima;
    private TextView zhuce;
    private Button denglu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
    }

    private void initView() {
        zhanghao = (EditText) findViewById(R.id.zhanghao);
        mima = (EditText) findViewById(R.id.mima);
        zhuce = (TextView) findViewById(R.id.zhuce);
        denglu = (Button) findViewById(R.id.denglu);
        zhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.zhuce:
                startActivity(new Intent(Login.this,MainActivity.class));
                break;
        }
    }
}
