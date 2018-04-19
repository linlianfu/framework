package cn.llf.framework.services.exam.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author: eleven
 * @since: 2018/4/19 14:17
 * @description: 描述学员每次练习结果信息
 */
@Data
public class AnswerInfo implements Serializable {

    private String id  = UUID.randomUUID().toString().replaceAll("-","");

    private boolean complete;

    private int correctCount;

    private int failCount;

    private double score;

    private Date completeTime;
}
