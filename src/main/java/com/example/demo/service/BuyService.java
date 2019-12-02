package com.example.demo.service;

import com.example.demo.util.ResultWapper;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
public interface BuyService {

    ResultWapper buy(long goodId, long buyNum);

}
