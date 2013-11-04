package org.lework.core.service.role.impl;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.lework.core.common.enumeration.Status;
import org.lework.core.dao.role.RoleDao;
import org.lework.core.entity.role.Role;
import org.lework.core.entity.user.User;
import org.lework.core.service.role.RoleService;
import org.lework.runner.orm.support.SearchFilter;
import org.lework.runner.orm.support.Specifications;
import org.lework.runner.utils.Collections3;
import org.lework.runner.utils.Strings;
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
 * Role Service implement
 * @author Gongle
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getUserRolesByStatus(User user, Status status) {
        if (user == null) {
            return new ArrayList<Role>();
        }

        Validate.notNull(status);
        return roleDao.findUserRolesByStatus(user.getId(), status.getCode());
    }

    @Override
    public List<Role> getAllRoleByStatus(Status status) {
        Validate.notNull(status) ;
        return  roleDao.findAllByStatus(status.getCode() ) ;
    }

    /**
     * @param id   角色主键
     * @param code 角色代码
     * @return true:代码值可用,false:代码值不可用
     */
    @Override
    public boolean validateRoleCode(String id, String code) {
        Role entity = roleDao.findByCode(code);
        if (entity == null) {  //code不存在
            return true;
        } else if (Strings.isBlank(id)) {   //新增操作
            return entity == null;
        } else {   //修改操作
            return entity.getId().equals(id);
        }
    }

    @Override
    public Page<Role> searchPageRole(Pageable pageable, List<SearchFilter> filters) {

        Specification<Role> spec = Specifications.build(filters, Role.class);
        return roleDao.findAll(spec, pageable);
    }

    @Override
    public Page<Role> getPageRole(Pageable pageable) {
        return roleDao.findAll(pageable);
    }

    @Override
    public void deleteRole(Role entity) {
        roleDao.delete(entity);
    }

    @Override
    public void deleteRole(String id) {
        roleDao.delete(id);
    }

    @Override
    public void deleteRoles(List<String> ids) {
        if(Collections3.isEmpty(ids)){
            return;
        }
        List<Role> entities = (List<Role>) roleDao.findAll(ids) ;
        roleDao.delete(entities);
    }

    @Override
    public void deleteRoles(String[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }
        List<Role> entities = (List<Role>) roleDao.findAll(Arrays.asList(ids));
        roleDao.delete(entities);
    }

    @Override
    public void saveRole(Role entity) {
        roleDao.save(entity);
    }

    @Override
    public Role getRoleByCode(String code) {
        return roleDao.findByCode(code);
    }

    @Override
    public List<Role> getAllRole() {
        return (List<Role>) roleDao.findAll();
    }

    @Override
    public Role getRole(String id) {
        return roleDao.findOne(id);

    }
}
