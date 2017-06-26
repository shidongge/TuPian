package com.example.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.syd.oden.gesturelock.view.GestureLockViewGroup;
import com.syd.oden.gesturelock.view.listener.GestureEventListener;
import com.syd.oden.gesturelock.view.listener.GesturePasswordSettingListener;
import com.syd.oden.gesturelock.view.listener.GestureUnmatchedExceedListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyLog mylog = new MyLog("[GestureLockActivity] ");

    private GestureLockViewGroup mGestureLockViewGroup;
    private boolean isReset = false;
    private RelativeLayout rl_reset;
    private TextView tv_state;

    void init() {
        initGesture();
        setGestureWhenNoSet();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl_reset = (RelativeLayout) findViewById(R.id.rl_reset);
        tv_state = (TextView) findViewById(R.id.tv_state);
        rl_reset.setOnClickListener(this);
    }
    private void initGesture() {
        mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.gesturelock);
        gestureEventListener();
        gesturePasswordSettingListener();
        gestureRetryLimitListener();
    }
    private void gestureEventListener() {
        mGestureLockViewGroup.setGestureEventListener(new GestureEventListener() {
            @Override
            public void onGestureEvent(boolean matched) {
                mylog.d("onGestureEvent matched: " + matched);
                if (!matched) {

                    tv_state.setTextColor(Color.RED);
                    tv_state.setText("手势密码错误");
                } else {
                    if (isReset) {
                        isReset = false;
                        Toast.makeText(MainActivity.this, "清除成功!", Toast.LENGTH_SHORT).show();
                        resetGesturePattern();
                    } else {
                        tv_state.setTextColor(Color.WHITE);
                        tv_state.setText("手势密码正确");
                    }
                }
            }
        });
    }
    private void gesturePasswordSettingListener() {
        mGestureLockViewGroup.setGesturePasswordSettingListener(new GesturePasswordSettingListener() {
            @Override
            public boolean onFirstInputComplete(int len) {
                mylog.d("onFirstInputComplete");
                if (len > 3) {
                    tv_state.setTextColor(Color.WHITE);
                    tv_state.setText("再次绘制手势密码");
                    return true;
                } else {
                    tv_state.setTextColor(Color.RED);
                    tv_state.setText("最少连接4个点，请重新输入!");
                    return false;
                }
            }

            @Override
            public void onSuccess() {
                mylog.d("onSuccess");
                tv_state.setTextColor(Color.WHITE);
                Toast.makeText(MainActivity.this, "密码设置成功!", Toast.LENGTH_SHORT).show();
                tv_state.setText("请输入手势密码解锁!");
            }

            @Override
            public void onFail() {
                tv_state.setTextColor(Color.RED);
                tv_state.setText("与上一次绘制不一致，请重新绘制");
            }
        });
    }
    private void gestureRetryLimitListener() {
        mGestureLockViewGroup.setGestureUnmatchedExceedListener(3, new GestureUnmatchedExceedListener() {
            @Override
            public void onUnmatchedExceedBoundary() {
                tv_state.setTextColor(Color.RED);
                tv_state.setText("错误次数过多，请稍后再试!");
            }
        });
    }
    private void setGestureWhenNoSet() {
        if (!mGestureLockViewGroup.isSetPassword()){
            mylog.d("未设置密码，开始设置密码");
            tv_state.setTextColor(Color.WHITE);
            tv_state.setText("绘制手势密码");
        }
    }

    private void resetGesturePattern() {
        mGestureLockViewGroup.removePassword();
        setGestureWhenNoSet();
        mGestureLockViewGroup.resetView();
    }

    @Override
    public void onClick(View v) {
        isReset = true;
        tv_state.setTextColor(Color.WHITE);
        tv_state.setText("请输入原手势密码");
        mGestureLockViewGroup.resetView();
    }
}
