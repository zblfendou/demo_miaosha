package com.example.demo.rabbitmq;

import com.example.demo.config.MQConfig;
import com.example.demo.model.Good;
import com.example.demo.service.GoodService;
import com.example.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
@RabbitListener(queues = MQConfig.QUEUE_B)
@Slf4j
@Service
public class MQConsumer {

    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodService goodService;

    @RabbitHandler
    public void process(String content) {
        if (content != null) {

            String[] idAndNum = content.split(",");
            Long goodId = Long.valueOf(idAndNum[0]);
            Integer buyNum = Integer.valueOf(idAndNum[1]);
            log.info("购买消息处理器-->商品goodId:{} ,购买的数量:{}", goodId, buyNum);

            Good good = goodService.getGood(new Good.Builder().id(goodId).build());
            /*库存是否足够*/
            if (good.getStock() <= 0) return;

            /*减库存 ，生成订单*/
            orderService.createPayingOrder(goodId, buyNum);
        }
    }
}
