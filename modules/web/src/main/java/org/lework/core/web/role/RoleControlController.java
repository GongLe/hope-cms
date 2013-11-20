package org.lework.core.web.role;

import com.google.common.collect.Lists;
import org.lework.core.common.enumeration.Status;
import org.lework.core.entity.organization.Organization;
import org.lework.core.entity.role.Role;
import org.lework.core.entity.role.RoleTypes;
import org.lework.core.service.organization.OrganizationService;
import org.lework.core.service.role.RoleService;
import org.lework.runner.utils.Collections3;
import org.lework.runner.utils.Strings;
import org.lework.runner.web.AbstractController;
import org.lework.runner.web.CallbackData;
import org.lework.runner.web.NotificationType;
import org.lework.runner.web.easyui.TreeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 角色权限控制 Controller
 *
 * @author Gongle
 */
@Controller
@RequestMapping(value = "roleControl")
public class RoleControlController extends AbstractController {
    public static final String DEFAULT_ROLE_ICON ="tree-user-group";
    @Autowired
    private RoleService roleService;
    @Autowired
    private OrganizationService organizationService;
    /**
     * list页面*
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list() {

        return "role/roleControl";
    }

    /**
     * 修改页面
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@ModelAttribute("entity") Role role ,Model model){
        model.addAttribute("statusList" , Status.values() ) ;
        model.addAttribute("typeList", RoleTypes.values()) ;
        model.addAttribute("checkedPermissionIds" , null );
        return  "role/roleControl-update" ;
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@Valid @ModelAttribute("entity") Role entity, BindingResult result,
                       @RequestParam(value = "groupId" ,required = false) String groupId ,
                       HttpServletResponse response) {
        //关联组
        Organization group = organizationService.getOrganization(groupId);
        if (group != null) {
            entity.setGroupId(group.getId());
            entity.setGroupName(group.getName());
        } else { //取消关联
            entity.setGroupId(null);
            entity.setGroupName(null);
        }
        if (result.hasErrors()) {
            callback(response, CallbackData.build("actionCallback", "操作提示", "角色&quot;" + entity.getName() + "&quot;保存失败" + result.toString(), NotificationType.ERROR));
        }
        try {
            //保存
            roleService.saveRole(entity);
            callback(response, CallbackData.build("actionCallback", "操作提示", "角色&quot;" + entity.getName() + "&quot;保存成功", NotificationType.SUCCESS));
        }catch (Exception e){
            e.printStackTrace();
            callback(response, CallbackData.build("actionCallback", "操作提示", "角色&quot;" + entity.getName() + "&quot;保存失败" + e.toString(), NotificationType.ERROR));
        }

    }

    /**
     * 角色授权主页面 for ajax load
     */
    @RequestMapping(value = "/shouquan", method = {RequestMethod.GET, RequestMethod.POST})
    public String shouquan(@RequestParam(value = "roleId") String roleId, Model model) {

        model.addAttribute("role",roleService.getRole(roleId)) ;
        return "role/roleControl-shouquan";
    }
    /**
     * 角色成员ajax load页面
     */
    @RequestMapping(value = "/member", method = {RequestMethod.GET, RequestMethod.POST})
    public String member(@RequestParam(value = "roleId") String roleId, Model model) {

        return "role/roleControl-member";
    }

    /**
     * 角色模块权限 ajax load页面
     */
    @RequestMapping(value = "/modules", method = {RequestMethod.GET, RequestMethod.POST})
    public String modules(@RequestParam(value = "roleId") String roleId, Model model) {

        return "role/roleControl-module";
    }

    /**
     * 根据角色组ID加载所属角色
     * return easyui tree json result
     */
    @RequestMapping(value = "/getRoleTreeByGroupId", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    List<TreeResult> getRoleTreeByGroupId(@RequestParam(value = "groupId", required = true) String groupId) {


        boolean disable; //高亮状态
        List<Role> entities;
        List<TreeResult> nodeList = Lists.newArrayList();
        entities = roleService.getAllRoleByGroupId(groupId);
        if (!Collections3.isEmpty(entities)) {
            for (Role r : entities) {
                disable = Strings.equals(r.getStatus(), Status.disable.getCode());
                nodeList.add(new TreeResult(r.getId(), r.getName(),  DEFAULT_ROLE_ICON  , Strings.EMPTY));
            }
        }

        return nodeList;
    }
    //TODO 获取角色成员 Datatables JSON
    //TODO 获取角色 菜单(模块) TreeGrid JSON
    /**
     * Preparable二次部分绑定的效果
     */
    @ModelAttribute("entity")
    public void prepareModel(Model model, @RequestParam(value = "id", required = false) String id) {
        if (Strings.isNotBlank(id)) {
            model.addAttribute("entity", roleService.getRole(id));
        } else {
            model.addAttribute("entity", new Role());
        }
    }

    /**
     * 不自动绑定对象中的roles属性
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("users");
        binder.setDisallowedFields("permissions");
    }


}
