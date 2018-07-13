package cn.llf.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author: eleven
 * @since: 2018/7/12 21:46
 * @description:
 */
@Slf4j
public class ContextFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("上下文过滤器执行前的service调用");
        //继续访问往后的web资源，如果缺少此调用链，则不会访问后续的web！！！！
        chain.doFilter(request,response);
        log.info("上下文过滤器执行后的service调用");
    }
}
