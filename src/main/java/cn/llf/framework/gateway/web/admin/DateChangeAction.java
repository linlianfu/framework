package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.services.other.dto.DateQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创建者：   linlf
 * 创建时间： 2017/11/9
 * 描述：
 */
@Slf4j
@Controller
@RequestMapping("dateChange")
public class DateChangeAction {

    @ResponseBody
    @RequestMapping("getDate")
    public String getDate(DateQuery query){
        log.info(query.getStartDate().toString());
        return query.toString();
    }
}
