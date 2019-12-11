package com.example.demo.config;

import com.example.demo.common.quartz.SchedulingUtils;
import com.example.demo.common.quartz.order.ProcessOvertimePayingOrderTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 描述:初始化任务
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/12/11
 */
@Configuration
@Slf4j
public class InitTask {
    @Inject
    private SchedulingUtils schedulingUtils;

    @PostConstruct
    public void initProcessOverTimeOrderTask() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plus = now.plus(30, ChronoUnit.SECONDS);

        schedulingUtils.addTimedTaskSchedule(new ProcessOvertimePayingOrderTask(plus));
      log.info("程序启动后添加超时订单处理任务成功!!!");
    }

}
