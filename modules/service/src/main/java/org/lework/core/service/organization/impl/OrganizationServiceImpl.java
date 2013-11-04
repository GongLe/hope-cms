package org.lework.core.service.organization.impl;

import org.lework.core.dao.organization.OrganizationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Organization Service Implement
 * User: Gongle
 * Date: 13-10-22
 */

@Service
@Transactional
public class OrganizationServiceImpl  {
    private static Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private OrganizationDao organizationDao;
}
