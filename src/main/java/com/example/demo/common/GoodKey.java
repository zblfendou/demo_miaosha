package com.example.demo.common;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
public class GoodKey extends BaseKeyPrefix {
    public GoodKey(int expiredSeconds, String prefix) {
        super(expiredSeconds, prefix);
    }
    public static GoodKey getGoodsList = new GoodKey(60, "gl");
    public static GoodKey getGoodsDetail = new GoodKey(60, "gd");
    public static GoodKey getGoodsStock = new GoodKey(0, "gs");
}
