package com.bwie.retrofitdesigndemo.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * a多条目bean类
 */
public class HomeMultipartEntity {
    public List<Home> list = new ArrayList<>();
    public int type = -1;//0 代表热销新品 ，1 代表魔力时尚，适配多条目的
    public static class Home{
        public int commodityId;
        public String commodityName;
        public String masterPic;
        public int price;
        public int saleNum;


    }





}
