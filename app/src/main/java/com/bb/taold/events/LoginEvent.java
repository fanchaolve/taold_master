package com.bb.taold.events;

/**
 * Created by zhouwan on 2017/9/21.
 */

public class LoginEvent {
    private boolean isLogin;

    public LoginEvent(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
