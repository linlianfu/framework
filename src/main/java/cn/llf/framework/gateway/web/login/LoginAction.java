package cn.llf.framework.gateway.web.login;

import cn.llf.framework.gateway.commons.AbstractFrameWorkAction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public boolean login(HttpServletRequest request, String account, String password){

        HttpSession session = request.getSession();
        session.setAttribute("account",account);
        session.setAttribute("password",password);
        return true;

    }
    @GetMapping("doLoginOut")
    public boolean doLoginOut(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        session.removeAttribute("account");
        session.removeAttribute("password");
        return false;
    }

    @GetMapping("checkLogin")
    public boolean checkLogin(HttpServletRequest request){

        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("account");
        return StringUtils.isNotBlank(account);
    }

}
