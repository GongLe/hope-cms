package org.hope.core.web.role;

import com.google.common.collect.Lists;
import org.hope.core.entity.role.Role;
import org.hope.core.service.role.RoleService;
import org.hope.runner.orm.support.SearchFilter;
import org.hope.runner.orm.support.Specifications;
import org.hope.runner.utils.Strings;
import org.hope.runner.web.AbstractController;
import org.hope.runner.web.datatables.DataTableResult;
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

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, @PageableDefaults(pageNumber = 0, value = 30) Pageable pageable,
                       HttpServletRequest request) {
        List<SearchFilter> filters = SearchFilter.buildFromHttpRequest(request);
        roleService.searchPageRole(pageable, filters);
        return "role/role";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("entity") Role role, BindingResult result,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            logger.info(result.toString());
            prompt(redirectAttributes, "提示信息", "保存失败! " + result.toString(), notify_type_error);
            return "redirect:/role";
        }
        prompt(redirectAttributes, "提示信息", "修改成功!", notify_type_success);
        return "redirect:/role";
    }

    /**
     * datatables  json result*
     */
    @RequestMapping(value = "getDatatablesJson", method = {RequestMethod.GET, RequestMethod.POST})
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
