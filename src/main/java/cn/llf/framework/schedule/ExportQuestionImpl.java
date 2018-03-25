package cn.llf.framework.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import priv.llf.quartz.UserJobExecutor;

/**
 * @Author: calvin
 * @Since: 2018/3/25 17:34
 * @Description:
 */
public class ExportQuestionImpl implements UserJobExecutor{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("开始导出试题。。。");
    }
}
