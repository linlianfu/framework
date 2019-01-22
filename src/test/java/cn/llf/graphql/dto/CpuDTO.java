package cn.llf.graphql.dto;

import lombok.Data;

/**
 * @author eleven
 * @date 2019/1/22
 * @description
 */
@Data
public class CpuDTO {
    /**
     * cpu名字
     */
    private String name;
    /**
     * 缓存大小
     */
    private String cache;
}
