
**1.zuul**<br><br>
  _Zuul的主要功能是路由转发和过滤器。<br>
  路由功能是微服务的一部分，比如／api/user转发到到user服务，/api/shop转发到到shop服务。<br>
  zuul默认和Ribbon结合实现了负载均衡的功能。<br><br>_
**2.springboot运行zuul时提示counterFactory could not be registered** <br><br>
    _错误原因：springboot 2.1.0版本不兼容<br>
    解决方法：更换springboot版本为2.0.5后可以正常运行<br>_

    