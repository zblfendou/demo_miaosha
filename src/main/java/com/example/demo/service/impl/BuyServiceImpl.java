package com.example.demo.service.impl;

import com.example.demo.common.GoodKey;
import com.example.demo.model.Good;
import com.example.demo.rabbitmq.MQSender;
import com.example.demo.service.BuyService;
import com.example.demo.service.GoodService;
import com.example.demo.service.RedisService;
import com.example.demo.util.ResultWapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
@Service
@Slf4j
public class BuyServiceImpl implements BuyService, InitializingBean {

    @Autowired
    private GoodService goodService;

    //标识是否库存不足
    private Map<Long, Boolean> localOverMap = new HashMap<>();

    @Autowired
    private RedisService redisService;

    @Autowired
    private MQSender MQSender;

    @Override
    public void afterPropertiesSet() {
        List<Good> goods = goodService.getGoods(new Good.Builder().build());
        goods.forEach(good -> {
            redisService.set(GoodKey.getGoodsStock, String.valueOf(good.getId()), good.getStock());
            localOverMap.put(good.getId(), false);
        });
    }

    @Override
    @Transactional
    public ResultWapper buy(long goodId, long buyNum) {

        log.info("购买商品-->商品ID：{} , 数量:{}", goodId, buyNum);

        /*判断是否卖光了*/
        if (localOverMap.get(goodId)) {
            return ResultWapper.error("库存不足，购买失败");
        }

        /*预减库存*/
        Long stock = redisService.decr(GoodKey.getGoodsStock, String.valueOf(goodId), buyNum);
        if (stock < 0) {
            localOverMap.put(goodId, true);
            return ResultWapper.error("库存不足，购买失败");
        }

        /*通过rabbitmq消息中间件生成订单并减库存*/
        MQSender.buyOver(goodId, buyNum);

        return ResultWapper.success();
    }

}
