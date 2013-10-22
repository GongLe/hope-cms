package org.lework.core.service.role.impl;


import org.lework.core.dao.role.RoleDao;
import org.lework.core.entity.role.Role;
import org.lework.core.service.role.RoleService;
import org.lework.runner.orm.support.SearchFilter;
import org.lework.runner.orm.support.Specifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
