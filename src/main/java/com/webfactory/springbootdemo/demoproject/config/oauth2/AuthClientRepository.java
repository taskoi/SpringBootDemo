package com.webfactory.springbootdemo.demoproject.config.oauth2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthClientRepository extends JpaRepository<AuthClientDetails, Long> {

    AuthClientDetails findByClientId(String clientId);
}
