package cn.llf.framework.services.other.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 创建者：   linlf
 * 创建时间： 2017/11/9
 * 描述：
 */
@Data
public class DateQuery {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
}
