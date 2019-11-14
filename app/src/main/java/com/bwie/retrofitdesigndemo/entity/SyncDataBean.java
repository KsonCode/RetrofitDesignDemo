package com.bwie.retrofitdesigndemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SyncDataBean {
    public Long id;
    public String commodityId;
    public int count;
    @Generated(hash = 1074046275)
    public SyncDataBean(Long id, String commodityId, int count) {
        this.id = id;
        this.commodityId = commodityId;
        this.count = count;
    }
    @Generated(hash = 1112026800)
    public SyncDataBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCommodityId() {
        return this.commodityId;
    }
    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
