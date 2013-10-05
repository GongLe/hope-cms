package org.hope.core.service.account.impl;


import org.hope.core.dao.user.UserDao;
import org.hope.core.entity.user.User;
import org.hope.core.service.account.AccountService;
import org.hope.runner.orm.support.SearchFilter;
import org.hope.runner.orm.support.Specifications;
import org.hope.runner.security.Digests;
import org.hope.runner.utils.Encodes;
import org.hope.runner.utils.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private UserDao userDao;

    @Override
    public void deleteUser(User entity) {
        userDao.delete(entity);
    }

    @Override
    public void deleteUser(String id) {
        userDao.delete(id);
    }

    @Override
    public void saveUser(User entity) {
        userDao.save(entity) ;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return  userDao.findByLoginName(loginName);
    }

    public List<User> getAllUser() {
        return (List<User>) userDao.findAll();
    }

    public Page<User> getPageUser(Pageable pageable) {
        return userDao.findAll(pageable) ;
    }

    /**
     * 获取一个分页用户
     *
     * @param pageable 分页信息
     * @param filters 属性过滤器
     * @return Page<User>
     * @see Page
     * @see Pageable
     * @see SearchFilter
     */
    public Page<User> searchPageUser(Pageable pageable, List<SearchFilter> filters) {

        Specification<User> spec = Specifications.build(filters, User.class);
        return userDao.findAll(spec, pageable);
    }

    public User getUser(String id) {
        return userDao.findOne(id);
    }
    public void registerUser(User user) {
        entryptPassword(user);
        user.setUserRegistered(new Date());
        userDao.save(user);
    }

    public void updateUser(User user) {
        if (Strings.isNotBlank(user.getPlainPassword())) {
            entryptPassword(user);
        }
        userDao.save(user);
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }
}
