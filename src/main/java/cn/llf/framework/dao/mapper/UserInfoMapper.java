package cn.llf.framework.dao.mapper;

import cn.llf.framework.dao.dto.UserInfo;
import cn.llf.mybatis.support.BaseSqlProvider;
import cn.llf.mybatis.support.MapKeyConstant;
import cn.llf.mybatis.support.MapperSqlMethod;
import cn.llf.mybatis.support.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * Author：calvin
 * Date:  2017/8/18 0018
 */
public interface UserInfoMapper{
    /**
     * 保存单条数据
     * @param
     * @return
     */
    @InsertProvider(type = BaseSqlProvider.class, method = MapperSqlMethod.SAVE)
    int save(UserInfo userInfo);

    /**
     * 删除单条数据
     * @param
     * @return
     */
    @DeleteProvider(type = BaseSqlProvider.class, method = MapperSqlMethod.DELETE_BY_ID)
    int deleteById(UserInfo userInfo);

    /**
     * 修改单条数据
     * @param
     * @return
     */
    @UpdateProvider(type = BaseSqlProvider.class, method = MapperSqlMethod.UPDATE)
    int upDate(UserInfo userInfo);

    /**
     * 查询所有的实体
     * @param
     * @return
     */
    @SelectProvider(type = BaseSqlProvider.class, method = MapperSqlMethod.FIND_ALL)
    List<UserInfo> findAll(UserInfo userInfo);

    /**
     * 搜索符合条件的实体(int型的数据，有默认值0，此时设定不会加入查询条件，后期待完善，可自定义注解解决此问题)
     * @param
     * @return
     */
    @SelectProvider(type = BaseSqlProvider.class, method = MapperSqlMethod.FIND_BY_CONDITION)
    List<UserInfo> findByCondition(UserInfo userInfo);


    /**
     * 搜索符合条件的实体(int型的数据，有默认值0，此时设定不会加入查询条件，后期待完善，可自定义注解解决此问题)
     * @param
     * @return
     */
    @SelectProvider(type = BaseSqlProvider.class, method = MapperSqlMethod.COUNT)
    int count(UserInfo userInfo);


    /**
     * 搜索符合条件的实体(int型的数据，有默认值0，此时设定不会加入查询条件，后期待完善，可自定义注解解决此问题)
     * @param
     * @return
     */
    @SelectProvider(type = BaseSqlProvider.class, method = MapperSqlMethod.FIND_PAGE)
    List<UserInfo> findPage(@Param(MapKeyConstant.ENTITY) UserInfo userInfo, @Param(Page.PAGE_KEY) Page page);




}
