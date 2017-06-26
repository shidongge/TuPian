package cn.bgs.myapplication;


/**
 * Created by shido on 2017/4/14.
 */

public interface IULoginView {
    String getName();
    String getPassword();
    void ClearName();
    void ClearPassword();
    void showLogining();
    void hintpro();//隐藏转圈
    void toActivity(Bean bean);
    void loadFail();//加载失败

}
