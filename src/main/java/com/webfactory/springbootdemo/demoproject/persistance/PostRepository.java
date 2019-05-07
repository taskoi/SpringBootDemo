package com.webfactory.springbootdemo.demoproject.persistance;

import com.webfactory.springbootdemo.demoproject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public Post findAllByTitle(String title);
}
