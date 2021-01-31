package com.github.eagle.service;


import com.github.eagle.domain.User;

import java.util.List;

/**
 * 用户管理模块的service组件接口
 *
 * @author Edwin Wu
 * @since 1.0.0
 */
public interface UserService {

    /**
     * 查询所有用户
     *
     * @return 用户信息
     */
    List<User> listUsers();

    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserById(Long id);

    /**
     * 新增用户
     *
     * @param user 用户信息
     */
    Long saveUser(User user);

    /**
     * 更新用户
     *
     * @param user 用户信息
     */
    Boolean updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    Boolean removeUser(Long id);

}
