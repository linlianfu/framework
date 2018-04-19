package cn.llf.framework.dao.impl.mybatis;

import cn.llf.framework.model.mybatis.Bill;
import org.springframework.stereotype.Repository;
import priv.llf.mybatis.dao.BaseMybatisDaoImpl;

/**
 * @author: eleven
 * @since: 2018/3/10 21:41
 * @description:
 */
@Repository("billDao")
public class BillDao extends BaseMybatisDaoImpl<Bill,String> {
}
