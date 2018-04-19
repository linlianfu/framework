package cn.llf.framework.async.executor.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @author: eleven
 * @since: 2017/8/17 0017
 */
@NoArgsConstructor
@Data
public class ImportCol implements Serializable {

    public ImportCol(int index, String content) {
        this.index = index;
        this.content = content;
    }

    public ImportCol(int index, boolean flag, String message) {
        this.index = index;
        this.flag = flag;
        this.message = message;
    }

    /**
     * 列下标
     */
    private int index;
    /**
     * 列内容
     */
    private String content;
    /**
     * 列数据是否正常 true：正常 false：异常
     */
    private boolean flag = true;
    /**
     * 列数据异常信息
     */
    private String message;
}
