package com.webfactory.springbootdemo.demoproject.web;

import com.webfactory.springbootdemo.demoproject.model.Log;
import com.webfactory.springbootdemo.demoproject.persistance.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class LogController {

    private final LogRepository logRepository;

    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping("/allLogs")
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }
}
