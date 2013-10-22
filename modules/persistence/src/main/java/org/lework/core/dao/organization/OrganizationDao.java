package org.lework.core.dao.organization;

import org.lework.core.entity.organization.Organization;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Organization Dao
 * User: Gongle
 * Date: 13-10-22
 */
public interface OrganizationDao extends PagingAndSortingRepository<Organization, String>, JpaSpecificationExecutor<Organization> {

    public Organization findByCode(String code);
}
