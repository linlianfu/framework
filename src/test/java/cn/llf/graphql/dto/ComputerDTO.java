package cn.llf.graphql.dto;

import lombok.Data;

import java.util.List;

/**
 * @author eleven
 * @date 2019/1/22
 * @description
 */
@Data
public class ComputerDTO {
    /**
     * 电脑名字
     */
    private String name;
    /**
     * 电脑cpu
     */
    private CpuDTO cpu;
    /**
     * 电脑内存条
     */
    private List<MemoryDTO> memoryList;
}
