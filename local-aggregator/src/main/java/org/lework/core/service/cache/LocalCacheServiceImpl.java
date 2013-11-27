package org.lework.core.service.cache;

import net.sf.ehcache.CacheManager;
import org.lework.core.utils.CacheUtils;
import org.lework.runner.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Encache Cache Service
 * User: Le
 * Date: 13-10-2
 */
@Service
public class LocalCacheServiceImpl implements CacheService {
    public static final String DEFAULT_CACHE_NAME = "DEFAULT_CACHE";

    @Autowired
    private CacheManager cacheManager;

    @Override
    public String getAppCachePrefix() {
        return "LOCAL_";
    }

    @Override
    public String getKey(String key) {
        return getAppCachePrefix() + key;
    }

    @Override
    public void updateCacheValue(String key, Object value) {
        CacheUtils.updateValue(cacheManager, DEFAULT_CACHE_NAME, getKey(key), value, new Long(DEFAULT_EXPIRY).intValue());
    }


    @Override
    public void updateCacheValue(String key, Object value, long expiry) {
        if (expiry == 0) {
            expiry = DEFAULT_EXPIRY;
        }
        CacheUtils.updateValue(cacheManager, DEFAULT_CACHE_NAME, getKey(key), value, new Long(expiry).intValue());
    }


    @Override
    public Object getCacheValue(String key) {
        return CacheUtils.getObjectValue(cacheManager, DEFAULT_CACHE_NAME, getKey(key));
    }


    @Override
    public void deleteCache(String key) {
        CacheUtils.invalidateValue(cacheManager, DEFAULT_CACHE_NAME, getKey(key));
    }


    @Override
    public List<String> getCacheKeys() {
        // TODO Auto-generated method stub
        return null;
    }

}
