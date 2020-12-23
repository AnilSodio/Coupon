package vc.thinker.cabbage.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.sys.bo.ResourceBO;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.sys.service.ResourceService;
import vc.thinker.cabbage.sys.vo.ResourceVO;
import vc.thinker.cabbage.web.i18n.MessageResource;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.contants.SysUserContant;


@Controller
@RequestMapping("${adminPath}/sys/resource")
public class ResourceController extends XController<ResourceBO, ResourceVO, ResourceService> {
    @Override
    protected void init() {
        super.setPrefixPath("modules/sys");
        super.setListName("resource_list");
        super.setEditName("resource_edit");
    }

    @Autowired
    private MessageResource messageResource;
    @Autowired
    private CountryService countryService;

    @Override
    @RequiresPermissions("sys:resource:list")
    @SecurityMapping(name = "form.list", permGroup = "sys.multilingualSetting", userType = SysUserContant.USER_TYPE_1)
    public String list(Model model, MyPage<ResourceBO> page, ResourceVO vo) {
        model.addAttribute("countryList", countryService.groupByLanguage());  //查询所国家
        return super.list(model, page, vo);
    }

    @Override
    @RequiresPermissions("sys:resource:edit")
    @SecurityMapping(name = "form.edit", permGroup = "sys.multilingualSetting", userType = SysUserContant.USER_TYPE_1)
    public String edit(Model model, Long id) {
        return super.edit(model, id);
    }

    @Override
    @RequiresPermissions("sys:resource:edit")
    @SecurityMapping(name = "form.edit", permGroup = "sys.multilingualSetting", userType = SysUserContant.USER_TYPE_1)
    public String save(ResourceBO resourceBO) {
        String path = super.save(resourceBO);
        messageResource.reload();  //刷新消息库,达到立即生效的效果
        return path;
    }
}
