package cn.llf.framework.gateway.web.login;

import cn.llf.framework.gateway.commons.AbstractFrameWorkAction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        session.setMaxInactiveInterval(10);

        Cookie[] cookies = request.getCookies();
        log.info("sessionId:{}",sessionId);
        boolean has = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("sessionId")){
                has = true;
            }
        }
        if (!has){
//            
            log.info("cookie过期，重新添加cookie");
            Cookie cookie = new Cookie("sessionId",sessionId);
            cookie.setMaxAge(15);
            response.addCookie(cookie);
        }
        Cookie cookie = new Cookie("addCookie",sessionId);
        cookie.setMaxAge(15);
        response.addCookie(cookie);
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

}
