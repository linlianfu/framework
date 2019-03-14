package cn.llf.graphql.dto;

import lombok.Data;

/**
 * @author eleven
 * @date 2019/1/22
 * @description
 */
@Data
public class MemoryDTO {
    /**
     * 内存名字
     */
    private String name;
    /**
     * 内存大小
     */
    private String size;
}
