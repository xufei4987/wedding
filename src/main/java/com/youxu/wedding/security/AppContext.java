package com.youxu.wedding.security;

public class AppContext implements AutoCloseable{
    private static final ThreadLocal<String> CURRENT_USER_WX_OPENID = new ThreadLocal<>();
    public AppContext(String openId){
        CURRENT_USER_WX_OPENID.set(openId);
    }

    @Override
    public void close() {
        CURRENT_USER_WX_OPENID.remove();
    }

    public static String getCurrentUserWxOpenId(){
        return CURRENT_USER_WX_OPENID.get();
    }
}
