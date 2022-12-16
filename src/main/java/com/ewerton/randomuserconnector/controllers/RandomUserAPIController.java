package com.ewerton.randomuserconnector.controllers;

import com.ewerton.randomuserconnector.models.Root;
import com.ewerton.randomuserconnector.models.RandomUser;
import com.ewerton.randomuserconnector.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RandomUserAPIController {

    private static final Logger log = LoggerFactory.getLogger(RandomUserAPIController.class);

    @Value("${url}")
    private String url;

    @Value("${userSize}")
    private int maxUsers;

    private final UserRepository repository;

    @Autowired
    RandomUserAPIController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/getUsers")
    public List<RandomUser> getUsers() {
        List<RandomUser> users = repository.findAll();

        if (users.size() < maxUsers) {
            RestTemplate restTemplate = new RestTemplate();
            Root root = restTemplate.getForObject(url, Root.class);
            if (root != null) {
                users.addAll(root.getResults().stream().map(
                        result -> new RandomUser(result.getName().getFullName(), result.getEmail(),
                            result.getLocation().getCountry(), result.getGender()))
                        .collect(Collectors.toList()));
                log.info("loaded user");
            }
            repository.saveAll(users);
        }
        return users;
    }

}
