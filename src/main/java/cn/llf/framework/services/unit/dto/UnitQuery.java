package cn.llf.framework.services.unit.dto;

import lombok.Data;

/**
 * @author: eleven
 * @since:  2017/10/22 0022
 * 描述：
 */
@Data
public class UnitQuery {
    /**
     * 单位名称
     */
    private String name;
    /**
     * 组织结构代码
     */
    private String organizationCode;
    /**
     * 单位物理地区
     */
    private District physicalDistrict;
}
