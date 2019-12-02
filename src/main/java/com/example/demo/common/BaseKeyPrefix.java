package com.example.demo.common;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
public class BaseKeyPrefix implements KeyPrefix {

    private int expiredSeconds;

    private String prefix;

    public BaseKeyPrefix(String prefix) {
        this(0, prefix);
    }

    public BaseKeyPrefix(int expiredSeconds, String prefix) {
        this.expiredSeconds = expiredSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expiredSeconds() {
        return expiredSeconds;
    }

    @Override
    public String getPrefix() {
        String simpleName = getClass().getSimpleName();
        return simpleName + ":" + prefix;
    }
}
