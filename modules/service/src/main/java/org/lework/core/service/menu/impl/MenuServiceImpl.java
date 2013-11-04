package org.lework.core.service.menu.impl;

import org.lework.core.dao.menu.MenuDao;
import org.lework.core.dao.permission.PermissionDao;
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
public class MenuServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    private MenuDao menuDao;
}
