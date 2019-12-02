package com.example.demo;

import com.example.demo.service.BuyService;
import com.example.demo.service.GoodService;
import com.example.demo.service.RedisService;
import com.example.demo.util.RedisUtil;
import com.example.demo.util.ResultWapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = DemoApplication.class)
@Slf4j
class DemoApplicationTests {

    @Autowired
    private BuyService buyService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void buy() throws InterruptedException {
        long goodId = 1L;
        long buyNum = 2;
        ResultWapper resultWapper = buyService.buy(goodId, buyNum);
        outJson(resultWapper);
        TimeUnit.SECONDS.sleep(2);
    }

    private void outJson(Object obj) {
        try {
            log.info(objectMapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
