package org.lework.core.service.cache;

import org.lework.runner.service.CacheService;
import org.lework.runner.spring.Profiles;
import org.lework.runner.spring.SpringTransactionalTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

/**
 * Cache Service Test Case
 */

@ContextConfiguration(locations = {"/applicationContext-root.xml"})
@ActiveProfiles(Profiles.DEVELOPMENT)
public class LocalCacheServiceTest extends SpringTransactionalTestCase {
    @Autowired
    private CacheService cacheService;
    private static String CACHE_KEY = "test_cache";
    private static String CACHE_VAL = "123456";

    @Test
    public void crud_cache() {
        cacheService.updateCacheValue(CACHE_KEY, CACHE_VAL);
        Assert.isTrue(cacheService.getCacheValue(CACHE_KEY).equals(CACHE_VAL));
        cacheService.deleteCache(CACHE_KEY);
        Assert.isTrue(cacheService.getCacheValue(CACHE_KEY) == null);
    }


}
