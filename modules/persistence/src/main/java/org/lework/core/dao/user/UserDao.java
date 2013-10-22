package org.lework.core.dao.user;

import org.lework.core.entity.user.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * User Dao
 */
public interface UserDao extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {
    User findByLoginName(String loginName);
    User findByEmail(String email);
}
