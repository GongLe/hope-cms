package org.lework.core.service.role;

import org.lework.core.common.enumeration.Status;
import org.lework.core.entity.role.Role;
import org.lework.core.entity.user.User;
import org.lework.runner.orm.support.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色Service层
 * User: Gongle
 * Date: 13-9-9
 */
public interface RoleService {

    public  boolean validateRoleCode(String id ,String code ) ;

    public Role getRole(String id);

    public List<Role> getAllRole();

    public List<Role> getAllRoleByStatus(Status status );

    /**
     * 获取用户拥有的角色
     * @param user
     * @param status
     * @return
     */
    public List<Role> getUserRolesByStatus(User user , Status status) ;

    public Role getRoleByCode(String code);


    public void saveRole(Role entity);

    public void deleteRole(String id);

    public void deleteRoles(List<String> ids );

    public void deleteRoles(String[] ids );

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
     * @see org.lework.runner.orm.support.SearchFilter
     */
    public Page<Role> searchPageRole(Pageable pageable, List<SearchFilter> filters);



}
