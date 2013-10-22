package org.lework.core.service.permission.impl;

import org.lework.core.dao.permission.PermissionDao;
import org.lework.core.entity.permission.Permission;
import org.lework.core.service.permission.PermissionService;
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
 * Permission Service implement
 * @author Gongle
 */

@Service
@Transactional
public class PermissionServiceImpl  implements PermissionService{

    private static Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    private PermissionDao permissionDao;

    @Autowired
    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public Page<Permission> searchPagePermission(Pageable pageable, List<SearchFilter> filters) {
        Specification<Permission> spec = Specifications.build(filters, Permission.class);
        return permissionDao.findAll(spec, pageable);

    }

    @Override
    public Page<Permission> getPagePermission(Pageable pageable) {
        return permissionDao.findAll(pageable) ;
    }

    @Override
    public void deletePermission(Permission entity) {
        permissionDao.delete(entity);
    }

    @Override
    public void deletePermission(String id) {
        permissionDao.delete(id);
    }

    @Override
    public void savePermission(Permission entity) {
         permissionDao.save(entity) ;
    }

    @Override
    public Permission getPermissionByCode(String code) {
        return  permissionDao.findByCode(code) ;
    }

    @Override
    public List<Permission> getAllPermission() {
       return  (List<Permission> ) permissionDao.findAll() ;
    }

    @Override
    public Permission getPermission(String id) {
         return  permissionDao.findOne(id) ;
    }
}
