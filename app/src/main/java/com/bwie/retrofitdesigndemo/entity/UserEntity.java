package com.bwie.retrofitdesigndemo.entity;

import com.bwie.retrofitdesigndemo.base.bean.BaseBean;

public class UserEntity extends BaseBean {

    public User result;
    public static class  User{
        public String userId;
        public String sessionId;
    }
}
