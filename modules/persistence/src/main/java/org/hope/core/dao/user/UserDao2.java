package org.hope.core.dao.user;

import org.hope.core.entity.user.User;
import org.hope.orm.JpaDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 使用Abstract Common Dao实现dao层
 *
 * @see JpaDao
 *      User: Gongle
 *      Date: 13-9-10
 */
@Repository
public class UserDao2 extends JpaDao<User, String> {
    public static final String QUERY_ONE = "from User where 1=1";
    public static final String QUERY_ALL = "from User where 1=1";

    public User findOne(String id) {
        return get(id);
    }

    public List<User> findAll() {
        return super.findAll();
    }

    public Page<User> findPageUser(Pageable pageable) {
        return findPage(pageable, QUERY_ALL);
    }

    public User findByLoginName(String loginName) {
        return findUnique("from User where loginName=?", loginName);
    }
}
