package cn.bgs.myapplication;

import android.os.Handler;

/**
 * Created by shido on 2017/4/14.
 */

public class UserLoginPersenter {
    private Userbiz userbiz;
    private IULoginView iuLoginView;
    private Handler hand = new Handler();
    public UserLoginPersenter(IULoginView iuLoginView){
        this.iuLoginView=iuLoginView;
        this.userbiz = new Userbiz();
    }
    public void Login(){
        iuLoginView.showLogining();
        userbiz.Login(iuLoginView.getName(), iuLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void success(final Bean bean) {
                hand.post(new Runnable() {
                    @Override
                    public void run() {
                        iuLoginView.toActivity(bean);
                        iuLoginView.hintpro();
                    }
                });
            }

            @Override
            public void failed() {
                hand.post(new Runnable() {
                    @Override
                    public void run() {
                        iuLoginView.loadFail();
                        iuLoginView.hintpro();
                    }
                });
            }
        });
    }
    public void Clear(){
        iuLoginView.ClearName();
        iuLoginView.ClearPassword();
    }
}
