package vc.thinker.cabbage.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinco.common.area.Country;
import com.sinco.common.area.CountryUtil;

import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.sys.bo.CountryBO;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.sys.vo.CountryVO;
import vc.thinker.cabbage.web.i18n.MyLocaleResolver;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;

@Controller
@RequestMapping("${adminPath}/sys/country")
public class CountryController extends XController<CountryBO, CountryVO, CountryService> {
	
	
	
    @Override
    public void init() {
        super.setPrefixPath("modules/sys/");
        super.setEditName("country_edit");
        super.setListName("country_list");
    }

    @Resource(name = "localeResolver")
    private MyLocaleResolver myLocaleResolver;

    @Value("${adminPath}")
    private String adminPath;

    @Override
    @RequiresPermissions("sys:country:list")
    @SecurityMapping(name = "role.list", permGroup = "sys.controlManage", userType = SysUserContant.USER_TYPE_1)
    public String list(Model model, MyPage<CountryBO> page, CountryVO countryVO) {
        return super.list(model, page, countryVO);
    }

    @Override
    @RequiresPermissions("sys:country:edit")
    @SecurityMapping(name = "role.edit", permGroup = "sys.controlManage", userType = SysUserContant.USER_TYPE_1)
    public String edit(Model model, Long id) {
    	List<Country> nations = this.findSupportNations();
    	model.addAttribute("supportNations", nations);
        return super.edit(model, id);
    }

    @Override
    @RequiresPermissions("sys:country:edit")
    @SecurityMapping(name = "form.edit", permGroup = "sys.controlManage", userType = SysUserContant.USER_TYPE_1)
    public String save(CountryBO countryBO) {
        return super.save(countryBO);
    }

    @RequestMapping("setDefault")
    @RequiresPermissions("sys:country:edit")
    @SecurityMapping(name = "role.edit", permGroup = "sys.controlManage", userType = SysUserContant.USER_TYPE_1)
    public String setDefault(Long id) {
//        myLocaleResolver.setCountry((CountryBO) service.setDefault(id));
    	service.setDefault(id);
        return "redirect:" + adminPath;
    }
    
    /**
     * 获取平台支持的国家
     * @return
     */
    private List<Country> findSupportNations(){
    	List<Country> nations = new ArrayList<Country>();
    	List<Country> list = CountryUtil.list;
    	for (Country country : list) {
			if (null != country.getCurrency()) {
				nations.add(country);
			}
		}
    	
    	return nations;
    }
}
