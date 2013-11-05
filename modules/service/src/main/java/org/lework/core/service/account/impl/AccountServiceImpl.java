package org.lework.core.service.account.impl;


import org.apache.commons.lang3.ArrayUtils;
import org.lework.core.dao.user.UserDao;
import org.lework.core.entity.role.Role;
import org.lework.core.entity.user.User;
import org.lework.core.service.account.AccountService;
import org.lework.runner.orm.support.SearchFilter;
import org.lework.runner.orm.support.Specifications;
import org.lework.runner.security.Digests;
import org.lework.runner.utils.Collections3;
import org.lework.runner.utils.Encodes;
import org.lework.runner.utils.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 验证用户名是否可用
     * @param id User主键
     * @param loginName 用户名
     * @return  true:代码值可用,false:代码值不可用
     */
    @Override
    public boolean validateLoginName(String id, String loginName) {
        User  entity = userDao.findByLoginName(loginName) ;
        if (entity == null) {  //loginName
            return true;
        } else if (Strings.isBlank(id)) {   //新增操作
            return entity == null;
        } else {   //修改操作
            return entity.getId().equals(id);
        }
    }

    @Override
    public boolean validateEmail(String id, String email) {
        User  entity = userDao.findByEmail(email)   ;
        if (entity == null) {  //email
            return true;
        } else if (Strings.isBlank(id)) {   //新增操作
            return entity == null;
        } else {   //修改操作
            return entity.getId().equals(id);
        }
    }

    @Override
    public void deleteUsers(String[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }
        List<User> entities = (List<User>) userDao.findAll(Arrays.asList(ids));
        userDao.delete(entities);
    }

    @Override
    public void deleteUsers(List<String> ids) {
        if (Collections3.isEmpty(ids)) {
            return;
        }
        List<User> entities = (List<User>) userDao.findAll(ids);
        userDao.delete(entities);
    }

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
