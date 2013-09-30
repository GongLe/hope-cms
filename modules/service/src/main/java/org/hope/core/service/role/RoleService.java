package org.hope.core.service.role;

import org.hope.core.entity.role.Role;
import org.hope.runner.orm.support.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色Service层
 * User: Gongle
 * Date: 13-9-9
 * Time: 下午9:48
 */
public interface RoleService {

    public Role getRole(String id);

    public List<Role> getAllRole();


    public Role getRoleByCode(String code);


    public void saveRole(Role entity);

    public void deleteRole(String id);

    public void deleteRole(Role entity);

    public Page<Role> getPageRole(Pageable pageable);

    /**
     * 获取一个分页角色信息
     *
     * @param pageable 分页信息
     * @param filters  属性过滤器
     * @return Page<Role>
     * @see Page
     * @see Pageable
     * @see org.hope.runner.orm.support.SearchFilter
     */
    public Page<Role> searchPageRole(Pageable pageable, List<SearchFilter> filters);
}