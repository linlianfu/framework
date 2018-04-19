package cn.llf.framework.async.executor.dto;

import cn.llf.framework.annotation.ExportField;
import lombok.Data;

/**
 * @author: eleven
 * @since: 2017/8/17 0017
 */
@Data
public class ImportCommonDto {
    /**
     * 行数据是否正确
     */
    private boolean correct;
    /**
     * 错误信息
     */
    @ExportField(name = "错误信息", position = 5)
    private String errorMessage;
}
