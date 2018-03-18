package cn.llf.framework.services.unit.south.impl;

import cn.llf.framework.dao.impl.mongo.UnitDaoImpl;
import cn.llf.framework.model.mongo.Unit;
import cn.llf.framework.services.unit.dto.District;
import cn.llf.framework.services.unit.dto.UnitQuery;
import cn.llf.framework.services.unit.south.IUnitService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import priv.llf.mybatis.support.Page;

import java.util.List;

/**
 * Author：calvin
 * Date:  2017/10/22 0022
 * 描述：
 */
@Slf4j
@Service("unitService")
public class UnitServiceImpl implements IUnitService {
    @Autowired
    UnitDaoImpl unitDao;

    public void add(Unit unit){
        unitDao.save(unit);
    }

    @Override
    public Page page(Page page, UnitQuery query) {
        Query remoteQuery = new Query();
        Criteria criteria = this.buildCriteria(query);
        remoteQuery.addCriteria(criteria);
        page = unitDao.page(page,remoteQuery);
        List<Unit> resultList = (List<Unit>) page.getCurrentPageData();
        if (CollectionUtils.isNotEmpty(resultList)){
        }else {
            log.info("分页数据不存在");
        }
        return page;
    }

    public Unit getDetailById(String id){
        Unit unit;
        unit = (Unit)unitDao.findById(id);
        return unit;
    }
    public List<Unit> findByQuery(UnitQuery query){
        Query remoteQuery = new  Query();
        Criteria criteria = this.buildCriteria(query);
        remoteQuery.addCriteria(criteria);
        List<Unit> result =  (List<Unit>)unitDao.findByQuery(remoteQuery);
        return result ;
    }
    public void update(Unit unit){
        unitDao.update(unit);
    }

    public void deleteById(String id){
        unitDao.deleteById(id);
    }

    @Override
    public long countByQuery(UnitQuery query) {
        Query remoteQuery = new  Query();
        Criteria criteria = this.buildCriteria(query);
        remoteQuery.addCriteria(criteria);
        long count = unitDao.countByQuery(remoteQuery);
        return count;
    }

    public Criteria buildCriteria(UnitQuery query){
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(query.getName())){
            criteria.and("name").is(query.getName());
        }
        if (StringUtils.isNotBlank(query.getOrganizationCode())){
            criteria.and("organizationCode").is(query.getOrganizationCode());
        }
        if (query.getPhysicalDistrict() != null){
            District district = query.getPhysicalDistrict();
            if (StringUtils.isNotBlank(district.getProvinceId())){
                criteria.and("physicalDistrict.provinceId").is(district.getProvinceId());
            }
            if (StringUtils.isNotBlank(district.getCityId())){
                criteria.and("physicalDistrict.cityId").is(district.getCityId());
            }
            if (StringUtils.isNotBlank(district.getCountryId())){
                criteria.and("physicalDistrict.countryId").is(district.getCountryId());
            }
        }
        return criteria;
    }
}
