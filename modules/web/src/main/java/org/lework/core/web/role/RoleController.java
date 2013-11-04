package org.lework.core.web.role;

import com.google.common.collect.Lists;
import org.lework.core.common.enumeration.Status;
import org.lework.core.entity.role.Role;
import org.lework.core.service.role.RoleService;
import org.lework.runner.orm.support.SearchFilter;
import org.lework.runner.utils.Strings;
import org.lework.runner.web.AbstractController;
import org.lework.runner.web.NotificationType;
import org.lework.runner.web.datatables.DataTableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户信息Controller
 *
 * @author Gongle
 */
@Controller
@RequestMapping(value = "role")
public class RoleController extends AbstractController {

    @Autowired
    private RoleService roleService;



    /**
     * list页面*
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list() {

        return "role/role";
    }

    /**
     * 修改页面
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@ModelAttribute("entity") Role role ,Model model){
        model.addAttribute("statusList" , Status.values() ) ;
        return  "role/role-update" ;
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@Valid @ModelAttribute("entity") Role role, BindingResult result,
                       HttpServletResponse response) {

        if (result.hasErrors()) {
            actionCallback(response, "保存失败!" + result.toString() , null, NotificationType.ERROR);
        }
        try {
            roleService.saveRole(role);
            actionCallback(response, "保存成功!", null, NotificationType.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            actionCallback(response, "保存失败!" + e.toString(), null, NotificationType.ERROR);
        }

    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestParam(value = "deleteId" ,required = false) String deleteId,
                       @RequestParam(value = "deleteIds" ,required = false) String deleteIds,
                       HttpServletResponse response) {

        try {
            //单个删除
            if(Strings.isNotBlank(deleteId)){
                roleService.deleteRole(deleteId);
                actionCallback(response, "删除成功!", null, NotificationType.SUCCESS);
            }else if(Strings.isNotBlank(deleteIds)){   //多个删除
               String []  ids  = Strings.split(deleteIds,",");
               roleService.deleteRoles(ids);
                actionCallback(response, "删除多条成功!", null, NotificationType.SUCCESS);
            }


        } catch (Exception e) {
            e.printStackTrace();
            actionCallback(response, "删除失败!" + e.toString(), null, NotificationType.ERROR);
        }

    }
    /**
     * 查看
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@ModelAttribute("entity") Role role ,Model model) {
        model.addAttribute("statusList" , Status.values() ) ;
        return "role/role-view";
    }
    /**
     * datatables  json result*
     */
    @RequestMapping(value = "/getDatatablesJson", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    DataTableResult<Role> getDatatablesJson(
            @PageableDefaults(pageNumber = 0, value = 10) Pageable pageable,
            @RequestParam(value = "sSearch", required = false) String sSearch) {

        List<SearchFilter> filters = Lists.newArrayList();
        if (Strings.isNotBlank(sSearch)) {
            filters.add(new SearchFilter("LIKES_name_OR_code", sSearch));
        }

        Page<Role> page = roleService.searchPageRole(pageable, filters);

        return DataTableResult.build(page);
    }

    /**
     * 验证角色代码是否可用
     * @return JSON
     */
    @RequestMapping(value = "/validateRoleCode", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    Boolean validateRoleCode(@RequestParam(value = "id", required = false) String id,
                             @RequestParam(value = "code", required = true) String code) {

        return roleService.validateRoleCode(id, code);
    }

    /**
     * Preparable二次部分绑定的效果
     */
    @ModelAttribute("entity")
    public void prepareModel(Model model, @RequestParam(value = "id", required = false) String id) {
        if (Strings.isNotBlank(id)) {
            model.addAttribute("entity", roleService.getRole(id));
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
