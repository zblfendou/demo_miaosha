package com.example.demo.common.quartz.test;

import com.example.demo.common.quartz.TimedTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/12/3
 */
@Slf4j
public class TestTask extends TimedTask {
    private long id;

    public TestTask() {
    }

    public TestTask(LocalDateTime startTime, long id) {
        super(startTime);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getJobName() {
        return "TASK_TEST_" + id;
    }

    @Override
    public String getJobGroup() {
        return "TEST";
    }

    @Override
    public void executeTask(ApplicationContext context) {

        log.info("我执行了，你们都看看吧。");
    }
}
