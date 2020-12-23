package vc.thinker.cabbage.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.user.bo.PayAmountBO;
import vc.thinker.cabbage.user.model.PayAmount;
import vc.thinker.cabbage.user.model.PayAmountExample;
import vc.thinker.cabbage.user.vo.PayAmountVO;

public interface PayAmountMapper {
    int countByExample(PayAmountExample example);

    int deleteByExample(PayAmountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PayAmount record);

    int insertSelective(PayAmount record);

    List<PayAmountBO> selectByExample(PayAmountExample example);

    PayAmountBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PayAmount record, @Param("example") PayAmountExample example);

    int updateByExample(@Param("record") PayAmount record, @Param("example") PayAmountExample example);

    int updateByPrimaryKeySelective(PayAmount record);

    int updateByPrimaryKey(PayAmount record);

	List<PayAmountBO> findPageByVo(@Param("page") Page<PayAmountBO> page, @Param("vo")PayAmountVO vo);
}