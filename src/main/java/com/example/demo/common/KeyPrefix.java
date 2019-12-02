package com.example.demo.common;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
public interface KeyPrefix {

    public int expiredSeconds();

    public String getPrefix();
}
