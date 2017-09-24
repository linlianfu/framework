package cn.llf.mybatis.dao;

import cn.llf.mybatis.support.BaseSqlProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Author：calvin
 * Date:  2017/8/18 0018
 */
public interface BaseMybatisMapper<T>{
    /**
     * 查询所有的实体
     * @param
     * @return
     */
    @SelectProvider(type = BaseSqlProvider.class, method = "findAll")
    List<T> findAll(T t);
}
