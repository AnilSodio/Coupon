package vc.thinker.cabbage.sys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.sys.bo.ExchangeRateBO;
import vc.thinker.cabbage.sys.model.ExchangeRate;
import vc.thinker.cabbage.sys.model.ExchangeRateExample;
import vc.thinker.cabbage.sys.vo.ExchangeRateVO;
import vc.thinker.cabbage.common.MyPage;

public interface ExchangeRateMapper {
    int countByExample(ExchangeRateExample example);

    int deleteByExample(ExchangeRateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExchangeRate record);

    int insertSelective(ExchangeRate record);

    List<ExchangeRateBO> selectByExample(ExchangeRateExample example);

    ExchangeRateBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExchangeRate record, @Param("example") ExchangeRateExample example);

    int updateByExample(@Param("record") ExchangeRate record, @Param("example") ExchangeRateExample example);

    int updateByPrimaryKeySelective(ExchangeRate record);

    int updateByPrimaryKey(ExchangeRate record);

	List<ExchangeRateBO> findPageByVo(@Param("page")MyPage<ExchangeRateBO> page,@Param("vo") ExchangeRateVO vo);
}