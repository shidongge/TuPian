package cn.bgs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IULoginView{
    private Button tijiao,chongzhi;
    private EditText name,password;
    private ProgressBar mPro;
    private UserLoginPersenter persenter = new UserLoginPersenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tijiao = (Button) findViewById(R.id.denglu);
        chongzhi = (Button) findViewById(R.id.chongzhi);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        mPro = (ProgressBar) findViewById(R.id.pro);

        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persenter.Login();
            }
        });

        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persenter.Clear();
            }
        });
    }

    @Override
    public String getName() {
        return name.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void ClearName() {

        name.setText("");
    }

    @Override
    public void ClearPassword() {
        password.setText("");
    }

    @Override
    public void showLogining() {
        mPro.setVisibility(View.VISIBLE);
    }

    @Override
    public void hintpro() {
        mPro.setVisibility(View.GONE);
    }

    @Override
    public void toActivity(Bean bean) {        Toast.makeText(MainActivity.this,bean.getName()+"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadFail() {
        Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
    }
}

