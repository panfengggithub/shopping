package com.tuling.shopping.controller;

import com.tuling.shopping.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 潘峰
 * @create 2022/5/13 20:57
 */
@RestController
@RequestMapping("/shopping")
public class Shopping {
    @GetMapping("/test")
    public AjaxResult test(){
        String hello = "helloWord";
        return AjaxResult.success("查询成功");
    }
}
