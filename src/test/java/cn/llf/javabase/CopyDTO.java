package cn.llf.javabase;

import lombok.Data;

/**
 * @author eleven
 * @date 2019/7/24
 * @description
 */
@Data
public class CopyDTO implements Cloneable{
    private int i = 2;
    /**
     * 测试复制
     */
    CodeDto codeDto;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
