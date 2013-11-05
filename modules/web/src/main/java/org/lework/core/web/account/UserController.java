package org.lework.core.web.account;

import com.google.common.collect.Lists;
import org.lework.core.common.enumeration.Status;
import org.lework.core.entity.user.User;
import org.lework.core.service.account.AccountService;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户Controller
 *
 * @author Gongle
 */
@Controller
@RequestMapping(value = "user")
public class UserController  extends AbstractController {
    @Autowired
    private AccountService accountService ;


    /**
     * list页面*
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list() {

        return "user/user";
    }

    /**
     * 修改页面
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@ModelAttribute("entity") User user ,Model model){
        model.addAttribute("statusList" , Status.values() ) ;
        return  "user/user-update" ;
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@Valid @ModelAttribute("entity") User user , BindingResult result,
                       HttpServletResponse response) {

        if (result.hasErrors()) {
            actionCallback(response, "保存失败!" + result.toString() , null, NotificationType.ERROR);
        }
        try {
            accountService.saveUser(user);
            actionCallback(response, "用户保存成功!", null, NotificationType.SUCCESS);
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
                accountService.deleteUser(deleteId);
                actionCallback(response, "删除成功!", null, NotificationType.SUCCESS);
            }else if(Strings.isNotBlank(deleteIds)){   //多个删除
                String []  ids  = Strings.split(deleteIds,",");
                accountService.deleteUsers(ids);
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
    public String view(@ModelAttribute("entity") User user ,Model model) {
        model.addAttribute("statusList" , Status.values() ) ;
        return "user/user-view";
    }

    /**
     * datatables  json result*
     */
    @RequestMapping(value = "/getDatatablesJson", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    DataTableResult<User> getDatatablesJson(
            @PageableDefaults(pageNumber = 0, value = 10) Pageable pageable,
            @RequestParam(value = "sSearch", required = false) String sSearch) {

        List<SearchFilter> filters = Lists.newArrayList();
        if (Strings.isNotBlank(sSearch)) {
            //匹配用户名 or 姓名
            filters.add(new SearchFilter("LIKES_loginName_OR_name", sSearch));
        }

        Page<User> page = accountService.searchPageUser(pageable, filters);

        return DataTableResult.build(page);
    }

    /**
     * 验证用户Email是否可用
     *
     * @return JSON true || false
     */
    @RequestMapping(value = "/validateEmail", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    Boolean validateUserEmail(@RequestParam(value = "userId", required = false) String id,
                              @RequestParam(value = "email", required = true) String email) {

        return accountService.validateEmail(id, email);
    }

    /**
     * 验证用户登录名是否可用
     *
     * @return JSON true || false
     */
    @RequestMapping(value = "/validateLoginName", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    Boolean validateUserLoginName(@RequestParam(value = "userId", required = false) String id,
                                  @RequestParam(value = "loginName", required = true) String loginName) {

        return accountService.validateLoginName(id, loginName);
    }

    /**
     * Preparable二次部分绑定的效果
     */
    @ModelAttribute("entity")
    public void prepareModel(Model model, @RequestParam(value = "id", required = false) String id) {
        if (Strings.isNotBlank(id)) {
            model.addAttribute("entity", accountService.getUser(id));
        } else {
            model.addAttribute("entity", new User());
        }
    }

    /**
     * 不自动绑定对象中的roles属性
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("roles");
    }

}
