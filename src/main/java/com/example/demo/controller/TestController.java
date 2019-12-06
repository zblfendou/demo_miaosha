package com.example.demo.controller;

import com.example.demo.common.quartz.SchedulingUtils;
import com.example.demo.common.quartz.order.ProcessOvertimePayingOrderTask;
import com.example.demo.service.BuyService;
import com.example.demo.service.GoodService;
import com.example.demo.util.ResultWapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 描述:
 *
 * @author: 张彬雷 [zhangbinlei@xinnet.com]
 * @since: 2019/12/2
 */
@RestController
public class TestController {
    @Autowired
    private BuyService buyService;
    @Inject
    private SchedulingUtils schedulingUtils;
    @Inject
    private GoodService goodService;

    @RequestMapping("/buy")
    public ResultWapper buy(@RequestParam("buyNum") long buyNum) {
        if (buyNum <= 0) return ResultWapper.error("购买数量必须大于0");
        return buyService.buy(1L, buyNum);
    }

    @RequestMapping("/addOrderTask")
    public ResultWapper addOrderTask() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plus = now.plus(180, ChronoUnit.SECONDS);

        schedulingUtils.addTimedTaskSchedule(new ProcessOvertimePayingOrderTask(plus));
        return ResultWapper.success();
    }


}
