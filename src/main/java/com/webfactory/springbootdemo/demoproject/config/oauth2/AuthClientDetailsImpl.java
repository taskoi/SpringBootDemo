package com.webfactory.springbootdemo.demoproject.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class AuthClientDetailsImpl implements ClientDetails {

    private AuthClientDetails authClientDetails;

    public AuthClientDetailsImpl(AuthClientDetails authClientDetails){
        this.authClientDetails = authClientDetails;
    }

    @Override
    public String getClientId() {
        return authClientDetails.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return authClientDetails.getResourceIds();
    }

    @Override
    public boolean isSecretRequired() {
        return authClientDetails.isSecretRequired();
    }

    @Override
    public String getClientSecret() {
        return authClientDetails.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return authClientDetails.isScoped();
    }

    @Override
    public Set<String> getScope() {
        return authClientDetails.getScope();
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authClientDetails.getAuthorizedGrantTypes();
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return authClientDetails.getRegisteredRedirectUri();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authClientDetails.getAuthorities();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return authClientDetails.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return authClientDetails.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return authClientDetails.getAdditionalInformation();
    }
}
