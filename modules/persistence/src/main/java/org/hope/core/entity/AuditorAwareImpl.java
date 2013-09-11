package org.hope.core.entity;

import org.springframework.data.domain.AuditorAware;

/**
 * Spring data jpa AuditorAware implement
 * User: Gongle
 * Date: 13-9-8
 * Time: 下午1:09
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        return "SYSTEM";  //To change body of implemented methods use File | Settings | File Templates.
    }
}
