package cn.llf.framework.services.exam.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: calvin
 * @Since: 2018/3/5 20:54
 * @Description:
 */
@Data
public class QuestionBaseDto {
    /**
     * 用户所属平台ID
     */
    private String platformId;
    /**
     * 用户所属平台版本ID
     */
    private String platformVersionId;

    /**
     * 用户所属项目ID
     */
    private String projectId;

    /**
     * 用户所属子项目ID
     */
    private String subProjectId;

    /**
     * 用户所属单位ID
     */
    private String unitId;

    /**
     * 用户所属组织机构ID
     */
    private String organizationId;

    /**试题ID*/
    private String id;

    /**试题类型 参考 QuestionType
     * 0:未知 1：判断 2：单选 3：多选
     * */
    private int questionType;

    /**题目*/
    private String topic;

    /**试题解析*/
    private String description;

    /**试题范围 参考 QuestionRange
     * 1:考试 2:练习 4:模拟
     * */
    private int questionRange;

    /**来源类型 参考 SourceType
     * 1:共享 2：个人 3：导入
     * */
    private int sourceType;

    /**来源试题id*/
    private String sourceQuestionId;

    /**上传试题id*/
    private String uploadQuestionId;

    /**所有者*/
    private String ownerId;

    /**试题库id*/
    private String libraryId;

    /**自定义业务对象id*/
    private String objectId;

    /**是否可用*/
    private boolean enabled;

    /**创建时间*/
    private String createTime;

    /**创建人*/
    private String createUserId;

    /**最后修改时间*/
    private String lastChangeTime;

    /**自定义业务对象**/
    List<ExamObjectDto> examObjects;

    /**试题模式 参考 QuestionMode
     * 1：简单 2：一般 3：困难
     * */
    private int mode;
}
