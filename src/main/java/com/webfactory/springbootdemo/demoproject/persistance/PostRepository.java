package com.webfactory.springbootdemo.demoproject.persistance;

import com.webfactory.springbootdemo.demoproject.model.Location;
import com.webfactory.springbootdemo.demoproject.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public Page<Post> findAllByTitle(Pageable pageable, String title);

    public Page<Post> findAllByLocation(Pageable pageable,Location location);

    public Page<Post> findAll(Pageable pageable);

    public List<Post> findAllByTitle( String title);

    public List<Post> findAllByLocation(Location location);

    public List<Post> findAll();
}
