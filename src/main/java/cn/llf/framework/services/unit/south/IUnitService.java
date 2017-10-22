package cn.llf.framework.services.unit.south;

import cn.llf.framework.model.Unit;
import cn.llf.framework.services.unit.dto.UnitQuery;
import cn.llf.mybatis.support.Page;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * Author：calvin
 * Date:  2017/10/22 0022
 * 描述：
 */
public interface IUnitService {
    /**
     * 增加一个单位信息
     * @param unit
     */
    void add(Unit unit);

    Page page(Page page,UnitQuery query);
    /**
     * 条件搜索单位
     * @param query
     */
    List<Unit> findByQuery(UnitQuery query);

    /**
     * 获取单位详情
     * @param id
     * @return
     */
    Unit getDetailById(String id);

    /**
     * 更新单位信息
     * @param unit
     */
    void update(Unit unit);

    /**
     * 删除一条数据
     * @param id
     */
    void deleteById(String id);
    /**
     * 统计条数
     * @param query
     * @return
     */
    long countByQuery(UnitQuery query);

    /**
     * 构建查询sql
     * @param query
     * @return
     */
    Criteria buildCriteria(UnitQuery query);
}
