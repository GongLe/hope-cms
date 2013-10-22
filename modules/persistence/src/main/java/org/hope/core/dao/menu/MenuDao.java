package org.hope.core.dao.menu;

import org.hope.core.entity.menu.Menu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Menu Dao
 * User: Gongle
 * Date: 13-10-22
 */
public interface MenuDao extends PagingAndSortingRepository<Menu, String>, JpaSpecificationExecutor<Menu> {
}
