package cn.llf.jdbc.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author eleven
 * @date 2019/3/31
 * @description
 */
public class MultipleDataSourceConfig extends AbstractRoutingDataSource {

    private static ThreadLocal<String> DATA_SOURCE_KEY = ThreadLocal.withInitial(() -> "calvinDataSource");

    @Override
    protected Object determineCurrentLookupKey() {
        return DATA_SOURCE_KEY.get();
    }


    public static void setDataSourceKey(int platform){

        if (platform == 1){
            DATA_SOURCE_KEY.set("calvinDataSource");
        }else {
            DATA_SOURCE_KEY.set("abilityCourseDataSource");
        }

    }
}
