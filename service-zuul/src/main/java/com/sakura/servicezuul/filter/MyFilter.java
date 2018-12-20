package com.sakura.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Zuul的路由过滤器
 */
@Component
public class MyFilter extends ZuulFilter {

    private  final Logger logger = LoggerFactory.getLogger(MyFilter.class);
    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     *
     * 此处我们选择路由之前
     */
    @Override
    public String filterType() {
        return "pre";
    }

    //返回一个int值来指定过滤器的执行顺序，不同的过滤器允许返回相同的数字。
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 可以根据参数 时间之类的因为判断是否要过滤 ture过滤 false不过滤
     * 当前设置不判断 直接过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。本例中，我们让它打印了请求的HTTP方法以及请求的地址。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //获取当前线程的请求上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String url = request.getRequestURI();
        logger.info("RequestURL:"+url);
        String token = request.getParameter("token");
        if (null == token){
            //设置不路由响应直接返回
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try{
                requestContext.getResponse().reset();
                PrintWriter writer =  requestContext.getResponse().getWriter();
                writer.write("sorry,you don't have token!");
                writer.close();
            }catch (IOException e){
                logger.error("zuul过滤器返回时出现异常",e);
            }

        }


        return null;
    }
}
