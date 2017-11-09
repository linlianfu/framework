package cn.llf.framework.model.enums;

import lombok.Getter;

/**
 * Author：calvin
 * Date:  2017/8/19 0019
 */
public enum Paper {
    LIBRARY("题库卷");
    @Getter
    private String type;
    Paper(String type) {
        this.type = type;
    }
}
