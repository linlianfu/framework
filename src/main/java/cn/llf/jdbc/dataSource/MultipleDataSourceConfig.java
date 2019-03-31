package cn.llf.jdbc.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author eleven
 * @date 2019/3/31
 * @description
 */
public class MultipleDataSourceConfig extends AbstractRoutingDataSource {

    // TODO: 2019/3/31   必须使用线程变量 
    public static String DATA_SOURCE_KEY = "calvin";

    @Override
    protected Object determineCurrentLookupKey() {
        return DATA_SOURCE_KEY;
    }


    public static void setDataSourceKey(int platform){

        if (platform == 1){
            DATA_SOURCE_KEY = "calvinDataSource";
        }else {
            DATA_SOURCE_KEY = "abilityCourseDataSource";
        }

    }
}
