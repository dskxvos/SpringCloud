package com.sakura.servicefeign.remote.hystricImpl;

import com.sakura.servicefeign.remote.HelloRmote;
import org.springframework.stereotype.Component;

@Component
public class HelloHystric implements HelloRmote {

    @Override
    public String hi(String name) {
        return "hi "+name+", the server have a erro";
    }
}
