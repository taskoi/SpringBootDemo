package com.webfactory.springbootdemo.demoproject.config;

import com.webfactory.springbootdemo.demoproject.config.oauth2.AuthClientDetailsService;
import com.webfactory.springbootdemo.demoproject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
@Import(ServerSecurityConfig.class)
//Resource Owner - Client access and Authorization server endpoint
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//    @PostConstruct
//    public void a() {
//        String password = passwordEncoder.encode("password");
//        System.out.println("password = " + password);
//
//        String password1 = passwordEncoder.encode("password");
//        System.out.println("password1 = " + password1);
//    }

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final CustomUserDetailsService userDetailsService;

    private final AuthClientDetailsService authClientDetailsService;

    public AuthorizationServerConfig(@Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService, AuthClientDetailsService authClientDetailsService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authClientDetailsService = authClientDetailsService;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(authClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);

    }

    @Bean
    public TokenStore tokenStore() {
        //return new JdbcTokenStore(dataSource);
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("/api");
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

}