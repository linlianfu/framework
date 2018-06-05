package cn.llf.framework.services.exam.dto;

import cn.llf.framework.annotation.ImportField;
import cn.llf.framework.async.executor.dto.ImportCommonDto;
import lombok.Data;

/**
 * @author: eleven
 * @since: 2018/4/21 11:10
 * @description:
 */
@Data
public class QuestionImportDto extends ImportCommonDto {
    /**
     * 所属题库
     */
    @ImportField(name = "所属题库")
    private String library;
    /**
     * 试题题型
     */
    @ImportField(name = "试题题型")
    private String questionType;
    /**
     *试题题目
     */
    @ImportField(name = "试题题目")
    private String title;
    /**
     *难度系数
     */
    @ImportField(name = "难度系数")
    private String grade;
    /**
     *正确答案
     */
    @ImportField(name = "正确答案")
    private String answer;
    /**
     *试题解析
     */
    @ImportField(name = "试题解析")
    private String analysis;
}
