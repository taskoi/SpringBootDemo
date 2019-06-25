package com.webfactory.springbootdemo.demoproject.persistance;

import com.webfactory.springbootdemo.demoproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    public Page<User> findAllByLocationCityContaining(Pageable pageable, String city);

    public User findByUsername(String username);

    public Page<User> findAllByNickname(Pageable pageable,String nickname);

    public User findByNickname(String nickname);

    public Page<User> findAllByUsername(Pageable pageable,String username);

    public User findByEmail(String email);

    public Page<User> findAll(Pageable pageable);

    public List<User> findAll();

    public List<User> findAllByLocationCityContaining(String city);

    public List<User> findAllByNickname(String nickname);

    public List<User> findAllByUsername(String username);
}
