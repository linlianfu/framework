package cn.llf.framework.async.executor;

import cn.llf.framework.services.exam.dto.QuestionImportDto;
import cn.llf.framework.utils.CSVImportUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import priv.llf.job.client.dto.response.UserJobExecuteResult;
import priv.llf.job.client.support.UserJobExecutor;
import priv.llf.job.serve.dto.args.UserJob;

import java.util.List;

/**
 * @author: eleven
 * @since: 2018/4/21 14:30
 * @description:
 */
@Slf4j
public class QuestionImportJobExecutor implements UserJobExecutor{


    @Override
    public UserJobExecuteResult execute(UserJob userJob, JobDataMap jobDataMap) {
        UserJobExecuteResult userJobExecuteResult = new UserJobExecuteResult();
        log.info("异步任务开始导入试题。。。。。");
        String filePath = jobDataMap.get("filePath").toString();
        CSVImportUtils csvImportUtils = new CSVImportUtils();
        try {
            List<QuestionImportDto> list = csvImportUtils.listBeanDao(filePath, QuestionImportDto.class);
            list.forEach(p->{
                log.info(p.toString());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userJobExecuteResult;
    }
}
