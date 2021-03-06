package cn.llf.exam;

import cn.llf.framework.dao.impl.mongo.PracticeAnswerPaperDao;
import cn.llf.framework.model.mongo.PracticeAnswerPaper;
import cn.llf.framework.services.exam.dto.AnswerInfo;
import cn.llf.framework.services.exam.dto.ExamObjectDto;
import cn.llf.framework.services.exam.dto.PracticeExam;
import com.mongodb.BasicDBObject;
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

    @Test
    public void listRemove(){
        List<String> list = new ArrayList<>();
        String temp = "2";
        list.add("1");
        list.add(temp);
        list.add(temp);
//        Iterator<String> iterator = list.iterator();

        //执行通过
//        for (;iterator.hasNext();){
//            System.out.println(iterator.next());
//            iterator.remove();
//        }
//        System.out.println("iterator循环删除后的集合长度："+list.size());



        //增强for循环报错
        //java.util.ConcurrentModificationException

//        for (String s : list) {
//            list.remove(s);
//            System.out.println(list.size());
//        }

        //执行异常：java.util.ConcurrentModificationException
//        list.forEach(p->{
//            list.remove(p);
//        });

        Set<String> set = new HashSet();
        set.add(temp);
        set.add(temp);
        set.add(temp);
//        System.out.println(set.size());
    }



    /**
     * 为用户添加一份答卷
     */
    @Test
    public void addPracticeAnswer(){
        PracticeAnswerPaper practiceAnswerPaper = new PracticeAnswerPaper();
        practiceAnswerPaper.setId(UUID.randomUUID().toString());
        practiceAnswerPaper.setHasPracticeNum(1);
        practiceAnswerPaper.setLimitPracticeNum(true);
        practiceAnswerPaper.setPracticeNum(3);
        practiceAnswerPaper.setUserId(UUID.randomUUID().toString().replaceAll("-",""));
        practiceAnswerPaper.setPass(false);

        List<ExamObjectDto> examObjectDtoList = new ArrayList<>();
        examObjectDtoList.add(new ExamObjectDto(UUID.randomUUID().toString().replaceAll("-",""),"schemeId"));
        examObjectDtoList.add(new ExamObjectDto(UUID.randomUUID().toString().replace("-",""),"learningId"));
        examObjectDtoList.add(new ExamObjectDto(UUID.randomUUID().toString().replace("-",""),"courseId"));
        practiceAnswerPaper.setExamObjectDtoList(examObjectDtoList);

        PracticeExam practiceExam = new PracticeExam();
        practiceExam.setId(UUID.randomUUID().toString());
        practiceExam.setName("4月19号测试卷2");
        practiceExam.setPassScore(70);
        practiceExam.setTotalScore(100);

        practiceAnswerPaper.setPracticeExam(practiceExam);

        AnswerInfo answerInfo = new AnswerInfo();
        answerInfo.setComplete(true);
        answerInfo.setCorrectCount(11);
        answerInfo.setFailCount(9);
        answerInfo.setScore(55);
        answerInfo.setCompleteTime(new Date());
        practiceAnswerPaper.setAnswerInfoList(Arrays.asList(answerInfo));
        practiceAnswerPaper.setHistoryBestScore(55);

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
        answerInfo.setScore(95);
        answerInfo.setId(UUID.randomUUID().toString());
        answerInfo.setFailCount(1);
        answerInfo.setCorrectCount(19);
        answerInfo.setComplete(false);
        answerInfo.setCompleteTime(new Date());

        Criteria criteria = Criteria.where("_id").is("706b651f-2a5b-4254-9ba1-629c2c7874f5");
        Query  query = new Query(criteria);
        int result = practiceAnswerPaperDao.addElement(query,"answerInfoList",answerInfo);
        log.info("执行数量：{}",result);
    }

    /**
     * 删除一份答题记录卷
     */
    @Test
    public void deleteAnswerRecord(){
        Criteria criteria = Criteria.where("_id").is("706b651f-2a5b-4254-9ba1-629c2c7874f5");
        int result = practiceAnswerPaperDao.deleteElement(new Query(criteria),"answerInfoList",new BasicDBObject("id","538cab43-e519-477c-8014-03832d429f26"));
        log.info("更新数量：{}",result);
    }

    /**
     * 更新练习次数
     */
    @Test
    public void updatePracticeNum(){
        Criteria criteria = Criteria.where("_id").is("55063bc1-d256-4ab4-9d75-279469d384c8");
        Update update = new Update();
        update.set("practiceNum",-1);
        long result = practiceAnswerPaperDao.getMt().updateFirst(new Query(criteria),update,PracticeAnswerPaper.class).getN();
        log.info("更新数量：{}",result);
    }

}
