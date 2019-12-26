package com.jeeno.oauth2authorize.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/23 10:48
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource(name = "myClientDetailService")
    private ClientDetailsService myClientDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        //这里client使用存在模式，可以实际过程调整为jdbc的方式
//        //这里说明一下，redirectUris的连接可以是多个，这里通过access_token都可以访问的
//        //简单点，就是授权的过程
//        clients.inMemory()
//                .withClient("client")
//                .secret(passwordEncoder.encode("secret"))
//                .authorizedGrantTypes("authorization_code", "refresh_token")
//                .scopes("All")
//                .autoApprove(true)
//                .redirectUris("http://localhost:9001/login", "http://www.baidu.com", "http://localhost:9001/hi");
        clients.withClientDetails(myClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //权限控制
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 认证体系使用security的方式
        endpoints.authenticationManager(authenticationManager);
        // 允许调用方式
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        // 使用refresh_token刷新令牌，必须要
        endpoints.userDetailsService(userDetailsService);
        DefaultTokenServices tokenServices = (DefaultTokenServices) endpoints.getDefaultAuthorizationServerTokenServices();
        // 指定token存储方式
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(myClientDetailsService);
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        // 令牌有效时长（秒）
        tokenServices.setAccessTokenValiditySeconds(120);
        // 刷新令牌周期（秒）
        tokenServices.setRefreshTokenValiditySeconds(60);
        endpoints.tokenServices(tokenServices);
        // 自定义的授权页面
        endpoints.pathMapping("/oauth/confirm_access","/custom/confirm_access");
    }

    @Bean
    public TokenStore tokenStore() {
        //token保存在内存中（也可以保存在数据库、Redis中）。
        //如果保存在中间件（数据库、Redis），那么资源服务器与认证服务器可以不在同一个工程中。
        //注意：如果不保存access_token，则没法通过access_token取得用户信息
        return new RedisTokenStore(redisConnectionFactory);
    }


}
