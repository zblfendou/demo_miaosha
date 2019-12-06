package com.example.demo.service.impl;

import com.example.demo.mapper.GoodMapper;
import com.example.demo.model.Good;
import com.example.demo.service.GoodService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/11/29
 */
@Service
@Slf4j
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodMapper goodMapper;
    @Inject
    private ObjectMapper objectMapper;

    @Override
    public List<Good> getGoods(Good condition) {
        return goodMapper.getGoods(condition);
    }

    @Override
    public Good getGood(Good condition) {
        return goodMapper.getGood(condition);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean reduceStock(Long goodId, int buyNum) {
        Good g = new Good.Builder().id(goodId).reduceStock(buyNum).build();
        int ret = goodMapper.reduceStock(g);
        return ret > 0;
    }
}
