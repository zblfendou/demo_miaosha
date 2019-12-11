package com.example.demo.service.impl;

import com.example.demo.common.GoodKey;
import com.example.demo.config.OrderConfig;
import com.example.demo.enums.OrderPayState;
import com.example.demo.mapper.HistoryOrderMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.model.HistoryOrder;
import com.example.demo.service.GoodService;
import com.example.demo.service.OrderService;
import com.example.demo.service.RedisService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderMapper orderMapper;

    @Inject
    private GoodService goodService;

    @Inject
    private RedisService redisService;
    @Inject
    private OrderConfig orderConfig;
    @Inject
    private HistoryOrderMapper historyOrderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPayingOrder(Long goodId, int buyNum) {
        /*减库存*/
        boolean reduceSuccess = goodService.reduceStock(goodId, buyNum);
        if (reduceSuccess) {
            log.info("减库存成功!!!");
            /*下单*/
            Order order = new Order.Builder()
                    .goodId(goodId)
                    .buyNum(buyNum)
                    .payState(OrderPayState.PAYING.getKey())
                    .createTime(new Date())
                    .build();
            orderMapper.insertSelective(order);
            log.info("创建订单成功!!!");
        } else {
            /*将预减商品库存返还*/
            redisService.incr(GoodKey.getGoodsStock, String.valueOf(goodId), buyNum);
            log.error("将预减商品库存返还成功!!!");
        }
    }

    @Override
    public List<Order> getOvertimePayingOrders() {
        PageHelper.startPage(1, 3);
        return orderMapper.getOvertimePayingOrders(orderConfig.getOvertimeMinute());
    }

    @Override
    @Transactional
    public void moveOvertimePayingOrder2OrderHistory() {

        List<Order> overtimePayingOrders = getOvertimePayingOrders();

        for (Order overtimePayingOrder : overtimePayingOrders) {

            HistoryOrder historyOrder = new HistoryOrder();
            BeanUtils.copyProperties(overtimePayingOrder, historyOrder);

            historyOrder.setId(null);

            historyOrderMapper.insert(historyOrder);

            orderMapper.deleteByPrimaryKey(overtimePayingOrder.getId());
        }
    }
}
