package org.lework.core.service.menu.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.lework.core.common.enumeration.Status;
import org.lework.core.dao.menu.MenuDao;
import org.lework.core.dao.permission.PermissionDao;
import org.lework.core.entity.menu.Menu;
import org.lework.core.entity.role.Role;
import org.lework.core.service.menu.MenuService;
import org.lework.core.service.menu.MenuTreeGridDTO;
import org.lework.runner.orm.support.SearchFilter;
import org.lework.runner.orm.support.Specifications;
import org.lework.runner.utils.Collections3;
import org.lework.runner.utils.Strings;
import org.lework.runner.web.easyui.TreeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Menu Service implement
 * User: Gongle
 * Date: 13-10-22
 */

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    private MenuDao menuDao;

    @Autowired
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public void downSortNum(Menu entity) {
        List<Menu> siblings;
        int curIndex = -1;
        int temp = -1;
        Menu next;
        //如果是根节点
        if (!entity.hasParent()) {
            siblings = menuDao.findRoots();
        } else {  //非根节点时,根据parentId获取同级所有节点
            siblings = menuDao.findChildMenusByParentId(entity.getParentId());
        }
        if (Collections3.isNotEmpty(siblings) && siblings.size() > 1) {
            curIndex = siblings.indexOf(entity);
            if (curIndex < siblings.size() - 1) {
                next = siblings.get(curIndex + 1);
                //互换sortNum
                temp = next.getSortNum();
                next.setSortNum(entity.getSortNum());
                entity.setSortNum(temp);
            }
        }
    }

    @Override
    public void upSortNum(Menu entity) {
        List<Menu> siblings;
        int curIndex = -1;
        int temp = -1;
        Menu previous;
        //如果是根节点
        if (!entity.hasParent()) {
            siblings = menuDao.findRoots();
        } else {  //非根节点时,根据parentId获取同级所有节点
            siblings = menuDao.findChildMenusByParentId(entity.getParentId());
        }
        if (Collections3.isNotEmpty(siblings) && siblings.size() > 1) {
            curIndex = siblings.indexOf(entity);
            if (curIndex > 0) {
                previous = siblings.get(curIndex - 1);
                //互换sortNum
                temp = previous.getSortNum();
                previous.setSortNum(entity.getSortNum());
                entity.setSortNum(temp);
            }
        }
    }

    @Override
    public boolean validateMenuCode(String id, String code) {
        Menu entity = menuDao.findByCode(code);
        if (entity == null) {  //code不存在
            return true;
        } else if (Strings.isBlank(id)) {   //新增操作
            return entity == null;
        } else {   //修改操作
            return entity.getId().equals(id);
        }
    }

    @Override
    public Menu getMenu(String id) {
        if(Strings.isBlank(id))
            return  null ;
        return menuDao.findOne(id);
    }

    @Override
    public List<Menu> getAllMenus() {
        return (List<Menu>) menuDao.findAll();
    }

    @Override
    public Menu getMenuByCode(String code) {
        return menuDao.findByCode(code);
    }

    @Override
    public List<Menu> getAllMenuByStatus(Status status) {
        Validate.notNull(status);
        return menuDao.findAllByStatus(status.getCode());
    }

    @Override
    public List<Menu> getRootMenus() {
        return    menuDao.findRootMenus();
    }

    @Override
    public List<Menu> getChildMenusByParentId(String parentId) {
        return  menuDao.findChildMenusByParentId(parentId) ;
    }

    @Override
    public List<Menu> getRoleMenusByStatus(Role role, Status status) {
        return menuDao.findRoleMenusByStatus(role.getId(), status.getCode());
    }

    @Override
    public List<Menu> getRoleMenus(Role role) {
        return menuDao.findRoleMenus(role.getId());
    }

    @Override
    public void saveMenu(Menu entity) {
        menuDao.save(entity);
    }

    @Override
    public void deleteMenu(String id) {
        menuDao.delete(id);
    }

    @Override
    public void deleteMenu(Menu entity) {
        menuDao.delete(entity);
    }

    @Override
    public void deleteMenus(List<String> ids) {
        if (Collections3.isEmpty(ids)) {
            return;
        }
        List<Menu> entities = (List<Menu>) menuDao.findAll(ids);
        menuDao.delete(entities);
    }

    @Override
    public void deleteMenus(String[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }
        List<Menu> entities = (List<Menu>) menuDao.findAll(Arrays.asList(ids));
        menuDao.delete(entities);
    }

    @Override
    public Page<Menu> getPageMenu(Pageable pageable) {
        return menuDao.findAll(pageable);
    }

    @Override
    public Page<Menu> searchPageMenu(Pageable pageable, List<SearchFilter> filters) {
        Specification<Menu> spec = Specifications.build(filters, Menu.class);
        return menuDao.findAll(spec, pageable);
    }

    @Override
    public List<Menu> getSelfAndChildMenus(String id) {
        List<Menu> ret = new ArrayList<Menu>();
        if (Strings.isBlank(id)) {
            return ret;
        }
        Menu entity = menuDao.findOne(id);
        ret.add(entity);
        fetchChild(entity, ret);
        return ret;
    }

    @Override
    public List<MenuTreeGridDTO> getMenuTreeGrid(List<Menu> ignore ) {
        //roots node
        List<Menu> roots = menuDao.findRoots();
        List<MenuTreeGridDTO> rootsDTO = new ArrayList<MenuTreeGridDTO>();
        MenuTreeGridDTO temp ;
        for(Menu root : roots ){
            if(contain(ignore,root) ){
                continue;
            }
            temp    = new MenuTreeGridDTO(root) ;
            rootsDTO.add(temp) ;
            fetchChild4TreeGrid(root,temp,ignore);
        }
        return rootsDTO;
    }

    @Override
    public List<TreeResult> getMenuTree(List<Menu> ignore) {
        //roots node
        List<Menu> roots = menuDao.findRoots();
        List<TreeResult> rootsDTO = new ArrayList<TreeResult>();
        TreeResult temp;
        for (Menu root : roots) {
            if (contain(ignore, root)) {
                continue;
            }
            temp = convert2TreeNode(root);
            rootsDTO.add(temp);
            fetchChild4Tree(root, temp, ignore);
        }
        return rootsDTO;
    }

    /**
     * 递归查找子节点到集合
     *
     * @param parent
     * @param collection
     */
    private void fetchChild(Menu parent, List<Menu> collection) {
        List<Menu> childMenu;
        if (parent.hasChild()) {
            childMenu = parent.getChildrenMenus();
            collection.addAll(childMenu);
            for (Menu m : childMenu) {
                if (m.hasChild()) {
                    fetchChild(m, collection);
                }
            }
        }
    }

    /**
     * 递归查找子节点 for easyui tree
     *
     * @param parent
     * @param parentDTO
     */
    private void fetchChild4Tree(Menu parent, TreeResult parentDTO ,List<Menu> ignore ) {    
        List<TreeResult> child = new ArrayList<TreeResult>();
        List<Menu> childMenu;
        TreeResult node;
        if (parent.hasChild()) {
            childMenu = parent.getChildrenMenus();
            for (Menu m : childMenu) {
                if(contain(ignore,m) ){  //忽略节点
                    continue;
                }
                node = convert2TreeNode(m) ;
                child.add(node);
                if (m.hasChild()) {
                    fetchChild4Tree(m, node,ignore);
                }
            }
        }
        parentDTO.getChildren().addAll(child);
    }

    /**
     * 递归查找子节点 for easyui treeGrid
     *
     * @param parent
     * @param parentDTO
     */
    private void fetchChild4TreeGrid(Menu parent, MenuTreeGridDTO parentDTO ,List<Menu> ignore ) {
        List<MenuTreeGridDTO> child = new ArrayList<MenuTreeGridDTO>();
        List<Menu> childMenu;
        MenuTreeGridDTO node;
        if (parent.hasChild()) {
            childMenu = parent.getChildrenMenus();
            for (Menu m : childMenu) {
                if(contain(ignore,m) ){  //忽略节点
                    continue;
                }
                node = new MenuTreeGridDTO(m);
                child.add(node);
                if (m.hasChild()) {
                    fetchChild4TreeGrid(m, node,ignore);
                }
            }
        }
        parentDTO.getChildren().addAll(child);
    }

    private TreeResult convert2TreeNode(Menu entity) {
        TreeResult ret = new TreeResult();
        ret.setId(entity.getId());
        ret.setText(entity.getName());
        if (Strings.equals(entity.getStatus(), Status.disable.getCode())) {
            ret.setIconCls("red");
        }
        ret.setState("open");
        ret.addAttribute("code", entity.getCode());
        return ret;

    }
    /**
     * 是否包含在集合
     *
     * @param dest
     * @param src
     */
    private boolean contain(List<Menu> dest, Menu src) {
        if (Collections3.isEmpty(dest)) {
            return false;
        }
        return dest.contains(src);
    }


}
