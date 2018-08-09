package cn.llf.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: eleven
 * @since: 2018/7/12 21:46
 * @description:
 */
@Data
@Slf4j
public class ContextFilter extends GenericFilterBean {



    public String name;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        log.info("ContextFilter过滤器start");
        log.info("参数name：{}",name);
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        //获取当前请求的域名
        String domain   = httpRequest.getServerName();
        //获取当前请求的端口
        int serverPort    = httpRequest.getServerPort();
        //获取当前请求的url
        String requestURI = httpRequest.getRequestURI();
        //获取当前请求的ip
        String ip = httpRequest.getRemoteAddr();

        response.setCharacterEncoding("UTF-8");
        //继续访问往后的web资源，如果缺少此调用链，则不会访问后续的web！！！！
        chain.doFilter(request,response);
//        log.info("ContextFilter过滤器end");
    }
}
