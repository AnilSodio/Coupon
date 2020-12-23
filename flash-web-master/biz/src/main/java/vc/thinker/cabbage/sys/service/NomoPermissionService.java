package vc.thinker.cabbage.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.sys.bo.PermissionBO;
import vc.thinker.sys.dao.PermissionDao;
import vc.thinker.sys.model.PermissionExample;

@Service
@Transactional
public class NomoPermissionService {

	@Autowired
	private PermissionDao permissionDao;

	public void deleteUselessPerssion() {
		
		List<PermissionBO> list = permissionDao.findAll();
		
		for(int i=0;i<list.size();i++){
			
			if(list.get(i).getGroupName().equals("权限管理") 
					|| list.get(i).getGroupName().equals("机构管理")
					|| list.get(i).getGroupName().equals("字典管理")){
				permissionDao.delete(list.get(i).getId());
			}
		}
		
	}
	
}
