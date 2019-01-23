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
     * 主键id
     */
    private String id;
    /**
     * cpu型号类型
     */
    private int cpuType;
    /**
     * cpu名字
     */
    private String name;
    /**
     * 缓存大小
     */
    private String cache;
}
