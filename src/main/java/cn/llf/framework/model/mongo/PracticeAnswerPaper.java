package cn.llf.framework.model.mongo;

import cn.llf.framework.services.exam.dto.AnswerInfo;
import cn.llf.framework.services.exam.dto.ExamObjectDto;
import cn.llf.framework.services.exam.dto.PracticeExam;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: eleven
 * @date : 2018/4/17 11:43
 * @description:
 */
@Data
public class PracticeAnswerPaper implements Serializable{

    private String id;

    private String userId;

    private PracticeExam practiceExam;

    List<AnswerInfo> answerInfoList;

    private List<ExamObjectDto> examObjectDtoList;
    /**
     * 是否限制练习次数true限制，false，不限
     */
    private boolean limitPracticeNum;

    /**
     * 限制的练习次数
     */
    private int practiceNum;
    /**
     * 已测验次数
     */
    private int hasPracticeNum;
    /**
     * 历史最高分
     */
    private Double historyBestScore;

    private String createTime;

}
