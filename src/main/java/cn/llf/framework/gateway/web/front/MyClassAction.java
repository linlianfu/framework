package cn.llf.framework.gateway.web.front;

import cn.llf.framework.gateway.commons.AbstractFrameWorkAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.llf.ability.course.south.service.IUserSelectCourseService;

/**
 * @author: eleven
 * @since: 2018/4/18 23:05
 * @description:
 */
@Slf4j
@Controller
@RequestMapping("myClass")
public class MyClassAction extends AbstractFrameWorkAction{

    @Autowired
    IUserSelectCourseService userSelectCourseService;


    /**
     * 用户选课
     * @return
     */
    @ResponseBody
    @RequestMapping("selectCourse")
    public boolean selectCourse(){


        return  false;
    }


}
