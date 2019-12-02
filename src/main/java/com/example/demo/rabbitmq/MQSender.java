package com.example.demo.rabbitmq;

import com.example.demo.config.MQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 描述:购买商品生产者
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
@Service
@Slf4j
public class MQSender implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private RabbitTemplate rebbitTemplate;

    public void buyOver(long goodId, long buyNum) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rebbitTemplate.convertAndSend(MQConfig.EXCHANGE_B, MQConfig.ROUTINGKEY_B, goodId + "," + buyNum, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("BuyProducer回调id:" + correlationData);
        if (ack) {
            log.info("BuyProducer消息消费成功");
        } else {
            log.info("BuyProducer消息消费失败" + cause);
        }
    }
}
