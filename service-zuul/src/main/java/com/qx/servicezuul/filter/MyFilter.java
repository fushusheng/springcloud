package com.qx.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyFilter extends ZuulFilter {
    /* filterType:返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
        pre:路由之前 FilterConstants.PRE_TYPE
        routing：路由之时 FilterConstants.ROUTE_TYPE
        post：路由之后 FilterConstants.POST_TYPE
        error:发送错误调用 FilterConstants.ERROR_TYPE
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
    /* filterOrder:过滤顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }
    /* shouldFilter:这里可以写逻辑判断，是否要过滤，为true时就代表要过滤
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }
    /*
    run :过滤器的具体逻辑，包括查sql、nosql去判断请求到底有没有权限访问
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getParameter("token");
        if(token==null){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            currentContext.setResponseBody("token is empty");
        }
        return null;
    }
}
