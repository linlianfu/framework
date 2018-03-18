package cn.llf.framework.dao.impl.mybatis;

import cn.llf.framework.model.mybatis.OcBillConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import priv.llf.mybatis.dao.BaseMybatisDaoImpl;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@Slf4j
@Repository("billConfig")
public class BillConfigImpl extends BaseMybatisDaoImpl<OcBillConfig,String> {

}
