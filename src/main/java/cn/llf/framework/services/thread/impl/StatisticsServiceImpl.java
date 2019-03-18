package cn.llf.framework.services.thread.impl;

import cn.llf.framework.services.thread.IStatisticsService;
import cn.llf.framework.services.thread.support.ThreadProcessTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author eleven
 * @date 2019/3/18
 * @description
 */
@Service("statisticsService")
public class StatisticsServiceImpl implements IStatisticsService {

    @Autowired
    TaskExecutor taskExecutor;


    @Override
    public String print(String message) {

        for (int i = 0;i<=10;i++){
            taskExecutor.execute(new ThreadProcessTask());
        }
        return "执行完毕";
    }
}
