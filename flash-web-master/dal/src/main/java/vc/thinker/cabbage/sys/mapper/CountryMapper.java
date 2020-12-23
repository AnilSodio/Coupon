package vc.thinker.cabbage.sys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.x.XMapper;
import vc.thinker.cabbage.sys.bo.CountryBO;
import vc.thinker.cabbage.sys.model.Country;
import vc.thinker.cabbage.sys.model.CountryExample;
import vc.thinker.cabbage.sys.vo.CountryVO;

public interface CountryMapper extends XMapper<CountryBO,CountryVO>{
    int countByExample(CountryExample example);

    int deleteByExample(CountryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Country record);

    int insertSelective(Country record);

    List<CountryBO> selectByExample(CountryExample example);

    CountryBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Country record, @Param("example") CountryExample example);

    int updateByExample(@Param("record") Country record, @Param("example") CountryExample example);

    int updateByPrimaryKeySelective(Country record);

    int updateByPrimaryKey(Country record);

	List<CountryBO> groupByLanguage();

	void updateFalseDefault();
}