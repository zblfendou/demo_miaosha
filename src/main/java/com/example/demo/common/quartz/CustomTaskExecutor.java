package com.example.demo.common.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.inject.Named;

@Named("customTaskExecutor")
@Slf4j
public class CustomTaskExecutor implements ApplicationContextAware {
    private ApplicationContext context;

    public void executeTask(Task task) {
        log.debug("Executing model :{}", task.getClass().getName());
        task.executeTask(context);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
