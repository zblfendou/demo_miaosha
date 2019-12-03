package com.example.demo.common.quartz.order;

import com.example.demo.common.quartz.TimedTask;
import com.example.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

/**
 * 描述:删除十五分钟之内未支付的订单
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/12/3
 */
@Slf4j
public class ProcessOvertimePayingOrderTask extends TimedTask {

    public ProcessOvertimePayingOrderTask(LocalDateTime startTime) {
        super(startTime);
    }

    public ProcessOvertimePayingOrderTask() {
    }

    @Override
    public String getJobName() {
        return "PROCESS_OVERTIME_PAYING";
    }

    @Override
    public String getJobGroup() {
        return "ORDER";
    }

    @Override
    public void executeTask(ApplicationContext context) {
        log.info("执行处理超时未支付订单任务开始");

        OrderService orderService = getService(context, OrderService.class);

        orderService.moveOvertimePayingOrder2OrderHistory();

        log.info("执行处理超时未支付订单任务结束");

    }
}
