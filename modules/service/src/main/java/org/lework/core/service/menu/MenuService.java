package org.lework.core.service.menu;

import org.lework.core.entity.menu.Menu;
import org.lework.core.entity.organization.Organization;
import org.lework.runner.orm.support.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 菜单Service层
 * User: Gongle
 * Date: 13-10-22
 */
public interface MenuService {

    public Menu getMenu(String id);

    public List<Menu> getAllMenus();

    public Menu getMenuByCode(String code);

    public void saveMenu(Menu entity);

    public void deleteMenu(String id);

    public void deleteMenu(Menu entity);

    public Page<Menu> getPageMenu(Pageable pageable);

    /**
     * 获取一个分页
     *
     * @param pageable 分页信息
     * @param filters  属性过滤器
     * @return Page<Menu>
     * @see Page
     * @see Pageable
     * @see org.lework.runner.orm.support.SearchFilter
     */
    public Page<Menu> searchPageMenu(Pageable pageable, List<SearchFilter> filters);
}
