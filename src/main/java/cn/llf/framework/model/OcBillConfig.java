package cn.llf.framework.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 创建者：   linlf
 * 创建时间： 2017/5/17
 * 描述：
 */
@Data
@Entity
@Alias("dbBillConfig")
@Table(name = "OC_BILL_CONFIG")
public class OcBillConfig implements Serializable {
    /**
     * 主键 Id
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "OBC_ID")
    private String id;
    /**
     * 是否提供发票|1：不提供 2：提供'
     */
    @Column(name = "OBC_IS_PROVIDE")
    private int isProvide;
    /**
     * 发票提供类型|0：无 1：学员自选是否需要发票 2：强制提供'
     */
    @Column(name = "OBC_PROVIDE_TYPE")
    private int provideType;
    /**
     *是否可开纸质发票|1：否 2：是
     */
    @Column(name = "OBC_PROVIDE_PAPER")
    private  int providePaper;
    /**
     *是否可开电子发票|1：否 2：是
     */
    @Column(name = "OBC_PROVIDE_ELECTRON")
    private  int provideElectron;
    /**
     *纸质发票抬头类型|0：无 1：个人 2：单位  3：个人和单位
     */
    @Column(name = "OBC_PAPER_TITLETYPE")
    private  int paperTitleType;
    /**
     *电子发票抬头类型|0：无 1：个人 2：单位 3：个人和单位
     */
    @Column(name = "OBC_ELECTRON_TITLETYPE")
    private  int electronTitleType;
    /**
     *平台ID
     */
    @Column(name = "OBC_PLM_ID")
    private  String platformId;
    /**
     *平台版本ID
     */
    @Column(name = "OBC_PVM_ID")
    private  String platformVersionId;
    /**
     *项目ID
     */
    @Column(name = "OBC_PRM_ID")
    private  String projectId;
    /**
     *子项目ID
     */
    @Column(name = "OBC_SUB_PRM_ID")
    private  String subProjectId;
    /**
     *单位ID
     */
    @Column(name = "OBC_UNIT_ID")
    private  String unitId;
    /**
     *组织机构ID
     */
    @Column(name = "OBC_ORG_ID")
    private  String organizationId;
}
