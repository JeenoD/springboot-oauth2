package com.jeeno.oauth2client.repository;

import com.jeeno.oauth2client.pojo.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/25 00:17
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
