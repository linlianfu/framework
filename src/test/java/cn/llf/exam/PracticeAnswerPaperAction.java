package cn.llf.exam;

import cn.llf.framework.dao.impl.mongo.PracticeAnswerPaperDao;
import cn.llf.framework.model.mongo.PracticeAnswerPaper;
import cn.llf.framework.services.exam.dto.AnswerInfo;
import cn.llf.framework.services.exam.dto.ExamObjectDto;
import cn.llf.framework.services.exam.dto.PracticeExam;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * @author: eleven
 * @since : 2018/4/17 11:55
 * @description:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:exam.xml")
public class PracticeAnswerPaperAction {

    @Autowired
    PracticeAnswerPaperDao practiceAnswerPaperDao;

    /**
     * 为用户添加一份答卷
     */
    @Test
    public void addPracticeAnswer(){
        log.info("1111");

        PracticeAnswerPaper practiceAnswerPaper = new PracticeAnswerPaper();
        practiceAnswerPaper.setId(UUID.randomUUID().toString());
        practiceAnswerPaper.setHasPracticeNum(1);
        practiceAnswerPaper.setLimitPracticeNum(true);
        practiceAnswerPaper.setPracticeNum(3);
        practiceAnswerPaper.setUserId(UUID.randomUUID().toString().replaceAll("-",""));

        List<ExamObjectDto> examObjectDtoList = new ArrayList<>();
        examObjectDtoList.add(new ExamObjectDto(UUID.randomUUID().toString().replaceAll("-",""),"schemeId"));
        examObjectDtoList.add(new ExamObjectDto(UUID.randomUUID().toString().replace("-",""),"courseId"));
        practiceAnswerPaper.setExamObjectDtoList(examObjectDtoList);

        PracticeExam practiceExam = new PracticeExam();
        practiceAnswerPaper.setPracticeExam(practiceExam);

        practiceExam.setId(UUID.randomUUID().toString());
        practiceExam.setName("4月19号测试卷");
        practiceExam.setPassScore(60);
        practiceExam.setTotalScore(100);

        AnswerInfo answerInfo = new AnswerInfo();
        answerInfo.setComplete(true);
        answerInfo.setCorrectCount(18);
        answerInfo.setFailCount(2);
        answerInfo.setScore(90);
        answerInfo.setCompleteTime(new Date());
        practiceAnswerPaper.setAnswerInfoList(Arrays.asList(answerInfo));
        practiceAnswerPaperDao.save(practiceAnswerPaper);
    }

    /**
     * 搜索试卷
     */
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

    /**
     * 添加一份答题记录卷
     */
    @Test
    public void addAnswerRecord(){
        AnswerInfo answerInfo = new AnswerInfo();
        answerInfo.setScore(85);
        answerInfo.setId(UUID.randomUUID().toString().replaceAll("-",""));
        answerInfo.setFailCount(3);
        answerInfo.setCorrectCount(17);
        answerInfo.setComplete(true);
        answerInfo.setCompleteTime(new Date());

        Criteria criteria = Criteria.where("_id").is("55063bc1-d256-4ab4-9d75-279469d384c8");
        Query  query = new Query(criteria);
        Update update = new Update();
        update.addToSet("answerInfoList",answerInfo);
        WriteResult result = practiceAnswerPaperDao.getMt().upsert(query,update,PracticeAnswerPaper.class);
    }

    /**
     * 删除一份答题记录卷
     */
    @Test
    public void deleteAnswerRecord(){
        Criteria criteria = Criteria.where("_id").is("55063bc1-d256-4ab4-9d75-279469d384c8");
        Update update = new Update();
        update.pull("answerInfoList",new BasicDBObject("_id","1fc6baca078a4ceab7eb2703ad5f32eb"));
        int result = practiceAnswerPaperDao.getMt().updateFirst(new Query(criteria),update,PracticeAnswerPaper.class).getN();
        log.info("更新数量：{}",result);
    }

}
