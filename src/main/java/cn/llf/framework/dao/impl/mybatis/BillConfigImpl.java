package cn.llf.framework.dao.impl.mybatis;

import cn.llf.framework.model.mybatis.OcBillConfig;
import cn.llf.mybatis.dao.impl.BaseMybatisDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@Slf4j
@Repository("billConfig")
public class BillConfigImpl extends BaseMybatisDaoImpl<OcBillConfig,String> {

}
