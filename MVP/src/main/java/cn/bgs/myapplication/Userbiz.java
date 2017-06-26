package cn.bgs.myapplication;

/**
 * Created by shido on 2017/4/14.
 */

public class Userbiz implements IUserBiz {
    @Override
    public void Login(final String name, final String password, final OnLoginListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("shishi".equals(name)&&"123456".equals(password)){
                    Bean bean = new Bean();
                    bean.setName(name);
                    bean.setPassword(password);
                    listener.success(bean);
                }else {
                    listener.failed();
                }
            }
        }).start();
    }
}
