package com.ewerton.randomuserconnector.controllers;

import com.ewerton.randomuserconnector.models.RandomUser;
import com.ewerton.randomuserconnector.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RandomUserAPIController {

    private static final Logger log = LoggerFactory.getLogger(RandomUserAPIController.class);

    private final UserRepository repository;

    @Autowired
    RandomUserAPIController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/getUsers")
    public List<RandomUser> getUsers() {
        return repository.findAll();
    }

}
