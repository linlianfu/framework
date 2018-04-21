package cn.llf.exam;

import cn.llf.framework.services.exam.dto.QuestionImportDto;
import cn.llf.framework.utils.CSVImportUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
