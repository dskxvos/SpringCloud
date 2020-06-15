package com.sakura.servicefeign.ribbon;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class MyRule implements IRule {

    private ILoadBalancer iLoadBalancer;

    private Server choose(ILoadBalancer iLoadBalancer,Object o){

        Random random = new Random();

        if (iLoadBalancer == null){
            return null;
        }else {
            Server server = null;
            while (null == server){

                if (Thread.interrupted()){
                    return null;
                }

                List<Server> enableServer = iLoadBalancer.getReachableServers();
                List<Server> allServer = iLoadBalancer.getAllServers();

                if (0 == allServer.size()  ){
                    return null;
                }else {
                    //TODO 自己的规则
                    server = enableServer.get(random.nextInt(enableServer.size()-1));
                    if (null == server){
                        System.out.println("server不存在");
                        Thread.yield();
                    }else {
                        if (server.isAlive()){
                            System.out.println(server.getHostPort());
                            return server;
                        }else {
                            System.out.println("server不可用");
                            Thread.yield();
                        }
                    }


                }
            }
            return server;
        }
    }

    @Override
    public Server choose(Object o) {
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.iLoadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.getLoadBalancer();
    }
}
