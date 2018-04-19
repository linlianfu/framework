package cn.llf.framework.services.unit.dto;

import lombok.Data;

/**
 * @author: eleven
 * @since:  2017/10/21 0021
 */
@Data
public class District {
    /**
     * 地区类型, 1: 省; 2: 市; 3: 县;
     */
    private int type;

    /**
     * 省ID
     */
    private String provinceId;

    /**
     * 省名字
     */
    private String province;

    /**
     * 市ID
     */
    private String cityId;

    /**
     * 市名字
     */
    private String city;

    /**
     * 县ID
     */
    private String countryId;

    /**
     * 县名字
     */
    private String country;
}
