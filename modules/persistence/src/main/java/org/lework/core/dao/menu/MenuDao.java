package org.lework.core.dao.menu;

import org.lework.core.entity.menu.Menu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Menu Dao
 * User: Gongle
 * Date: 13-10-22
 */
public interface MenuDao extends PagingAndSortingRepository<Menu, String>, JpaSpecificationExecutor<Menu> {
    public Menu findByCode(String code);
}
