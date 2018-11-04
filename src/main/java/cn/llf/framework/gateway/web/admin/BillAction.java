package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.dao.impl.mybatis.BillDao;
import cn.llf.framework.model.mybatis.Bill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: eleven
 * @since: 2018/3/10 21:40
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("bill")
public class BillAction {

    @Autowired
    BillDao billDao;

    @GetMapping(value = "listBill")
    public List<Bill> listBill(){
        List<Bill> result = new ArrayList<>();
        result = billDao.getSqlSession().selectList("listBillConfig");

        return result;

    }


}
