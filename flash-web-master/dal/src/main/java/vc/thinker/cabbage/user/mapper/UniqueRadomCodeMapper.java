package vc.thinker.cabbage.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.user.bo.UniqueRadomCodeBO;

public interface UniqueRadomCodeMapper {

	UniqueRadomCodeBO getCode(@Param("type")String type);

	void addNewCode(@Param("type")String type,@Param("insertValues")String insertValues);

	void update(@Param("code")Long code,@Param("type")String type);

	List<String> getListCode(@Param("type")String type, @Param("num")int num);
   
}