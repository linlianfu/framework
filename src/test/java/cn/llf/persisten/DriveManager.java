package cn.llf.persisten;

import cn.llf.framework.model.mybatis.UserInfo;
import cn.llf.framework.utils.prop.SystemProperty;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author eleven
 * @date 2018/11/25
 * @description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/common.xml")
public class DriveManager {

    @Autowired
    SystemProperty systemProperty;

    public Connection getConnection(){
        String dataBaseDriver = systemProperty.getProperty("database.driver");
        String dataBaseUrl = systemProperty.getProperty("database.url");
        String user = systemProperty.getProperty("database.user");
        String password = systemProperty.getProperty("database.password");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataBaseDriver);
        dataSource.setUrl(dataBaseUrl);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("数据库连接失败");
        }
        return null;
    }

    @Test
    public void testGetData(){

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM  user_info");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String age = resultSet.getString("age");
                String identity = resultSet.getString("identity");
                UserInfo userInfo = new UserInfo();
                userInfo.setId(id);
                userInfo.setName(name);
                userInfo.setAge(Integer.parseInt(age));
                userInfo.setIdentity(identity);
                log.info("查询集合：{}",userInfo.toString());
            }

            PreparedStatement insertStatement = connection.prepareStatement("INSERT into user_info values(REPLACE(UUID(),'-',''),'java_driver',22,'312315050')");
            int updateCount = insertStatement.executeUpdate();
            log.info("update count：{}",updateCount);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
