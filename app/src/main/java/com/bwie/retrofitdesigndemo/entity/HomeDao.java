package com.bwie.retrofitdesigndemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HomeDao {
    @Id
    private Long id;
    private String jsonResult;//返回的json串
    @Generated(hash = 1379032252)
    public HomeDao(Long id, String jsonResult) {
        this.id = id;
        this.jsonResult = jsonResult;
    }
    @Generated(hash = 1174376228)
    public HomeDao() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getJsonResult() {
        return this.jsonResult;
    }
    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }
}
