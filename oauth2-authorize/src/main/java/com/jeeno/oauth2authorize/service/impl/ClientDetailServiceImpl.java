package com.jeeno.oauth2authorize.service.impl;

import com.jeeno.oauth2authorize.pojo.ClientDO;
import com.jeeno.oauth2authorize.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/24 13:09
 */
@Slf4j
@Service("myClientDetailService")
public class ClientDetailServiceImpl implements ClientDetailsService {

    @Resource
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDO clientDO = clientRepository.getClientDOByClientId(clientId);
        if (clientDO == null) {
            throw new ClientRegistrationException("invalid client-id");
        }
        return clientDO;
    }

}
