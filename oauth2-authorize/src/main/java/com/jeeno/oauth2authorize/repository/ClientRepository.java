package com.jeeno.oauth2authorize.repository;

import com.jeeno.oauth2authorize.pojo.ClientDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/24 13:17
 */
@Repository
public interface ClientRepository extends JpaRepository<ClientDO, Long> {

    /**
     * 通过客户id查找客户信息
     * @param clientId 客户id
     * @return ClientDO
     */
    ClientDO getClientDOByClientId(String clientId);
}
