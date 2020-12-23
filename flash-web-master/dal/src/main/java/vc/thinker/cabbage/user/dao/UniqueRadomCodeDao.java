package vc.thinker.cabbage.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vc.thinker.cabbage.user.bo.UniqueRadomCodeBO;
import vc.thinker.cabbage.user.mapper.UniqueRadomCodeMapper;
import vc.thinker.utils.CommUtil;

@Repository
public class UniqueRadomCodeDao {

	@Autowired
	private UniqueRadomCodeMapper mapper;

	public UniqueRadomCodeBO getCode(String type) {
		UniqueRadomCodeBO bo = mapper.getCode(type);
		if(bo!=null){
			mapper.update(bo.getCode(),type);//已使用
		}
		return bo;
	}
	
	/**
	 * 批量插入code
	 * @param type 类型
	 * @param nums 个数
	 */
	public void addNewCode(String type,int nums) {
		StringBuffer str = new StringBuffer();
		for(int i=1;i<=nums;i++){
			if(i!=nums){
				str.append("('1'),");
			}else{
				str.append("('1')");
			}
		}
		mapper.addNewCode(type,str.toString());
	}

	public List<String> getListCode(String type, int num) {
		List<String> code = mapper.getListCode(type,num);
		if(code.size()>0){
			code.forEach(e->{
				mapper.update(CommUtil.null2Long(e),type);//已使用
			});
		}
		return code;
	}


   
}
