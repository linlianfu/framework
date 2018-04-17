package cn.llf.framework.model.mongo;

import cn.llf.framework.services.exam.dto.ExamObjectDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: eleven
 * @date : 2018/4/17 11:48
 * @description:
 */
@Data
public class PracticeExam implements Serializable {

    private String id;

    private double totalScore;

    private double passScore;

    private String name;

    private List<ExamObjectDto> examObjectDtoList;
}
