package cn.llf.framework.model;

import cn.llf.framework.services.unit.dto.Attachment;
import cn.llf.framework.services.unit.dto.District;
import cn.llf.framework.services.unit.dto.UnitAffiliation;
import cn.llf.framework.services.unit.dto.UnitManager;
import lombok.Data;

import java.util.List;

/**
 * Author：calvin
 * Date:  2017/10/21 0021
 */
@Data
public class Unit {
    /**
     * 单位ID
     */
    private String unitId;

    /**
     * 单位名称
     */
    private String name;

    /**
     * 组织机构证
     */
    private String organizationCode;

    /**
     * 单位性质
     */
    private String unitAttribute;

    /**
     * 所属行业名称
     */
    private String industryName;

    /**
     * 单位所属地区，非所属辖区
     */
    private District physicalDistrict;

    /**
     * 单位地址
     */
    private String address;

    /**
     * 单位联系电话
     */
    private String phone;

    //=========================  以下属性可以开关控制获取  ===============================//
    /**
     * 单位级别
     */
    private String unitLevel;

    /**
     * 单位附件
     */
    private List<Attachment> attachments;

    /**
     * 所属辖区/管理辖区
     */
    private District jurisdiction;

    /**
     * 单位归属信息
     */
    private UnitAffiliation affiliation;

    /**
     * 内置的超级管理员
     */
    private UnitManager internalManager;

    /**
     * 管理员数量
     */
    private long managerCount;
}
