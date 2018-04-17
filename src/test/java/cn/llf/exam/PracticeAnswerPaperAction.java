package cn.llf.exam;

import cn.llf.framework.dao.impl.mongo.PracticeAnswerPaperDao;
import cn.llf.framework.model.mongo.PracticeAnswerPaper;
import cn.llf.framework.model.mongo.PracticeExam;
import cn.llf.framework.services.exam.dto.ExamObjectDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: eleven
 * @date : 2018/4/17 11:55
 * @description:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:exam.xml")
public class PracticeAnswerPaperAction {

    @Autowired
    PracticeAnswerPaperDao practiceAnswerPaperDao;

    @Test
    public void addPracticeAnswer(){
        log.info("1111");

        PracticeAnswerPaper practiceAnswerPaper = new PracticeAnswerPaper();
        practiceAnswerPaper.setId(UUID.randomUUID().toString());
        practiceAnswerPaper.setHasPracticeNum(1);
        practiceAnswerPaper.setLimitPracticeNum(false);
        practiceAnswerPaper.setPracticeNum(3);
        practiceAnswerPaper.setUserId(UUID.randomUUID().toString());

        List<ExamObjectDto> examObjectDtoList = new ArrayList<>();
        examObjectDtoList.add(new ExamObjectDto(UUID.randomUUID().toString(),"schemeId"));
        examObjectDtoList.add(new ExamObjectDto(UUID.randomUUID().toString(),"courseId"));
        practiceAnswerPaper.setExamObjectDtoList(examObjectDtoList);

        PracticeExam practiceExam = new PracticeExam();
        practiceAnswerPaper.setPracticeExam(practiceExam);

        practiceExam.setId(UUID.randomUUID().toString());
        practiceExam.setName("测试测试");
        practiceExam.setPassScore(66);
        practiceExam.setTotalScore(77);

        practiceAnswerPaperDao.save(practiceAnswerPaper);
    }


    @Test
    public void getPracticeAnswerPaper(){
        List<ExamObjectDto> examObjectDtoList = new ArrayList<>();
        examObjectDtoList.add(new ExamObjectDto("74f2378a-c417-4c4c-bc3c-4a3a74bd425c","schemeId"));
        Criteria criteria = Criteria.where("examObjectDtoList").all(examObjectDtoList);

        List<PracticeAnswerPaper> list = (List<PracticeAnswerPaper>)practiceAnswerPaperDao.findByQuery(new Query(criteria));
        if (CollectionUtils.isNotEmpty(list)){
            list.forEach(p->{
                log.info(p.toString());
            });
        }

    }




}
