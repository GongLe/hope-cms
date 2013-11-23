package org.lework.core.service.account;

import org.lework.core.entity.user.User;
import org.lework.runner.orm.support.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Account Service
 */
public interface AccountService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;


    public List<User> getAllUser();

    public User getUser(String id);

    /**
     * 根据loginName查找用户
     *
     * @param loginName 用户名
     */
    public User getUserByLoginName(String loginName);

    /**
     * 根据Email查找用户
     *
     * @param email Email
     */
    public User getUserByEmail(String email);

    /**
     * 新建用户
     */
    public void registerUser(User user);

    /**
     * 修改用户
     */
    public void updateUser(User user);

    public Page<User> getPageUser(Pageable pageable);

    /**
     * 获取一个分页用户
     *
     * @param pageable 分页信息
     * @param filters  属性过滤器
     * @return Page<User>
     * @see Page
     * @see Pageable
     * @see org.lework.runner.orm.support.SearchFilter
     */
    public Page<User> searchPageUser(Pageable pageable, List<SearchFilter> filters);

    public void saveUser(User entity);

    public void deleteUser(User entity);

    public void deleteUser(String id);

    public void deleteUsers(List<String> ids);

    public void deleteUsers(String[] ids);

    /**
     * 验证用户名是否可用
     *
     * @param id        User主键
     * @param loginName 用户名
     * @return true:代码值可用,false:代码值不可用
     */
    public boolean validateLoginName(String id, String loginName);
    /**
     * 验证Email是否可用
     * @param id User主键
     * @param email 用户Email
     * @return  true:代码值可用,false:代码值不可用
     */
    public boolean validateEmail(String id, String email);

}