package com.jeeno.oauth2authorize.repository;

import com.jeeno.oauth2authorize.pojo.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/23 16:50
 */
@Repository
public interface UserRepository extends JpaRepository<UserDO, Long> {

    /**
     * 根据用户名查找用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UserDO findByUsername(String username);
}
