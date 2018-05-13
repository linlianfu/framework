package cn.llf.exam;

import cn.llf.framework.async.executor.QuestionImportJobExecutor;
import cn.llf.framework.services.exam.dto.QuestionImportDto;
import cn.llf.framework.utils.CSVImportUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import priv.llf.job.client.dto.args.UserJobDetailDto;
import priv.llf.job.client.service.UserJobClient;

import java.util.List;

/**
 * @author: eleven
 * @since: 2018/4/21 11:15
 * @description:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:exam.xml")
public class QuestionImportAction {

    @Autowired
    UserJobClient userJobClient;
    @Test
    public void questionImport(){
        String filePath = "E:\\project\\framework\\artifact\\radio.csv";
        CSVImportUtils csvImportUtils = new CSVImportUtils();
        try {
            List<QuestionImportDto> list = csvImportUtils.listBeanDao(filePath, QuestionImportDto.class);
            list.forEach(p->{
                log.info(p.toString());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void questionImportByJob(){
        UserJobDetailDto userJobDetailDto = new UserJobDetailDto();
        userJobDetailDto.setJobClass(QuestionImportJobExecutor.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("filePath","E:\\project\\framework\\artifact\\radio.csv");
        userJobDetailDto.setJobDataMap(jobDataMap);
        userJobDetailDto.setName("importQuestion");
        userJobDetailDto.setGroup("exam");
        try {
            userJobClient.execute(userJobDetailDto);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
//        Thread.currentThread().
    }

}
