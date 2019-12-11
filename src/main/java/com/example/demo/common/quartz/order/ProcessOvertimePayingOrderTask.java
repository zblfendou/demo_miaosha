package com.example.demo.common.quartz.order;

import com.example.demo.common.quartz.SchedulingUtils;
import com.example.demo.common.quartz.TimedTask;
import com.example.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 描述:删除十五分钟之内未支付的订单
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/12/3
 */
@Slf4j
public class ProcessOvertimePayingOrderTask extends TimedTask {

    private static final long serialVersionUID = 7523973842062477630L;

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
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plus = now.plus(30, ChronoUnit.SECONDS);

        SchedulingUtils schedulingUtils = getService(context, SchedulingUtils.class);
        schedulingUtils.addTimedTaskSchedule(new ProcessOvertimePayingOrderTask(plus));
        log.info("执行处理超时未支付订单任务结束");

    }
}
