package com.jeeno.oauth2authorize.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Jeeno
 * @version 1.0.0
 * @date 2019/12/24 10:11
 */
@Getter
@Setter
@Entity
@ToString
@Builder
@Table(name = "client_info")
@AllArgsConstructor
@NoArgsConstructor
public class ClientDO implements ClientDetails {

    /**
     * 回调url 的分隔符
     */
    public static final String SEPARATOR = ",";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * client-id
     */
    private String clientId;
    /**
     * client-secret (加密后的密文)
     */
    private String clientSecret;
    /**
     * 回调url
     */
    private String directUrl;
    /**
     * 作用域
     */
    private String scope;
    /**
     * 授权类型
     */
    private String grantType;

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return getSet(scope);
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return getSet(grantType);
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return getSet(directUrl);
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return 300;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return 60;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    /**
     * 按字符串按分隔符','进行拆分
     * @param str 字符串
     * @return Collection
     */
    private Set<String> getSet(String str) {
        Set<String> result = new HashSet<>();
        if (StringUtils.isBlank(str)) {
            return result;
        }
        String[] urls = str.split(SEPARATOR);
        result.addAll(Arrays.asList(urls));
        return result;
    }

}
