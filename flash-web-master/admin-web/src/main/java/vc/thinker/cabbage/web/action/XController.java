package vc.thinker.cabbage.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.sys.service.XService;
import vc.thinker.core.web.BaseController;

import javax.annotation.PostConstruct;

/**
 * HTH
 *
 * @param <BO>
 * @param <VO>
 * @param <Service>
 */
public abstract class XController<BO, VO, Service extends XService> extends BaseController {
    @Autowired
    protected Service service;
    private String prefixPath = "modules/";  //视图前缀路径
    private String listName;   //列表视图名
    private String editName;   //编辑视图名
    private String pageModelName = "page";  //分页page
    private String voModelName = "vo";  //vo
    private String boModelName = "info";  //详情bo

    /**
     * 用来初始化视图名称和前缀等
     */
    @PostConstruct
    protected abstract void init();

    @RequestMapping("list")
    public String list(Model model, MyPage<BO> page, VO vo) {
        service.findListByPageAndVO(page, vo);
        model.addAttribute(getPageModelName(), page);
        model.addAttribute(getVoModelName(), vo);
        return getPrefixPath() + getListName();
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Long id) {
        model.addAttribute(getBoModelName(), id == null ? new Object() : service.findOne(id));
        return getPrefixPath() + getEditName();
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(BO bo) {
        service.save(bo);
        return "redirect:list";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(Long id) {
        service.delete(id);
        return "redirect:list";
    }

    //set get

    public String getPrefixPath() {
        return prefixPath;
    }

    public void setPrefixPath(String prefixPath) {
        int index = prefixPath.lastIndexOf("/");
        if (index == -1 || index != prefixPath.length() - 1) {  //如果前缀字符串没有/或者不在最后，追加/
            prefixPath += "/";
        }
        this.prefixPath = prefixPath;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getEditName() {
        return editName;
    }

    public void setEditName(String editName) {
        this.editName = editName;
    }

    public String getPageModelName() {
        return pageModelName;
    }

    public void setPageModelName(String pageModelName) {
        this.pageModelName = pageModelName;
    }

    public String getVoModelName() {
        return voModelName;
    }

    public void setVoModelName(String voModelName) {
        this.voModelName = voModelName;
    }

    public String getBoModelName() {
        return boModelName;
    }

    public void setBoModelName(String boModelName) {
        this.boModelName = boModelName;
    }
}
