package vc.thinker.cabbage.se;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.common.QRCodeUtil;
import vc.thinker.cabbage.common.SysCodeUtil;
import vc.thinker.cabbage.se.bo.SysCodeBO;
import vc.thinker.cabbage.se.dao.SysCodeDao;
import vc.thinker.cabbage.se.vo.SysCodeVO;
import vc.thinker.cabbage.user.service.UniqueRadomCodeService;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class SysCodeService {

	@Autowired
	private SysCodeDao sysCodeDao;

	@Autowired
	private UniqueRadomCodeService uniqueRadomCodeService;

	public List<SysCodeBO> findPageByVo(String applet_redirect_url, MyPage<SysCodeBO> page, SysCodeVO vo) {
		List<SysCodeBO> list = sysCodeDao.findPageByVo(page, vo);
		list.forEach(e -> {
			e.setTwoCode(QRCodeUtil.generateToBase64(applet_redirect_url + e.getSysCode(), 200, 200));
		});
		return sysCodeDao.findPageByVo(page, vo);
	}

	// 打印(print)
	public void print(MyPage<SysCodeBO> page, SysCodeVO vo) {

		page.setPageSize(100);
		if (null == vo.getIsPrint()) {
			vo.setIsPrint(false);
		}

		List<SysCodeBO> code_list = sysCodeDao.findPageByVo(page, vo);
		code_list.forEach(e -> {
			e.setIsPrint(true);
			e.setPrintTime(new Date());
			sysCodeDao.update(e);
		});
	}

	// 创建(create)
	public void createSysCode(String belongType) {

		List<String> code_list = uniqueRadomCodeService.getListCode(SeCommonConstants.CODE_SYSCODE, 100);
		code_list.forEach(e -> {

			SysCodeBO insert_bo = new SysCodeBO();

			insert_bo.setSysCode(SysCodeUtil.repair(e));
			insert_bo.setIsBinding(false);
			insert_bo.setIsPrint(false);
			insert_bo.setCreateTime(new Date());

			sysCodeDao.save(insert_bo);
		});
	}

	public List<SysCodeBO> findAllNoPrint() {
		return sysCodeDao.findAllNoPrint();
	}

	public SysCodeBO findBySysCode(String sysCode) {
		return sysCodeDao.findBySysCode(sysCode);
	}

	public List<SysCodeBO> findAllNoBinding() {
		return sysCodeDao.findAllNoBinding();
	}

	// single
	public void createSingleSysCode(String belongType) {

		/*List<String> code_list = uniqueRadomCodeService.getListCode(SeCommonConstants.CODE_SYSCODE, 1);
		code_list.forEach(e -> {*/

			SysCodeBO insert_bo1 = new SysCodeBO();

			insert_bo1.setSysCode(SysCodeUtil.repair(belongType));
			insert_bo1.setIsBinding(false);
			insert_bo1.setIsPrint(false);
			insert_bo1.setCreateTime(new Date());

			sysCodeDao.saveSingle((insert_bo1));
		/* }); */
	}

}
