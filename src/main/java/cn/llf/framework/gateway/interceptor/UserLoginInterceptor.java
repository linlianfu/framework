package cn.llf.framework.gateway.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author eleven
 * @date 2018/11/15
 * @description 用户登录拦截器
 */
@Slf4j
public class UserLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("用户未登录，将返回未登录状态码");

//        HandlerMethod method = (HandlerMethod)handler;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        response.sendError(HttpServletResponse.SC_BAD_REQUEST,"用户未登录，将返回未登录状态码");
//        PrintWriter writer = response.getWriter();
//        JSONObject res = new JSONObject();
//        res.put("info","");
//        res.put("code","403");
//        writer.print(res.toString());
        return true;
    }
}
