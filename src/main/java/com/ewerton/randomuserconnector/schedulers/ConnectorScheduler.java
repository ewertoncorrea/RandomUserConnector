package com.ewerton.randomuserconnector.schedulers;

import com.ewerton.randomuserconnector.controllers.RandomUserAPIController;
import com.ewerton.randomuserconnector.models.RandomUser;
import com.ewerton.randomuserconnector.models.Root;
import com.ewerton.randomuserconnector.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConnectorScheduler {

    private static final Logger log = LoggerFactory.getLogger(ConnectorScheduler.class);

    @Value("${url}")
    private String url;

    @Value("${userSize}")
    private int maxUsers;

    private final UserRepository repository;

    @Autowired
    ConnectorScheduler(UserRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRateString = "${period}")
    public void reportCurrentTime() {
        List<RandomUser> users = repository.findAll();
        if (users.size() < maxUsers) {
            RestTemplate restTemplate = new RestTemplate();
            Root root = restTemplate.getForObject(url, Root.class);
            if (root != null) {
                RandomUser user = root.getResults().stream().map(
                                result -> new RandomUser(result.getName().getFullName(), result.getEmail(),
                                        result.getLocation().getCountry(), result.getGender()))
                        .findFirst().orElse(null);
                users.add(user);
                log.info("loaded user" + user.toString());
                repository.saveAll(users);
            }
        }
    }
}
