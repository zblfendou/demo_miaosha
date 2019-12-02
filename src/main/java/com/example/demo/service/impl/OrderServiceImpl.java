package com.example.demo.service.impl;

import com.example.demo.common.GoodKey;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.service.GoodService;
import com.example.demo.service.OrderService;
import com.example.demo.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodService goodService;

    @Autowired
    private RedisService redisService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(Long goodId, int buyNum) {
        /*减库存*/
        boolean reduceSuccess = goodService.reduceStock(goodId, buyNum);
        if (reduceSuccess) {
            log.info("减库存成功!!!");
            /*下单*/
            Order order = new Order.Builder().goodId(goodId).buyNum(buyNum).build();
            orderMapper.insertSelective(order);
            log.info("创建订单成功!!!");
        } else {
            /*将预减商品库存返还*/
            redisService.incr(GoodKey.getGoodsStock, String.valueOf(goodId), buyNum);
            log.error("将预减商品库存返还成功!!!");
        }
    }
}
