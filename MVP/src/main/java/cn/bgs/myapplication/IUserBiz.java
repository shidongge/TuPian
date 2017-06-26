package cn.bgs.myapplication;

/**
 * Created by shido on 2017/4/14.
 */

public interface IUserBiz {
    public void Login(String name,String password,OnLoginListener listener);
}
