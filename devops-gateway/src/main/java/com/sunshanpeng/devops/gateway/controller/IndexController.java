package com.sunshanpeng.devops.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    @GetMapping("/")
    public Map<String,Object> index(){
        Map<String,Object> map = new HashMap<>();
        map.put("success", true);
        map.put("model", "ok");
        return map;
    }

    @GetMapping("/defaultfallback")
    public Map<String,Object> defaultfallback(){
        Map<String,Object> map = new HashMap<>();
        map.put("success", false);
        map.put("errorMessage", "服务降级");
        return map;
    }
}
