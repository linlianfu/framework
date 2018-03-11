package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.dao.impl.mybatis.BillDao;
import cn.llf.framework.model.mybatis.Bill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: calvin
 * @Since: 2018/3/10 21:40
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("bill")
public class BillAction {

    @Autowired
    BillDao billDao;

    @ResponseBody
    @RequestMapping(value = "listBill",method = RequestMethod.GET)
    public List<Bill> listBill(){
        List<Bill> result = new ArrayList<>();
        result = billDao.getSqlSession().selectList("listBillConfig");

        return result;

    }


}
