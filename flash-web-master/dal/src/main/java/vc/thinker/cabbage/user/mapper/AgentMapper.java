package vc.thinker.cabbage.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.user.bo.AgentBO;
import vc.thinker.cabbage.user.model.Agent;
import vc.thinker.cabbage.user.model.AgentExample;

public interface AgentMapper {
    int countByExample(AgentExample example);

    int deleteByExample(AgentExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(Agent record);

    int insertSelective(Agent record);

    List<AgentBO> selectByExample(AgentExample example);

    AgentBO selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") Agent record, @Param("example") AgentExample example);

    int updateByExample(@Param("record") Agent record, @Param("example") AgentExample example);

    int updateByPrimaryKeySelective(Agent record);

    int updateByPrimaryKey(Agent record);
}