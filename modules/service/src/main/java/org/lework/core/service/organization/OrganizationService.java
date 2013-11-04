package org.lework.core.service.organization;

import org.lework.core.entity.organization.Organization;
import org.lework.core.entity.permission.Permission;
import org.lework.runner.orm.support.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Organization Service 层
 * User: Gongle
 * Date: 13-10-22
 */
public interface OrganizationService {

    /**
     * 根据上级组织ID获取下级组织
     * @param parentId 上级ID
     */
    public List<Organization> getSubOrgByParentId(String parentId)  ;

    /**
     *验证code值是否有效.
     * @param id
     * @param code 组织code
     */
    public boolean validateOrgCode(String id ,String code ) ;

    public Organization getOrganization(String id);

    public List<Organization> getAllOrganizations();

    public Organization getOrganizationByCode(String code);

    public void saveOrganization(Organization entity);

    public void deleteOrganization(String id);

    public void deleteOrganization(Organization entity);

    public Page<Organization> getPageOrganization(Pageable pageable);


    /**
     * 获取一个分页
     *
     * @param pageable 分页信息
     * @param filters  属性过滤器
     * @return Page<Organization>
     * @see Page
     * @see Pageable
     * @see org.lework.runner.orm.support.SearchFilter
     */
    public Page<Organization> searchPageOrganization(Pageable pageable, List<SearchFilter> filters);

}
