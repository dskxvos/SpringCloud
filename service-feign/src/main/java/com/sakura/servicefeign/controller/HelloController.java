package com.sakura.servicefeign.controller;

import com.sakura.servicefeign.remote.HelloRmote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private HelloRmote helloRmote;

    //如果类上存在RequestMapping 需在此处把值也添加上去
    @RequestMapping("/hi")
    public String hi(@RequestParam("name") String name){
        return helloRmote.hi(name);
    }

}
