package cn.llf.framework.utils;

import cn.llf.framework.annotation.ImportField;
import cn.llf.framework.async.executor.dto.ImportCol;
import cn.llf.framework.async.executor.dto.ImportCommonDto;
import cn.llf.framework.async.executor.dto.ImportRow;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: eleven
 * @since: 2017/8/17 0017
 */
@Slf4j
public class CSVImportUtils {

    /**
     * 将excel数据转换为对应的DAO
     * @param filePath     文件的全路径
     * @param targetClass  属性数量必须大于等于表格列数，但是前面的属性注解所对的name必须和表格标题一致，额外需要的属性必须放在最后面，否则不会解析数据
     * @throws Exception IO流读取异常
     * @return 返回不含元素的集合可能两种情况
     *          1.空表格，读取不到有效数据
     *          2.表格标题和导入的实体类的属性的name的不一致。
     */
    public<T extends ImportCommonDto> List<T> listBeanDao(String filePath, Class<T> targetClass) throws  Exception{
        List<T> listTarget = new ArrayList<>();
        List<ImportRow> rows = this.listExcelDataModel(filePath);
        if (rows == null || rows.size() <= 1){
            log.warn(">>>>>获取不到有效数据");
        }else{
            log.info(">>>>>开始DAO标题和表格标题校验。。。。");
            boolean titleCheckResult = this.titleCheck(targetClass,rows.get(0).getImportCols());
            if (titleCheckResult){
                log.info(">>>>>共获取到【{}】条有效数据",rows.size()-1);
                rows.remove(0);//移除标题行
                log.info(">>>>>开始模型转换。。。。。");
                for (ImportRow importRow : rows){
                    T target = BeanUtils.instantiate(targetClass);
                    target.setCorrect(true);
                    Field[] fields = target.getClass().getDeclaredFields();
                    List<ImportCol> cols = importRow.getImportCols();
                    for (int i = 0;i<fields.length;i++){
                        ImportField importFields = fields[i].getAnnotation(ImportField.class);
                        //代码覆盖率检查会默认给model增加一个字段，导致数组越界，因此以字段是否有注解来判断是否本地申明的字段
//                        fields[i].isSynthetic()  可以用此方法判断字段是否是第三方框架生成的符合字段
                        if (importFields != null){
                            Class typeClass = fields[i].getType();
                            fields[i].setAccessible(true);
                            if (typeClass  ==  BigDecimal.class){
                                BigDecimal bigDecimal = new BigDecimal(cols.get(i).getContent());
                                fields[i].set(target,bigDecimal);
                            }else {
                                fields[i].set(target,cols.get(i).getContent());
                            }
                        }
                    }
                    listTarget.add(target);
                }
            }
        }
        return listTarget;
    }
    /**
     * 获取excel数据
     * @param filePath
     * @return
     * @throws Exception
     */
    public List<ImportRow> listExcelDataModel (String filePath) throws Exception{
        log.info(">>>>>开始数据解析。。。。。");
        List<ImportRow> rows = new ArrayList<>();
        List<String> lineList = new ArrayList<String>();
        List<String> unitList = new ArrayList<>();
       @Cleanup BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
        String stemp;
        while ((stemp = br.readLine()) != null) {
            lineList.add(stemp);
        }
        int rowNum = lineList.size();
        int colNum = this.getColNum(lineList);

        for(int i=0;i<rowNum;i++){
            ImportRow row = new ImportRow(i);
            List<ImportCol> cols = new ArrayList<>();
            for(int j=0;j<colNum;j++){
                unitList.add(this.getSingleUnitValue(lineList,i,j));
                ImportCol col = new ImportCol(j,this.getSingleUnitValue(lineList,i,j));
                cols.add(col);
            }
            row.setImportCols(cols);
            rows.add(row);
        }
        return  rows;
    }
    /**
     * 获取某个单元格
     * @param row
     * @param col
     * @return
     */
    public String getSingleUnitValue(List<String> lineList,int row, int col) {
        String unitValue = null;
        int colnum = this.getColNum(lineList);
        if (colnum > 1) {
            unitValue = lineList.get(row).toString().split(",")[col];
        } else if(colnum == 1){
            unitValue = lineList.get(row).toString();
        } else {
            unitValue = null;
        }
        if (StringUtils.isNotEmpty(unitValue)){
            //去除前后空格
            unitValue.trim().replaceAll("^\\p{Z}*|\\p{Z}*$", "");
        }
        return unitValue;
    }

    /**
     * 获取列数
     * @return
     */
    public int getColNum(List<String> lineList) {
        if (!lineList.toString().equals("[]")) {
            if (lineList.get(0).toString().contains(",")) {// csv为逗号分隔文件
                return lineList.get(0).toString().split(",").length;
            } else if (lineList.get(0).toString().trim().length() != 0) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }


    /**
     *导入模板标题和model的标题核对，不一致返回false
     * @param targetClass
     * @param cols
     * @param <T>
     * @return
     */
    private<T> boolean titleCheck(Class<T> targetClass,List<ImportCol> cols){
        T target = BeanUtils.instantiate(targetClass);
        Field[] fields = target.getClass().getDeclaredFields();
        for (int i =0 ;i<fields.length;i++){
            ImportField importField = fields[i].getAnnotation(ImportField.class);
            if(importField != null){
                if (StringUtils.isNotEmpty(cols.get(i).getContent())){
                    if (cols.get(i).getContent().equals((importField.name()))){
                        continue;
                    }else {
                        log.warn(">>>>>检验到model【{}】注解的标题和导入模板的标题不一致，停止数据解析。。。",target.getClass().getName());
                        log.warn(">>>>>model第【{}】个属性{}对应的name为：{}，标题对应的第【{}】个单元格标题为:{}",i,fields[i].getName(),importField.name(),i,cols.get(i).getContent());
                        return false;
                    }
                }else {
                    log.warn(">>>>>导入模板标题行第{}个标题为空，停止解析。。。",i);
                    return false;
                }
            }
        }
        return true;
    }
}
