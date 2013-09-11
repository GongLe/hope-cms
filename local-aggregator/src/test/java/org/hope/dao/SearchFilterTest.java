package org.hope.dao;

import com.google.common.collect.Lists;
import org.hope.core.entity.user.User;
import org.hope.core.service.account.AccountService;
import org.hope.orm.support.SearchFilter;
import org.hope.test.spring.Profiles;
import org.hope.test.spring.SpringTransactionalTestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 测试jpa属性过滤器
 */
@ContextConfiguration(locations = {"/applicationContext-root.xml"})
@ActiveProfiles(Profiles.DEVELOPMENT)
public class SearchFilterTest extends SpringTransactionalTestCase {
    private static Logger logger = LoggerFactory.getLogger(SearchFilterTest.class);
    @Autowired
    private AccountService accountService;

    @Test
    public void pageFilter() {
        List<SearchFilter> filters = Lists.newArrayList(new SearchFilter("LIKES_loginName_OR_name", "admin"));
        Page<User> pageUser = accountService.searchPageUser(new PageRequest(0, 10), filters);
        Assert.isTrue(pageUser.getTotalElements() == 1);
    }
}
