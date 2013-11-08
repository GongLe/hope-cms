package org.lework.core.web.menu;

import com.google.common.collect.Lists;
import org.lework.core.common.enumeration.Status;
import org.lework.core.entity.menu.Menu;
import org.lework.core.service.menu.MenuService;
import org.lework.core.service.menu.MenuTreeGridDTO;
import org.lework.runner.orm.support.SearchFilter;
import org.lework.runner.utils.Collections3;
import org.lework.runner.utils.Strings;
import org.lework.runner.web.AbstractController;
import org.lework.runner.web.NotificationType;
import org.lework.runner.web.datatables.DataTableResult;
import org.lework.runner.web.easyui.TreeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Menu Controller
 * User: Gongle
 * Date: 13-10-22
 */
@Controller
@RequestMapping(value = "menu")
public class MenuController extends AbstractController {

    @Autowired
    private MenuService menuService;

    /**
     * list页面*
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list() {

        return "menu/menu";
    }

    /**
     * 修改页面
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@ModelAttribute("entity") Menu menu ,Model model){
        model.addAttribute("statusList" , Status.values() ) ;

        return  "menu/menu-update" ;
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@Valid @ModelAttribute("entity") Menu menu, BindingResult result,
                       HttpServletResponse response) {

        if (result.hasErrors()) {
            actionCallback(response, "保存失败!" + result.toString() , null, NotificationType.ERROR);
        }
        try {
            menuService.saveMenu(menu);
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
                menuService.deleteMenu(deleteId);
                actionCallback(response, "删除成功!", null, NotificationType.SUCCESS);
            }else if(Strings.isNotBlank(deleteIds)){   //多个删除
                String []  ids  = Strings.split(deleteIds,",");
                menuService.deleteMenus(ids);
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
    public String view(@ModelAttribute("entity") Menu menu ,Model model) {
        model.addAttribute("statusList" , Status.values() ) ;
        return "menu/menu-view";
    }

    /**
     * http://fontawesome.io/3.2.1/icons/
     */
    @RequestMapping(value = "/fontawesome", method = RequestMethod.GET)
    public String fontawesome(Model model) {
        return "menu/fontawesome";
    }

    /**======================
     *       ajax json data
     * ======================
     **/

    /**
     * 验证角色代码是否可用
     *
     * @return JSON true || false
     */
    @RequestMapping(value = "/validateMenuCode", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    Boolean validateMenuCode(@RequestParam(value = "menuId", required = false) String id,
                             @RequestParam(value = "code", required = true) String code) {
        return menuService.validateMenuCode(id, code);
    }


    /**
     * datatables  json result*
     */
    @RequestMapping(value = "/getDatatablesJson", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    DataTableResult<Menu> getDatatablesJson(
            @PageableDefaults(pageNumber = 0, value = 10) Pageable pageable,
            @RequestParam(value = "sSearch", required = false) String sSearch) {

        List<SearchFilter> filters = Lists.newArrayList();
        if (Strings.isNotBlank(sSearch)) {
            filters.add(new SearchFilter("LIKES_name_OR_code", sSearch));
        }

        Page<Menu> page = menuService.searchPageMenu(pageable, filters);

        return DataTableResult.build(page);
    }

    /**
     * get  easyui treeGrid json
     *
     * @param ignoreNodeId 隐藏节点,和子节点
     */
    @RequestMapping(value = "/getTreeGrid", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
    List<MenuTreeGridDTO> getTreeGrid(@RequestParam(value = "ignoreNodeId", required = false) String ignoreNodeId) {
        List<Menu> ignoreNodes = menuService.getSelfAndChildMenus(ignoreNodeId);
        return menuService.getMenuTreeGrid(ignoreNodes);
    }

    /**
     * get  easyui tree json
     *
     * @param ignoreNodeId 隐藏节点,和子节点
     */
    @RequestMapping(value = "/getTree", method = {RequestMethod.GET, RequestMethod.POST})
    public
    @ResponseBody
     List<TreeResult>  getTree(@RequestParam(value = "ignoreNodeId", required = false) String ignoreNodeId) {
        List<Menu> ignoreNodes = menuService.getSelfAndChildMenus(ignoreNodeId);
        TreeResult root = new TreeResult("root","上级菜单",Strings.EMPTY,"root") ;
        root.getChildren().addAll( menuService.getMenuTree(ignoreNodes))  ;
        return  menuService.getMenuTree(ignoreNodes) ;
    }


}
