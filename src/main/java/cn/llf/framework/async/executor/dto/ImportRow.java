package cn.llf.framework.async.executor.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Author：calvin
 * Date: 2017/8/17 0017
 */
@Data
public class ImportRow implements Serializable {

    public ImportRow(int index) {
        this.index = index;
    }

    /**
     * 行下标
     */
    private int index;
    /**
     * 行数据是否正常 true：正常 false：异常
     */
    private boolean flag = true;
    /**
     * 行数据异常信息
     */
    private String message;
    /**
     * 当前行列数据
     */
    private List<ImportCol> importCols = new ArrayList<>();
}
