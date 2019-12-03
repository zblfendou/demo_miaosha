package com.example.demo;

import com.example.demo.common.quartz.SchedulingUtils;
import com.example.demo.common.quartz.TestTask;
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

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = DemoApplication.class)
@Slf4j
class DemoApplicationTests {

    @Autowired
    private BuyService buyService;

    @Autowired
    private ObjectMapper objectMapper;
    @Inject
    private SchedulingUtils schedulingUtils;


    @Test
    public void buy() throws InterruptedException {
        long goodId = 1L;
        long buyNum = 2;
        ResultWapper resultWapper = buyService.buy(goodId, buyNum);
        outJson(resultWapper);
        TimeUnit.SECONDS.sleep(1);
    }


    @Test
    public void testSchedule() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plus = now.plus(10, ChronoUnit.SECONDS);
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(plus.format(formatter));
        schedulingUtils.addTimedTaskSchedule(new TestTask(plus,231));
        TimeUnit.SECONDS.sleep(11);
    }

    private void outJson(Object obj) {
        try {
            log.info(objectMapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
