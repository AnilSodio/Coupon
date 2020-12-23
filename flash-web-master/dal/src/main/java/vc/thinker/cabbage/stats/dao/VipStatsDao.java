package vc.thinker.cabbage.stats.dao;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.stats.bo.RealTimeGroupStatsBO;
import vc.thinker.cabbage.stats.bo.VipStatsBO;
import vc.thinker.cabbage.stats.mapper.VipStatsMapper;
import vc.thinker.cabbage.stats.model.VipStats;
import vc.thinker.cabbage.stats.model.VipStatsExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VipStatsDao {

    @Autowired
    private VipStatsMapper mapper;


    /**
     * generate code begin
     **/
    public List<VipStatsBO> findAll() {
        VipStatsExample example = new VipStatsExample();
        return mapper.selectByExample(example);
    }

    List<VipStatsBO> findAll(Iterable<java.lang.Long> ids) {
        VipStatsExample example = new VipStatsExample();
        example.createCriteria().andIdIn(Lists.newArrayList(ids));
        return mapper.selectByExample(example);
    }

    public long count() {
        VipStatsExample example = new VipStatsExample();
        return mapper.countByExample(example);
    }

    public List<VipStats> save(Iterable<VipStats> entities) {
        List<VipStats> list = new ArrayList<VipStats>();
        for (VipStats VipStats : entities) {
            list.add(save(VipStats));
        }
        return list;
    }

    public VipStats save(VipStats record) {
        if (record.getId() == null) {
            mapper.insertSelective(record);
        } else {
            mapper.updateByPrimaryKeySelective(record);
        }
        return record;
    }


    public void update(VipStats record) {
        mapper.updateByPrimaryKeySelective(record);
    }

    public VipStatsBO findOne(java.lang.Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean exists(java.lang.Long id) {
        if (id == null) {
            return false;
        }
        VipStatsExample example = new VipStatsExample();
        example.createCriteria().andIdEqualTo(id);
        return mapper.countByExample(example) > 0;
    }

    public void delete(java.lang.Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void remove(java.lang.Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void delete(VipStats entity) {
        mapper.deleteByPrimaryKey(entity.getId());
    }

    public void delete(Iterable<VipStats> entities) {
        List<java.lang.Long> ids = Lists.newArrayList();
        for (VipStats entity : entities) {
            ids.add(entity.getId());
        }
        deleteByIds(ids);
    }

    public void deleteByIds(Iterable<java.lang.Long> ids) {
        VipStatsExample example = new VipStatsExample();
        example.createCriteria().andIdIn(Lists.newArrayList(ids));
        mapper.deleteByExample(example);
    }

    public void deleteAll() {
        VipStatsExample example = new VipStatsExample();
        mapper.deleteByExample(example);
    }

    /**
     * generate code end
     **/
    public BigDecimal sumByDate(String date) {
        return mapper.sumByDate(date);
    }

    public BigDecimal sumAvgByDate(String beginDate, String endDate) {
        return mapper.sumAvgByDate(beginDate, endDate);
    }

    public BigDecimal findVipPayByTimeRange(String day,String time1, String time2) {
        return mapper.findVipPayByTimeRange(day,time1, time2);
    }

    public BigDecimal findVipPayByAgeRange(Integer age1, Integer age2, String date) {
        return mapper.findVipPayByAgeRange(age1, age2, date);
    }

    public List<RealTimeGroupStatsBO> findVipPayByGroupType(String groupType, String date) {
        return mapper.findVipPayByGroupType(groupType, date);
    }

    public BigDecimal sumByTime(String year, String month) {
        return mapper.sumByTime(year, month);
    }
    
    public List<VipStatsBO> sumByTimeGroupByCurrency(String year, String month) {
    	return mapper.sumByTimeGroupByCurrency(year, month);
    }

	public void deleteByDate(String beginDate, String endDate) {
		 mapper.deleteByDate(beginDate,endDate);
	}

    public Double getTotalVipRecharge(){
        return mapper.getTotalVipRecharge();
    }

	public Integer totalVips() {
		return mapper.totalVips();
	}

	public List<VipStatsBO> sumByDateGouupByCurrency(String date) {
		return mapper.sumByDateGouupByCurrency(date);
	}

	public List<VipStatsBO> sumAvgByDateGroupByCurrency(String beginDate, String endDate) {
		return mapper.sumAvgByDateGroupByCurrency(beginDate,endDate);
	}
}
