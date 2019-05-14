package com.webfactory.springbootdemo.demoproject.persistance;


import com.webfactory.springbootdemo.demoproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
