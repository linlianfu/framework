package cn.llf.framework.gateway.web.login;

import cn.llf.framework.gateway.commons.AbstractFrameWorkAction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: eleven
 * @since: 2018/7/13 21:19
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("login")
public class LoginAction extends AbstractFrameWorkAction {


    @GetMapping("login")
    public boolean login(HttpServletRequest request,HttpServletResponse response,String account, String password){
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        log.info("sessionId:{}",sessionId);
//        session.setMaxInactiveInterval(10);
        boolean has = false;
        if (cookies != null && cookies.length != 0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("test")){
                    has = true;
                }
            }
        }
        if (!has){
            log.info("不存在指定key的cookie，系第一次请求或者cookie过期，故重新添加cookie");
            Cookie cookie = new Cookie("test","my name is server");
            cookie.setMaxAge(20);
            response.addCookie(cookie);
        }else {
            log.info("指定key的cookie尚未过期");
        }
        return true;
    }
    @GetMapping("doLoginOut")
    public boolean doLoginOut(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        session.removeAttribute("account");
        session.removeAttribute("password");
        session.invalidate();
        return false;
    }

    @GetMapping("checkLogin")
    public boolean checkLogin(HttpServletRequest request){

        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("account");
        return StringUtils.isNotBlank(account);
    }


    @GetMapping("forwardToCheckLogin")
    public String forwardToCheckLogin(HttpServletRequest request,HttpServletResponse response){
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/web/admin/course/listCourseByType");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            log.info("跳转失败");
            e.printStackTrace();
        }
        try {
            response.sendRedirect("/web/admin/bill/listBill");
        } catch (IOException e) {
            log.info("跳转失败");
            e.printStackTrace();
        }
        return "跳转成功";
    }
}
