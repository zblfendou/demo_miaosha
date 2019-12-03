package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 描述:订单配置信息
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/12/3
 */
@Component
@ConfigurationProperties(prefix = "sysconfig.order")
@PropertySource(value = "classpath:config/order.properties")
public class OrderConfig {

    /*支付中订单超时时间*/
    private String overtimeMinute;

    public String getOvertimeMinute() {
        return overtimeMinute;
    }

    public void setOvertimeMinute(String overtimeMinute) {
        this.overtimeMinute = overtimeMinute;
    }
}
