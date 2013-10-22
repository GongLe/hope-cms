package org.lework.core.service.permission;

import org.lework.core.entity.permission.Permission;
import org.lework.runner.orm.support.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 权限Service层
 * User: Gongle
 * Date: 13-9-9
 */
public interface PermissionService {

    public Permission getPermission(String id);

    public List<Permission> getAllPermission();


    public Permission getPermissionByCode(String code);

    public void savePermission(Permission entity);

    public void deletePermission(String id);

    public void deletePermission(Permission entity);

    public Page<Permission> getPagePermission(Pageable pageable);

    /**
     * 获取一个分页
     *
     * @param pageable 分页信息
     * @param filters  属性过滤器
     * @return Page<Permission>
     * @see Page
     * @see Pageable
     * @see org.lework.runner.orm.support.SearchFilter
     */
    public Page<Permission> searchPagePermission(Pageable pageable, List<SearchFilter> filters);
}
