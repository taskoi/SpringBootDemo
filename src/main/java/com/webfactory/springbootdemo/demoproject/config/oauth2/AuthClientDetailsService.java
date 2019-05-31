package com.webfactory.springbootdemo.demoproject.config.oauth2;

import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class AuthClientDetailsService implements ClientDetailsService {

    @Autowired
    AuthClientRepository authClientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        AuthClientDetails authClientDetails = authClientRepository.findByClientId(clientId);
        return new AuthClientDetailsImpl(authClientDetails);
    }
}
