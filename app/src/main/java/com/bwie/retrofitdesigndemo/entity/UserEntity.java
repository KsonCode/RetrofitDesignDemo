package com.bwie.retrofitdesigndemo.entity;


import com.bwie.lib_core.base.bean.BaseBean;

public class UserEntity extends BaseBean {

    public User result;
    public static class  User{
        public String userId;
        public String sessionId;
    }
}
