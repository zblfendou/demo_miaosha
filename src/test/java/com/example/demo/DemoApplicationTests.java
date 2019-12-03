package com.example.demo;

import com.example.demo.common.quartz.SchedulingUtils;
import com.example.demo.common.quartz.order.ProcessOvertimePayingOrderTask;
import com.example.demo.common.quartz.test.TestTask;
import com.example.demo.model.Order;
import com.example.demo.service.BuyService;
import com.example.demo.service.OrderService;
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
import java.util.List;
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
    @Inject
    private OrderService orderService;

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
        LocalDateTime plus = now.plus(5, ChronoUnit.SECONDS);

        schedulingUtils.addTimedTaskSchedule(new ProcessOvertimePayingOrderTask(plus));

        TimeUnit.SECONDS.sleep(11);
    }

    @Test
    public void testOrder() {
        List<Order> payingOrders = orderService.getOvertimePayingOrders();
        outJson(payingOrders);
    }

    private void outJson(Object obj) {
        try {
            log.info(objectMapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
