package com.example.demo.service;

import com.example.demo.model.Good;

import java.util.List;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
public interface GoodService {
    List<Good> getGoods(Good condition);

    Good getGood(Good condition);

    boolean reduceStock(Long goodId, int buyNum);
}
