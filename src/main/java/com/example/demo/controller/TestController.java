package com.example.demo.controller;

import com.example.demo.service.BuyService;
import com.example.demo.util.ResultWapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/buy")
    public ResultWapper buy(@RequestParam("buyNum") long buyNum) {
        if (buyNum <= 0) return ResultWapper.error("购买数量必须大于0");
        return buyService.buy(1L, buyNum);
    }
}
