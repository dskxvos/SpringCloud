package com.sakura.servicefeign.remote;

import com.sakura.servicefeign.remote.hystricImpl.HelloHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * value：表示需要访问的服务在注册中心的名字
 * feign整合了熔断机制  开启配置 feign.hystrix.enabled=true
 *  注解里申明 fallback的类  该类需要实现当前接口 并且注入spring的IOC容器 即可
 * 方法体内为正常请求出现错误时返回的结果
 */
@FeignClient(value = "eureka-client-hi",fallback = HelloHystric.class)
public interface HelloRmote {
    @RequestMapping(value = "/hi")
    String hi(@RequestParam("name") String name);
}
