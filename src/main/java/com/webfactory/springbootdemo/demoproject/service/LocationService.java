package com.webfactory.springbootdemo.demoproject.service;

import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.persistance.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;



}
