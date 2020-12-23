package vc.thinker.cabbage.se;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.se.bo.CabinetFirmwareBO;
import vc.thinker.cabbage.se.bo.FirmwareModuleBO;
import vc.thinker.cabbage.se.dao.CabinetFirmwareDao;
import vc.thinker.cabbage.se.dao.FirmwareModuleDao;
import vc.thinker.cabbage.se.model.CabinetFirmware;
import vc.thinker.cabbage.se.vo.CabinetFirmwareVO;

@Service
@Transactional
public class CabinetFirmwareService {

	@Autowired
	private CabinetFirmwareDao cabinetFirmwareDao;
	
	@Autowired
	private FirmwareModuleDao firmwareModuleDao;
	
	/**
	 * 查找最后的版本
	 * @param mouble
	 * @return
	 */
	public CabinetFirmwareBO findLastByMouble(String mouble) {
		return cabinetFirmwareDao.findLastByMouble(mouble);
	}

	public List<CabinetFirmwareBO> findPageByVo(Page<CabinetFirmwareBO> page, CabinetFirmwareVO vo) {
		return cabinetFirmwareDao.findPageByVo(page,vo);
	}

	public CabinetFirmwareBO findOne(Long id) {
		return cabinetFirmwareDao.findOne(id);
	}
	
	public List<FirmwareModuleBO> findAllModule(){
		return firmwareModuleDao.findAll();
	}

	public void saveOrUpdate(CabinetFirmware firmware) {
		
		if(null != firmware.getId()){
			firmware.setUpdateTime(new Date());
		}else {
			firmware.setCreateTime(new Date());
		}
		
		cabinetFirmwareDao.save(firmware);
	}

	public void delete(Long id) {
		cabinetFirmwareDao.delete(id);
	}

	public List<CabinetFirmwareBO> findAll() {
		return cabinetFirmwareDao.findPageByVo(null,null);
	}
	
}
