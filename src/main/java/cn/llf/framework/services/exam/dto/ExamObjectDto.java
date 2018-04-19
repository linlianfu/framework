package cn.llf.framework.services.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: eleven
 * @since: 2018/3/5 20:55
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamObjectDto {
    /**自定义业务对象id**/
    private String objectId;
    /**自定义业务对象类型**/
    private String type;
}
