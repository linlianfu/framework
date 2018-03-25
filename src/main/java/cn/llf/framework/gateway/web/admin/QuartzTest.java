package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.schedule.ExportQuestionImpl;
import priv.llf.quartz.IQuartzManager;
import priv.llf.quartz.dto.QuartzDto;
import priv.llf.quartz.impl.QuartzManagerImpl;

/**
 * @Author: calvin
 * @Since: 2018/3/25 17:32
 * @Description:
 */
public class QuartzTest {

    public static void main(String[] arg){
        IQuartzManager quartzManager = new QuartzManagerImpl();
        QuartzDto quartzDto = new QuartzDto();
        quartzDto.setIntervalMinuter(1);
        quartzDto.setName("framework");
        quartzDto.setGroup("llf");
        quartzDto.setCronExpression("0/3 * * * * ?");
        quartzDto.setJobClass(ExportQuestionImpl.class);
        quartzManager.createQuartz(quartzDto);
    }
}
