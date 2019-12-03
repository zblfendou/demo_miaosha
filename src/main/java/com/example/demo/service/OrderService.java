package com.example.demo.service;

import com.example.demo.model.Order;

import java.util.List;

/**
 * 描述:订单接口
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param goodId 商品ID
     * @param buyNum 购买数量
     */
    void createPayingOrder(Long goodId, int buyNum);

    /**
     * 获取超时未支付的订单
     */
    List<Order> getOvertimePayingOrders();

    /**
     * 将超时未支付的订单移到历史表
     */
    void moveOvertimePayingOrder2OrderHistory();
}
