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

    @RequestMapping("/hi")
    public String hi(@RequestParam("name") String name){
        return helloRmote.hi(name);
    }

}
