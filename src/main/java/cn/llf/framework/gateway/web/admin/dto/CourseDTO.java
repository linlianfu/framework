package cn.llf.framework.gateway.web.admin.dto;

import cn.eleven.common.bean.superbean.ResponseBean;
import lombok.Data;

/**
 * @author eleven
 * @date 2019/6/22
 * @description
 */
@Data
public class CourseDTO implements ResponseBean {
    /**
     * 课程id
     */
    private String id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 是否启用
     */
    private boolean enable;
}
