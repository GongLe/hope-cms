package org.lework.core.web.role;

import com.google.common.collect.Lists;
import org.lework.core.common.enumeration.Status;
import org.lework.core.entity.menu.Menu;
import org.lework.core.entity.organization.Organization;
import org.lework.core.entity.role.Role;
import org.lework.core.entity.role.RoleTypes;
import org.lework.core.entity.user.User;
import org.lework.core.service.account.AccountService;
import org.lework.core.service.menu.MenuService;
import org.lework.core.service.organization.OrganizationService;
import org.lework.core.service.role.RoleService;
import org.lework.runner.utils.Collections3;
import org.lework.runner.utils.Strings;
import org.lework.runner.web.AbstractController;
import org.lework.runner.web.CallbackData;
import org.lework.runner.web.NotificationType;
import org.lework.runner.web.datatables.DataTableResult;
import org.lework.runner.web.easyui.TreeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private AccountService accountService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private MenuService menuService;
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
    public String update(@ModelAttribute("entity") Role role,
                         Model model) {
        model.addAttribute("statusList", Status.values());
        model.addAttribute("typeList", RoleTypes.values());

        model.addAttribute("checkedPermissionIds", null);

        return "role/roleControl-update";
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
            callback(response, CallbackData.build("actionCallback", "角色&quot;" + entity.getName() + "&quot保存失败", NotificationType.ERROR));
        }
        try {
            //保存
            roleService.saveRole(entity);
            callback(response, CallbackData.build("actionCallback", "角色&quot;" + entity.getName() + "&quot保存成功", NotificationType.DEFAULT));
        }catch (Exception e){
            e.printStackTrace();
            callback(response, CallbackData.build("actionCallback", "角色&quot;" + entity.getName() + "&quot保存失败", NotificationType.ERROR));
        }

    }

    /**
     * 角色授权主页面 for ajax load
     */
    @RequestMapping(value = "/shouquan", method = {RequestMethod.GET, RequestMethod.POST})
    public String shouquan(@RequestParam(value = "roleId") String roleId, Model model) {

        model.addAttribute("role", roleService.getRole(roleId));
        return "role/roleControl-shouquan";
    }

    /**
     * 角色成员ajax load页面
     */
    @RequestMapping(value = "/member", method = {RequestMethod.GET, RequestMethod.POST})
    public String member(@RequestParam(value = "roleId") String roleId, Model model) {
        model.addAttribute("role", roleService.getRole(roleId));
        return "role/roleControl-member";
    }

    /**添加成员到角色list页面**/
    @RequestMapping(value = "/addMember", method = {RequestMethod.GET })
    public String addMember(@RequestParam(value = "roleId") String roleId, Model model) {
        model.addAttribute("role", roleService.getRole(roleId));
        return "role/roleControl-addMember";
    }

    /**
     * 添加角色与用户关联关系
     *
     * @param roleId 角色ID
     * @param orgId  组织ID
     * @param response
     */
    @RequestMapping(value = "/createRelateUser", method = RequestMethod.POST)
    public void createRelateUser(@RequestParam(value = "roleId") String roleId,
                                 @RequestParam(value = "orgId") String orgId,
                                 HttpServletResponse response) {
        Role role = roleService.getRole(roleId);
        callback(response, CallbackData.build("createRelateCallback", "创建关联&quot;" + role.getName() + " &quot;关联成功",
                NotificationType.DEFAULT));
    }

    /**
     * 解除角色与用户关联关系
     *
     * @param roleId 角色ID
     * @param orgId  组织ID
     * @param response
     */
    @RequestMapping(value = "/removeRelatedUser", method = RequestMethod.POST)
    public void removeRelatedUser(@RequestParam(value = "roleId") String roleId,
                                  @RequestParam(value = "orgId") String orgId,
                                  HttpServletResponse response) {
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

    /**
     * ====================================
     *    获取角色成员 Datatables JSON
     * ====================================
     */
    /**
     * 获取角色关联的用户
     *
     * @param pageable
     * @param roleId   菜单ID
     * @param search   用户名||登录名
     * @return Datatables Json Data
     */
    @RequestMapping(value = "/geRoleRelatedUserJson", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    DataTableResult<User> geRoleRelatedUserJson(@PageableDefault(page = 0, size = 15) Pageable pageable,
                                                @RequestParam(value = "roleId") String roleId,
                                                @RequestParam(value = "search", required = false) String search) {

        Page<User> page = accountService.searchUserPageByRoleId(pageable, roleId, search);
        return DataTableResult.build(page);
    }

    /**
     * 获取角色关联的菜单(模块)
     *
     * @param pageable
     * @param roleId   菜单ID
     * @param search   用户名||登录名
     * @return Datatables Json Data
     */
    @RequestMapping(value = "/geRoleRelatedMenuJson", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    DataTableResult<Menu> geRoleRelatedMenuJson(@PageableDefault(page = 0, size = 15) Pageable pageable,
                                                @RequestParam(value = "roleId") String roleId,
                                                @RequestParam(value = "search", required = false) String search) {

        Page<Menu> page = menuService.searchMenuPageByRoleId(pageable, roleId, search);
        return DataTableResult.build(page);
    }


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
