package com.example.demo.enums;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/12/2
 */
public enum OrderPayState {

    PAYING(1, "支付中"), PAID(2, "已支付");

    private int key;
    private String desc;

    OrderPayState(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
