package com.webfactory.springbootdemo.demoproject.persistance;

import com.webfactory.springbootdemo.demoproject.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<Log, String> {
}
