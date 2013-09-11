package org.hope.core.dao.permission;

import org.hope.core.entity.permission.Permission;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 权限Dao
 * User: Gongle
 * Date: 13-9-9
 * Time: 下午9:24
 */
public interface PermissionDao extends PagingAndSortingRepository<Permission, String>, JpaSpecificationExecutor<Permission> {
    public Permission findByCode(String code);
}
